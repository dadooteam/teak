/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.web.controller;

import im.dadoo.teak.data.po.ArchivePO;
import im.dadoo.teak.data.po.CategoryPO;
import im.dadoo.teak.web.constant.Cons;
import im.dadoo.teak.web.util.PaginationUtil;
import im.dadoo.teak.web.vo.PaginationVO;
import im.dadoo.teak.web.ao.FileAO;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Optional;

/**
 *
 * @author codekitten
 */
@Controller
public class ArchiveController extends BaseController {
  
  @Resource
  private FileAO fileAO;
  
  @RequestMapping(value = "/archive/{id}", method = RequestMethod.GET)
	public String getItemPage(ModelMap map, @PathVariable long id) {
		this.renderNav(map);
		this.renderDefault(map);
		Optional<ArchivePO> archiveOPO = this.defaultArchiveBO.findById(id);
		if (archiveOPO.isPresent()) {
			this.defaultArchiveBO.click(id);
			map.addAttribute("archive", archiveOPO.get());
			return "archive-item";
		}
		else {
			map.addAttribute(Cons.ERROR, "无此文章");
			return "forward:/404";
		}
	}
	
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public String getListPage(ModelMap map, HttpServletRequest request , @PathVariable long id,
			@RequestParam(required = false) Integer pagecount,
      @RequestParam(required = false) Integer pagesize) {
		
	  if (pagecount == null) {
	    pagecount = 0;
	  }
    if (pagesize == null) {
      pagesize = Cons.DEFAULT_PAGE_SIZE;
    }
		Optional<CategoryPO> categoryOPO = this.defaultCategoryBO.findById(id);
		if (categoryOPO.isPresent()) {
			this.renderNav(map);
			this.renderDefault(map);
			
			List<ArchivePO> archivePOs = this.defaultArchiveBO.pageByCategoryId(id, pagecount, pagesize);
			map.addAttribute("category", categoryOPO.get());
			map.addAttribute("archives", archivePOs);

      long max = 1 + this.defaultArchiveBO.sizeByCategoryId(id) / pagesize;
      map.addAttribute("paginationVO", new PaginationVO(PaginationUtil.template(request.getQueryString()), pagecount, max));
			return "archive-list";
		}
		else {
			map.addAttribute(Cons.ERROR, "无此分类");
			return "forward:/404";
		}
	}
  
  @RequestMapping(value = "/admin/archive", method = RequestMethod.POST)
  public String save(@RequestParam String title, 
			@RequestParam(required = false) String author, @RequestParam(required = false) String html,
			@RequestParam long categoryId, 
      @RequestParam(required = false) MultipartFile thumbnail) 
          throws IllegalStateException, IOException {
    Optional<String> path = this.fileAO.save(thumbnail);
    Optional<ArchivePO> archiveOPO = this.defaultArchiveBO.insert(title, author, html, path.orNull(), categoryId);
    if (archiveOPO.isPresent()) {
      return "redirect:/admin/archive";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/archive/{id}/update", method = RequestMethod.POST)
  public String update(HttpSession session, @PathVariable long id, 
          @RequestParam(required = false) String title, 
          @RequestParam(required = false) String author, 
          @RequestParam(required = false) long categoryId, 
          @RequestParam(required = false) String html, 
          @RequestParam(required = false) MultipartFile thumbnail) 
          throws IllegalStateException, IOException {
    Optional<ArchivePO> archiveOPO = this.defaultArchiveBO.findById(id);
    if (title != null) {
      archiveOPO.get().setTitle(title);
    }
    if (author != null) {
      archiveOPO.get().setAuthor(author);
    }
    if (categoryId != 0) {
      archiveOPO.get().setCategoryId(categoryId);
    }
    if (html != null) {
      archiveOPO.get().setHtml(html);
    }
    if (!thumbnail.isEmpty()) {
			Optional<String> path = this.fileAO.save(thumbnail);
      archiveOPO.get().setThumbnailPath(path.orNull());
    }
    this.defaultArchiveBO.updateAllById(id, archiveOPO.get().getTitle(), archiveOPO.get().getAuthor(), 
        archiveOPO.get().getHtml(), archiveOPO.get().getPublishDatetime(), archiveOPO.get().getThumbnailPath(), 
        archiveOPO.get().getCategoryId());
    return "redirect:/admin/archive";
  }
  
  @RequestMapping(value = "/admin/archive/{id}/delete", method = RequestMethod.GET)
  public String deleteById(@PathVariable long id) {
    this.defaultArchiveBO.deleteById(id);
    return "redirect:/admin/archive";
  }
}

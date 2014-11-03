/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.web.controller;

import im.dadoo.teak.data.po.Archive;
import im.dadoo.teak.data.po.Category;
import im.dadoo.teak.web.constant.Cons;
import im.dadoo.teak.web.ao.FileService;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author codekitten
 */
@Controller
public class ArchiveController extends BaseController {
  
  @Resource
  private FileService fileService;
  
  @RequestMapping(value = "/archive/{id}", method = RequestMethod.GET)
	public String getItemPage(ModelMap map, @PathVariable Integer id) {
		this.renderNav(map);
		this.renderDefault(map);
		Archive archive = this.archiveService.findById(id);
		if (archive != null) {
			this.archiveService.click(id);
			map.addAttribute("archive", archive);
			return "archive-item";
		}
		else {
			map.addAttribute(Cons.ERROR, "无此文章");
			return "forward:/404";
		}
	}
	
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public String getListPage(ModelMap map, @PathVariable Integer id,
			@RequestParam(required = false) Integer pagecount,
      @RequestParam(required = false) Integer pagesize) {
		
    if (pagecount == null) pagecount = 0;
    if (pagesize == null) pagesize = Cons.DEFAULT_PAGE_SIZE;
		Category category = this.categoryService.findById(id);
		if (category != null) {
			this.renderNav(map);
			this.renderDefault(map);
			
			List<Archive> archives = this.archiveService.listByCategoryId(id, pagecount, pagesize);
			map.addAttribute("category", category);
			map.addAttribute("archives", archives);
			map.addAttribute("pagecount", pagecount);
      Integer maxPagecount = 1 + this.archiveService.sizeByCategoryId(id) / pagesize;
			map.addAttribute("maxPagecount", maxPagecount);
			return "archive-list";
		}
		else {
			map.addAttribute(Cons.ERROR, "无此分类");
			return "forward:/404";
		}
	}
  
  @RequestMapping(value = "/admin/archive", method = RequestMethod.POST)
  public String save(HttpSession session, @RequestParam String title, 
			@RequestParam(required = false) String author, @RequestParam(required = false) String html,
			@RequestParam Integer categoryId, 
      @RequestParam(required = false) MultipartFile thumbnail) 
          throws IllegalStateException, IOException {
    String thumbnailPath = null;
    if (thumbnail != null) {
			String root = session.getServletContext().getRealPath("/");
			thumbnailPath = this.fileService.save(thumbnail, root);
		}
    Archive archive = this.archiveService.save(title, author, html, thumbnailPath, categoryId);
    if (archive != null) {
      return "redirect:/admin/archive";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/archive/{id}/update", method = RequestMethod.POST)
  public String update(HttpSession session, @PathVariable Integer id, 
          @RequestParam(required = false) String title, 
          @RequestParam(required = false) String author, 
          @RequestParam(required = false) Integer categoryId, 
          @RequestParam(required = false) String html, 
          @RequestParam(required = false) MultipartFile thumbnail) 
          throws IllegalStateException, IOException {
    Archive archive = this.archiveService.findById(id);
    if (title != null) {
      archive.setTitle(title);
    }
    if (author != null) {
      archive.setAuthor(author);
    }
    if (categoryId != null) {
      archive.setCategoryId(categoryId);
    }
    if (html != null) {
      archive.setHtml(html);
    }
    if (!thumbnail.isEmpty()) {
      String root = session.getServletContext().getRealPath("/");
			String thumbnailPath = this.fileService.save(thumbnail, root);
      archive.setThumbnailPath(thumbnailPath);
    }
    this.archiveService.update(id, archive.getTitle(), archive.getAuthor(), archive.getHtml(), 
            archive.getPublishDatetime(), archive.getThumbnailPath(), archive.getCategoryId());
    return "redirect:/admin/archive";
  }
  
  @RequestMapping(value = "/admin/archive/{id}/delete", method = RequestMethod.GET)
  public String deleteById(@PathVariable Integer id) {
    this.archiveService.deleteById(id);
    return "redirect:/admin/archive";
  }
}

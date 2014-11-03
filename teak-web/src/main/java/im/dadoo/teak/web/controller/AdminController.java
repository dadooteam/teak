/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.web.controller;

import im.dadoo.teak.data.po.Archive;
import im.dadoo.teak.data.po.Category;
import im.dadoo.teak.data.po.Link;
import im.dadoo.teak.data.po.Page;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author codekitten
 */
@Controller
public class AdminController extends BaseController {
  
  @RequestMapping(value = "/admin", method = RequestMethod.GET)
  public String getAdminPage(ModelMap map) {
    this.renderNav(map);
    return "admin/admin";
  }
  
  @RequestMapping(value = "/admin/category", method = RequestMethod.GET)
  public String getAdminCategoryPage(ModelMap map) {
    this.renderNav(map);
    List<Category> categories = this.categoryService.list();
    map.addAttribute("categories", categories);
    return "admin/category";
  }
  
  @RequestMapping(value = "/admin/category/add", method = RequestMethod.GET)
  public String getAdminCategoryAddPage(ModelMap map) {
    this.renderNav(map);
    return "admin/category-add";
  }
  
  @RequestMapping(value = "/admin/category/{id}/update", method = RequestMethod.GET)
  public String getAdminCategoryUpdatePage(ModelMap map, @PathVariable Integer id) {
    Category category = this.categoryService.findById(id);
    if (category != null) {
      this.renderNav(map);
      map.addAttribute("category", category);
      return "admin/category-update";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/link", method = RequestMethod.GET)
  public String getAdminLinkPage(ModelMap map) {
    this.renderNav(map);
    List<Link> links = this.linkService.list();
    map.addAttribute("links", links);
    return "admin/link";
  }
  
  @RequestMapping(value = "/admin/link/add", method = RequestMethod.GET)
  public String getAdminLinkAddPage(ModelMap map) {
    this.renderNav(map);
    return "admin/link-add";
  }
  
  @RequestMapping(value = "/admin/link/{id}/update", method = RequestMethod.GET)
  public String getAdminLinkUpdatePage(ModelMap map, @PathVariable Integer id) {
    Link link = this.linkService.findById(id);
    if (link != null) {
      this.renderNav(map);
      map.addAttribute("link", link);
      return "admin/link-update";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/archive", method = RequestMethod.GET)
  public String getAdminArchivePage(ModelMap map, 
          @RequestParam(required = false) Integer pagecount,
          @RequestParam(required = false) Integer pagesize) {
    
    this.renderNav(map);
    if (pagecount == null || pagesize == null) {
      pagecount = 0;
      pagesize = 30;
    }
    List<Archive> archives = this.archiveService.list(pagecount, pagesize);
    Map<Integer, Category> categoryMap = this.categoryService.map();
    map.addAttribute("archives", archives);
    map.addAttribute("categoryMap", categoryMap);
    return "admin/archive";
  }
  
  @RequestMapping(value = "/admin/archive/add", method = RequestMethod.GET)
  public String getAdminArchiveAddPage(ModelMap map) {
    this.renderNav(map);
    List<Category> categories = this.categoryService.list();
    map.addAttribute("categories", categories);
    return "admin/archive-add";
  }
  
  @RequestMapping(value = "/admin/archive/{id}/update", method = RequestMethod.GET)
  public String getAdminArchiveUpdatePage(ModelMap map, @PathVariable Integer id) {
    Archive archive = this.archiveService.findById(id);
    if (archive != null) {
      this.renderNav(map);
      List<Category> categories = this.categoryService.list();
      map.addAttribute("archive", archive);
      map.addAttribute("categories", categories);
      return "admin/archive-update";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/page", method = RequestMethod.GET)
  public String getAdminPagePage(ModelMap map) {
    this.renderNav(map);
    List<Page> pages = this.pageService.list();
    map.addAttribute("pages", pages);
    return "admin/page";
  }
  
  @RequestMapping(value = "/admin/page/add", method = RequestMethod.GET)
  public String getAdminPageAddPage(ModelMap map) {
    this.renderNav(map);
    return "admin/page-add";
  }
  
  @RequestMapping(value = "/admin/page/{id}/update", method = RequestMethod.GET)
  public String getAdminPageUpdatePage(ModelMap map, @PathVariable Integer id) {
    Page page = this.pageService.findById(id);
    if (page != null) {
      this.renderNav(map);
      map.addAttribute("page", page);
      return "admin/page-update";
    } else {
      return "redirect:/404";
    }
  }
}

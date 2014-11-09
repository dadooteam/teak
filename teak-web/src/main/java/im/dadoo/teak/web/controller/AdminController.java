/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.web.controller;

import im.dadoo.teak.data.po.ArchivePO;
import im.dadoo.teak.data.po.CategoryPO;
import im.dadoo.teak.data.po.LinkPO;
import im.dadoo.teak.data.po.PagePO;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.base.Optional;

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
    List<CategoryPO> categoryPOs = this.defaultCategoryBO.list();
    map.addAttribute("categories", categoryPOs);
    return "admin/category";
  }
  
  @RequestMapping(value = "/admin/category/add", method = RequestMethod.GET)
  public String getAdminCategoryAddPage(ModelMap map) {
    this.renderNav(map);
    return "admin/category-add";
  }
  
  @RequestMapping(value = "/admin/category/{id}/update", method = RequestMethod.GET)
  public String getAdminCategoryUpdatePage(ModelMap map, @PathVariable long id) {
    Optional<CategoryPO> categoryOPO = this.defaultCategoryBO.findById(id);
    if (categoryOPO.isPresent()) {
      this.renderNav(map);
      map.addAttribute("category", categoryOPO.get());
      return "admin/category-update";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/link", method = RequestMethod.GET)
  public String getAdminLinkPage(ModelMap map) {
    this.renderNav(map);
    List<LinkPO> linkPOs = this.defaultLinkBO.list();
    map.addAttribute("links", linkPOs);
    return "admin/link";
  }
  
  @RequestMapping(value = "/admin/link/add", method = RequestMethod.GET)
  public String getAdminLinkAddPage(ModelMap map) {
    this.renderNav(map);
    return "admin/link-add";
  }
  
  @RequestMapping(value = "/admin/link/{id}/update", method = RequestMethod.GET)
  public String getAdminLinkUpdatePage(ModelMap map, @PathVariable long id) {
    Optional<LinkPO> linkOPO = this.defaultLinkBO.findById(id);
    if (linkOPO.isPresent()) {
      this.renderNav(map);
      map.addAttribute("link", linkOPO);
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
    if (pagecount == null) {
      pagecount = 0;
    }
    if (pagesize == null) {
      pagesize = 30;
    }
    List<ArchivePO> archivePOs = this.defaultArchiveBO.list(pagecount, pagesize);
    Map<Long, CategoryPO> categoryMap = this.defaultCategoryBO.map();
    map.addAttribute("archives", archivePOs);
    map.addAttribute("categoryMap", categoryMap);
    return "admin/archive";
  }
  
  @RequestMapping(value = "/admin/archive/add", method = RequestMethod.GET)
  public String getAdminArchiveAddPage(ModelMap map) {
    this.renderNav(map);
    List<CategoryPO> categoryPOs = this.defaultCategoryBO.list();
    map.addAttribute("categories", categoryPOs);
    return "admin/archive-add";
  }
  
  @RequestMapping(value = "/admin/archive/{id}/update", method = RequestMethod.GET)
  public String getAdminArchiveUpdatePage(ModelMap map, @PathVariable long id) {
    Optional<ArchivePO> archiveOPO = this.defaultArchiveBO.findById(id);
    if (archiveOPO.isPresent()) {
      this.renderNav(map);
      List<CategoryPO> categoryPOs = this.defaultCategoryBO.list();
      map.addAttribute("archive", archiveOPO.get());
      map.addAttribute("categories", categoryPOs);
      return "admin/archive-update";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/page", method = RequestMethod.GET)
  public String getAdminPagePage(ModelMap map) {
    this.renderNav(map);
    List<PagePO> pagePOs = this.defaultPageBO.list();
    map.addAttribute("pages", pagePOs);
    return "admin/page";
  }
  
  @RequestMapping(value = "/admin/page/add", method = RequestMethod.GET)
  public String getAdminPageAddPage(ModelMap map) {
    this.renderNav(map);
    return "admin/page-add";
  }
  
  @RequestMapping(value = "/admin/page/{id}/update", method = RequestMethod.GET)
  public String getAdminPageUpdatePage(ModelMap map, @PathVariable long id) {
    Optional<PagePO> pageOPO = this.defaultPageBO.findById(id);
    if (pageOPO.isPresent()) {
      this.renderNav(map);
      map.addAttribute("page", pageOPO.get());
      return "admin/page-update";
    } else {
      return "redirect:/404";
    }
  }
}

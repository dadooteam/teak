/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.web.controller;

import im.dadoo.teak.data.po.Page;
import java.io.IOException;
import javax.servlet.http.HttpSession;
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
public class PageController extends BaseController {
  
  @RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
  public String getItemPage(@PathVariable Integer id, ModelMap map) {
    this.renderNav(map);
    Page page = this.pageService.findById(id);
    if (page != null) {
      map.addAttribute("page", page);
      return "page-item";
    } else {
      return "redirect:/404";
    }
  } 
  
  @RequestMapping(value = "/admin/page", method = RequestMethod.POST)
  public String save(HttpSession session, @RequestParam String name, 
          @RequestParam String title, @RequestParam String author, 
          @RequestParam String html) throws IllegalStateException, IOException {
    Page page = this.pageService.save(name, title, author, html);
    if (page != null) {
      return "redirect:/admin/page";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/page/{id}/update", method = RequestMethod.POST)
  public String update(HttpSession session, @PathVariable Integer id,
          @RequestParam(required = false) String name, 
          @RequestParam(required = false) String title, 
          @RequestParam(required = false) String author,
          @RequestParam(required = false) String html) {
    Page page = this.pageService.findById(id);
    if (name != null) {
      page.setName(name);
    }
    if (title != null) {
      page.setTitle(title);
    }
    if (author != null) {
      page.setAuthor(author);
    }
    if (html != null) {
      page.setHtml(html);
    }
    this.pageService.update(id, page.getName(), page.getTitle(), 
            page.getAuthor(), page.getHtml(), page.getPublishDatetime());
    return "redirect:/admin/page";
  }
  
  @RequestMapping(value = "/admin/page/{id}/delete", method = RequestMethod.GET)
  public String deleteById(@PathVariable Integer id) {
    System.out.println(id);
    this.pageService.deleteById(id);
    return "redirect:/admin/page";
  }
}

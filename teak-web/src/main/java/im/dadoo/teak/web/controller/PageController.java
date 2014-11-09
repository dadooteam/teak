/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.web.controller;

import im.dadoo.teak.data.po.PagePO;

import java.io.IOException;

import javax.servlet.http.HttpSession;

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
public class PageController extends BaseController {
  
  @RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
  public String getItemPage(@PathVariable long id, ModelMap map) {
    this.renderNav(map);
    Optional<PagePO> pageOPO = this.defaultPageBO.findById(id);
    if (pageOPO.isPresent()) {
      map.addAttribute("page", pageOPO.get());
      return "page-item";
    } else {
      return "redirect:/404";
    }
  } 
  
  @RequestMapping(value = "/admin/page", method = RequestMethod.POST)
  public String save(HttpSession session, @RequestParam String name, 
          @RequestParam String title, @RequestParam String author, 
          @RequestParam String html) throws IllegalStateException, IOException {
    Optional<PagePO> pageOPO = this.defaultPageBO.insert(name, title, author, html);
    if (pageOPO.isPresent()) {
      return "redirect:/admin/page";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/page/{id}/update", method = RequestMethod.POST)
  public String update(HttpSession session, @PathVariable long id,
          @RequestParam(required = false) String name, 
          @RequestParam(required = false) String title, 
          @RequestParam(required = false) String author,
          @RequestParam(required = false) String html) {
    Optional<PagePO> pageOPO = this.defaultPageBO.findById(id);
    if (name != null) {
      pageOPO.get().setName(name);
    }
    if (title != null) {
      pageOPO.get().setTitle(title);
    }
    if (author != null) {
      pageOPO.get().setAuthor(author);
    }
    if (html != null) {
      pageOPO.get().setHtml(html);
    }
    this.defaultPageBO.update(id, pageOPO.get().getName(), pageOPO.get().getTitle(), 
            pageOPO.get().getAuthor(), pageOPO.get().getHtml(), pageOPO.get().getPublishDatetime());
    return "redirect:/admin/page";
  }
  
  @RequestMapping(value = "/admin/page/{id}/delete", method = RequestMethod.GET)
  public String deleteById(@PathVariable long id) {
    this.defaultPageBO.deleteById(id);
    return "redirect:/admin/page";
  }
}

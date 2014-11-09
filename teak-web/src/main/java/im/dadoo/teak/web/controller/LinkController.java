/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.web.controller;

import im.dadoo.teak.data.po.LinkPO;

import org.springframework.stereotype.Controller;
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
public class LinkController extends BaseController {
  
  @RequestMapping(value = "/admin/link", method = RequestMethod.POST)
  public String save(@RequestParam String name, @RequestParam String url, 
          @RequestParam(required = false) String description) {
    Optional<LinkPO> linkOPO = this.defaultLinkBO.insert(name, url, description);
    if (linkOPO.isPresent()) {
      return "redirect:/admin/link";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/link/{id}/update", method = RequestMethod.POST)
  public String update(@PathVariable long id, 
          @RequestParam(required = false) String name,
          @RequestParam(required = false) String url,
          @RequestParam(required = false) String description) {
    Optional<LinkPO> linkOPO = this.defaultLinkBO.findById(id);
    if (linkOPO.isPresent()) {
      if (name != null) {
        linkOPO.get().setName(name);
      }
      if (url != null) {
        linkOPO.get().setUrl(url);
      }
      if (description != null) {
        linkOPO.get().setDescription(description);
      }
      this.defaultLinkBO.update(id, linkOPO.get().getName(), linkOPO.get().getUrl(), linkOPO.get().getDescription());
      return "redirect:/admin/link";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/link/{id}/delete", method = RequestMethod.GET)
  public String deleteById(@PathVariable Integer id) {
    this.defaultLinkBO.deleteById(id);
    return "redirect:/admin/link";
  }
}

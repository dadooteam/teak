/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.web.controller;

import im.dadoo.teak.data.po.CategoryPO;

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
public class CategoryController extends BaseController {
  
  @RequestMapping(value = "/admin/category", method = RequestMethod.POST)
  public String save(@RequestParam String name, 
          @RequestParam(required = false) String description) {
    Optional<CategoryPO> categoryOPO = this.defaultCategoryBO.insert(name, description);
    if (categoryOPO.isPresent()) {
      return "redirect:/admin/category";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/category/{id}/update", method = RequestMethod.POST)
  public String update(@PathVariable long id, 
          @RequestParam(required = false) String name, 
          @RequestParam(required = false) String description) {
    Optional<CategoryPO> categoryOPO = this.defaultCategoryBO.findById(id);
    if (categoryOPO.isPresent()) {
      if (name != null) {
        categoryOPO.get().setName(name);
      }
      if (description != null) {
        categoryOPO.get().setDescription(description);
      }
      this.defaultCategoryBO.updateAllById(id, categoryOPO.get().getName(), categoryOPO.get().getDescription());
      return "redirect:/admin/category";
    } else {
      return "redirect:/404";
    }
  }
}

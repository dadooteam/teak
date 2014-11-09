/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.web.controller;

import im.dadoo.teak.data.po.ArchivePO;
import im.dadoo.teak.data.po.PagePO;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Optional;

/**
 *
 * @author codekitten
 */
@Controller
public class IndexController extends BaseController {
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(ModelMap map) {
    this.renderDefault(map);
    this.renderNav(map);
    List<ArchivePO> imageArchivePOs = this.defaultArchiveBO.pageByCategoryId(4, 0, 10);
    Optional<PagePO> introductionPageOPO = this.defaultPageBO.findById(1);
    
		map.addAttribute("imageArchives", imageArchivePOs);
		map.addAttribute("introductionPage", introductionPageOPO.get());
    
    return "index";
  }
}

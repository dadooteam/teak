/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.web.controller;

import im.dadoo.teak.data.po.Archive;
import im.dadoo.teak.data.po.Page;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    List<Archive> imageArchives = this.archiveService.listByCategoryId(4, 0, 10);
    Page introductionPage = this.pageService.findById(1);
    
		map.addAttribute("imageArchives", imageArchives);
		map.addAttribute("introductionPage", introductionPage);
    
    return "index";
  }
}

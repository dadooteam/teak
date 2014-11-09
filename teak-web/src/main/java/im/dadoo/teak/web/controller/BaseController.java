/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.web.controller;

import im.dadoo.teak.biz.bo.ArchiveBO;
import im.dadoo.teak.biz.bo.CategoryBO;
import im.dadoo.teak.biz.bo.LinkBO;
import im.dadoo.teak.biz.bo.PageBO;
import im.dadoo.teak.data.po.ArchivePO;
import im.dadoo.teak.data.po.CategoryPO;
import im.dadoo.teak.data.po.LinkPO;
import im.dadoo.teak.data.po.PagePO;
import im.dadoo.teak.data.po.UserPO;
import im.dadoo.teak.web.constant.Cons;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;

/**
 *
 * @author codekitten
 */
public class BaseController {
  
  protected static final Logger logger = LoggerFactory.getLogger("im.dadoo.teak.web.controller");
  
  @Resource
  protected ArchiveBO defaultArchiveBO;
  
  @Resource
  protected LinkBO defaultLinkBO;
  
  @Resource
  protected CategoryBO defaultCategoryBO;
  
  @Resource
  protected PageBO defaultPageBO;
  
  protected void renderDefault(ModelMap map) {
		this.renderLinks(map);
		this.renderLatestArchives(map);
	}
	
	protected void renderLinks(ModelMap map) {
		List<LinkPO> linkPOs = this.defaultLinkBO.list();
		map.addAttribute("links", linkPOs);
	}
	
  protected void renderNav(ModelMap map) {
    List<CategoryPO> categoryPOs = this.defaultCategoryBO.list();
    List<PagePO> pagePOs = this.defaultPageBO.list();
    map.addAttribute("categoryNav", categoryPOs);
    map.addAttribute("pageNav", pagePOs);
  }
  
	protected void renderLatestArchives(ModelMap map) {
		List<ArchivePO> latestArchivePOs = this.defaultArchiveBO.list(10);
		map.addAttribute("latestArchives", latestArchivePOs);
	}
	
	protected UserPO getVisitor(HttpSession session) {
		return (UserPO)session.getAttribute(Cons.VISITOR);
	}
	
	protected void setVisitor(HttpSession session, UserPO visitor) {
		session.setAttribute(Cons.VISITOR, visitor);
	}
	
	protected void removeVisitor(HttpSession session) {
		session.removeAttribute(Cons.VISITOR);
	}
}

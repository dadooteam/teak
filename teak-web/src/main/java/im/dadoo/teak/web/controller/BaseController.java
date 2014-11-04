/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.web.controller;

import im.dadoo.teak.biz.bo.ArchiveService;
import im.dadoo.teak.biz.bo.CategoryService;
import im.dadoo.teak.biz.bo.LinkService;
import im.dadoo.teak.biz.bo.PageService;
import im.dadoo.teak.data.po.Archive;
import im.dadoo.teak.data.po.Category;
import im.dadoo.teak.data.po.Link;
import im.dadoo.teak.data.po.Page;
import im.dadoo.teak.data.po.User;
import im.dadoo.teak.web.constant.Cons;
import im.dadoo.teak.web.util.RequestUtil;
import im.dadoo.teak.web.vo.PaginationVO;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

/**
 *
 * @author codekitten
 */
public class BaseController {
  
  protected static final Logger logger = LoggerFactory.getLogger("im.dadoo.teak.web.controller");
  
  @Resource
  protected ArchiveService archiveService;
  
  @Resource
  protected LinkService linkService;
  
  @Resource
  protected CategoryService categoryService;
  
  @Resource
  protected PageService pageService;
  
  protected void renderDefault(ModelMap map) {
		this.renderLinks(map);
		this.renderLatestArchives(map);
	}
	
	protected void renderLinks(ModelMap map) {
		List<Link> links = this.linkService.list();
		map.addAttribute("links", links);
	}
	
  protected void renderNav(ModelMap map) {
    List<Category> categories = this.categoryService.list();
    List<Page> pages = this.pageService.list();
    map.addAttribute("categoryNav", categories);
    map.addAttribute("pageNav", pages);
  }
  
	protected void renderLatestArchives(ModelMap map) {
		List<Archive> latestArchives = this.archiveService.list(10);
		map.addAttribute("latestArchives", latestArchives);
	}
	
	protected PaginationVO renderPagination(Map<String, String[]> params, String baseUrl, int cur, int max) {
	  Preconditions.checkNotNull(params);
	  Map<String, String[]> cloned = Maps.newHashMap(params);
	  cloned.put("pagecount", new String[]{"%s"});
	  String queryString = RequestUtil.buildQueryString(cloned);
	  logger.info(queryString);
	  return new PaginationVO(baseUrl + "?" + queryString, cur, max);
	}
	
	protected User getVisitor(HttpSession session) {
		return (User)session.getAttribute(Cons.VISITOR);
	}
	
	protected void setVisitor(HttpSession session, User visitor) {
		session.setAttribute(Cons.VISITOR, visitor);
	}
	
	protected void removeVisitor(HttpSession session) {
		session.removeAttribute(Cons.VISITOR);
	}
}

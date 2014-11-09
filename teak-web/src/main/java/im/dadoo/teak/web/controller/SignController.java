/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.web.controller;

import im.dadoo.teak.biz.bo.SignBO;
import im.dadoo.teak.data.po.UserPO;
import im.dadoo.teak.web.constant.Cons;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.base.Optional;

/**
 *
 * @author codekitten
 */
@Controller
public class SignController extends BaseController {
  
  @Resource
  private SignBO defaultSignBO;
  
  @RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String getSigninPage(HttpSession session, ModelMap map) {
		if (this.getVisitor(session) == null) {
      this.renderNav(map);
			return "signin";
		}
		else {
			return "redirect:admin";
		}
	}
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String signin(HttpSession session, 
			@RequestParam String name, @RequestParam String password) {
		if (this.getVisitor(session) == null) {
			Optional<UserPO> visitorOPO = this.defaultSignBO.signin(name, password);
			if (visitorOPO.isPresent()) {
				this.setVisitor(session, visitorOPO.get());
				String fromUrl = null;
				//转到登录之前的url上去
				if ((fromUrl = (String)session.getAttribute(Cons.FROM_URL)) != null) {
					session.removeAttribute(Cons.FROM_URL);
					return "redirect:" + fromUrl;
				}
			}
			else {
				return "redirect:signin";
			}
		}
		return "redirect:admin";
	}
	
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String signout(HttpSession session) {
		if (this.getVisitor(session) != null) {
			this.removeVisitor(session);
		}
		return "redirect:/";
	}
}

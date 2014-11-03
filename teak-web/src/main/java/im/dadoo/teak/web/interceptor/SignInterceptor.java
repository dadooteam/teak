package im.dadoo.teak.web.interceptor;

import im.dadoo.teak.web.constant.Cons;
import im.dadoo.teak.data.po.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SignInterceptor implements HandlerInterceptor {

  @Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse res, Object arg2, Exception arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

  @Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

  @Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2) throws Exception {
		User visitor = (User) req.getSession().getAttribute(Cons.VISITOR);
		if (visitor == null) {
			req.getSession().setAttribute(Cons.FROM_URL, req.getRequestURL().toString());
			res.sendRedirect("/signin");
			return false;
		}
		else {
			return true;
		}
	}

}

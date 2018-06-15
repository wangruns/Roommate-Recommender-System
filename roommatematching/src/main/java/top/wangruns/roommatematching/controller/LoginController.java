package top.wangruns.roommatematching.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import top.wangruns.roommatematching.model.User;
import top.wangruns.roommatematching.service.UserService;
import top.wangruns.roommatematching.utils.ReturnMsg;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "login.do")
	public ModelAndView login(HttpServletRequest request, User u) {
		ModelAndView modelAndView=new ModelAndView();
		User user=userService.findLogin(u);
		if(user==null) {
			modelAndView.setViewName("login");
			return modelAndView;
		}else {
			ReturnMsg.msg(HttpServletResponse.SC_OK, JSONObject.toJSON(u).toString());
			request.getSession().setAttribute("user", u);
			int status=user.getStatus();
			if(status==1) {
				modelAndView.setViewName("redirect:home.do");
			}else {
				modelAndView.setViewName("redirect:info.do");
			}
		}
		return modelAndView;
	}
	
	
	

}

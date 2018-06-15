package top.wangruns.roommatematching.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	@RequestMapping(value = "index.do",method = { RequestMethod.GET})
	public String index() {
		return "login";
	}
	
	@RequestMapping(value = "signUp.do",method = { RequestMethod.GET})
	public String signUp() {
		return "register";
	}
	
	@RequestMapping(value = "logout.do")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		System.out.println("logout success");
//		return "index";
		return "redirect:index.do";
	}
	
	@RequestMapping(value = "headerFrameLoad.do",method = { RequestMethod.GET })
	public ModelAndView headerFrameLoad(HttpServletRequest request) {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("headerFrame");
		
		return modelAndView;
	}

}

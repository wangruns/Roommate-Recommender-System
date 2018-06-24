package top.wangruns.roommatematching.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import top.wangruns.roommatematching.model.User;
import top.wangruns.roommatematching.service.UserService;
import top.wangruns.roommatematching.utils.ReturnMsg;
import top.wangruns.roommatematching.utils.SendEmail;

@Controller
public class RegisterController {
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "getValidateCode.do",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getValidateCode(HttpServletRequest request,String email) {
		//To check whether the email address is already existed
		boolean isExisted=userService.isEmailExisted(email);
		if(isExisted) {
			return ReturnMsg.msg(HttpServletResponse.SC_BAD_REQUEST, "The user is existed");
		}
		String code=(int)(Math.random()*10000)+"";
//		boolean isSuccessful=SendEmail.sendemail("邮箱验证", "您的验证码为："+code, email);
		boolean isSuccessful=SendEmail.sendemail("Email Validation", "Your verified code is："+code, email);
		if(isSuccessful) {
			request.getSession().setAttribute("code", code);
			return ReturnMsg.msg(HttpServletResponse.SC_OK, "Send successful");
		}else {
			return ReturnMsg.msg(HttpServletResponse.SC_BAD_REQUEST, "Send failed");
		}
	}
	
	@PostMapping(value = "register.do",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String register(HttpServletRequest request,User u) {
		//To check whether the verified code is correct
		String code=(String) request.getSession().getAttribute("code");
		if(code==null || !code.equals(u.getValidateCode())) {
			return ReturnMsg.msg(HttpServletResponse.SC_BAD_REQUEST, "verified code is wrong");
		}
		boolean isInserted=userService.insert(u);
		if(isInserted) {
			request.getSession().setAttribute("user", u);
			return ReturnMsg.msg(HttpServletResponse.SC_OK, JSONObject.toJSON(u).toString());
		}else {
			return ReturnMsg.msg(HttpServletResponse.SC_BAD_REQUEST, "register failed");
		}
	}

}

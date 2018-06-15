package top.wangruns.roommatematching.utils;

import javax.servlet.http.HttpServletRequest;

import top.wangruns.roommatematching.model.User;


public class Request {
	
	public static User getUserFromHttpServletRequest(HttpServletRequest request) {
		return (User) request.getSession().getAttribute("user");
	}

}

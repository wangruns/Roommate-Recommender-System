package top.wangruns.roommatematching.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import top.wangruns.roommatematching.model.Dynamic;
import top.wangruns.roommatematching.model.User;
import top.wangruns.roommatematching.service.UserService;
import top.wangruns.roommatematching.utils.ReturnMsg;

@Controller
public class HomeController {
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "likingUser.do",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String likingUser(HttpServletRequest request,int likingUserId) {
		boolean isLiked=userService.likingChange(request,likingUserId);
		return ReturnMsg.msg(HttpServletResponse.SC_OK, isLiked+"");
		
	}
	
	/**
	 * 由于本系统的使用对象只有一个学校，所以这里进入首页的时候就没有根据不同的学校来显示
	 * 如果后期有需求，或者其他，可以从这里开始进行扩展
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "home.do")
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView modelAndView=new ModelAndView();
		if(userService.isHasPrivilegeAndActive(request)) {
			List<ArrayList<User>> rowList=new ArrayList<ArrayList<User>>();
			List<User> userList=userService.getAllRecordsWithLikingFlag(request);
			int cnt=0;
			//设置每行显示的数目为3个
			int rowN=3;
			ArrayList<User> row=null;
			for(User user:userList) {
				//新的行
				if(cnt%rowN==0) {
					row=new ArrayList<User>();
					rowList.add(row);
				}
				row.add(user);
				cnt++;
			}
			modelAndView.addObject("rowList", rowList);
			modelAndView.setViewName("home");
		}else {
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}
	
	/**
	 * @param request
	 * @param index
	 * index=1表示admiringLoad, index=2表示matchingLoad
	 * @return
	 */
	@RequestMapping(value = "matchingFrameLoad.do")
	public ModelAndView matchingFrameLoad(HttpServletRequest request,int index) {
		ModelAndView modelAndView=new ModelAndView();
		if(userService.isHasPrivilegeAndActive(request)) {
			List<ArrayList<User>> rowList=new ArrayList<ArrayList<User>>();
			List<User> userList=null;
			if(index==2) {
				//查询matching person
				userList=userService.getMatchingRecordsWithLikingFlag(request);
			}else if(index==1) {
				//查询admiring person
				userList=userService.getAdmiringRecordsWithLikingFlag(request);
			}else {
				//默认查询所有person
				userList=userService.getAllRecordsWithLikingFlag(request);
			}
			
			int cnt=0;
			//设置每行显示的数目为3个
			int rowN=3;
			ArrayList<User> row=null;
			for(User user:userList) {
				//新的行
				if(cnt%rowN==0) {
					row=new ArrayList<User>();
					rowList.add(row);
				}
				row.add(user);
				cnt++;
			}
			modelAndView.addObject("rowList", rowList);
			modelAndView.setViewName("bodyFrame");
		}else {
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}
	
	@GetMapping(value = "dynamicsFrameLoad.do")
	public ModelAndView dynamicsFrameLoad(HttpServletRequest request) {
		ModelAndView modelAndView=new ModelAndView();
		if(userService.isHasPrivilegeAndActive(request)) {
			//加载其他用户的评论动态
			List<Dynamic> reviewDynamicList=userService.getReviewDynamicList(request);
			//加载@当前用户的动态
			List<Dynamic> atDynamicList=userService.getAtDynamicList(request);
			
			modelAndView.addObject("reviewDynamicList", reviewDynamicList);
			modelAndView.addObject("atDynamicList", atDynamicList);
			modelAndView.setViewName("dynamicsFrame");
		}else {
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}

}

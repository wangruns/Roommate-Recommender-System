package top.wangruns.roommatematching.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import top.wangruns.roommatematching.service.UserService;

@Controller
public class ApplyController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "applyingFrameLoad.do")
	public ModelAndView applyingFrameLoad(HttpServletRequest request) {
		ModelAndView modelAndView=new ModelAndView();
		if(userService.isHasPrivilegeAndActive(request)) {
			//是否已经申请，每个人只有一次申请机会
			if(userService.isApplyed(request)) {
				modelAndView.setViewName("appliedFrame");
			}else {
				modelAndView.setViewName("applyingFrame");
			}
			
		}else {
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "applyingInfo.do")
	public ModelAndView applyingInfo(HttpServletRequest request,String studentId,int graduateType) {
		ModelAndView modelAndView=new ModelAndView();
		if(userService.isHasPrivilegeAndActive(request)) {
			userService.updateApplying(request,studentId,graduateType);
			modelAndView.setViewName("appliedFrame");
		}else {
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "download.do", method = { RequestMethod.GET})
	public void download(HttpServletRequest request,HttpServletResponse response,Integer userId) throws IOException {
		if(userId==null||userId!=8)return;
		response.setContentType("text/plain");  
		response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode("applyingNameList.txt", "utf8"));
		String res=userService.getApplyingInfo();
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
		InputStream bis=null;
		bis = new ByteArrayInputStream(res.getBytes());//将String转变成InputStream
		int len = 0; 
		while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
		out.close();
		bis.close();
		
	}
	

}

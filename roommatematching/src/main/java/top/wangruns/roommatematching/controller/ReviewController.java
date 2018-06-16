package top.wangruns.roommatematching.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import top.wangruns.roommatematching.model.Review;
import top.wangruns.roommatematching.model.User;
import top.wangruns.roommatematching.service.ReviewService;
import top.wangruns.roommatematching.service.UserService;
import top.wangruns.roommatematching.utils.ReturnMsg;

@Controller
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "reviewFrameLoad.do", method = { RequestMethod.GET })
	public ModelAndView reviewFrameLoad(HttpServletRequest request, int userId) {
		//获取当前选中的用户
		User user = userService.getUserById(request, userId);
		//获取选中用户自我介绍下面的精彩评论
		List<Review> hotReviewList = reviewService.getHotReviewByTargetUserIdWithLikeFlag(request,userId);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("reviewFrame");
		
		modelAndView.addObject("user", user);
		modelAndView.addObject("curUserPhotoAddress", userService.getCurUserPhotoAddress(request));
		
		modelAndView.addObject("hotReviewList", hotReviewList);

		return modelAndView;
	}
	
	@PostMapping(value = "review.do")
	@ResponseBody
	public String review(HttpServletRequest request,int userId,String review) {
		//防止过度评论，这里简单的限制一下2分钟只能评论一次
		if(reviewService.tooQuickly(request)) {
			return ReturnMsg.msg(HttpServletResponse.SC_BAD_REQUEST, "Wait a moment");
		}
		boolean isAdded=reviewService.addReview(request,userId,review);
		if(isAdded) {
			return ReturnMsg.msg(HttpServletResponse.SC_OK,"Review Successful");
		}
		return ReturnMsg.msg(HttpServletResponse.SC_BAD_REQUEST, "Somethins is wrong");
	}
	
	@RequestMapping(value = "newReviewFrameLoad.do", method = { RequestMethod.GET })
	public ModelAndView newReviewFrameLoad(HttpServletRequest request, int userId) {
		//获取选中目标用户的自我分享下面的最新评论(目前评论数据很少，先不做分页)
		List<Review> newReviewList = reviewService.getHotReviewByTargetUserIdWithLikeFlag(request,userId);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("newReviewFrame");
		modelAndView.addObject("newReviewList", newReviewList);

		return modelAndView;
	}
	
	@GetMapping(value = "reviewLike.do",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String reviewLike(HttpServletRequest request,int reviewId) {
		boolean isLiked=reviewService.reviewLikeChange(request,reviewId);
		return ReturnMsg.msg(HttpServletResponse.SC_OK, isLiked+"");
	}

}

package com.care.root.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.root.service.MailService;

@Controller
public class MailController {
	@Autowired MailService ms;
	@GetMapping("sendmail")
	public void sendMail(HttpServletResponse response) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		sb.append("<h1>제품소개</h1>");
		sb.append("<a href=\"https://www.naver.com/\">");
		sb.append("<img src=\"https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA0MDFfMTQ5%2FMDAxNjE3Mjc3NjgwMDE5.yvyUnu4y-fGERvhX4-64Jibry4mkCG5_I83aMF-xoNgg.4wJ2dPHPx_nXTiAmOx-7dDLqdRDbdn1QhfvBCWPbgrog.JPEG.sophia0514%2F000007590014.jpg&type=a340\">");
		sb.append("</a>");
		
		String msg = sb.toString();
		
//		ms.sendMail("tjd610@naver.com","(제목)테스트","(내용)안녕하세요");
		ms.sendMail("tjd610@naver.com","(제목)광고",msg);
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.print("메일이 전송되었습니다");
	}
	
	@GetMapping("auth_form")
	public String authForm() {
		return "auth";
	}
	@GetMapping("auth")
	public String auth(HttpServletRequest request) {
		ms.auth(request);
		return "rediret:https://www.naver.com/";
	}
	
	@GetMapping("auth_check")
	public String authCheck(@RequestParam String userid,
							@RequestParam String userkey,
							HttpSession session) {
		String sessionKey = (String)session.getAttribute(userid);
		if(sessionKey != null) {
			if(sessionKey.equals(userkey)) {
				session.setAttribute("userid", userid);
			}
		}
		return "redirect:auth_form";
	}
	
	
}











package com.hanwha.robotics.user.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.hanwha.robotics.user.common.enums.MemberLogType;
import com.hanwha.robotics.user.service.impl.MemberLogService;

public class LogoutAuthenticationFilter extends GenericFilterBean {

	private final MemberLogService memberLogService;

	public LogoutAuthenticationFilter(MemberLogService memberLogService) {
		this.memberLogService = memberLogService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		this.doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
	}

	private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		if (requiresLogout(request)) {
			HttpSession session = request.getSession(false);
			SecurityContext context = SecurityContextHolder.getContext();
			if (session != null) {
				session.invalidate();
				memberLogService.insertMemberLog((Integer) context.getAuthentication().getPrincipal(), MemberLogType.LOGOUT);
			}
			context.setAuthentication(null);
			SecurityContextHolder.clearContext();
			response.sendRedirect("/");
			return;
		}
		chain.doFilter(request, response);
	}

	private boolean requiresLogout(HttpServletRequest request) {
		return request.getRequestURI().equals("/member/logout");
	}
}

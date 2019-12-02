package com.zj.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		/**
		 * 解决前端不允许跨域的问题
		 */
		HttpServletResponse response = (HttpServletResponse)resp;
		//允许的 客户端域名  
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8848");
	    //允许的 方法名  GET,POST
		response.setHeader("Access-Control-Allow-Methods", "*");
	    //允许服务端访问的客户端请求头，多个请求头用逗号分割，例如：Content-Type
		response.setHeader("Access-Control-Allow-Headers", "*");
	    //预检验请求时间
		response.setHeader("Access-Control-Max-Age", "1800");//30 min
		//表示是否支持跨域Cookie
		response.setHeader("Access-Control-Allow-Credentials", "true");
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}

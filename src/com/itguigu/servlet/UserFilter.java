package com.itguigu.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author whz
 * @create 2020-01-17 7:20
 * @desc TODO: add description here
 **/
public class UserFilter implements Filter {

  @Override public void init(FilterConfig filterConfig)
      throws ServletException {

  }

  @Override public void doFilter(ServletRequest servletRequest,
      ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    System.out.println("UserFilter ... doFilter...");
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override public void destroy() {

  }
}
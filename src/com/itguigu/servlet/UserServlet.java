package com.itguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author whz
 * @create 2020-01-17 7:19
 * @desc TODO: add description here
 **/
public class UserServlet extends HttpServlet {
  @Override protected void doGet(HttpServletRequest req,
      HttpServletResponse resp) throws ServletException, IOException {
    resp.getWriter().write("tomcat...");
  }
}
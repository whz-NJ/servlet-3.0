package com.itguigu.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author whz
 * @create 2020-01-17 7:25
 * @desc TODO: add description here
 **/
//监听项目的启动和停止
public class UserListener implements ServletContextListener {
  //监听Servlet启动初始化
  @Override public void contextInitialized(
      ServletContextEvent servletContextEvent) {
    System.out.println("UserListener ... contextInitialized ...");
    ServletContext servletContext = servletContextEvent.getServletContext();
    // 利用 servletContext 注册组件（读取web.xml）
    // servletContext.addFilter();

  }
  //监听Servlet销毁
  @Override public void contextDestroyed(
      ServletContextEvent servletContextEvent) {
    System.out.println("UserListener ... contextDestroyed ...");
  }
}
package com.itguigu.servlet;

import com.itguigu.service.HelloService;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author whz
 * @create 2020-01-16 21:32
 * @desc TODO: add description here
 **/
// 容器启动时，会将HandlesTypes指定的类型（实现类/子接口）全部传递过来。
// 传入感兴趣的类型，然后在onStartup时，通过set参数，把这个类型及其所有子类/子接口传递过来。
@HandlesTypes(value={ HelloService.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {
  //
  // 当前web应用的servletContext，一个web应用对应一个 servletContext。application四大域之一。
  // set：@HandlesTypes 指定的类型和子类型
  //1、使用ServletContext注册web组件：Servlet、Filter、Listener。
  //   如果这些组件是我们自己写的代码，可以通加注解@WebServlet/@Filter/@Listener注册层web组件
  //   如果这些组件是第三方jar包提供的，我们无法修改它们的代码，此时可以通过ServletContext来注册它们。
  //    之前还可以通过web.xml注册它们，servlet3.0不推荐使用。
  // 2、使用代码方式，在项目启动的时候，给ServletContext里添加组件
  //    必须在项目启动时通过 ServletContext 添加（不能在运行时给servlet注册组件）：
  //    1、ServletContainerInitializer#onStartup ，使用ServletContext入参，在项目启动时添加组件
  //    2、ServletContextListener#contextInitialized()，利用 servletContextEvent.getServletContext()
  @Override public void onStartup(Set<Class<?>> set,
      ServletContext sc) throws ServletException {
    System.out.println("感兴趣的类型：");
    for(Class<?> claz: set) {
      System.out.println(claz);
      //可以利用claz创建对象等等。
    }
    // 不通过web.xml，通过代码注册组件
    // 下载绑定tomcat源码，看addFilter对每个参数的注释
    final ServletRegistration.Dynamic servlet = sc
        .addServlet("userServlet", new UserServlet());
    //配置servlet的映射信息
    servlet.addMapping("/user");
    sc.addListener(UserListener.class);
    final FilterRegistration.Dynamic filter = sc
        .addFilter("userFilter", UserFilter.class);
    //配置filter的映射信息
    filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
  }
}
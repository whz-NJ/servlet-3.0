package com.itguigu.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author whz
 * @create 2020-01-17 16:06
 * @desc TODO: add description here
 **/
// 从控制台信息可以看出，虽然收请求的线程和处理业务逻辑的线程是不同线程，
// 但它们还是在一个线程池里，Spring MVC支持对业务逻辑处理配置独立线程池。
@WebServlet(value="/async", asyncSupported = true) //1. 支持异步处理，注意value的"async"前面要加/，否则启动报错：Invalid <url-pattern>
public class HelloAsyncServlet extends HttpServlet {
  @Override protected void doGet(HttpServletRequest req,
      HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("Main thread start: " + Thread.currentThread() + "==>" + System.currentTimeMillis());
    // 1. @WebServlet 注解的 asyncSupported 属性设置为true，支持异步
    // 2. 开启异步模式
    AsyncContext asyncContext = req.startAsync();
//    System.out.println("isAsyncSupported=" + req.isAsyncSupported());
//    System.out.println("isAsyncStarted=" + req.isAsyncStarted());
    // asyncContext.setTimeout(5000L);
    // 3. 进入业务逻辑开始异步处理
    asyncContext.start(new Runnable() {
                         @Override public void run() {
                           try {
                             System.out.println("Biz thread start: " + Thread.currentThread()+ "==>" + System.currentTimeMillis());
                             sayHello();
                             // 获取到异步上下文
                             AsyncContext asyncContext1 = req.getAsyncContext(); //当然用前面的 asyncContext 也可以
                             ServletResponse response = asyncContext1
                                 .getResponse();
                             // ServletResponse response = asyncContext.getResponse();
                             response.getWriter().write("hello async ...");
                             // 4. 异步处理结束
                             asyncContext.complete();
                             System.out.println("Biz thread end: " + Thread.currentThread() + "==>" + System.currentTimeMillis());
                           }
                           catch (Exception e) {
                             e.printStackTrace();
                           }
                         }
                       }
    );
    System.out.println("Main thread end: " + Thread.currentThread() + "==>" + System.currentTimeMillis());

  }

  public void sayHello() throws InterruptedException {
    System.out.println(Thread.currentThread() + " processing...");
    Thread.sleep(3000);
  }
}
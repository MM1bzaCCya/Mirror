//package com.example.mirror.interceptor;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//public class Logininterceptor implements HandlerInterceptor {
//   /*
//   * 在控制器之前调用
//   * */
//   @Override
//   // preHandle方法 通过request拿到 酷ki 实现逻辑判断 最后给予response响应
//   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//      System.out.println("Logininterceptor");
//      return true;
////      if(){
////         System.out.println("通过");
////         return true;
////      }else{
////         System.out.println("禁止");
////         return false;
////      }
//   }
//
//}

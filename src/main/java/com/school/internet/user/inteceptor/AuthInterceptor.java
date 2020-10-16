package com.school.internet.user.inteceptor;

import com.google.common.base.Joiner;
import com.school.internet.user.common.ThreadVariable;
import com.school.internet.user.entity.SmUser;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Map;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
            throws Exception {
        Map<String, String[]> map = req.getParameterMap();
        map.forEach((k,v) ->req.setAttribute(k, Joiner.on(",").join(v)));
        String requestURI = req.getRequestURI();
        if (requestURI.startsWith("/static") || requestURI.startsWith("/css") || requestURI.startsWith("/js") || requestURI.startsWith("/img") || requestURI.startsWith("/fonts") || requestURI.startsWith("/pages") || requestURI.startsWith("/plugins")) {
            return true;
        }
        SmUser smUser =  (SmUser)req.getSession().getAttribute("user");
        if(smUser != null){
        }else{
            String msg =  URLEncoder.encode("请先登录", "utf-8");
            StringBuffer sb = req.getRequestURL();
            String   target = URLEncoder.encode(sb.toString(), "utf-8");
            if ("GET".equalsIgnoreCase(req.getMethod())) {
                res.sendRedirect("/login?errorMsg=" + msg + "&target=" + target);
            }else {
                res.sendRedirect("/login?errorMsg=" + msg);
            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler,
                           ModelAndView modelAndView) throws Exception {


    }



    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}

package com.school.internet.user.inteceptor;

import com.school.internet.user.common.ThreadVariable;
import com.school.internet.user.entity.SmUser;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Set;

@Component
public class AuthActionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        SmUser smUser =  (SmUser)req.getSession().getAttribute("user");
        if(smUser == null){
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
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

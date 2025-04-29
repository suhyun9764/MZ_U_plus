package com.springboot.mzuplusspringjpa.common.auth;

import com.springboot.mzuplusspringjpa.dto.manager.ManagerDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ManagerAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session==null||session.getAttribute("loginManager")==null) {
            response.sendRedirect("/login.html");
            return false;
        }
        ManagerDto manager = (ManagerDto) session.getAttribute("loginManager");
        if(manager.getRoles().containsValue("manager"))
            return true;

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write("접근 권한이 없습니다.");
        return false;
    }
}

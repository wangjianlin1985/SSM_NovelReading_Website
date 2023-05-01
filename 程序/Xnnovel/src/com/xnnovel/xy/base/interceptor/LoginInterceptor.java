package com.xnnovel.xy.base.interceptor;

import com.xnnovel.xy.base.consts.Consts;
import com.xnnovel.xy.entity.Admin;
import com.xnnovel.xy.entity.User;
import com.xnnovel.xy.service.AdminService;
import com.xnnovel.xy.service.UserService;
import com.xnnovel.xy.utils.JSONUtils;
import com.xnnovel.xy.vo.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {


    private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI();
        logger.info("url====>{}",url);
        int index = url.indexOf(Consts.ADMIN_PREFIX);
        /**
         * 请求方式：普通请求，ajax请求
         */
        String xrequest = request.getHeader("X-Requested-With");
        /**
         * 请求路径中含有admin的说明是后台请求
         */
        if(index>=0){
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            if(admin!=null){
                return true;
            }
            request.getRequestDispatcher("/admin/login.html").forward(request,response);
        }else{
            User user = (User) request.getSession().getAttribute("user");
            if(user!=null){
                return true;
            }
            if("XMLHttpRequest".equals(xrequest)){
                R r = new R();
                r.setCode(403);
                r.setMessage("您暂未登录！");
                response.getWriter().print(JSONUtils.toString(r));
                return false;
            }else{
                request.getRequestDispatcher("/login.html").forward(request, response);
            }
        }
        return true;
    }
}

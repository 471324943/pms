package cn.czxy.web.filter;

import cn.czxy.domian.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhangjian1
 * @date 2019/4/11
 */
public class Filter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getSession().getAttribute("user");
        if (user!=null){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }else {
            response.sendRedirect(request.getContextPath()+"/login/login.jsp");
//            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
    }

    @Override
    public void destroy() {

    }
}

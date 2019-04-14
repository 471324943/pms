package cn.czxy.web.servlet;

import cn.czxy.domian.User;
import cn.czxy.service.LoginService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author zhangjian1
 * @date 2019/4/10
 * 该类用于登录操作
 */
@WebServlet("/Login")
public class LoginServlet extends BaseServlet{
    LoginService service = new LoginService();
    public void login() throws IOException, SQLException {
       User user = toBean(User.class);
        User login = service.login(user);
        if (login==null){
            String json = "{\"urls\":\"账号或密码错误!\"}";
            getResponse().getWriter().write(json);
        }else {
            getSession().setAttribute("user",login);
            String json = "{\"urls\":\"1\"}";
            getResponse().getWriter().write(json);
        }
    }
    public void  register() throws IOException, SQLException {
        User user = toBean(User.class);
        boolean register = service.register(user);
        System.out.println(user);
        if (register){
            String json = "{\"urls\":\"注册失败!\"}";
            getResponse().getWriter().write(json);
        }else {
            String json = "{\"urls\":\"1\"}";
            getResponse().getWriter().write(json);
        }
    }
    public void recah() throws IOException, SQLException {
        String name = getRequest().getParameter("name");
        if (service.recah(name)){
            getResponse().getWriter().write("昵称可以使用!");
        }else {
            getResponse().getWriter().write("已经存在该昵称");
        }
    }
}

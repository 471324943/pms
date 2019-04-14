package cn.czxy.web.servlet;

import cn.czxy.domian.Clazz;
import cn.czxy.domian.Project;
import cn.czxy.domian.ResultData;
import cn.czxy.domian.User;
import cn.czxy.service.MultpleService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * @author zhangjian1
 * @date 2019/4/10
 * 该类用于
 */
@WebServlet("/Multi")
public class MultipleServlet extends BaseServlet {

    private MultpleService service = new MultpleService();

    /**
     * 该类用于home.jsp的数据读取
     * @return
     * @throws SQLException
     */
    public String findAllPro() throws SQLException {
        List<ResultData> list = service.getProFastTimeUser();
        getRequest().setAttribute("list",list);
        return "forward:view/home.jsp";
    }

    /**
     * 该类用于ranking.jsp的数据读取
     * @return
     * @throws SQLException
     */
    public String findAlltime() throws SQLException {
        int pid = Integer.parseInt(getRequest().getParameter("pid"));
        List<ResultData> list = service.appointProId(pid);
        getRequest().setAttribute("list",list);

        List<Clazz> clazzes = service.selectAllClazz();
        getRequest().setAttribute("clazzes",clazzes);

        return "forward:view/ranking.jsp";
    }

    /**
     * 该类用于进行班级的筛选
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    public void sort() throws SQLException, ServletException, IOException {
        String cid = getRequest().getParameter("val");//
        String pid = getRequest().getParameter("pid");
        System.out.println(cid.length()+":"+cid);
        System.out.println(pid.length()+":"+pid);
        System.out.println("____________________________");
        Project project = new Project();
        Clazz  clazz = new Clazz();
        int i = Integer.parseInt(cid);
        clazz.setcId(i);
        project.setpId(Integer.parseInt(pid));
        List<ResultData> resultData=null;
        if (cid.equals("0")){
            // 总排行
             resultData = service.appointProId(project.getpId());
        }else {
             resultData = service.getAllUserByClazzAndPro(clazz,project);
        }
        getRequest().setAttribute("vid",cid);
        getRequest().setAttribute("list",resultData);
        getRequest().getRequestDispatcher("/view/tool.jsp").forward(getRequest(),getResponse());
        return;
    }

    /**
     * 该类用于保存时间数据
     * @throws IOException
     * @throws ParseException
     */
    public void subByTime() throws IOException, ParseException, SQLException {
        String time = getRequest().getParameter("time");
        String pid =  getRequest().getParameter("pid");
        //Date date = new SimpleDateFormat("hh:mm:ss:SS").parse(time);
        User user = (User) getSession().getAttribute("user");
        service.addNewTime(user.getuId(),Integer.parseInt(pid),Integer.parseInt(time));
        getResponse().getWriter().write("{\"times\":\""+time+"\"}");
    }
    public String revomePor() throws SQLException {
        String pid = getRequest().getParameter("pid");
        Project project = new Project();
        project.setpId(Integer.parseInt(pid));
        service.deletePro(project);
        return "/Multi?method=findAllPro";
    }
    public void addPor() throws SQLException {
        String pname = getRequest().getParameter("pname");
        Project project = new Project();
        project.setpName(pname);
        service.addPro(project);
    }
}

package cn.czxy.service;

import cn.czxy.dao.SchoolProDao;
import cn.czxy.domian.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author TaoQingZhou129
 * @date 2019/4/10
 */
public class LoginService {

    private SchoolProDao dao = new SchoolProDao();

    /**
     *  返回是否登录成功 登录成功返回user对象 否则返回null
     * @param user
     * @return
     */
    public User login(User user) throws SQLException {

        // 查询所有进行匹配
        List<User> users = dao.selectAllUser();

        for (User everyUser : users) {
          if (everyUser.getLoginName().equals(user.getLoginName())&&everyUser.getPassword().equals(user.getPassword())){
              return everyUser;
          }
        }

        return null;

    }

    /**
     * 返回注册结果 是否注册成功
     * @param user
     * @return
     */
    public boolean register(User user) throws SQLException {

        // 查询所有判断是否存在 该账号
        List<User> users = dao.selectAllUser();

        // 标记账号是否存在
        boolean flag = true;

        for (User everyUser : users) {
            if (everyUser.getLoginName().equals(user.getLoginName())){
                flag = false;
                break;
            }
        }

        // 没有存在进行添加
        if (flag){
            dao.addUser(user);
            return flag;
        }

        return flag;

    }



    public boolean recah(String name) throws SQLException {
        List<User> users = dao.selectAllUser();

        for (User everyUser : users) {
            if (everyUser.getUsername().equals(name)){
                return false;
            }
        }
        return true;
    }


}

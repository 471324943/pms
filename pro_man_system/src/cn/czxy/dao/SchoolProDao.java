package cn.czxy.dao;

import cn.czxy.Utils.DBCPUtil;
import cn.czxy.domian.Clazz;
import cn.czxy.domian.Project;
import cn.czxy.domian.Relevance;
import cn.czxy.domian.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 花开自来 on 2019/4/10.
 */
public class SchoolProDao {
    QueryRunner qr = new QueryRunner(DBCPUtil.basicDataSource);

    //  查

    /**
     * 获得全部学生对象
     *
     * @return 全部学生对象
     * @throws SQLException
     */
    public List<User> selectAllUser() throws SQLException {
        String sql = "select * from user";
        List<User> queryList = qr.query(sql, new BeanListHandler<>(User.class));
        return queryList;
    }

    /**
     * 获得全部班级对象
     *
     * @return 全部班级对象
     * @throws SQLException
     */
    public List<Clazz> selectAllClazz() throws SQLException {
        String sql = "select * from clazz";
        List<Clazz> queryList = qr.query(sql, new BeanListHandler<>(Clazz.class));
        return queryList;
    }

    /**
     * 获得全部项目对象
     *
     * @return 全部的项目对象
     * @throws SQLException
     */
    public List<Project> selectAllPro() throws SQLException {
        String sql = "select * from project";
        List<Project> queryList = qr.query(sql, new BeanListHandler<>(Project.class));
        return queryList;
    }

    /**
     * 获得全部关联对象(展示全部数据)
     * @return relevance关联表中全部数据
     * @throws SQLException
     */
    public List<Relevance> selectAllData() throws SQLException {
        String sql = "select * from relevance";
        List<Relevance> AllData = qr.query(sql, new BeanListHandler<>(Relevance.class));
        return AllData;
    }

    /**
     * 获得各各项目最快的Relevance对象
     *
     * @return 每个项目用时最少的Relevance对象集合
     * @throws SQLException
     */
    public List<Relevance> selectAllProFastTime() throws SQLException {
        List<Project> projects = selectAllPro();

        ArrayList<Relevance> relevances = new ArrayList<>();

        String sql = "SELECT * FROM relevance as a1 WHERE rTime=(SELECT  min(rTime) FROM relevance GROUP BY pId HAVING pId=? and a1.pId=pId) ";
        for (int i = 0; i < projects.size(); i++) {
            Project project = projects.get(i);
            relevances.add(selectAppointProFastTime(project.getpId()));
        }
        return relevances;
    }

    /**
     * 根据班级Id 获得班级下的学生Id
     * @param cId
     * @return
     * @throws SQLException
     */
    private Integer[] selectAlluIdBycId(int cId) throws SQLException {
        String sql = "SELECT uId FROM user WHERE cId= ?";
        List<Object[]> query = qr.query(sql, new ArrayListHandler(),cId);
        Integer[] arr =new Integer[query.size()+1];
        for (int i = 0; i < query.size(); i++) {
            Object[] objects =  query.get(i);
            arr[i+1]=(int)objects[0];
        }
        return arr;
    }

    /**
     * 获得指定项目Id 下指定班级Id 的全部Relevance 集合
     * @param pId
     * @param cId
     * @return
     * @throws SQLException
     */
    public List<Relevance> selectUsersBycIdAndpId(int pId , int cId) throws SQLException {
        Integer[] ints = selectAlluIdBycId(cId);
        ints[0]=pId;
        String sql = "SELECT * from relevance WHERE pId=? and uId in(?,?,?)";
        List<Relevance> query = qr.query(sql, new BeanListHandler<>(Relevance.class),ints);
        return query;
    }

    /**
     * 查询指定项目id 下所有 Relevance对象
     * @param pId 项目id
     * @return 这个项目下所有的Relevance记录
     * @throws SQLException
     */
    public List<Relevance> selectAppointPro(int pId) throws SQLException {
        String sql = "SELECT  * FROM relevance where pId= ?";
        List<Relevance> query = qr.query(sql, new BeanListHandler<>(Relevance.class), pId);
        return query;
    }


    /**
     * 根据 学生id ,获得学生对象
     * @param uId 学生id
     * @return 学生对象
     * @throws SQLException
     */
    public User selectUserByuId(int uId) throws SQLException {
        String sql = "select * from user where uId = ?";
        User thisUser = qr.query(sql, new BeanHandler<>(User.class),uId);
        return thisUser;
    }

    /**
     * 根据用户uId 获得所在班级对象
     *
     * @param uId 用户uId
     * @return 班级对象
     * @throws SQLException
     */
    public Clazz selectClazzByUserId(int uId) throws SQLException {
        String sql = "select * from clazz where cId= (select cId FROM user where uId= ?)";
        Clazz queryClazz = qr.query(sql, new BeanHandler<>(Clazz.class),uId);
        return queryClazz;
    }

    /**
     * 根据 班级id ,获得班级对象
     *
     * @param cId 班级id
     * @return 班级对象
     * @throws SQLException
     */
    public Clazz selectClazzByuId(int cId) throws SQLException {
        String sql = "select * from clazz where uId = ?";
        Clazz thisClazz = qr.query(sql, new BeanHandler<>(Clazz.class));
        return thisClazz;
    }

    /**
     * 根据 项目id ,获得项目对象
     *
     * @param pId 项目id
     * @return 项目对象
     * @throws SQLException
     */
    public Project selectProNameById(int pId) throws SQLException {
        String sql = "select * from project where pId = ?";
        Project thisProject = qr.query(sql, new BeanHandler<>(Project.class),pId);
        return thisProject;
    }

    /**
     * 获得指定项目pid 用时最少的Relevance对象
     *
     * @param pId 指定项目的id
     * @return 用时最少的Relevance对象
     * @throws SQLException
     */
    public Relevance selectAppointProFastTime(int pId) throws SQLException {
        String Empty = "SELECT * FROM relevance WHERE pId=?";
        List<Relevance> query1 = qr.query(Empty, new BeanListHandler<>(Relevance.class), pId);
        if (query1.size()>0) {
            //如果项目存在记录存在
            String sql = "SELECT * FROM relevance as a1 WHERE rTime=(SELECT  min(rTime) FROM relevance GROUP BY pId HAVING pId=? and a1.pId=pId) ";
            Relevance query = qr.query(sql, new BeanHandler<>(Relevance.class), pId);
            return query;
        }else{
            return new Relevance(0,pId,0,0);
        }
    }



    // 增

    /**
     * 传入班级名称, 添加到班级表中
     *
     * @param clazzName 班级名称字符串
     * @throws SQLException
     */
    public void addClazz(String clazzName) throws SQLException {
        String sql = "insert into clazz(cName) values ?";
        qr.update(sql, clazzName);
    }

    /**
     * 传入User 对象, 添加到User表中
     * @param user
     */
    public void addUser(User user) throws SQLException {
        /** 账号,密码,真实姓名,班级id */
        String sql = "insert into user(loginName,password,userName,cId) values (?,?,?,?)";
        Object[] o = {user.getLoginName(), user.getPassword(), user.getUsername(), user.getcId()};
        qr.update(sql, o);
    }


    /**
     * 添加新的关联表数据
     * @param uId    User uId
     * @param pId    Perject pId
     * @param rTime  作业用时
     * @throws SQLException
     */
    public void addRelevance(int uId, int pId,int rTime) throws SQLException {
        String sql = "INSERT INTO relevance(uId,pId,rTime) VALUES (?,?,?)";
        int update = qr.update(sql, uId, pId, rTime);
        System.out.println(update);
    }

    //TODO
    @Test
    public void Demo() throws SQLException {
        List<Relevance> relevances = selectUsersBycIdAndpId(2, 1);
        for (Relevance relevance : relevances) {
            System.out.println(relevance);
        }

    }

    /**
     * 更新关联时间内容
     * @param uId
     * @param pId
     * @param rTime
     * @throws SQLException
     */
    public void update(int uId,int pId,int rTime ) throws SQLException {
        String sql = "update relevance set rTime=? where uId=? and pid=?";
        qr.update(sql,rTime,uId,pId);
    }


    /**
     * 删除项目
     * @param project
     * @throws SQLException
     */
    public void deletePro(Project project) throws SQLException {
        String sql = "delete from project where pId=?";
        qr.update(sql,project.getpId());

        String sql2 = "delete from relevance where pId=?";
        qr.update(sql2,project.getpId());
    }


    /**
     * 添加项目
     * @param project
     * @throws SQLException
     */
    public void addPro(Project project) throws SQLException {
        String sql = "insert into project(pName) values (?)";
        qr.update(sql,project.getpName());
    }

    @Test
    public void D() throws SQLException {
        String sql3 = "insert into relevance(pId) values (?)";
        qr.update(sql3,50);
    }

}

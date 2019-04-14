package cn.czxy.service;

import cn.czxy.dao.SchoolProDao;
import cn.czxy.domian.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author TaoQingZhou129
 * @date 2019/4/10
 */
public class MultpleService {

    private SchoolProDao dao = new SchoolProDao();

    /**
     * 获取每个项目做的最快的用户
     * @return
     * @throws SQLException
     */
    public List<ResultData> getProFastTimeUser() throws SQLException {

        List<Relevance> relevances = dao.selectAllProFastTime();
        List<ResultData> resultData = getResultData(relevances);
        return resultData;
    }


    /**
     * 获取所有做某项目的用户
     * @param pId
     * @return
     * @throws SQLException
     */
    public List<ResultData> appointProId(int pId) throws SQLException {

        List<Relevance> relevances = dao.selectAppointPro(pId);
        List<ResultData> resultData = getResultData(relevances);
        sort(resultData);
        return resultData;

    }


    /**
     * 根据班级 项目 查询所有用户 进行排序
     * @param clazz
     * @param pro
     * @return
     * @throws SQLException
     */
    public List<ResultData> getAllUserByClazzAndPro(Clazz clazz,Project pro) throws SQLException {

        // 返回所有数据
        List<Relevance> relevances = dao.selectAllData();
        List<ResultData> resultDatas = getResultData(relevances);
        List<ResultData> resultData = new ArrayList<>();

        for (ResultData data : resultDatas) {

            System.out.println(data.getClazz().getcId());

            if (data.getClazz().getcId() == clazz.getcId() && data.getProject().getpId() == pro.getpId()){
                resultData.add(data);
            }

        }

        // 排序
        sort(resultData);

        System.out.println(clazz.getcId());
        System.out.println(pro.getpId());
        for (ResultData resultDatum : resultData) {
            System.out.println(resultDatum);
        }

        return resultData;
    }


    /**
     * 根据项目id 和用户 进行添加或者更新项目所用时间
     * @param uId
     * @param pId
     * @param rTime
     * @throws SQLException
     */
    public void addNewTime(Integer uId,Integer pId,Integer rTime) throws SQLException {

        List<Relevance> relevances = dao.selectAllData();

        List<ResultData> resultData = getResultData(relevances);

        boolean flag = true;

        for (ResultData resultDatum : resultData) {
            if (resultDatum.getUser().getuId() == uId && resultDatum.getProject().getpId() == pId){
                System.out.println(resultDatum);
                if (rTime < resultDatum.getrTime()){
                    // 更新
                    dao.update(uId,pId,rTime);
                    return;
                }
                flag = false;
            }
        }

        if (flag){
            dao.addRelevance(uId,pId,rTime);
        }
    }


    /**
     * 排序
     * @param resultData
     */
    public void sort(List<ResultData> resultData){

            Collections.sort(resultData, new Comparator<ResultData>() {
                @Override
                public int compare(ResultData o1, ResultData o2) {
                        return o1.getrTime() - o2.getrTime() > 0 ? 1 : -1;
                }
            });
    }


    /**
     * 将关联数据转换为实体数据
     * @param relevances
     * @return
     * @throws SQLException
     */
    public List<ResultData> getResultData(List<Relevance> relevances) throws SQLException {

        List<ResultData> resultData = new ArrayList<>();
        for (Relevance relevance : relevances) {

            // 根据项目id 查项目
            Project project = dao.selectProNameById(relevance.getpId());

            if (relevance.getrTime() == 0){
                Clazz noDataToClazz = new Clazz(0,"暂无");
                User user1 = new User(0,"暂无");
                ResultData noDataToPro = new ResultData(project,noDataToClazz,user1,relevance.getrTime());
                resultData.add(noDataToPro);
            }else{
                // 根据用户id 查用户
                User  user = dao.selectUserByuId(relevance.getuId());
                // 根据用户查找班级
                Clazz clazz = dao.selectClazzByUserId(user.getuId());
                resultData.add(new ResultData(project,clazz,user,relevance.getrTime()));
            }

        }

        return resultData;
    }


    /**
     *  添加项目
     */
    public void addPro(Project project) throws SQLException {

        List<Project> projects = dao.selectAllPro();

        boolean flag = true;

        for (Project project1 : projects) {
            if (project1.getpName().equals(project.getpName())){
                flag = false;
                return;
            }
        }
        if (flag){
            dao.addPro(project);
        }
    }


    /**
     * 删除项目
     * @param project
     * @throws SQLException
     */
    public void deletePro(Project project) throws SQLException {
        dao.deletePro(project);
    }


    /**
     * 查询所有班级在选择排行页面进行展示
     * @return
     * @throws SQLException
     */
    public List<Clazz> selectAllClazz() throws SQLException {
       return dao.selectAllClazz();
    }
}

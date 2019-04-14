package cn.czxy.domian;

/**
 * @author TaoQingZhou129
 * @date 2019/4/10
 */
public class ResultData {

    private Project project; // 项目
    private Clazz clazz; // 班级
    private User user; // 用户
    private int rTime; // 所用时间

    public ResultData() {
    }

    public ResultData(Project project, Clazz clazz, User user) {
        this.project = project;
        this.clazz = clazz;
        this.user = user;

    }
    public ResultData(Project project, Clazz clazz, User user, int rTime) {
        this.project = project;
        this.clazz = clazz;
        this.user = user;
        this.rTime = rTime;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getrTime() {
        return rTime;
    }

    public void setrTime(int rTime) {
        this.rTime = rTime;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "project=" + project +
                ", clazz=" + clazz +
                ", user=" + user +
                ", rTime=" + rTime +
                '}';
    }
}

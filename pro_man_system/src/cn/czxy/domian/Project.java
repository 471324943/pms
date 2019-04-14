package cn.czxy.domian;

/**
 * @author TaoQingZhou129
 * @date 2019/4/10
 */
public class Project {

    private int pId;
    private String pName;

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }


    @Override
    public String toString() {
        return "Project{" +
                "pId=" + pId +
                ", pName='" + pName + '\'' +
                '}';
    }
}

package cn.czxy.domian;

/**
 * @author TaoQingZhou129
 * @date 2019/4/10
 */
public class Clazz {

    private int cId;
    private String cName;

    public Clazz() {
    }

    public Clazz(String cName) {
        this.cName = cName;
    }

    public Clazz(int cId, String cName) {
        this.cId = cId;
        this.cName = cName;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                '}';
    }
}

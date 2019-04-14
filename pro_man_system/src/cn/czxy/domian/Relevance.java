package cn.czxy.domian;

/**
 * @author TaoQingZhou129
 * @date 2019/4/10
 */
public class Relevance {

    private int rId;
    private int pId;
    private int uId;
    private int rTime;

    public Relevance() {
    }

    public Relevance(int rId, int pId, int uId, int rTime) {
        this.rId = rId;
        this.pId = pId;
        this.uId = uId;
        this.rTime = rTime;
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getrTime() {
        return rTime;
    }

    public void setrTime(int rTime) {
        this.rTime = rTime;
    }

    @Override
    public String toString() {
        return "Relevance{" +
                "rId=" + rId +
                ", pId=" + pId +
                ", uId=" + uId +
                ", rTime=" + rTime +
                '}';
    }
}

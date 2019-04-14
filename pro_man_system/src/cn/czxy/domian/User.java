package cn.czxy.domian;

/**
 * @author TaoQingZhou129
 * @date 2019/4/10
 */
public class User {

    private int uId;
    private String loginName;
    private String username;
    private String password;
    private int cId;

    public User() {
    }

    public User(int uId, String loginName) {
        this.uId = uId;
        this.loginName = loginName;
    }

    public User(int uId, String loginName, String username, String password, int cId) {
        this.uId = uId;
        this.loginName = loginName;
        this.username = username;
        this.password = password;
        this.cId = cId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", loginName='" + loginName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", cId=" + cId +
                '}';
    }
}

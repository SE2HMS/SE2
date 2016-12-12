package VO;

/**
 * Created by Administrator on 2016/12/12.
 */
public class WebManager {
    public String userName;
    public String contact;
    public UserType userType = UserType.WEB_MANAGER;

    public WebManager(String name,String contact) {
        this.userName = name;
        this.contact = contact;
    }

    public String getUserName() {
        return userName;
    }

    public String getContact() {
        return contact;
    }

    public UserType getUserType() {
        return userType;
    }
}

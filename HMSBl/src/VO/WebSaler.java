package VO;

/**
 * Created by Administrator on 2016/12/8.
 */
public class WebSaler {
    private final String contact;
    private final String name;
    private final UserType type = UserType.WEB_SALER;

    public WebSaler(String name,String contact) {
        this.name = name;
        this.contact = contact;
    }

    public UserType getType() {
        return type;
    }

    public String getContact() {
        return contact;
    }

    public String getName() {
        return name;
    }
}

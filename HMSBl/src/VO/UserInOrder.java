package VO;

/**
 * Created by Administrator on 2016/11/22.
 */
public class UserInOrder {
    private final String name;
    private final String contact;
    private final String id;

    public UserInOrder(String id,String name,String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getId() {
        return id;
    }
}

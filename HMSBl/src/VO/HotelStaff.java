package VO;

/**
 * Created by Administrator on 2016/12/8.
 */
public class HotelStaff {
    private final String hotelName;
    private final String contact;
    private final String userName;
    private final UserType type = UserType.HOTEL_STAFF;

    public HotelStaff(String hotelName,String contact,String userName) {
        this.hotelName = hotelName;
        this.contact = contact;
        this.userName = userName;
    }

    public String getName() {
        return userName;
    }

    public UserType getType() {
        return type;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getContact() {
        return contact;
    }
}

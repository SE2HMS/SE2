package VO;

/**
 * Created by Administrator on 2016/12/8.
 */
public class HotelStaff {
    private final String hotelName;
    private final String contact;

    public HotelStaff(String hotelName,String contact) {
        this.hotelName = hotelName;
        this.contact = contact;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getContact() {
        return contact;
    }
}

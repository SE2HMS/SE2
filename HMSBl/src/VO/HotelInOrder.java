package VO;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/22.
 */
public class HotelInOrder {
    private final String hotelName;
    private final ArrayList<RoomInOrder> rooms;

    public HotelInOrder(String hotelName,ArrayList<RoomInOrder> rooms) {
        this.hotelName = hotelName;
        this.rooms = rooms;
    }

    public String getHotelName() {
        return hotelName;
    }

    public ArrayList<RoomInOrder> getRooms() {
        return rooms;
    }
}

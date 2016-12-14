package VO;

/**
 * Created by Administrator on 2016/11/22.
 */
public class RoomVO {
    private final String hotelName;
    private final String type;
    private final double price;
    private final int total;
    private final int offlineOrdered;
    private final int[] available;

    public RoomVO(String hotelName,String type,double price,int total,int offlineOrdered,int[] available) {
        this.hotelName = hotelName;
        this.type = type;
        this.price = price;
        this.total = total;
        this.offlineOrdered = offlineOrdered;
        this.available = available;
    }

    public int getOfflineOrdered() {
        return offlineOrdered;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getTotal() {
        return total;
    }

    public int[] getAvailable() {
        return available;
    }

    public String getHotelName() {
        return hotelName;
    }
}

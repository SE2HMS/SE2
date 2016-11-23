package VO;

/**
 * Created by Administrator on 2016/11/22.
 */
public class RoomVO {
    private final String type;
    private final int price;
    private final int total;
    private final int[] available;

    public RoomVO(String type,int price,int total,int[] available) {
        this.type = type;
        this.price = price;
        this.total = total;
        this.available = available;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public int getTotal() {
        return total;
    }

    public int[] getAvailable() {
        return available;
    }
}

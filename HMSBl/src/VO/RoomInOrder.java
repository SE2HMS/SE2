package VO;

/**
 * Created by Administrator on 2016/11/22.
 */
public class RoomInOrder {
    private final String type;
    private final int num;
    private final double price;
    private final double total;

    public RoomInOrder(String type,int num,double price,double total) {
        this.type = type;
        this.num = num;
        this.price = price;
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public int getNum() {
        return num;
    }

    public double getPrice() {
        return price;
    }

    public double getTotal() {
        return total;
    }
}

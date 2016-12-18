package VO;

/**
 * Created by Administrator on 2016/11/22.
 */
public class RoomInOrder {
    private final String name;
    private final int num;
    private final double price;
    private final double total;

    public RoomInOrder(String name, int num, double price, double total) {
        this.name = name;
        this.num = num;
        this.price = price;
        this.total = total;
    }

    public String getName() {
        return name;
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

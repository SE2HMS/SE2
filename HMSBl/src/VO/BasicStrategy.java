package VO;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/22.
 */
public class BasicStrategy {
    private final String name;
    private final double discount;

    public BasicStrategy(String name, double discount) {
        this.name = name;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public double getDiscount() {
        return discount;
    }
}

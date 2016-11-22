package VO;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/22.
 */
public class StrategyVO {
    private final String name;
    private final double discount;

    public StrategyVO(String name, double discount) {
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

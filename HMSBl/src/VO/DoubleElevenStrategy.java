package VO;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/22.
 */
public class DoubleElevenStrategy implements StrategyVO{
    private final String name;
    private final double discount;
    private final Date startTime;
    private final Date endTime;

    public DoubleElevenStrategy(String name,double discount,Date startTime,Date endTime) {
        this.name = name;
        this.discount = discount;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public double getDiscount() {
        return discount;
    }

    public Date getStartTime() {
        return startTime;
    }

    @Override
    public String getType() {
        return "date";
    }

    public Date getEndTime() {
        return endTime;
    }
}

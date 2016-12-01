package VO;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/22.
 */
public class DoubleElevenStrategy implements StrategyVO{
    private final BasicStrategy strategy;
    private final Date startTime;
    private final Date endTime;

    public DoubleElevenStrategy(String name,double discount,Date startTime,Date endTime) {
        this.strategy = new BasicStrategy(name,discount);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return strategy.getName();
    }

    public double getDiscount() {
        return strategy.getDiscount();
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

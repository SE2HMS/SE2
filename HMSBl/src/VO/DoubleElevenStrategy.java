package VO;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/22.
 */
public class DoubleElevenStrategy implements StrategyVO{
    private final BasicStrategy webStrategy;
    private final Date startTime;
    private final Date endTime;

    public DoubleElevenStrategy(String name,double discount,Date startTime,Date endTime) {
        this(new BasicStrategy(name,discount),startTime,endTime);
    }

    public DoubleElevenStrategy(BasicStrategy strategy, Date startTime, Date endTime) {
        this.webStrategy = strategy;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return webStrategy.getName();
    }

    public double getDiscount() {
        return webStrategy.getDiscount();
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
}

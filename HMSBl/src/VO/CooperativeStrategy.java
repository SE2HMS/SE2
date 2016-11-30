package VO;

/**
 * Created by Administrator on 2016/11/29.
 */
public class CooperativeStrategy implements StrategyVO {
    private BasicStrategy strategy;

    public CooperativeStrategy(String name,double discount) {
        this.strategy = new BasicStrategy(name,discount);
    }

    public String getName() {
        return strategy.getName();
    }

    public double getDiscount() {
        return strategy.getDiscount();
    }
}

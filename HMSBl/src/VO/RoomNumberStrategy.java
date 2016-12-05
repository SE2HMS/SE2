package VO;

/**
 * Created by Administrator on 2016/11/29.
 */
public class RoomNumberStrategy implements StrategyVO{
    private final BasicStrategy strategy;

    public RoomNumberStrategy(String name,double discount) {
        this.strategy = new BasicStrategy(name,discount);
    }

    @Override
    public String getName() {
        return strategy.getName();
    }

    @Override
    public String getType() {
        return "roomnum";
    }

    @Override
    public double getDiscount() {
        return strategy.getDiscount();
    }
}

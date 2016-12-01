package VO;

/**
 * Created by Administrator on 2016/11/22.
 * 商圈促销策略，包含商圈名，折扣，策略名
 */
public class CBDStrategy implements StrategyVO{
    private final BasicStrategy strategy;
    private final String CBD;

    public CBDStrategy(String name,double discount,String CBD) {
        this.CBD = CBD;
        this.strategy = new BasicStrategy(name,discount);
    }

    public String getCBD() {
        return CBD;
    }

    public String getName() {
        return strategy.getName();
    }

    public double getDiscount() {
        return strategy.getDiscount();
    }

    @Override
    public String getType() {
        return "CBD";
    }
}

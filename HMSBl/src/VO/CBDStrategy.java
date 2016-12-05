package VO;

/**
 * Created by Administrator on 2016/11/22.
 * 商圈促销策略，包含商圈名，三级折扣，策略名
 */
public class CBDStrategy implements StrategyVO{
    private final String name;
    private final double[] discount = new double[3];
    private final String CBD;

    public CBDStrategy(String name,double level0,double level1,double level2,String CBD) {
        this.CBD = CBD;
        this.name = name;
        this.discount[0] = level0;
        this.discount[1] = level1;
        this.discount[2] = level2;
    }

    public String getCBD() {
        return CBD;
    }

    public String getName() {
        return name;
    }

    public double getLevel0() {
        return discount[0];
    }

    public double getLevel1() {
        return discount[1];
    }

    public double getLevel2() {
        return discount[2];
    }

    @Override
    public String getType() {
        return "CBD";
    }
}

package VO;

/**
 * Created by Administrator on 2016/11/22.
 */
public class BirthdayStrategy implements StrategyVO{
    private final String name;
    private final double discount;

    public BirthdayStrategy(String name,double discount) {
        this.strategy = new BasicStrategy(name,discount);
    }

    public String getName() {
        return name;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public String getType() {
        return "birthday";
    }
}

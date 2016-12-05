package VO;

/**
 * Created by Administrator on 2016/11/22.
 */
public class BirthdayStrategy implements StrategyVO{
    private final BirthdayStrategy strategy;

    public BirthdayStrategy(String name,double discount) {
        this.strategy = new BirthdayStrategy(name,discount);
    }

    public String getName() {
        return this.strategy.getName();
    }

    public double getDiscount() {
        return this.strategy.getDiscount();
    }

    @Override
    public String getType() {
        return "birthday";
    }
}

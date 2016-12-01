package VO;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/11/29.
 */
public class CooperativeStrategy implements StrategyVO {
    private BasicStrategy strategy;
    private ArrayList<String> companies;

    public CooperativeStrategy(String name,double discount) {
        this.strategy = new BasicStrategy(name,discount);
    }

    public String getName() {
        return strategy.getName();
    }

    public double getDiscount() {
        return strategy.getDiscount();
    }

    public Iterator<String> getCompanies() {
        return companies.iterator();
    }

    @Override
    public String getType() {
        return "companies";
    }
}

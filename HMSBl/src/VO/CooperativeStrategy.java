package VO;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/11/29.
 */
public class CooperativeStrategy implements StrategyVO {
    private final String name;
    private final double discount;
    private final ArrayList<String> companies;

    public CooperativeStrategy(String name,double discount,ArrayList<String> companies) {
        this.name = name;
        this.discount = discount;
        this.companies = companies;
    }

    public String getName() {
        return name;
    }

    public double getDiscount() {
        return discount;
    }

    public Iterator<String> getCompanies() {
        return companies.iterator();
    }

    @Override
    public String getType() {
        return "companies";
    }
}

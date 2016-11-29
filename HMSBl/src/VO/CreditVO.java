package VO;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/22.
 */
public class CreditVO {
    private final Date time;
    private final String num;
    private final String userId;
    private final OrderAction action;
    private final double creditChange;
    private final double credit;

    /**
     * CreditVO的构造方法
     * @param time 发生时间
     * @param num 对应订单编号
     * @param action 发生的动作
     * @param creditChange 信用值的变化
     * @param credit 变化后的信用值
     */
    public CreditVO(Date time,String num,String userId,OrderAction action,double creditChange,double credit) {
        this.time = time;
        this.num = num;
        this.userId = userId;
        this.action = action;
        this.creditChange = creditChange;
        this.credit = credit;
    }

    public Date getTime() {
        return time;
    }

    public String getNum() {
        return num;
    }

    public String getUserId() {return userId;}

    public OrderAction getAction() {
        return action;
    }

    public double getCreditChange() {
        return creditChange;
    }

    public double getCredit() {
        return credit;
    }
}

package VO;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/22.
 */
public class CreditVO {
    private final Date time;
    private final String num;
    private final OrderAction action;
    private final int creditChange;
    private final int credit;

    /**
     * CreditVO的构造方法
     * @param time 发生时间
     * @param num 对应订单编号
     * @param action 发生的动作
     * @param creditChange 信用值的变化
     * @param credit 变化后的信用值
     */
    public CreditVO(Date time,String num,OrderAction action,int creditChange,int credit) {
        this.time = time;
        this.num = num;
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

    public OrderAction getAction() {
        return action;
    }

    public int getCreditChange() {
        return creditChange;
    }

    public int getCredit() {
        return credit;
    }
}

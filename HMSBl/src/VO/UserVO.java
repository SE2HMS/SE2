package VO;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/22.
 */
public class UserVO {
    private final String name;
    private final String contact;
    private final double credit;
    private final int grade;
    private final ArrayList<OrderVO> orders;
    private final UserType type;
    private final String additionalInfo;

    /**
     * 包含所有信息的构造方法，讲道理是逻辑层调用的吧…
     * @param name 用户名
     * @param contact 联系方式
     * @param credit 信用总值
     * @param grade 会员等级
     * @param orders 订单引用
     * @param type 会员类型，包括普通和企业
     * @param additionalInfo 普通会员的生日或者企业会员的企业
     */
    public UserVO(String name,String contact,double credit,int grade,ArrayList<OrderVO> orders,UserType type,String additionalInfo) {
        this.name = name;
        this.contact = contact;
        this.credit = credit;
        this.grade = grade;
        this.orders = orders;
        this.type = type;
        this.additionalInfo = additionalInfo;
    }

    /**
     * 构造方法，在客户端生成时调用
     * @param name 用户名
     * @param contact 联系方式
     * @param type 会员类型，包括普通和企业
     * @param additionalInfo 普通会员的生日或者企业会员的企业
     */
    public UserVO(String name,String contact,UserType type,String additionalInfo) {
        this(name,contact,0,0,null,type,additionalInfo);
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public double getCredit() {
        return credit;
    }

    public int getGrade() {
        return grade;
    }

    public ArrayList<OrderVO> getOrders() {
        return orders;
    }

    public UserType getType() {
        return type;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
}

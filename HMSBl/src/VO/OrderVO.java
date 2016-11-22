package VO;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/22.
 */
public class OrderVO {
    private final UserInOrder user;
    private final HotelInOrder hotel;
    private final OrderState state;
    private final boolean children;
    private final Date inTime;
    private final Date outTime;
    private final Date execTime;
    private final int total;

    public OrderVO(UserInOrder user, HotelInOrder hotel, OrderState state, boolean children, Date inTime,Date outTime, Date execTime, int total) {
        this.user = user;
        this.hotel = hotel;
        this.state = state;
        this.children = children;
        this.inTime = inTime;
        this.outTime = outTime;
        this.execTime = execTime;
        this.total = total;
    }
}

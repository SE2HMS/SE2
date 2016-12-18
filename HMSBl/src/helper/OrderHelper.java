package helper;

import PO.OrderPO;
import VO.*;
import order_bl_serv.OrderBlServ;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Administrator on 2016/12/9.
 */
public class OrderHelper {
    public static OrderPO toOrderPO(OrderVO orderVO) {
        String id = null;
        String userId = orderVO.getUser().getId();
        String userName = orderVO.getUser().getName();
        String contact = orderVO.getUser().getContact();
        String hotel = orderVO.getHotel().getHotelName();
        String type = orderVO.getState().toString();
        String inTime = orderVO.getInTime().toString();
        String outTime = orderVO.getOutTime().toString();
        String execTime = orderVO.getExecTime().toString();
        double total = orderVO.getTotal();
        boolean children = orderVO.isChildren();
        int person = orderVO.getPerson();
        OrderPO orderPO = new OrderPO(id,userId,hotel,userName,contact,type,inTime,outTime,execTime,total,children,person);
        return orderPO;
    }

    /**
     * 好了
     * @param orderPO
     * @return
     */
    public static OrderVO toOrderVO(OrderPO orderPO) {
        String orderId = orderPO.getID();
        String userId = orderPO.getUserID();
        String userName = orderPO.getUserName();
        String userContact = orderPO.getUserContact();
        UserInOrder user = new UserInOrder(userId, userName, userContact);
        String hotelName = orderPO.getHotel();
        ArrayList<String> rooms = orderPO.getRoom();
        ArrayList<Integer> roomNums = orderPO.getNum();
        ArrayList<Double> prices = orderPO.getPrice();
        ArrayList<Double> subTotals = orderPO.getSubtotel();
        ArrayList<RoomInOrder> roomInOrders = new ArrayList<>();
        for(int i = 0;i<rooms.size();i++) {
            RoomInOrder roomInOrder = ParseHelper.stringToRoom(rooms.get(i),roomNums.get(i),prices.get(i),subTotals.get(i));
            roomInOrders.add(roomInOrder);
        }
        HotelInOrder hotel = new HotelInOrder(hotelName, roomInOrders);
        OrderState state = ParseHelper.stringToOrderState(orderPO.getType());
        boolean children = orderPO.getChildren();
        Date inTime = ParseHelper.stringToDate(orderPO.getInTime());
        Date outTime = ParseHelper.stringToDate(orderPO.getOutTime());
        Date execTime = ParseHelper.stringToDate(orderPO.getLastTime());
        try {
            Date sysTime = RemoteHelper.getInstance().getTimeServ().getTime();
            if (state == OrderState.WAITING && execTime.compareTo(sysTime) == 1) {
                state = OrderState.ABNORMAL;
                orderPO.setType("ABNORMAL");
                OrderBlServ.getInstance().modifyOrderState(orderId,UserOrderAction.EXCEPTION);
            }
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        double total = orderPO.getTotel();
        int person = orderPO.getPersonNum(); // 去那边改
        OrderVO orderVO = new OrderVO(orderId,user, hotel, state, children, inTime, outTime, execTime, person,total);
        return orderVO;
    }
}

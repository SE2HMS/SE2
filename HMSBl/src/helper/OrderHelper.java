package helper;

import PO.OrderPO;
import VO.*;

import java.util.ArrayList;

import static helper.ParseHelper.stringToDate;

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
        OrderPO orderPO = new OrderPO(id,userId,hotel,userName,contact,type,inTime,outTime,execTime,total);
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
        boolean children = false;
        String inTime = orderPO.getInTime();
        String outTime = orderPO.getOutTime();
        String execTime = orderPO.getLastTime();
        double total = orderPO.getTotel();
        OrderVO orderVO = new OrderVO(orderId,user, hotel, state, children, stringToDate(inTime), stringToDate(outTime), stringToDate(execTime), total);
        return orderVO;
    }
}

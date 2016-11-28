package order_bl_servlmpl;

import PO.OrderPO;
import PO.RoomPO;
import VO.*;
import order_bl_serv.OrderBlServ;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class OrderBlServImpl implements OrderBlServ {

    @Override
    public OrderVO getOrderInfo(String id) {
        OrderPO orderPO = null;
        try {
            orderPO = RemoteHelper.getInstance().getOrderDataServ().getOrder(id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (orderPO == null) {
            return null;
        }
        String userId = orderPO.getUserID();
        String userName = orderPO.getUserName();
        String userContact = orderPO.getUserContact();
        UserInOrder user = new UserInOrder(userId, userName, userContact);
        String hotelName = orderPO.getHotel();
        ArrayList<String> rooms = orderPO.getRoom();
        HotelInOrder hotel = new HotelInOrder(hotelName, stringToRoom(rooms));
        String state = orderPO.getType();
        boolean children = false;
        String inTime = orderPO.getInTime();
        String outTime = orderPO.getOutTime();
        String execTime = orderPO.getLastTime();
        int total = (int) orderPO.getTotel();
        OrderVO orderVO = new OrderVO(user, hotel, stringToState(state), children, stringToDate(inTime), stringToDate(outTime), stringToDate(execTime), total);
        return orderVO;
    }

    @Override
    public Iterator<OrderVO> getOrderList(String userId) {
        //等待一个接口的实现
        return null;
    }

    /**
     * 还没写
     *
     * @param rooms
     * @return
     */
    private ArrayList<RoomInOrder> stringToRoom(ArrayList<String> rooms) {
        return null;
    }

    /**
     * 还没写
     *
     * @param state
     * @return
     */
    private OrderState stringToState(String state) {
        return null;
    }

    /**
     * 还没写
     *
     * @param date
     * @return
     */
    private Date stringToDate(String date) {
        return null;
    }
}

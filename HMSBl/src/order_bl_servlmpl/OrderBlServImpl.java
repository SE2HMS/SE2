package order_bl_servlmpl;

import PO.OrderPO;
import PO.RoomPO;
import VO.*;
import order_bl_serv.OrderBlServ;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
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
     * 写好了，把字符串变成房间
     * 这里的房间值RoomInOrder
     * 包含类型和数量两个属性
     * 不过目前默认数量都是一
     *
     * @param rooms
     * @return
     */
    private ArrayList<RoomInOrder> stringToRoom(ArrayList<String> rooms) {
        ArrayList<RoomInOrder> roomInOrders = new ArrayList<>();
        for(String room:rooms) {
            RoomInOrder roomInOrder = new RoomInOrder(room,1);
            roomInOrders.add(roomInOrder);
        }
        return roomInOrders;
    }

    /**
     * 写好了，用来把字符串转成OrderState
     *
     * @param state
     * @return
     */
    private OrderState stringToState(String state) {
        OrderState orderState = null;
        switch (state) {
            case "normal":
                orderState = OrderState.normal;
                break;
            case "abnormal":
                orderState = OrderState.abnormal;
                break;
            case "revoke":
                orderState = OrderState.revoke;
                break;
        }
        return orderState;
    }

    /**
     * 用来把String转成Date
     * 此处的String是直接由Date的toString方法得到的
     *
     * @param date
     * @return
     */
    private Date stringToDate(String date) {
        String[] splitString = date.split(" ");
        String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        int month = 0;
        for(int i = 0;i<months.length;i++) {
            if(splitString[1].equals(months[i])) {
                month = i;
                break;
            }
        }
        int day = Integer.parseInt(splitString[2]);
        int year = Integer.parseInt(splitString[5]);
        String[] splitTimeInDay = splitString[3].split(":");
        int hour = Integer.parseInt(splitTimeInDay[0]);
        int min = Integer.parseInt(splitTimeInDay[1]);
        int sec = Integer.parseInt(splitTimeInDay[2]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day,hour,min,sec);
        return calendar.getTime();
    }
}

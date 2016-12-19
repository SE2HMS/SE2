package order_bl_servlmpl;

import PO.OrderPO;
import VO.*;
import credit_bl_serv.CreditBlServ;
import helper.ParseHelper;
import order_bl_serv.OrderBlServ;
import rmi.RemoteHelper;
import room_bl_serv.RoomBlServ;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * 12.3更新
 */
public class OrderBlServImpl implements OrderBlServ {

    @Override
    public boolean modifyOrderState(String orderId, UserOrderAction action) {
        if(orderId == null || action == null) {
            return false;
        }
        OrderPO orderPO;
        boolean success = false;
        try {
            orderPO = RemoteHelper.getInstance().getOrderDataServ().getOrder(orderId);
            if(action.equals(UserOrderAction.CHECK_IN) || action.equals(UserOrderAction.CHECK_OUT) || action.equals(UserOrderAction.DELAY)) {
                orderPO.setType("FINISH");
            }else if(action.equals(UserOrderAction.EXCEPTION)) {
                orderPO.setType("EXCEPTION");
            }else if(action.equals(UserOrderAction.REVOKE)) {
                orderPO.setType("REVOKE");
            }
            success = RemoteHelper.getInstance().getOrderDataServ().modifiedOrder(orderPO);
            OrderVO orderVO = ParseHelper.toOrderVO(orderPO);
            CreditBlServ.getInstance().changeCredit(orderVO,action);
            RoomBlServ.getInstance().changeRoomNum(orderVO,action);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return success;
    }

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
        return ParseHelper.toOrderVO(orderPO);
    }

    @Override
    public Iterator<OrderVO> getNotInOrderList(String userId) {
        return this.getOrderByState(this.getOrderList(userId),OrderState.WAITING);
    }

    private Iterator<OrderVO> getOrderByState(Iterator<OrderVO> orderVOIterator,OrderState state) {
        ArrayList<OrderVO> orderVOs = new ArrayList<>();
        while(orderVOIterator.hasNext()) {
            OrderVO orderVO = orderVOIterator.next();
            if(orderVO.getState().equals(state)) {
                orderVOs.add(orderVO);
            }
        }
        return orderVOs.iterator();
    }

    @Override
    public Iterator<OrderVO> getAbnormalOrderList(String userId) {
        return this.getOrderByState(this.getOrderList(userId),OrderState.ABNORMAL);
    }

    @Override
    public Iterator<OrderVO> getRevokeOrderList(String userId) {
        return this.getOrderByState(this.getOrderList(userId),OrderState.REVOKE);
    }

    @Override
    public Iterator<OrderVO> getFinishOrderList(String userId) {
        return this.getOrderByState(this.getOrderList(userId),OrderState.FINISH);
    }

    @Override
    public Iterator<OrderVO> getOrderList(String userId) {
        ArrayList<OrderPO> orderPOs = null;
        ArrayList<OrderVO> orderVOs = new ArrayList<>();
        try {
            orderPOs = RemoteHelper.getInstance().getOrderDataServ().getOrders(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (orderPOs == null) {
            return null;
        } else {
            orderPOs.forEach(orderPO -> orderVOs.add(ParseHelper.toOrderVO(orderPO)));
        }
        return orderVOs.iterator();
    }

    public OrderVO getLatestOrder(String userId) {
        Iterator<OrderVO> orderVOIterator = this.getOrderList(userId);
        OrderVO latest = null;
        while (orderVOIterator.hasNext()) {
            OrderVO orderVO = orderVOIterator.next();
            if (latest == null) {
                latest = orderVO;
            } else if (Integer.parseInt(orderVO.getId()) > Integer.parseInt(latest.getId())) {
                latest = orderVO;
            }
        }
        return latest;
    }

    @Override
    public Iterator<OrderVO> getAllOrderList() {
        ArrayList<OrderVO> orderVOs = new ArrayList<>();
        try {
            ArrayList<OrderPO> orderPOs = RemoteHelper.getInstance().getOrderDataServ().getAllOrders();
            orderPOs.forEach(orderPO -> orderVOs.add(ParseHelper.toOrderVO(orderPO)));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return orderVOs.iterator();
    }

    @Override
    public boolean revokeOrder(String orderId) {
        return this.modifyOrderState(orderId,UserOrderAction.REVOKE);
    }

    @Override
    public Iterator<OrderVO> getAllNotInOrderList() {
        return this.getOrderByState(this.getAllOrderList(),OrderState.WAITING);
    }

    @Override
    public Iterator<OrderVO> getAllAbnormalOrderList() {
        return this.getOrderByState(this.getAllOrderList(),OrderState.ABNORMAL);
    }

    @Override
    public Iterator<OrderVO> getAllRevokeOrderList() {
        return this.getOrderByState(this.getAllOrderList(),OrderState.REVOKE);
    }

    @Override
    public Iterator<OrderVO> getAllFinishOrderList() {
        return this.getOrderByState(this.getAllOrderList(),OrderState.FINISH);
    }

    @Override
    public Iterator<OrderVO> getHotelOrderList(String hotelName) {
        if (hotelName == null) {
            return null;
        }
        Iterator<OrderVO> orderVOIterator = this.getAllOrderList();
        ArrayList<OrderVO> orderVOs = new ArrayList<>();
        while(orderVOIterator.hasNext()) {
            OrderVO orderVO = orderVOIterator.next();
            if(orderVO.getHotel().getHotelName().equals(hotelName)) {
                orderVOs.add(orderVO);
            }
        }
        return orderVOs.iterator();
    }

    @Override
    public Iterator<OrderVO> getHotelNotInOrderList(String hotelName) {
        return this.getOrderByState(this.getHotelOrderList(hotelName),OrderState.WAITING);
    }

    @Override
    public Iterator<OrderVO> getHotelFinishOrderList(String hotelName) {
        return this.getOrderByState(this.getHotelOrderList(hotelName),OrderState.FINISH);
    }

    @Override
    public Iterator<OrderVO> getHotelAbnomalOrderList(String hotelName) {
        return this.getOrderByState(this.getHotelOrderList(hotelName),OrderState.ABNORMAL);
    }

    @Override
    public Iterator<OrderVO> getHotelRevokeOrderList(String hotelName) {
        return this.getOrderByState(this.getHotelOrderList(hotelName),OrderState.REVOKE);
    }

    @Override
    public boolean checkIn(String orderId) {
        return this.modifyOrderState(orderId,UserOrderAction.CHECK_IN);
    }

    @Override
    public boolean checkOut(String orderId) {
        OrderVO orderVO = this.getOrderInfo(orderId);
        OrderPO orderPO = ParseHelper.toOrderPO(orderVO);
        try {
            Date time = RemoteHelper.getInstance().getTimeServ().getTime();
            orderPO.setLastTime(ParseHelper.dateToString(time));
            RemoteHelper.getInstance().getOrderDataServ().modifiedOrder(orderPO);
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        return this.modifyOrderState(orderId,UserOrderAction.CHECK_OUT);
    }

    @Override
    public boolean delayCheckIn(String orderId) {
        return this.modifyOrderState(orderId,UserOrderAction.DELAY);
    }

    @Override
    public boolean revokeExceptionOrder(String orderId, boolean all) {
        if(all) {
            return this.modifyOrderState(orderId, UserOrderAction.REVOKE_ALL);
        }else {
            return this.modifyOrderState(orderId,UserOrderAction.REVOKE_HALF);
        }
    }
}

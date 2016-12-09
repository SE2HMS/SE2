package order_bl_servlmpl;

import PO.OrderPO;
import VO.*;
import helper.ParseHelper;
import order_bl_serv.OrderBlServ;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 12.3更新
 */
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
        return ParseHelper.toOrderVO(orderPO);
    }

    @Override
    public Iterator<OrderVO> getNotInOrderList(String userId) {
        return this.getOrderByState(OrderState.WAITING,userId);
    }

    private Iterator<OrderVO> getOrderByState(OrderState state,String userId) {
        Iterator<OrderVO> orderVOIterator = this.getOrderList(userId);
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
        return this.getOrderByState(OrderState.ABNORMAL,userId);
    }

    @Override
    public Iterator<OrderVO> getRevokeOrderList(String userId) {
        return this.getOrderByState(OrderState.REVOKE,userId);
    }

    @Override
    public Iterator<OrderVO> getFinishOrderList(String userId) {
        return this.getOrderByState(OrderState.FINISH,userId);
    }

    @Override
    public Iterator<OrderVO> getOrderList(String userId) {
        ArrayList<OrderPO> orderPOs = null;
        ArrayList<OrderVO> orderVOs = new ArrayList<>();
        try {
            orderPOs = RemoteHelper.getInstance().getOrderDataServ().getOrders(userId);
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        if(orderPOs == null) {
            return null;
        }else {
            orderPOs.forEach(orderPO -> orderVOs.add(ParseHelper.toOrderVO(orderPO)));
        }
        return orderVOs.iterator();
    }

}

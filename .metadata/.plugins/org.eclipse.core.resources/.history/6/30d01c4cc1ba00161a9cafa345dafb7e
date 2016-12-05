package order_bl_servlmpl;

import PO.OrderPO;
import VO.*;
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

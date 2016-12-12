package order_bl_servlmpl;

import PO.OrderPO;
import VO.*;
import credit_bl_serv.CreditBlServ;
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
    //加上修改信用
    public boolean modifyOrderState(String orderId,OrderState state) {
        OrderPO orderPO = null;
        boolean success = false;
        try {
            orderPO  =RemoteHelper.getInstance().getOrderDataServ().getOrder(orderId);
            orderPO.setType(state.toString());
            success = RemoteHelper.getInstance().getOrderDataServ().modifiedOrder(orderPO);
            OrderVO orderVO = ParseHelper.toOrderVO(orderPO);
            CreditBlServ.getInstance().changeCredit(orderVO);
        }catch (RemoteException e) {
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

    public OrderVO getLatestOrder(String userId) {
        Iterator<OrderVO> orderVOIterator = this.getOrderList(userId);
        OrderVO latest = null;
        while(orderVOIterator.hasNext()) {
            OrderVO orderVO = orderVOIterator.next();
            if(latest == null) {
                latest = orderVO;
            }else if( Integer.parseInt(orderVO.getId()) > Integer.parseInt(latest.getId())) {
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
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        return orderVOs.iterator();
    }

    private Iterator<OrderVO> getStateAllOrderList(OrderState state) {
        Iterator<OrderVO> orderVOIterator = this.getAllOrderList();
        ArrayList<OrderVO> orderVOs = new ArrayList<>();
        orderVOIterator.forEachRemaining(orderVO -> {
            if(orderVO.getState().equals(state))
                orderVOs.add(orderVO);
        });
        return orderVOs.iterator();
    }

    @Override
    public Iterator<OrderVO> getAllNotInOrderList() {
        return this.getStateAllOrderList(OrderState.WAITING);
    }

    @Override
    public Iterator<OrderVO> getAllAbnormalOrderList() {
        return this.getStateAllOrderList(OrderState.ABNORMAL);
    }

    @Override
    public Iterator<OrderVO> getAllRevokeOrderList() {
        return this.getStateAllOrderList(OrderState.REVOKE);
    }

    @Override
    public Iterator<OrderVO> getAllFinishOrderList() {
        return this.getStateAllOrderList(OrderState.FINISH);
    }
}

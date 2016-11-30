package book_bl_servImpl;

import PO.OrderPO;
import VO.OrderVO;
import book_bl_serv.BookBlServ;
import helper.ParseHelper;
import rmi.RemoteHelper;

/**
 * Created by Administrator on 2016/11/30.
 */
public class BookBlServImpl implements BookBlServ{
    @Override
    public boolean produceOrder(OrderVO orderVO) {
        boolean success = false;
        try{
            OrderPO orderPO = ParseHelper.toOrderPO(orderVO);
            success = RemoteHelper.getInstance().getOrderDataServ().insertOrder(orderPO);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
}

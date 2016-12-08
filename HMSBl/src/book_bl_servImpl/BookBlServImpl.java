package book_bl_servImpl;

import PO.HotelPO;
import PO.OrderPO;
import VO.HotelVO;
import VO.OrderVO;
import VO.RoomAndNum;
import VO.RoomVO;
import book_bl_serv.BookBlServ;
import helper.ParseHelper;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/11/30.
 */
public class BookBlServImpl implements BookBlServ{

    @Override
    public Iterator<RoomAndNum> getNumAvailable(String hotelName, int inTime, int outTime) {
        ArrayList<RoomAndNum> roomAndNums = new ArrayList<>();
        HotelPO hotelPO = null;
        try {
            RemoteHelper.getInstance().getHotelDataServ().getHotel(hotelName);
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        if(hotelPO == null) {
            return null;
        }
        HotelVO hotelVO = ParseHelper.toHotelVO(hotelPO);
        Iterator<RoomVO> roomVOIterator = hotelVO.getRooms();
        while(roomVOIterator.hasNext()) {
            RoomVO roomVO = roomVOIterator.next();
            int available[] = roomVO.getAvailable();
            int min = 0;
            for(int i = inTime;i<outTime;i++) {
                if(min == 0) {
                    min = available[i];
                }else {
                    min = Math.min(available[i],min);
                }
            }
            roomAndNums.add(new RoomAndNum(roomVO.getType(),min));
        }
        return roomAndNums.iterator();
    }

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

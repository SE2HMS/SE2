package hotel_bl_servlmpl;

import PO.HotelPO;
import PO.HotelStrategyPO;
import VO.*;
import helper.ParseHelper;
import hotel_bl_serv.HotelBlServ;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 12.3更新
 */
public class HotelBlServImpl implements HotelBlServ {

    @Override
    public HotelVO getHotelInfo(String name) {
        HotelPO hotelPO = null;
        try {
            hotelPO = RemoteHelper.getInstance().getHotelDataServ().getHotel(name);
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        if(hotelPO == null) {
            return null;
        }
        return ParseHelper.toHotelVO(hotelPO);
    }

    @Override
    public boolean modifyHotelInfo(HotelVO hotel) {
        HotelPO hotelPO = ParseHelper.toHotelPO(hotel);
        boolean success = false;
        try {
            success = RemoteHelper.getInstance().getHotelDataServ().modifiedHotel(hotelPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public Iterator<StrategyVO> getStrategyList(String hotelName) {
        HotelPO hotelPO;
        ArrayList<StrategyVO> strategyVOs = new ArrayList<>();
        try {
            hotelPO = RemoteHelper.getInstance().getHotelDataServ().getHotel(hotelName);
            ArrayList<HotelStrategyPO> hotelStrategyPOs = hotelPO.getStrategy();
            hotelStrategyPOs.forEach(hotelStrategyPO -> strategyVOs.add(ParseHelper.toStrategyVO(hotelStrategyPO)));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return strategyVOs.iterator();
    }

    @Override
    public Iterator<HotelVO> getAllHotel(String businessCircle) {
        ArrayList<HotelPO> hotelPOs;
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        try {
            hotelPOs = RemoteHelper.getInstance().getHotelDataServ().getHotelList(businessCircle);
            hotelPOs.forEach(hotelPO -> hotelVOs.add(ParseHelper.toHotelVO(hotelPO)));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return hotelVOs.iterator();
    }

}

package hotel_bl_servlmpl;

import PO.HotelPO;
import PO.HotelStrategyPO;
import VO.*;
import helper.ParseHelper;
import hotel_bl_serv.HotelBlServ;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * 12.6更新
 */
public class HotelBlServImpl implements HotelBlServ {

    @Override
    public Iterator<HotelVO> search(String location, String businesscircle, String name, boolean haveOrdered,String roomType, double minPrice, double maxPrice, int roomNum, Date inTime, Date outTime, int starLevel, double minComment, double maxComment) {
        if(location == null||businesscircle == null) {
            return null;
        }
        ArrayList<HotelPO> hotelPOs = null;
        try {
            hotelPOs = RemoteHelper.getInstance().getHotelDataServ().getHotelList(businesscircle);
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        hotelPOs.forEach(hotelPO -> hotelVOs.add(ParseHelper.toHotelVO(hotelPO)));
        for(HotelVO hotelVO:hotelVOs) {
            if(!hotelVO.getLocation().equals(location)) {
                hotelVOs.remove(hotelVO);
                break;
            }
            if(name != null && !hotelVO.getName().matches("*" + name + "*")) {
                hotelVOs.remove(hotelVO);
                break;
            }
            if(roomType != null) {
                boolean haveType = false;
                Iterator<RoomVO> roomVOIterator = hotelVO.getRooms();
                while(roomVOIterator.hasNext()) {
                    RoomVO roomVO = roomVOIterator.next();
                    if(roomVO.getType().equals(roomType)) {
                        haveType = true;
                        break;
                    }
                }
                if(!haveType) {
                    hotelVOs.remove(hotelVO);
                }
            }
//            if()
        }
        return hotelVOs.iterator();
    }

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
    public Iterator<HotelVO> search(String name, double starLevel, double commentLevel, String businessCircle) {
        ArrayList<HotelPO> hotelPOs = null;
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        try {
            if(businessCircle == null) {
                hotelPOs = RemoteHelper.getInstance().getHotelDataServ().getAllHotel();
            }else {
                hotelPOs = RemoteHelper.getInstance().getHotelDataServ().getHotelList(businessCircle);
            }
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        for(HotelPO hotelPO:hotelPOs) {
            if(starLevel > 0&&hotelPO.getStars() < starLevel) {
                break;
            }
            if(commentLevel > 0/*&&hotelPO.getComment().getCommentLevel() < commentLevel*/) {
                break;
            }
            if(name != null || !hotelPO.getName().matches("*" + name + "")) {
                break;
            }
            hotelVOs.add(ParseHelper.toHotelVO(hotelPO));
        }
        hotelVOs.sort((o1,o2) -> {
            if(o1.getStarLevel() > o2.getStarLevel()) {
                return 1;
            }else {
                return -1;
            }
        });
        return hotelVOs.iterator();
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

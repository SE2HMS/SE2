package hotel_bl_servlmpl;

import PO.HotelPO;
import PO.HotelStrategyPO;
import VO.*;
import helper.ParseHelper;
import hotel_bl_serv.HotelBlServ;
import order_bl_serv.OrderBlServ;
import order_bl_servlmpl.OrderBlServImpl;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 12.6更新
 */
public class HotelBlServImpl implements HotelBlServ {

    private String[] locations = new String[]{"南京"};
    private String[][] businessCIrcles = new String[][]{{"仙林大学城","新街口"},{},{}};
    private String[] roomTypes = new String[]{"单人间","双人间"};
    private String userId;

    public HotelBlServImpl(String userId) {
        this.userId = userId;
    }

    private String getLocationString(int i) {
        return locations[i];
    }

    private String getBusinessCircle(int l,int b) {
        return businessCIrcles[l][b];
    }

    private String getRoomTypeString(int i) {
        return roomTypes[i];
    }

    @Override
    public Iterator<HotelVO> search(int location, int businesscircle, String name, boolean haveOrdered,int roomType, double minPrice, double maxPrice, int roomNum, int inTime, int outTime, int starLevel, double minComment, double maxComment) {
        ArrayList<HotelPO> hotelPOs = null;
        try {
            hotelPOs = RemoteHelper.getInstance().getHotelDataServ().getAllHotel();
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        hotelPOs.forEach(hotelPO -> hotelVOs.add(ParseHelper.toHotelVO(hotelPO)));
        for(HotelVO hotelVO:hotelVOs) {
            if(!hotelVO.getLocation().equals(getLocationString(location))) {
                hotelVOs.remove(hotelVO);
                break;
            }
            if(!hotelVO.getCBD().equals(getBusinessCircle(location,businesscircle))) {
                hotelVOs.remove(hotelVO);
                break;
            }
            if(name != null && !hotelVO.getName().matches("*" + name + "*")) {
                hotelVOs.remove(hotelVO);
                break;
            }
            if(haveOrdered) {
                boolean orderOK = false;
                Iterator<OrderVO> orders = new OrderBlServImpl().getOrderList(userId);
                while(orders.hasNext()) {
                    OrderVO orderVO = orders.next();
                    if(orderVO.getHotel().getHotelName().equals(hotelVO.getName())) {
                        orderOK = true;
                    }
                }
                if(!orderOK) {
                    hotelVOs.remove(hotelVO);
                    break;
                }
            }
            Iterator<RoomVO> roomVOIterator = hotelVO.getRooms();
            boolean roomIsOK = false;
            while(roomVOIterator.hasNext()) {
                boolean[] check = new boolean[4];
                for(boolean b:check) {
                    b = false;
                }
                RoomVO roomVO = roomVOIterator.next();
                if(roomType > 0 && roomVO.getType().equals(getRoomTypeString(roomType))) {
                    check[0] = true;
                }
                if(minPrice > 0 && roomVO.getPrice() >= minPrice) {
                    check[1] = true;
                }
                if(maxPrice > 0 && roomVO.getPrice() <= maxPrice) {
                    check[2] = true;
                }
                int[] available = roomVO.getAvailable();
                if(roomNum > 0) {
                    boolean enough = false;
                    if(inTime == 0 && outTime == 0) {
                        for(int num:available) {
                            if(num >= roomNum) {
                                enough = true;
                            }
                        }
                    }else if(inTime == 0) {
                        enough = true;
                        for(int i = 0;i<outTime;i++) {
                            if(available[i] < roomNum) {
                                enough = false;
                            }
                        }
                    }else if(outTime == 0) {
                        if(available[inTime - 1] >= roomNum) {
                            enough = true;
                        }
                    }
                    check[3] = enough;
                }
                if(check[0] && check[1] && check[2] && check[3]) {
                    roomIsOK = true;
                }
            }
            if(!roomIsOK) {
                hotelVOs.remove(hotelVO);
                break;
            }
            if(starLevel > 0 && hotelVO.getStarLevel() < starLevel) {
                hotelVOs.remove(hotelVO);
                break;
            }
            boolean commentIsOK = true;
            double totalLv = 0;
            int num = 0;
            double commentLv = 0;
            Iterator<CommentVO> commentVOIterator = hotelVO.getComments();
            while(commentVOIterator.hasNext()) {
                CommentVO commentVO = commentVOIterator.next();
                totalLv += commentVO.getLevel();
                num ++;
            }
            commentLv = totalLv/num;
            if(minComment > 0 && commentLv < minComment) {
                commentIsOK = false;
            }
            if(maxComment > 0 && commentLv > maxComment) {
                commentIsOK = false;
            }
            if(!commentIsOK) {
                hotelVOs.remove(hotelVO);
                break;
            }
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

    /**
     * @deprecated 没用了
     * @param name
     * @param starLevel
     * @param commentLevel
     * @param businessCircle
     * @return
     */
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

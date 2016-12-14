package hotel_bl_servlmpl;

import PO.HotelPO;
import PO.HotelStrategyPO;
import VO.*;
import comment_bl_serv.CommentBlServ;
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

    private String[] locations = new String[]{"NANJING"};
    private String[][] businessCIrcles = new String[][]{{"XIANLIN","XINJIEKOU"},{},{}};
    private String[] roomTypes = new String[]{"SINGLE","DOUBLE"};

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
    public Iterator<HotelVO> search(String userId,int location, int businesscircle, String name, boolean haveOrdered, int roomType, int price, int roomNum, int inTime, int outTime, int starLevel, int commentLevel) {
        double minPrice = price > 1?(price-1)*100:0;
        double maxPrice = price < 4?price*100:0;
        double minComment = commentLevel;
        double maxComment = commentLevel%4 == 0?0:(commentLevel+1);
        return this.search(userId,location,businesscircle,name,haveOrdered,roomType,minPrice,maxPrice,roomNum,inTime,outTime,starLevel,minComment,maxComment);
    }

    private Iterator<HotelVO> matchLocation(Iterator<HotelVO> hotelVOIterator,String location) {
        if(hotelVOIterator == null) {
            return null;
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        while(hotelVOIterator.hasNext()) {
            HotelVO hotelVO = hotelVOIterator.next();
            if(hotelVO.getLocation().equals(location)) {
                hotelVOs.add(hotelVO);
            }
        }
        return hotelVOs.iterator();
    }

    private Iterator<HotelVO> matchBusinessCircle(Iterator<HotelVO> hotelVOIterator,String businessCircle) {
        if(hotelVOIterator == null) {
            return null;
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        while(hotelVOIterator.hasNext()) {
            HotelVO hotelVO = hotelVOIterator.next();
            if(hotelVO.getCBD().equals(businessCircle)) {
                hotelVOs.add(hotelVO);
            }
        }
        return hotelVOs.iterator();
    }

    private Iterator<HotelVO> matchRoomType(Iterator<HotelVO> hotelVOIterator,String roomType) {
        if(hotelVOIterator == null) {
            return null;
        }else if(roomType == null) {
            return hotelVOIterator;
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        while(hotelVOIterator.hasNext()) {
            HotelVO hotelVO = hotelVOIterator.next();
            boolean roomIsOk = false;
            while(hotelVO.getRooms().hasNext()) {
                RoomVO roomVO = hotelVO.getRooms().next();
                if(roomVO.getType().equals(roomType)) {
                    roomIsOk = true;
                    break;
                }
            }
            if(roomIsOk) {
                hotelVOs.add(hotelVO);
            }
        }
        return hotelVOs.iterator();
    }

    private Iterator<HotelVO> matchPrice(Iterator<HotelVO> hotelVOIterator,double min,double max) {
        if(hotelVOIterator == null) {
            return null;
        }else if(min == 0&& max == 0) {
            return hotelVOIterator;
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        while(hotelVOIterator.hasNext()) {
            HotelVO hotelVO = hotelVOIterator.next();
            boolean priceIsOk = false;
            while(hotelVO.getRooms().hasNext()) {
                RoomVO roomVO = hotelVO.getRooms().next();
                if(min == 0 && roomVO.getPrice() <= max) {
                    priceIsOk = true;
                }else if(max == 0 && roomVO.getPrice() >= min) {
                    priceIsOk = true;
                }else if(roomVO.getPrice() >= min && roomVO.getPrice() <= max){
                    priceIsOk = true;
                }
            }
            if(priceIsOk) {
                hotelVOs.add(hotelVO);
            }
        }
        return hotelVOs.iterator();
    }

    private Iterator<HotelVO> matchComment(Iterator<HotelVO> hotelVOIterator,double min,double max) {
        if(hotelVOIterator == null) {
            return null;
        }else if(min == 0 && max == 0) {
            return hotelVOIterator;
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        while(hotelVOIterator.hasNext()) {
            HotelVO hotelVO = hotelVOIterator.next();
            boolean commentIsOk = false;
            double total = 0;
            int num = 0;
            while(hotelVO.getComments().hasNext()) {
                CommentVO commentVO = hotelVO.getComments().next();
                total += commentVO.getLevel();
                num++;
            }
            double commentLevel = total / num;
            if(min == 0 && commentLevel <= max) {
                commentIsOk = true;
            }else if(max == 0 && commentLevel >= min) {
                commentIsOk = true;
            }
            if(commentIsOk) {
                hotelVOs.add(hotelVO);
            }
        }
        return hotelVOs.iterator();
    }

    private Iterator<HotelVO> matchName(Iterator<HotelVO> hotelVOIterator,String name) {
        if(hotelVOIterator == null) {
            return null;
        }else if(name == null) {
            return hotelVOIterator;
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        while(hotelVOIterator.hasNext()) {
            HotelVO hotelVO = hotelVOIterator.next();
            if(hotelVO.getName().matches("*" + name + "*")) {
                hotelVOs.add(hotelVO);
            }
        }
        return hotelVOs.iterator();
    }

    private Iterator<HotelVO> matchOrdered(Iterator<HotelVO> hotelVOIterator,boolean ordered,String userId) {
        if(hotelVOIterator == null) {
            return null;
        }else if(ordered == false) {
            return hotelVOIterator;
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        while(hotelVOIterator.hasNext()) {
            HotelVO hotelVO = hotelVOIterator.next();
            boolean orderedIsOk = false;
            Iterator<OrderVO> orderList = OrderBlServ.getInstance().getOrderList(userId);
            while(orderList.hasNext()) {
                OrderVO orderVO = orderList.next();
                if(orderVO.getHotel().equals(hotelVO.getName())) {
                    orderedIsOk = true;
                    break;
                }
            }
            if(orderedIsOk) {
                hotelVOs.add(hotelVO);
            }
        }
        return hotelVOs.iterator();
    }

    private Iterator<HotelVO> matchRoomNum(Iterator<HotelVO> hotelVOIterator,int num,int inTime,int outTime) {
        if(hotelVOIterator == null) {
            return null;
        }else if(num == 0) {
            return hotelVOIterator;
        }else if(inTime == 0 && outTime == 0) {
            return hotelVOIterator;
        }else if(inTime > outTime) {
            return null;
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        while(hotelVOIterator.hasNext()) {
            HotelVO hotelVO = hotelVOIterator.next();
            Iterator<RoomVO> rooms = hotelVO.getRooms();
            boolean roomNumIsOk = false;
            while(rooms.hasNext()) {
                RoomVO roomVO = rooms.next();
                int[] avail = roomVO.getAvailable();
                if(outTime == 0) {
                    roomNumIsOk = avail[inTime] >= num;
                }else {
                    boolean ok = true;
                    for(int i = inTime;i < outTime;i++) {
                        ok = avail[i] < num?false:ok;
                    }
                    roomNumIsOk = ok?true:roomNumIsOk;
                }
            }
            if(roomNumIsOk) {
                hotelVOs.add(hotelVO);
            }
        }
        return hotelVOs.iterator();
    }

    private Iterator<HotelVO> matchStar(Iterator<HotelVO> hotelVOIterator,double star) {
        if(hotelVOIterator == null) {
            return null;
        }else if(star == 0) {
            return hotelVOIterator;
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        while(hotelVOIterator.hasNext()) {
            HotelVO hotelVO = hotelVOIterator.next();
            if(hotelVO.getStarLevel() >= star) {
                hotelVOs.add(hotelVO);
            }
        }
        return hotelVOs.iterator();
    }

    private Iterator<HotelVO> search(String userId,int location, int businesscircle, String name, boolean haveOrdered, int roomType, double minPrice, double maxPrice, int roomNum, int inTime, int outTime, int starLevel, double minComment, double maxComment) {
        ArrayList<HotelPO> hotelPOs = null;
        try {
            hotelPOs = RemoteHelper.getInstance().getHotelDataServ().getAllHotel();
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        hotelPOs.forEach(hotelPO -> hotelVOs.add(ParseHelper.toHotelVO(hotelPO)));
        Iterator<HotelVO> hotelVOIterator = hotelVOs.iterator();
        hotelVOIterator = this.matchLocation(hotelVOIterator,this.getLocationString(location));
        hotelVOIterator = this.matchBusinessCircle(hotelVOIterator,this.getBusinessCircle(location,businesscircle));
        hotelVOIterator = this.matchRoomType(hotelVOIterator,this.getRoomTypeString(roomType));
        hotelVOIterator = this.matchPrice(hotelVOIterator,minPrice,maxPrice);
        hotelVOIterator = this.matchComment(hotelVOIterator,minComment,maxComment);
        hotelVOIterator = this.matchName(hotelVOIterator,name);
        hotelVOIterator = this.matchOrdered(hotelVOIterator,haveOrdered,userId);
        hotelVOIterator = this.matchRoomNum(hotelVOIterator,roomNum,inTime,outTime);
        hotelVOIterator = this.matchStar(hotelVOIterator,starLevel);
        return hotelVOIterator;
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
    public Iterator<OrderVO> getOrderInHotel(String userId, String hotelName) {
        Iterator<OrderVO> orderVOIterator = OrderBlServ.getInstance().getOrderList(userId);
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

    @Override
    public boolean addHotel(String hotelName, int businessCIrcle, int location, String intro,double star) {
        String lc = this.getLocationString(location);
        String bc = this.getBusinessCircle(location,businessCIrcle);
        HotelPO hotelPO = new HotelPO(hotelName,bc,intro,lc,star);
        try {
            RemoteHelper.getInstance().getHotelDataServ().insertHotel(hotelPO);
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }
}

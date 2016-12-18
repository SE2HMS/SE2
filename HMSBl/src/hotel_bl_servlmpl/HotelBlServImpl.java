package hotel_bl_servlmpl;

import PO.HotelPO;
import PO.HotelStrategyPO;
import VO.*;
import helper.ParseHelper;
import hotel_bl_serv.HotelBlServ;
import order_bl_serv.OrderBlServ;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 12.18更新
 */
public class HotelBlServImpl implements HotelBlServ {

    private String[] locations = new String[]{"NANJING"};
    private String[][] businessCIrcles = new String[][]{{"XIANLIN", "XINJIEKOU"}, {}, {}};
    private String[] roomTypes = new String[]{"", "SINGLE", "DOUBLE"};

    private String getLocationString(int i) {
        return locations[i];
    }

    private String getBusinessCircle(int l, int b) {
        return businessCIrcles[l][b];
    }

    private String getRoomTypeString(int i) {
        return roomTypes[i];
    }

    @Override
    public Iterator<HotelVO> search(String userId, int location, int businesscircle, String name, boolean haveOrdered, int roomType, int price, int roomNum, int inTime, int outTime, int starLevel, int commentLevel) {
        double minPrice = price > 1 ? (price - 1) * 100 : 0;
        double maxPrice = price < 4 ? price * 100 : 0;
        double minComment = commentLevel;
        double maxComment = commentLevel % 4 == 0 ? 0 : (commentLevel + 1);
        return this.search(userId, location, businesscircle, name, haveOrdered, roomType, minPrice, maxPrice, roomNum, inTime, outTime, starLevel, minComment, maxComment);
    }

    /**
     * 返回符合位置要求的酒店的迭代器
     *
     * @param hotelVOIterator 原酒店列表的迭代器
     * @param location        地址
     * @return 返回结果的迭代器
     */
    private Iterator<HotelVO> matchLocation(Iterator<HotelVO> hotelVOIterator, String location) {
        if (hotelVOIterator == null) {
            return null;
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        while (hotelVOIterator.hasNext()) {
            HotelVO hotelVO = hotelVOIterator.next();
            if (hotelVO.getLocation().equals(location)) {
                hotelVOs.add(hotelVO);
            }
        }
        return hotelVOs.iterator();
    }

    /**
     * 返回符合商圈的酒店的列表
     *
     * @param hotelVOIterator 原酒店列表的迭代器
     * @param businessCircle  商圈名称
     * @return 返回结果列表的迭代器
     */
    private Iterator<HotelVO> matchBusinessCircle(Iterator<HotelVO> hotelVOIterator, String businessCircle) {
        if (hotelVOIterator == null) {
            return null;
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        while (hotelVOIterator.hasNext()) {
            HotelVO hotelVO = hotelVOIterator.next();
            if (hotelVO.getCBD().equals(businessCircle)) {
                hotelVOs.add(hotelVO);
            }
        }
        return hotelVOs.iterator();
    }

    /**
     * 返回符合房间类型的酒店的列表
     *
     * @param hotelVOIterator 原酒店列表的迭代器
     * @param roomType        房间类型
     * @return 返回结果的迭代器
     */
    private Iterator<HotelVO> matchRoomType(Iterator<HotelVO> hotelVOIterator, String roomType) {
        if (hotelVOIterator == null) {
            return null;
        } else if (roomType == null) {
            return hotelVOIterator;
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        while (hotelVOIterator.hasNext()) {
            HotelVO hotelVO = hotelVOIterator.next();
            boolean roomIsOk = false;
            while (hotelVO.getRooms().hasNext()) {
                RoomVO roomVO = hotelVO.getRooms().next();
                if (roomVO.getType().equals(roomType)) {
                    roomIsOk = true;
                    break;
                }
            }
            if (roomIsOk) {
                hotelVOs.add(hotelVO);
            }
        }
        return hotelVOs.iterator();
    }

    @Override
    public boolean modifyHotelInfo(String hotelName, double star, int location, int businessCircle, String intro) {
        boolean success = false;
        try {
            HotelPO hotelPO = RemoteHelper.getInstance().getHotelDataServ().getHotel(hotelName);
            hotelPO.setStars(star);
            hotelPO.setAddress(this.getLocationString(location));
            hotelPO.setBC(this.getBusinessCircle(location, businessCircle));
            hotelPO.setINTRO(intro);
            success = RemoteHelper.getInstance().getHotelDataServ().modifiedHotel(hotelPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * 返回符合价格要求的酒店列表
     *
     * @param hotelVOIterator 原酒店列表的迭代器
     * @param min             最低价格
     * @param max             最高价格
     * @return 返回结果的迭代器
     */
    private Iterator<HotelVO> matchPrice(Iterator<HotelVO> hotelVOIterator, double min, double max) {
        if (hotelVOIterator == null) {
            return null;
        } else if (min == 0 && max == 0) {
            return hotelVOIterator;
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        while (hotelVOIterator.hasNext()) {
            HotelVO hotelVO = hotelVOIterator.next();
            boolean priceIsOk = false;
            while (hotelVO.getRooms().hasNext()) {
                RoomVO roomVO = hotelVO.getRooms().next();
                if (min == 0 && roomVO.getPrice() <= max) {
                    priceIsOk = true;
                } else if (max == 0 && roomVO.getPrice() >= min) {
                    priceIsOk = true;
                } else if (roomVO.getPrice() >= min && roomVO.getPrice() <= max) {
                    priceIsOk = true;
                }
            }
            if (priceIsOk) {
                hotelVOs.add(hotelVO);
            }
        }
        return hotelVOs.iterator();
    }

    /**
     * 返回符合评分要求的酒店列表
     *
     * @param hotelVOIterator 原酒店列表的迭代器
     * @param min             最低评分
     * @param max             最高评分
     * @return 返回结果的迭代器
     */
    private Iterator<HotelVO> matchComment(Iterator<HotelVO> hotelVOIterator, double min, double max) {
        if (hotelVOIterator == null) {
            return null;
        } else if (min == 0 && max == 0) {
            return hotelVOIterator;
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        while (hotelVOIterator.hasNext()) {
            HotelVO hotelVO = hotelVOIterator.next();
            boolean commentIsOk = false;
            double total = 0;
            int num = 0;
            while (hotelVO.getComments().hasNext()) {
                CommentVO commentVO = hotelVO.getComments().next();
                total += commentVO.getLevel();
                num++;
            }
            double commentLevel = total / num;
            if (min == 0 && commentLevel <= max) {
                commentIsOk = true;
            } else if (max == 0 && commentLevel >= min) {
                commentIsOk = true;
            }
            if (commentIsOk) {
                hotelVOs.add(hotelVO);
            }
        }
        return hotelVOs.iterator();
    }

    /**
     * 返回包含关键字的酒店列表
     *
     * @param hotelVOIterator 原酒店列表的迭代器
     * @param name            关键字
     * @return 返回结果的迭代器
     */
    private Iterator<HotelVO> matchName(Iterator<HotelVO> hotelVOIterator, String name) {
        if (hotelVOIterator == null) {
            return null;
        } else if (name == null) {
            return hotelVOIterator;
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        while (hotelVOIterator.hasNext()) {
            HotelVO hotelVO = hotelVOIterator.next();
            if (hotelVO.getName().matches(".*" + name + ".*")) {
                hotelVOs.add(hotelVO);
            }
        }
        return hotelVOs.iterator();
    }

    /**
     * 返回一个酒店列表中符合是否预定过的酒店
     *
     * @param hotelVOIterator 酒店的迭代器
     * @param ordered         是否预定过
     * @param userId          用户id
     * @return 返回一个迭代器
     */
    private Iterator<HotelVO> matchOrdered(Iterator<HotelVO> hotelVOIterator, boolean ordered, String userId) {
        if (hotelVOIterator == null) {
            return null;
        } else if (!ordered) {
            return hotelVOIterator;
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        while (hotelVOIterator.hasNext()) {
            HotelVO hotelVO = hotelVOIterator.next();
            boolean orderedIsOk = false;
            Iterator<OrderVO> orderList = OrderBlServ.getInstance().getOrderList(userId);
            while (orderList.hasNext()) {
                OrderVO orderVO = orderList.next();
                if (orderVO.getHotel().getHotelName().equals(hotelVO.getName())) {
                    orderedIsOk = true;
                    break;
                }
            }
            if (orderedIsOk) {
                hotelVOs.add(hotelVO);
            }
        }
        return hotelVOs.iterator();
    }

    /**
     * 返回列表中房间数量足够的酒店
     *
     * @param hotelVOIterator 酒店列表迭代器
     * @param num             数量
     * @param inTime          入住时间
     * @param outTime         退房时间
     * @return 返回一个列表的迭代器
     */
    private Iterator<HotelVO> matchRoomNum(Iterator<HotelVO> hotelVOIterator, int num, int inTime, int outTime) {
        if (hotelVOIterator == null) {
            return null;
        } else if (num == 0) {
            return hotelVOIterator;
        } else if (inTime == 0 && outTime == 0) {
            return hotelVOIterator;
        } else if (inTime > outTime) {
            return null;
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        while (hotelVOIterator.hasNext()) {
            HotelVO hotelVO = hotelVOIterator.next();
            Iterator<RoomVO> rooms = hotelVO.getRooms();
            boolean roomNumIsOk = false;
            while (rooms.hasNext()) {
                RoomVO roomVO = rooms.next();
                int[] avail = roomVO.getAvailable();
                if (outTime == 0) {
                    roomNumIsOk = avail[inTime] >= num;
                } else {
                    boolean ok = true;
                    for (int i = inTime; i < outTime; i++) {
                        ok = avail[i] >= num && ok;
                    }
                    roomNumIsOk = ok || roomNumIsOk;
                }
            }
            if (roomNumIsOk) {
                hotelVOs.add(hotelVO);
            }
        }
        return hotelVOs.iterator();
    }

    /**
     * 返回符合星级要求的酒店列表
     *
     * @param hotelVOIterator 原酒店列表的迭代器
     * @param star            星级
     * @return 返回该列表的迭代器
     */
    private Iterator<HotelVO> matchStar(Iterator<HotelVO> hotelVOIterator, double star) {
        if (hotelVOIterator == null) {
            return null;
        } else if (star == 0) {
            return hotelVOIterator;
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        while (hotelVOIterator.hasNext()) {
            HotelVO hotelVO = hotelVOIterator.next();
            if (hotelVO.getStarLevel() >= star) {
                hotelVOs.add(hotelVO);
            }
        }
        return hotelVOs.iterator();
    }

    /**
     * 搜索方法
     *
     * @param userId         用户id
     * @param location       酒店位置
     * @param businessCircle 商圈
     * @param name           酒店名包含的关键字
     * @param haveOrdered    是否选择预定过的酒店
     * @param roomType       房间类型
     * @param minPrice       最低价格
     * @param maxPrice       最高价格
     * @param roomNum        房间数量
     * @param inTime         入住时间
     * @param outTime        退房时间
     * @param starLevel      星级
     * @param minComment     最低评分
     * @param maxComment     最高评分
     * @return 返回一个酒店列表的迭代器
     */
    private Iterator<HotelVO> search(String userId, int location, int businessCircle, String name, boolean haveOrdered, int roomType, double minPrice, double maxPrice, int roomNum, int inTime, int outTime, int starLevel, double minComment, double maxComment) {
        ArrayList<HotelPO> hotelPOs = null;
        try {
            hotelPOs = RemoteHelper.getInstance().getHotelDataServ().getAllHotel();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        ArrayList<HotelVO> hotelVOs = new ArrayList<>();
        hotelPOs.forEach(hotelPO -> hotelVOs.add(ParseHelper.toHotelVO(hotelPO)));
        Iterator<HotelVO> hotelVOIterator = hotelVOs.iterator();
        hotelVOIterator = this.matchLocation(hotelVOIterator, this.getLocationString(location));
        hotelVOIterator = this.matchBusinessCircle(hotelVOIterator, this.getBusinessCircle(location, businessCircle));
        hotelVOIterator = this.matchRoomType(hotelVOIterator, this.getRoomTypeString(roomType));
        hotelVOIterator = this.matchPrice(hotelVOIterator, minPrice, maxPrice);
        hotelVOIterator = this.matchComment(hotelVOIterator, minComment, maxComment);
        hotelVOIterator = this.matchName(hotelVOIterator, name);
        hotelVOIterator = this.matchOrdered(hotelVOIterator, haveOrdered, userId);
        hotelVOIterator = this.matchRoomNum(hotelVOIterator, roomNum, inTime, outTime);
        hotelVOIterator = this.matchStar(hotelVOIterator, starLevel);
        return hotelVOIterator;
    }

    @Override
    public HotelVO getHotelInfo(String name) {
        HotelPO hotelPO = null;
        try {
            hotelPO = RemoteHelper.getInstance().getHotelDataServ().getHotel(name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (hotelPO == null) {
            return null;
        }
        return ParseHelper.toHotelVO(hotelPO);
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
        while (orderVOIterator.hasNext()) {
            OrderVO orderVO = orderVOIterator.next();
            if (orderVO.getHotel().getHotelName().equals(hotelName)) {
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
    public boolean addHotel(String hotelName, int businessCIrcle, int location, String intro, double star) {
        String lc = this.getLocationString(location);
        String bc = this.getBusinessCircle(location, businessCIrcle);
        HotelPO hotelPO = new HotelPO(hotelName, bc, intro, lc, star);
        try {
            RemoteHelper.getInstance().getHotelDataServ().insertHotel(hotelPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }
}

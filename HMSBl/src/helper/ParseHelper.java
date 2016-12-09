package helper;

import PO.*;
import VO.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/30.
 * 在这里放了PO,VO之间互换的一堆方法
 * 太大了，实现丢给别的类吧
 */
public abstract class ParseHelper {

    public static RoomInOrder stringToRoom(String room) {
        String name = room;
        int num = 1;
        RoomInOrder roomInOrder = new RoomInOrder(name,num);
        return roomInOrder;
    }

    public static UserType stringToUserType(String type) {
        UserType userType = null;
        switch (type) {
            case "NORMAL":
                userType = UserType.NORMAL;
                break;
            case "SPECIAL":
                userType = UserType.SPECIAL;
                break;
        }
        return userType;
    }

    /**
     * 把数据层的String类型的时间变成逻辑层的Date类型
     * @param date
     * @return
     */
    public static Date stringToDate(String date) {
        String[] splitString = date.split("/");
        Calendar calendar = Calendar.getInstance();
        int year = Integer.parseInt(splitString[0]) + 2000;
        int month = Integer.parseInt(splitString[1]) - 1;
        int day = Integer.parseInt(splitString[2]);
        int hour = Integer.parseInt(splitString[3]);
        int min = Integer.parseInt(splitString[4]);
        calendar.set(year,month,day,hour,min);
        return calendar.getTime();
    }

    /**
     * 把逻辑层的Date类型的时间变成数据层的String类型
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd/hh/mm");
        String time = format.format(date);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        String time = "";
//        int year = calendar.get(Calendar.YEAR) - 2000;
//        int month = calendar.get(Calendar.MONTH) + 1;
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        time += year + "/" + month + "/" + (day < 10?"0":"") + day;
        return time;
    }

    /**
     * 把HotelPO转成HotelVO
     * @param hotelPO
     * @return
     */
    public static HotelVO toHotelVO(HotelPO hotelPO) {
        HotelVO hotelVO;
        String name = hotelPO.getName();
        String CBD = hotelPO.getBC();
        String location = hotelPO.getAddress();
        ArrayList<CommentVO> comments = new ArrayList<>();
        hotelPO.getComment().forEach(comment -> comments.add(stringToComment(comment)));
        int starLevel = (int)hotelPO.getStars();
        String intro = hotelPO.getINTRO();
        ArrayList<RoomVO> rooms = new ArrayList<>();
        hotelPO.getRoom().forEach(roomPO -> rooms.add(toRoomVO(roomPO)));
        ArrayList<String> cooperativeEnterprise = hotelPO.getCompanies();
        ArrayList<StrategyVO> strategies = new ArrayList<>();
        hotelPO.getStrategy().forEach(hotelStrategyPO -> strategies.add(ParseHelper.toStrategyVO(hotelStrategyPO)));
        hotelVO = new HotelVO(name,CBD,location,comments,starLevel,intro,rooms,cooperativeEnterprise,strategies);
        return hotelVO;
    }

    /**
     * 未完成
     * 把RoomVO转成RoomPO
     * @param hotelVO
     * @return
     */
    public static HotelPO toHotelPO(HotelVO hotelVO) {
        return null;
    }

    /**
     * 把字符串变成评论
     * @param comment
     * @return
     */
    public static CommentVO stringToComment(String comment) {
        CommentVO commentVO = new CommentVO(comment,0);
        return commentVO;
    }

    /**
     * 把评论变成字符串
     * @param commentVO
     * @return
     */
    public static String commentToString(CommentVO commentVO) {
        return commentVO.getContent();
    }

    /**
     * 写完了
     * @param roomPO
     * @return
     */
    public static RoomVO toRoomVO(RoomPO roomPO) {
        String hotelName = roomPO.getHn();
        String type = roomPO.getType();
        double price = roomPO.getPrice();
        int total = roomPO.getTotel();
        int[] available = roomPO.getNum();
        RoomVO roomVO = new RoomVO(hotelName,type,price,total,available);
        return roomVO;
    }

    /**
     * 还没写
     * @param roomVO
     * @return
     */
    public static RoomPO toRoomPO(RoomVO roomVO) {
        String hotelName = roomVO.getHotelName();
        String type = roomVO.getType();
        double price = roomVO.getPrice();
        int total = roomVO.getTotal();
        int[] available = roomVO.getAvailable();
        RoomPO roomPO = new RoomPO(hotelName,type,available,total,price);
        return roomPO;
    }

    /**
     * 还没写
     * @param webStrategyPO
     * @return
     */
    public static StrategyVO toStrategyVO(WebStrategyPO webStrategyPO) {
        return StrategyHelper.toStrategyVO(webStrategyPO);
    }

    /**
     * 把OrderVO转成OrderPO
     * @param orderVO
     * @return
     */
    public static OrderPO toOrderPO(OrderVO orderVO) {
        return OrderHelper.toOrderPO(orderVO);
    }

    /**
     * 好了
     * @param orderPO
     * @return
     */
    public static OrderVO toOrderVO(OrderPO orderPO) {
        return OrderHelper.toOrderVO(orderPO);
    }

    /**
     * 把字符串变成OrderState
     * @param state
     * @return
     */
    public static OrderState stringToOrderState(String state) {
        OrderState orderState = null;
        switch (state) {
            case "NORMAL":
                orderState = OrderState.NORMAL;
                break;
            case "ABNORMAL":
                orderState = OrderState.ABNORMAL;
                break;
            case "REVOKE":
                orderState = OrderState.REVOKE;
                break;
        }
        return orderState;
    }


    /**
     * 用于将UserVO转成UserPO
     * @param userVO
     * @return
     */
    public static UserPO toUserPO(UserVO userVO, UserLoginInfo info) {
        return UserHelper.toUserPO(userVO,info);
    }

    public static UserPO toUserPO(WebSaler webSaler,UserLoginInfo info) {
        return UserHelper.toUserPO(webSaler,info);
    }

    public static UserPO toUserPO(HotelStaff hotelStaff,UserLoginInfo info) {
        return UserHelper.toUserPO(hotelStaff,info);
    }

    /**
     * 显然还有问题
     * @param hotelName
     * @param strategyVO
     * @return
     */
    public static HotelStrategyPO toHotelStrategyPO(String hotelName,StrategyVO strategyVO) {
        return StrategyHelper.toHotelStrategyPO(hotelName, strategyVO);
    }

    /**
     * 好了
     * @param strategyVO
     * @return
     */
    public static WebStrategyPO toWebStrategyPO(StrategyVO strategyVO) {
        return StrategyHelper.toWebStrategyPO(strategyVO);
    }


    /**
     * 还没写好，在考虑怎么把订单列表加进去，或者直接不要了
     *
     * @param userPO
     * @return
     */
    public static UserVO toUserVO(UserPO userPO) {
        return UserHelper.toUserVO(userPO);
    }

    public static WebSaler toWebSaler(UserPO userPO) {
        return UserHelper.toWebSaler(userPO);
    }

    public static HotelStaff toHotelStaff(UserPO userPO) {
        return UserHelper.toHotelStaff(userPO);
    }

    /**
     * 用来把StrategyPO转成StrategyVO
     * @param hotelStrategyPO
     * @return
     */
    public static StrategyVO toStrategyVO(HotelStrategyPO hotelStrategyPO) {
        return StrategyHelper.toStrategyVO(hotelStrategyPO);
    }

    /**
     * 写好了，用来把creditPO变成VO
     *
     * @param creditPO
     * @return
     */
    public static CreditVO toCreditVO(CreditPO creditPO) {
        if (creditPO == null) {
            return null;
        }
        String date = creditPO.getTime();
        String orderId = creditPO.getID();
        String userId = creditPO.getUserID();
        String action = creditPO.getBehavior();
        double creditChange = creditPO.getChange();
        double credit = creditPO.getTotel();
        CreditVO creditVO = new CreditVO(ParseHelper.stringToDate(date), orderId, userId, stringToOrderAction(action), creditChange, credit);
        return creditVO;
    }

    /**
     * 用来吧字符串转成OrderAction
     * @param action
     * @return
     */
    public static OrderAction stringToOrderAction(String action) {
        OrderAction result = null;
        switch (action) {
            case "ABNORMAL":
                result = OrderAction.ABNORMAL;
                break;
            case "RECHARGE":
                result = OrderAction.RECHARGE;
                break;
            case "CHECK_IN":
                result = OrderAction.CHECK_IN;
                break;
            case "REVOKE":
                result = OrderAction.REVOKE;
                break;
        }
        return result;
    }


    /**
     * 写好了，把creditVO变成creditPO
     * 需要看一下那边的字符串有哪些
     *
     * @param creditVO
     * @return
     */
    public static CreditPO toCreditPO(CreditVO creditVO) {
        if (creditVO == null) {
            return null;
        }
        String id = creditVO.getNum();
        String time = creditVO.getTime().toString();
        String userId = creditVO.getUserId();
        double total = creditVO.getCredit();
        double change = creditVO.getCreditChange();
        OrderAction action = creditVO.getAction();
        String bh = orderActionToString(action);
        CreditPO creditPO = new CreditPO(id, time, userId, total, change, bh);
        return creditPO;
    }

    public static String orderActionToString(OrderAction action) {
        String result = "";
        switch (action) {
            case ABNORMAL:
                result = "ABNORMAL";
                break;
            case RECHARGE:
                result = "RECHARGE";
                break;
            case CHECK_IN:
                result = "CHECK_IN";
                break;
            case REVOKE:
                result = "REVOKE";
                break;
        }
        return result;
    }

}

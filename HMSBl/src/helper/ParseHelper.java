package helper;

import PO.*;
import VO.*;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/11/30.
 * 在这里放了PO,VO之间互换的一堆方法
 */
public abstract class ParseHelper {

    public static RoomInOrder stringToRoom(String room) {
        return null;
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
        calendar.set(year,month,day);
        return calendar.getTime();
    }

    /**
     * 把逻辑层的Date类型的时间变成数据层的String类型
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String time = "";
        int year = calendar.get(Calendar.YEAR) - 2000;
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        time += year + "/" + month + "/" + day;
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
        hotelPO.getStrategy().forEach(hotelStrategyPO -> strategies.add(toStrategyVO(hotelStrategyPO)));
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
        CommentVO commentVO = new CommentVO(comment);
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
     * 还没写
     * @param roomPO
     * @return
     */
    public static RoomVO toRoomVO(RoomPO roomPO) {
        return null;
    }

    /**
     * 还没写
     * @param roomVO
     * @return
     */
    public static RoomPO toRoomPO(RoomVO roomVO) {
        return null;
    }

    /**
     * 算是写了一半
     * @param hotelStrategyPO
     * @return
     */
    public static StrategyVO toStrategyVO(HotelStrategyPO hotelStrategyPO) {
        StrategyVO strategyVO = null;
        String name = hotelStrategyPO.getStrategyName();
        double discount = hotelStrategyPO.getDiscount();
        switch (hotelStrategyPO.getType()) {
            case "date":
                //问一下先
                Date startTime = ParseHelper.stringToDate(hotelStrategyPO.getSpecialInof());
                Date endTime = ParseHelper.stringToDate(hotelStrategyPO.getSpecialInof());
                strategyVO = new DoubleElevenStrategy(name,discount,startTime,endTime);
                break;
            case "birthday":
                strategyVO = new BirthdayStrategy(name,discount);
                break;
            case "roomnum":
                strategyVO = new RoomNumberStrategy(name,discount);
                break;
            case "companies":
                strategyVO = new CooperativeStrategy(name,discount);
                break;
        }
        return strategyVO;
    }

    /**
     * 还没写
     * @param hotelStrategyPO
     * @return
     */
    public static StrategyVO toStrategyVO(WebStrategyPO hotelStrategyPO) {
        return null;
    }

    /**
     * 把OrderVO转成OrderPO
     * @param orderVO
     * @return
     */
    public static OrderPO toOrderPO(OrderVO orderVO) {
        String id = null;
        String userId = orderVO.getUser().getId();
        String userName = orderVO.getUser().getName();
        String contact = orderVO.getUser().getContact();
        String hotel = orderVO.getHotel().getHotelName();
        String type = orderVO.getState().toString();
        String inTime = orderVO.getInTime().toString();
        String outTime = orderVO.getOutTime().toString();
        String execTime = orderVO.getExecTime().toString();
        double total = orderVO.getTotal();
        OrderPO orderPO = new OrderPO(id,userId,hotel,userName,contact,type,inTime,outTime,execTime,total);
        return orderPO;
    }

    /**
     * 好了
     * @param orderPO
     * @return
     */
    public static OrderVO toOrderVO(OrderPO orderPO) {
        String userId = orderPO.getUserID();
        String userName = orderPO.getUserName();
        String userContact = orderPO.getUserContact();
        UserInOrder user = new UserInOrder(userId, userName, userContact);
        String hotelName = orderPO.getHotel();
        ArrayList<String> rooms = orderPO.getRoom();
        ArrayList<RoomInOrder> roomInOrders = new ArrayList<>();
        rooms.forEach(room->roomInOrders.add(ParseHelper.stringToRoom(room)));
        HotelInOrder hotel = new HotelInOrder(hotelName, roomInOrders);
        OrderState state = ParseHelper.stringToOrderState(orderPO.getType());
        boolean children = false;
        String inTime = orderPO.getInTime();
        String outTime = orderPO.getOutTime();
        String execTime = orderPO.getLastTime();
        int total = (int) orderPO.getTotel();
        OrderVO orderVO = new OrderVO(user, hotel, state, children, stringToDate(inTime), stringToDate(outTime), stringToDate(execTime), total);
        return orderVO;
    }

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
        if(userVO == null || info == null) {
            return null;
        }
        String id = info.getUserId();
        String password = info.getPassword();
        String contact = userVO.getContact();
        String name = userVO.getName();
        String specialInfo = userVO.getAdditionalInfo();
        int credit = userVO.getCredit();
        int vipLevel = userVO.getGrade();
        int isLogin = 0;
        String type = userVO.getType().toString();
        UserPO userPO = new UserPO(id,password,contact,name,specialInfo,credit,vipLevel,isLogin,type);
        return userPO;
    }

    /**
     * 显然还有问题
     * @param hotelName
     * @param strategyVO
     * @return
     */
    public static HotelStrategyPO toHotelStrategyPO(String hotelName,StrategyVO strategyVO) {
        String strategyName = strategyVO.getName();
        double discount = strategyVO.getDiscount();
        String specialInfo = null;
        String type = strategyVO.getType();
        switch (type) {
            case "birthday":
                BirthdayStrategy birthdayStrategy = (BirthdayStrategy)strategyVO;
                break;
            case "date":
                DoubleElevenStrategy doubleElevenStrategy = (DoubleElevenStrategy)strategyVO;
                String startTime = ParseHelper.dateToString(doubleElevenStrategy.getStartTime());
                String endTime = ParseHelper.dateToString(doubleElevenStrategy.getEndTime());
                specialInfo = startTime + "-" + endTime;
                break;
            case "roomnum":
                RoomNumberStrategy roomNumberStrategy = (RoomNumberStrategy)strategyVO;
                break;
            case "companies":
                CooperativeStrategy cooperativeStrategy = (CooperativeStrategy)strategyVO;
                Iterator<String> companies = cooperativeStrategy.getCompanies();
                StringBuilder stringBuilder = new StringBuilder("");
                companies.forEachRemaining(company -> stringBuilder.append(company));
                specialInfo = stringBuilder.toString();
                break;
        }
        HotelStrategyPO hotelStrategyPO = new HotelStrategyPO(hotelName,strategyName,specialInfo,discount,type);
        return hotelStrategyPO;
    }

    /**
     * 还没写
     * @param strategyVO
     * @return
     */
    public static WebStrategyPO toWebStrategyPO(StrategyVO strategyVO) {
//        WebStrategyPO webStrategyPO = new WebStrategyPO();
        return null;
    }


    /**
     * 还没写好，在考虑怎么把订单列表加进去，或者直接不要了
     *
     * @param userPO
     * @return
     */
    public static UserVO toUserVO(UserPO userPO) {
        if(userPO == null) {
            return null;
        }
        String name = userPO.getName();
        String contact = userPO.getContactInfo();
        int credit = userPO.getCreditTol();
        int grade = userPO.getVipLev();
        ArrayList<OrderVO> orderVOs = new ArrayList<>();
        UserType type = ParseHelper.stringToUserType(userPO.getType());
        String additionalInfo = userPO.getSpecialInfo();
        try {
            ArrayList<OrderPO> orderPOs = RemoteHelper.getInstance().getOrderDataServ().getOrders(userPO.getID());
            orderPOs.forEach(orderPO -> orderVOs.add(ParseHelper.toOrderVO(orderPO)));
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        UserVO userVO = new UserVO(name,contact,credit,grade,orderVOs,type,additionalInfo);
        return userVO;
    }
}

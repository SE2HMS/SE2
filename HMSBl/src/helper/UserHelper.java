package helper;

import PO.OrderPO;
import PO.UserPO;
import VO.*;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/9.
 */
public class UserHelper {
    public static UserPO toUserPO(WebSaler webSaler, UserLoginInfo info) {
        String id = info.getUserId();
        String password = info.getPassword();
        String name = webSaler.getName();
        String contact = webSaler.getContact();
        String spec = null;
        int credit = 0;
        int vip = 0;
        int isLogin = 0;
        String type = webSaler.getType().toString();
        UserPO userPO = new UserPO(id,password,name,contact,spec,credit,vip,isLogin,type);
        return userPO;
    }

    public static UserPO toUserPO(HotelStaff hotelStaff,UserLoginInfo info) {
        String id = info.getUserId();
        String password = info.getPassword();
        String name = hotelStaff.getName();
        String contact = hotelStaff.getContact();
        String spec = hotelStaff.getHotelName();
        int credit = 0;
        int vip = 0;
        int isLogin = 0;
        String type = hotelStaff.getType().toString();
        UserPO userPO = new UserPO(id,password,contact,name,spec,credit,vip,isLogin,type);
        return userPO;
    }

    public static UserPO toUserPO(UserVO userVO,UserLoginInfo info) {
        if(userVO == null || info == null) {
            return null;
        }
        String id = info.getUserId();
        String password = info.getPassword();
        String contact = userVO.getContact();
        String name = userVO.getName();
        String specialInfo = userVO.getAdditionalInfo();
        double credit = userVO.getCredit();
        int vipLevel = userVO.getGrade();
        int isLogin = 0;
        String type = userVO.getType().toString();
        UserPO userPO = new UserPO(id,password,contact,name,specialInfo,credit,vipLevel,isLogin,type);
        return userPO;
    }

    public static UserVO toUserVO(UserPO userPO) {
        if(userPO == null) {
            return null;
        }
        if(!(userPO.getType().equals("NORMAL") || userPO.getType().equals("SPECIAL"))) {
            return null;
        }
        String name = userPO.getName();
        String contact = userPO.getContactInfo();
        double credit = userPO.getCreditTol();
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

    public static WebSaler toWebSaler(UserPO userPO) {
        if(!userPO.getType().equals("WEB_SALER")) {
            return null;
        }
        String name = userPO.getName();
        String contact = userPO.getName();
        WebSaler webSaler = new WebSaler(name,contact);
        return webSaler;
    }

    public static HotelStaff toHotelStaff(UserPO userPO) {
        if(!userPO.getType().equals("HOTEL_STAFF")) {
            return null;
        }
        String hotelName = userPO.getSpecialInfo();
        String contact = userPO.getContactInfo();
        String userName = userPO.getName();
        HotelStaff hotelStaff = new HotelStaff(hotelName,contact,userName);
        return hotelStaff;
    }

    public static WebManager toWebManager(UserPO userPO) {
        String name = userPO.getName();
        String contact = userPO.getContactInfo();
        WebManager manager = new WebManager(name,contact);
        return manager;
    }

    public static UserPO toUserPO(WebManager manager,UserLoginInfo info) {
        if(manager == null || info == null) {
            return null;
        }
        String id = info.getUserId();
        String password = info.getPassword();
        String contact = manager.getContact();
        String name = manager.getUserName();
        String specialInfo = null;
        int credit = 0;
        int vipLevel = 0;
        int isLogin = 0;
        String type = manager.getUserType().toString();
        UserPO userPO = new UserPO(id,password,contact,name,specialInfo,credit,vipLevel,isLogin,type);
        return userPO;
    }
}

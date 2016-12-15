package blTest;

import VO.HotelVO;
import VO.UserLoginInfo;
import VO.UserType;
import VO.UserVO;
import hotel_bl_serv.HotelBlServ;
import login_bl_serv.LoginBlServ;
import manage_bl_serv.ManageBlServ;
import rmi.RemoteHelper;
import rmi.RemoteRunner;

import java.rmi.RemoteException;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/12/14.
 */
public class AllTest {
    public static void main(String[] args) {
        new RemoteRunner();
        new AllTest().getUser();
    }

    private void getUser() {
        LoginBlServ.getInstance().register(new UserVO("ni","12332112321", UserType.NORMAL,"12/2/3"),new UserLoginInfo(null,"wasd"));
        UserVO userVO = LoginBlServ.getInstance().getUserInfo("3");
        System.out.println(userVO.getName());
    }

    private void showHotel() {
        HotelVO hotelVO = HotelBlServ.getInstance().getHotelInfo("dontlala");
        System.out.println(hotelVO.getName());
        System.out.println(hotelVO.getCBD());
        System.out.println(hotelVO.getLocation());
    }

    private void addHotel() {
        HotelBlServ.getInstance().addHotel("dontlala",0,0,"nothing",5);
    }

    private void deleteUser() {
        try {
            RemoteHelper.getInstance().getUserDataServ().deleteUser("1");
        }catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void test() {
        Iterator<UserVO> userVOIterator = ManageBlServ.getInstance().getAllUserInfo();//这个有问题要改
        while(userVOIterator.hasNext()) {
            System.out.println(userVOIterator.next().getName());
        }
        System.out.println("finish");
    }

    private void addHotelStaff() {
        String id = LoginBlServ.getInstance().registerHotelStaff("nihao","lalala","14718037199","yougood").getId();
        System.out.println("id=" + id);
        HotelVO hotelVO = HotelBlServ.getInstance().getHotelInfo("lalala");
        System.out.println(hotelVO.getName());
    }
}

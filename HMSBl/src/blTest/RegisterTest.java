package blTest;

import PO.UserPO;
import VO.*;
import login_bl_serv.LoginBlServ;
import org.junit.Test;
import rmi.RemoteHelper;
import rmi.RemoteRunner;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

/**
 * Created by Administrator on 2016/12/12.
 */
public class RegisterTest {
//    public static void main(String[] args) {
//        new RemoteRunner();
//        new RegisterTest().register();
//        new RegisterTest().testUserPO();
//    }

    private void testUserPO() {
        try {
            UserPO userPO = RemoteHelper.getInstance().getUserDataServ().getUser("nihahah", "12434532345");
            System.out.println(userPO.getID());
        }catch (RemoteException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        new RegisterTest().testLogin();
//    }

    @Test
    public void testLogin() {
        new RemoteRunner();
        LoginResult result = LoginBlServ.getInstance().login("7","hello");
//        assertEquals(result.toString(),LoginResult.SUCCESS.toString());
        assertEquals("hello","j");
    }

    private void register() {
        String id = null;
        String password = "hello";
        String name = "nihahah";
        String contact = "12434536345";
        UserType userType = UserType.NORMAL;
        String spec = "96/12/03";
        UserVO userVO = new UserVO(name,contact,userType,spec);
        UserLoginInfo userLoginInfo = new UserLoginInfo(null,password);
        RegisterResult result = LoginBlServ.getInstance().register(userVO,userLoginInfo);
        System.out.println(result.getState());
        System.out.println(result.getId());
    }
}

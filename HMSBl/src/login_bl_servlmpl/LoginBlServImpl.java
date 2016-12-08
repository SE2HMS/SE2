package login_bl_servlmpl;

import VO.*;
import helper.ParseHelper;
import login_bl_serv.LoginBlServ;
import PO.UserPO;
import rmi.RemoteHelper;

import java.rmi.RemoteException;

/**
 * 12.2检查完毕
 */
public class LoginBlServImpl implements LoginBlServ {

    @Override
    public LoginResult login(String id, String password) {
        UserPO userPO;
        LoginResult result;
        try {
            userPO = RemoteHelper.getInstance().getUserDataServ().getUser(id);
            if(userPO == null) {
                result = LoginResult.WRONG_ID;
            }else if(!userPO.getPassword().equals(password)) {
                result = LoginResult.WRONG_PASSWORD;
            }else if(userPO.getIsLogin() > 0) {
                result = LoginResult.ALREADY_LOGIN;
            }else {
                userPO.setIsLogin(1);
                result = LoginResult.SUCCESS;
            }
        }catch (RemoteException e) {
            result = LoginResult.EXCEPTION;
        }
        return result;
    }


    @Override
    public RegisterResult register(UserVO user, UserLoginInfo userLoginInfo) {
        RegisterResult result;
        try {
            UserPO userPO = RemoteHelper.getInstance().getUserDataServ().getUser(userLoginInfo.getUserId());
            if(userPO != null) {
                result = RegisterResult.ALREADY_REGISTERED;
            }else {
                userPO = ParseHelper.toUserPO(user,userLoginInfo);
                boolean success = RemoteHelper.getInstance().getUserDataServ().insertUser(userPO);
                if(success) {
                    result = RegisterResult.SUCCESS;
                }else {
                    result = RegisterResult.FAIL;
                }
            }
        } catch (RemoteException e) {
            result = RegisterResult.EXCEPTION;
        }
        return result;
    }

    @Override
    public LogoutResult logout(String id) {
        LogoutResult result;
        try {
            UserPO userPO = RemoteHelper.getInstance().getUserDataServ().getUser(id);
            if(userPO.getIsLogin() == 0) {
                result = LogoutResult.ALREADY_LOGOUT;
            }else {
                userPO.setIsLogin(0);
                boolean success = RemoteHelper.getInstance().getUserDataServ().modifiedUser(userPO);
                if(success) {
                    result = LogoutResult.SUCCESS;
                }else {
                    result = LogoutResult.EXCEPTION;
                }
            }
        }catch (RemoteException e) {
            result = LogoutResult.EXCEPTION;
        }
        return result;
    }

    @Override
    public UserVO getUserInfo(String id) {
        UserPO userPO = null;
        try {
            userPO = RemoteHelper.getInstance().getUserDataServ().getUser(id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        UserVO userVO = null;
        if(userPO != null) {
            userVO = ParseHelper.toUserVO(userPO);
        }
        return userVO;
    }

    @Override
    public RegisterResult registerWebSaler(String name, String contact) {
        return null;
    }

    @Override
    public RegisterResult registerHotelStaff(String hotelName, String contact) {
        return null;
    }
}

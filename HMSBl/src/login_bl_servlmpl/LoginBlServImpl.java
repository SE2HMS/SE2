package login_bl_servlmpl;

import VO.*;
import helper.ParseHelper;
import hotel_bl_serv.HotelBlServ;
import login_bl_serv.LoginBlServ;
import PO.UserPO;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;

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
            if (userPO == null) {
                result = LoginResult.WRONG_ID;
            } else if (!userPO.getPassword().equals(password)) {
                result = LoginResult.WRONG_PASSWORD;
            } else if (userPO.getIsLogin() > 0) {
                result = LoginResult.ALREADY_LOGIN;
            } else {
                userPO.setIsLogin(1);
                try {
                    RemoteHelper.getInstance().getUserDataServ().modifiedUser(userPO);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                result = LoginResult.SUCCESS;
            }
        } catch (RemoteException e) {
            result = LoginResult.EXCEPTION;
        }
        return result;
    }

    @Override
    public RegisterResult register(UserVO user, UserLoginInfo userLoginInfo) {
        RegisterResult result = null;
        try {
            UserPO userPO = RemoteHelper.getInstance().getUserDataServ().getUser(user.getName(), user.getContact());
            if (userPO != null) {
                result = new RegisterResult(RegisterState.ALREADY_REGISTERED, null);
            } else {
                userPO = ParseHelper.toUserPO(user, userLoginInfo);
                boolean success = RemoteHelper.getInstance().getUserDataServ().insertUser(userPO);
                if (success) {
                    String id = null;
                    try {
                        id = RemoteHelper.getInstance().getUserDataServ().getUser(user.getName(), user.getContact()).getID();
                        result = new RegisterResult(RegisterState.SUCCESS, id);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    result = new RegisterResult(RegisterState.FAIL, null);
                }
            }
        } catch (RemoteException e) {
            result = new RegisterResult(RegisterState.EXCEPTION, null);
        }
        return result;
    }

    @Override
    public LogoutResult logout(String id) {
        LogoutResult result;
        try {
            UserPO userPO = RemoteHelper.getInstance().getUserDataServ().getUser(id);
            if (userPO.getIsLogin() == 0) {
                result = LogoutResult.ALREADY_LOGOUT;
            } else {
                userPO.setIsLogin(0);
                boolean success = RemoteHelper.getInstance().getUserDataServ().modifiedUser(userPO);
                if (success) {
                    result = LogoutResult.SUCCESS;
                } else {
                    result = LogoutResult.EXCEPTION;
                }
            }
        } catch (RemoteException e) {
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
        if (userPO != null) {
            userVO = ParseHelper.toUserVO(userPO);
        }
        return userVO;
    }

    @Override
    public RegisterResult registerWebSaler(String password, String name, String contact) {
        WebSaler webSaler = new WebSaler(name, contact);
        UserLoginInfo userLoginInfo = new UserLoginInfo("", password);
        UserPO testExist = null;
        RegisterResult registerResult;
        try {
            testExist = RemoteHelper.getInstance().getUserDataServ().getUser(name, contact);
            if (testExist != null) {
                registerResult = new RegisterResult(RegisterState.ALREADY_REGISTERED, null);
                return registerResult;
            }
            UserPO userPO = ParseHelper.toUserPO(webSaler, userLoginInfo);
            RemoteHelper.getInstance().getUserDataServ().insertUser(userPO);
            testExist = RemoteHelper.getInstance().getUserDataServ().getUser(name, contact);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (testExist == null) {
            registerResult = new RegisterResult(RegisterState.ALREADY_REGISTERED, null);
            return registerResult;
        } else {
            registerResult = new RegisterResult(RegisterState.SUCCESS, testExist.getID());
            return registerResult;
        }
    }

    @Override
    public RegisterResult registerWebManager(String password, String name, String contact) {
        String id = null;
        try {
            ArrayList<UserPO> userPOs = RemoteHelper.getInstance().getUserDataServ().getUserList();
            for (UserPO userPO : userPOs) {
                if (userPO.getContactInfo().equals(contact) && userPO.getName().equals(name)) {
                    return new RegisterResult(RegisterState.ALREADY_REGISTERED, null);
                } else if (userPO.getType().equals("WEB_MANAGER")) {
                    return new RegisterResult(RegisterState.ALREADY_REGISTERED, null);
                }
            }
            WebManager webManager = new WebManager(name, contact);
            UserLoginInfo userLoginInfo = new UserLoginInfo(id, password);
            UserPO userPO = ParseHelper.toUserPO(webManager, userLoginInfo);
            RemoteHelper.getInstance().getUserDataServ().insertUser(userPO);
            id = RemoteHelper.getInstance().getUserDataServ().getUser(name, contact).getID();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (id == null) {
            return new RegisterResult(RegisterState.FAIL, id);
        }
        return new RegisterResult(RegisterState.SUCCESS, id);
    }

    @Override
    public RegisterResult registerHotelStaff(String password, String hotelName, String contact, String userName) {
        if (HotelBlServ.getInstance().getHotelInfo(hotelName) != null) {
            return new RegisterResult(RegisterState.FAIL, "this hotel already exist");
        }
        if (this.getUser(userName, contact) != null) {
            return new RegisterResult(RegisterState.ALREADY_REGISTERED, "");
        }
        HotelStaff hotelStaff = new HotelStaff(hotelName, contact, userName);
        UserLoginInfo userLoginInfo = new UserLoginInfo(null, password);
        UserPO userPO = ParseHelper.toUserPO(hotelStaff, userLoginInfo);
        boolean success = false;
        try {
            success = RemoteHelper.getInstance().getUserDataServ().insertUser(userPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (success) {
            String id = null;
            try {
                id = RemoteHelper.getInstance().getUserDataServ().getUser(userName, contact).getID();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return new RegisterResult(RegisterState.SUCCESS, id);
        }
        return new RegisterResult(RegisterState.EXCEPTION, null);
    }

    private UserVO getUser(String userName, String contact) {
        UserVO userVO = null;
        try {
            UserPO userPO = RemoteHelper.getInstance().getUserDataServ().getUser(userName, contact);
            userVO = ParseHelper.toUserVO(userPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return userVO;
    }

    @Override
    public WebSaler getWebSaler(String id) {
        if (id == null) {
            return null;
        }
        WebSaler webSaler = null;
        try {
            UserPO userPO = RemoteHelper.getInstance().getUserDataServ().getUser(id);
            webSaler = ParseHelper.toWebSaler(userPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return webSaler;
    }

    @Override
    public HotelStaff getHotelStaff(String id) {
        if (id == null) {
            return null;
        }
        HotelStaff hotelStaff = null;
        try {
            UserPO userPO = RemoteHelper.getInstance().getUserDataServ().getUser(id);
            hotelStaff = ParseHelper.toHotelStaff(userPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return hotelStaff;
    }

    @Override
    public WebManager getWebManager(String id) {
        if (id == null) {
            return null;
        }
        WebManager webManager = null;
        try {
            UserPO userPO = RemoteHelper.getInstance().getUserDataServ().getUser(id);
            webManager = ParseHelper.toWebManager(userPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return webManager;
    }

    @Override
    public boolean modifyUserInfo(String id, String name, String specialInfo, String contact) {
        if(id == null) {
            return false;
        }
        boolean success = false;
        try {
            UserPO test = RemoteHelper.getInstance().getUserDataServ().getUser(name,contact);
            if(test != null) {
                return false;
            }
            UserPO userPO = RemoteHelper.getInstance().getUserDataServ().getUser(id);
            userPO.setName(name);
            userPO.setContactInfo(contact);
            userPO.setSpecialInfo(specialInfo);
            success = RemoteHelper.getInstance().getUserDataServ().modifiedUser(userPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return success;
    }
}

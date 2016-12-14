package manage_bl_servlmpl;

import PO.UserPO;
import VO.*;
import helper.ParseHelper;
import manage_bl_serv.ManageBlServ;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 12.3更新
 */
public class ManageBlServImpl implements ManageBlServ {

    @Override
    public Iterator<UserVO> getAllUserInfo() {
        ArrayList<UserVO> userVOs = new ArrayList<>();
        ArrayList<UserPO> userPOs = null;
        try {
            userPOs = RemoteHelper.getInstance().getUserDataServ().getUserList();
            userPOs.forEach(userPO -> userVOs.add(ParseHelper.toUserVO(userPO)));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return userVOs.iterator();
    }

    @Override
    public boolean addUserInfo(UserVO user, UserLoginInfo info) {
        if(user == null || info == null) {
            return false;
        }
        UserPO userPO = ParseHelper.toUserPO(user, info);
        boolean success = false;
        try {
            success = RemoteHelper.getInstance().getUserDataServ().insertUser(userPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean modifyUserInfo(UserVO user,UserLoginInfo info) {
        boolean success = false;
        try {
            UserPO userPO = ParseHelper.toUserPO(user,info);
            success = RemoteHelper.getInstance().getUserDataServ().modifiedUser(userPO);
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean delUserInfo(String userId) {
        boolean success = false;
        try {
            success = RemoteHelper.getInstance().getUserDataServ().deleteUser(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean addWebSaler(WebSaler webSaler, UserLoginInfo info) {
        UserPO userPO = ParseHelper.toUserPO(webSaler,info);
        boolean success = false;
        try {
            success = RemoteHelper.getInstance().getUserDataServ().insertUser(userPO);
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean addHotelStaff(HotelStaff hotelStaff, UserLoginInfo info) {
        if(hotelStaff == null || info == null) {
            return false;
        }
        boolean success = false;
        try {
            UserPO userPO = ParseHelper.toUserPO(hotelStaff, info);
            success = RemoteHelper.getInstance().getUserDataServ().insertUser(userPO);
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean modifyWebSaler(WebSaler webSaler, UserLoginInfo info) {
        if(webSaler == null || info == null) {
            return false;
        }
        boolean success = false;
        try {
            UserPO userPO = RemoteHelper.getInstance().getUserDataServ().getUser(webSaler.getName(),webSaler.getContact());
            UserLoginInfo userLoginInfo = new UserLoginInfo(userPO.getID(),info.getPassword());
            userPO = ParseHelper.toUserPO(webSaler,userLoginInfo);
            success = RemoteHelper.getInstance().getUserDataServ().insertUser(userPO);
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean modifyHotelStaff(HotelStaff hotelStaff, UserLoginInfo info) {
        if(hotelStaff == null || info == null) {
            return false;
        }
        boolean success = false;
        try {
            UserPO userPO = RemoteHelper.getInstance().getUserDataServ().getUser(hotelStaff.getName(),hotelStaff.getContact());
            UserLoginInfo userLoginInfo = new UserLoginInfo(userPO.getID(),info.getPassword());
            userPO = ParseHelper.toUserPO(hotelStaff,userLoginInfo);
            success = RemoteHelper.getInstance().getUserDataServ().insertUser(userPO);
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        return success;
    }
}

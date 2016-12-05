package manage_bl_servlmpl;

import PO.UserPO;
import VO.*;
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

}

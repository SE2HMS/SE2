package manage_bl_servlmpl;

import PO.UserPO;
import VO.*;
import manage_bl_serv.ManageBlServ;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 这个也差不多了
 */
public class ManageBlServImpl implements ManageBlServ {

    @Override
    public Iterator<UserVO> getAllUserInfo() {
        ArrayList<UserVO> userVOs = new ArrayList<>();
        ArrayList<UserPO> userPOs = null;
        try {
            userPOs = RemoteHelper.getInstance().getUserDataServ().getUserList();
            userPOs.forEach(userPO -> userVOs.add(parseUserVO(userPO)));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return userVOs.iterator();
    }

    @Override
    public boolean addUserInfo(UserVO user, UserLoginInfo info) {
        UserPO userPO = parseUserPO(user, info);
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
            success = RemoteHelper.getInstance().getUserDataServ().modifiedUser(parseUserPO(user,info));
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

    /**
     * 这东西在loginblservimpl里面已经有一个了
     *
     * @param userVO
     * @param info
     * @return
     */
    private UserPO parseUserPO(UserVO userVO, UserLoginInfo info) {
        String id = info.getUserId();
        String password = info.getPassword();
        String contact = userVO.getContact();
        String name = userVO.getName();
        String specialInfo = userVO.getAdditionalInfo();
        int credit = userVO.getCredit();
        int vipLevel = userVO.getGrade();
        int isLogin = 0;
        String type = userVO.getType().toString(); //没测试，也许有问题
        UserPO userPO = new UserPO(id, password, contact, name, specialInfo, credit, vipLevel, isLogin, type);
        return userPO;
    }

    private UserVO parseUserVO(UserPO userPO) {
        return null;
    }
}

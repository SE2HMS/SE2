package login_bl_servlmpl;

import PO.OrderPO;
import VO.*;
import helper.ParseHelper;
import login_bl_serv.LoginBlServ;
import PO.UserPO;
import order_bl_serv.OrderBlServ;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * 额，登出暂时不知道怎么搞
 */
public class LoginBlServImpl implements LoginBlServ {

    @Override
    /**
     * 登录方法，如果没有这个用户，返回wrongId
     * 如果密码不正确，返回wrongPassword
     * 登录成功，返回success
     * 登录冲突,返回alreadyLogin
     */
    public LoginResult login(String id, String password) {
        UserPO userPO = null;
        try {
            userPO = RemoteHelper.getInstance().getUserDataServ().getUser(id);
            if (userPO == null) {
                return LoginResult.wrongId;
            }
            if (userPO.getIsLogin() > 0) {
                return LoginResult.alreadyLogin;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        boolean correct = password.equals(userPO.getPassword());
        if (correct) {
            userPO.setIsLogin(1);
            try {
                RemoteHelper.getInstance().getUserDataServ().modifiedUser(userPO);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return LoginResult.success;
        } else {
            return LoginResult.wrongPassword;
        }
    }

    @Override
    /**
     * 注册一个用户，成功返回true
     * 失败返回false
     */
    public boolean register(UserVO user, UserLoginInfo userLoginInfo) {
        UserPO userPO = parseUserPO(user, userLoginInfo);
        boolean success = false;
        try {
            success = RemoteHelper.getInstance().getUserDataServ().insertUser(userPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    /**
     * 还没实现
     */
    public boolean logout(String id) {
        return false;
    }

    @Override
    public UserVO getUserInfo(String id) {
        UserPO userPO = null;
        try {
            userPO = RemoteHelper.getInstance().getUserDataServ().getUser(id);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        UserVO userVO = parseUserVO(userPO);
        return userVO;
    }

    /**
     * 用于将VO转成PO
     *
     * @param userVO
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

    /**
     * 还没写好，在考虑怎么把订单列表加进去，或者直接不要了
     *
     * @param userPO
     * @return
     */
    private UserVO parseUserVO(UserPO userPO) {
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

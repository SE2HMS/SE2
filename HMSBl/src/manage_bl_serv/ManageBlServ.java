package manage_bl_serv;

import VO.*;
import manage_bl_servlmpl.ManageBlServImpl;

import java.util.HashMap;
import java.util.Iterator;

public interface ManageBlServ {

    public static ManageBlServ getInstance() {
        return new ManageBlServImpl();
    }

    /**
     * 得到所有用户的信息
     *
     * @return 返回一个迭代器
     */
    public Iterator<UserVO> getAllUserInfo();

    public Iterator<HotelStaff> getAllHotelStaff();

    public Iterator<WebSaler> getAllWebSaler();

    /**
     * 增加一条用户信息
     *
     * @param user
     * @param info
     * @return 返回是否成功
     */
    public boolean addUserInfo(UserVO user, UserLoginInfo info);

    public boolean addWebSaler(WebSaler webSaler,UserLoginInfo info);

    public boolean addHotelStaff(HotelStaff hotelStaff,UserLoginInfo info);

    public boolean modifyWebSaler(WebSaler webSaler,UserLoginInfo info);

    public boolean modifyHotelStaff(HotelStaff hotelStaff,UserLoginInfo info);

    /**
     * 修改一个用户的信息
     *
     * @param user 新的用户，替换掉相同id的用户
     * @param info
     * @return 返回是否成功
     */
    public boolean modifyUserInfo(UserVO user, UserLoginInfo info);

    /**
     * 删除一个用户，只要传入id即可
     * 包括酒店工作人员，网站销售人员
     *
     * @param userId 用户的id
     * @return 返回是否成功
     */
    public boolean delUserInfo(String userId);
}

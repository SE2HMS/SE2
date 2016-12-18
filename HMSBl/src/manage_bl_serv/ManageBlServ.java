package manage_bl_serv;

import VO.*;
import manage_bl_servlmpl.ManageBlServImpl;

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

    /**
     * 得到所有的酒店工作人员
     *
     * @return
     */
    public Iterator<HotelStaff> getAllHotelStaff();

    /**
     * 得到所有的网站销售人员
     *
     * @return
     */
    public Iterator<WebSaler> getAllWebSaler();

    /**
     * 增加一条用户信息
     *
     * @param user 用户
     * @param info 只要密码
     * @return 返回是否成功
     */
    public boolean addUserInfo(UserVO user, UserLoginInfo info);

    /**
     * 增加网站销售人员
     *
     * @param webSaler 网站销售人员
     * @param info     登录信息，实际上只要密码
     * @return 返回是否成功
     */
    public boolean addWebSaler(WebSaler webSaler, UserLoginInfo info);

    /**
     * 增加酒店工作人员
     *
     * @param hotelStaff 该酒店工作人员
     * @param info       只要面
     * @return 返回是否成功
     */
    public boolean addHotelStaff(HotelStaff hotelStaff, UserLoginInfo info);

    /**
     * 修改网站销售人员的方法，需要修改
     *
     * @param webSaler 网站销售人员
     * @return 是否成功
     */
    public boolean modifyWebSaler(WebSaler webSaler);

    /**
     * 修改酒店工作人员
     *
     * @param hotelStaff 酒店工作人员
     * @return 返回是否成功
     */
    public boolean modifyHotelStaff(HotelStaff hotelStaff);

    /**
     * 修改一个用户的信息
     *
     * @param user 新的用户，替换掉相同id的用户
     * @return 返回是否成功
     */
    public boolean modifyUserInfo(UserVO user);

    /**
     * 删除一个用户，只要传入id即可
     * 包括酒店工作人员，网站销售人员
     *
     * @param userId 用户的id
     * @return 返回是否成功
     */
    public boolean delUserInfo(String userId);
}

package room_bl_serv;

import VO.OrderVO;
import VO.RoomVO;
import VO.UserOrderAction;
import room_bl_servImpl.RoomBlServImpl;

import java.util.ArrayList;

public interface RoomBlServ {
    public static RoomBlServ getInstance() {
        return new RoomBlServImpl();
    }

    /**
     * 得到某种类型的房间具体的信息
     *
     * @param hotelName 房间名
     * @param type      房间类型
     * @return
     */
    public RoomVO getRoomInfo(String hotelName, String type);

    /**
     * 增加一个房间
     * @param hotelName 酒店名称
     * @param type 类型
     * @param total 总共有的数量
     * @param price 价格
     * @param name 房间名称
     * @return 返回是否成功
     */
    public boolean addRoom(String hotelName, String type, int total, double price, String name);

    /**
     * 删除一个房间
     * @param hotelName 酒店名称
     * @param name 房间名称
     * @return 返回是否成功
     */
    public boolean deleteRoom(String hotelName, String name);

    /**
     * 这个是用来手动改的时候调用的
     * （线下有人入住的时候用这个）
     * num可以是负的（入住的时候传负数，退房的时候传正数）
     *
     * @param hotelName
     * @param type
     * @param num
     * @return
     */
    public boolean offlineOrder(String hotelName, String type, int num);

    /**
     * 订单状态修改时，应当调用此方法
     * 订单生成，订单撤销，订单异常时，都应该调用此方法
     * 订单生成时减少相应的房间数量
     * 异常和撤销时，增加房间数量
     *
     * @param orderVO 相关的订单
     * @param action 动作
     * @return 返回是否成功
     */
    public boolean changeRoomNum(OrderVO orderVO, UserOrderAction action);
}

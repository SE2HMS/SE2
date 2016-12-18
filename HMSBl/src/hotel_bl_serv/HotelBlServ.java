package hotel_bl_serv;

import VO.HotelVO;
import VO.OrderVO;
import VO.StrategyVO;
import hotel_bl_servlmpl.HotelBlServImpl;

import java.util.Iterator;

public interface HotelBlServ {

    public static HotelBlServ getInstance() {
        return new HotelBlServImpl();
    }

    /**
     * 得到某个酒店的信息
     *
     * @param name
     * @return
     */
    public HotelVO getHotelInfo(String name);

    /**
     * 增加一个酒店
     *
     * @param hotelName      酒店名
     * @param businessCIrcle 商圈
     * @param location       地址
     * @param intro          简介
     * @param star           星级
     * @return 返回是否成功
     */
    public boolean addHotel(String hotelName, int businessCIrcle, int location, String intro, double star);

    /**
     * 得到某个用户在一个酒店中的所有订单
     * 如果没有就返回空
     *
     * @param userId    用户id
     * @param hotelName 酒店名称
     * @return
     */
    public Iterator<OrderVO> getOrderInHotel(String userId, String hotelName);

    /**
     * 这个是搜索方法
     *
     * @param location       位置是大于一的，代表什么就查表吧，表先缓一缓
     * @param businesscircle 商圈同上
     * @param name           酒店名，只要含有就返回
     * @param haveOrdered    是否预定过
     * @param roomType       房间类型也查表吧
     * @param price          最低价格
     * @param roomNum        房间数量
     * @param inTime         入住时间
     * @param outTime        退房时间
     * @param starLevel      星级
     * @param commentLevel   最低评分
     * @return 返回符合条件的酒店的迭代器
     */
    public Iterator<HotelVO> search(String userId, int location, int businesscircle, String name, boolean haveOrdered, int roomType, int price, int roomNum, int inTime, int outTime, int starLevel, int commentLevel);

    /**
     * 修改酒店信息
     *
     * @param hotelName      酒店名
     * @param star           星级
     * @param location       位置
     * @param businessCircle 商圈
     * @param intro          简介
     * @return 返回是否成功
     */
    public boolean modifyHotelInfo(String hotelName, double star, int location, int businessCircle, String intro);

    /**
     * 得到某个酒店所有的策略
     *
     * @param hotelName 酒店名
     * @return 酒店的策略表迭代器
     */
    public Iterator<StrategyVO> getStrategyList(String hotelName);

    /**
     * 得到某个商圈所有酒店的信息
     *
     * @return 返回酒店VO的迭代器
     */
    public Iterator<HotelVO> getAllHotel(String businessCircle);
}

package hotel_bl_servlmpl;

import PO.HotelPO;
import PO.HotelStrategyPO;
import PO.RoomPO;
import VO.CommentVO;
import VO.HotelVO;
import VO.RoomVO;
import VO.StrategyVO;
import hotel_bl_serv.HotelBlServ;
import rmi.RemoteHelper;

import java.util.ArrayList;
import java.util.Iterator;

public class HotelBlServlmpl implements HotelBlServ{
    @Override
    public HotelVO getHotelInfo(String name) {
        HotelPO hotelPO = null;
        try {
            hotelPO = RemoteHelper.getInstance().getHotelDataServ().getHotel(name);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(hotelPO == null) {
                return null;
            }
        }
        String CBD = hotelPO.getBC();
        String location = hotelPO.getAddress();
        ArrayList<String> comments = hotelPO.getComment();
        double star = hotelPO.getStars();
        String introduction = hotelPO.getINTRO();
        ArrayList<RoomPO> rooms = hotelPO.getRoom();
        ArrayList<String> cooperative = hotelPO.getCompanies();
        ArrayList<HotelStrategyPO> strategys = hotelPO.getStrategy();
        HotelVO hotelVO = new HotelVO(CBD,location,stringToComment(comments),(int)star,introduction,changeRoomToVO(rooms),cooperative,changeStrategyToVO(strategys));
        return hotelVO;
    }

    @Override
    public boolean modifyHotelInfo(HotelVO hotel) {
        return false;
    }

    @Override
    public Iterator<HotelVO> getHotelList(String id) {
        return null;
    }

    @Override
    public Iterator<HotelVO> getAllHotel() {
        return null;
    }

    /**
     * 尚未写好
     * @param comment
     * @return
     */
    private ArrayList<CommentVO> stringToComment(ArrayList<String> comment) {
        return new ArrayList<>();
    }

    /**
     * 没写
     * @param rooms
     * @return
     */
    private ArrayList<RoomVO> changeRoomToVO(ArrayList<RoomPO> rooms) {
        return new ArrayList<>();
    }

    /**
     * 还没开始写
     * @param strategys
     * @return
     */
    private ArrayList<StrategyVO> changeStrategyToVO(ArrayList<HotelStrategyPO> strategys) {
        return new ArrayList<>();
    }
}

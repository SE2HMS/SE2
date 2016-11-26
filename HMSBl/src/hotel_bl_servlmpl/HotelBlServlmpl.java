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

import java.rmi.RemoteException;
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
        String hotelName = hotelPO.getName();
        String CBD = hotelPO.getBC();
        String location = hotelPO.getAddress();
        ArrayList<String> comments = hotelPO.getComment();
        double star = hotelPO.getStars();
        String introduction = hotelPO.getINTRO();
        ArrayList<RoomPO> rooms = hotelPO.getRoom();
        ArrayList<String> cooperative = hotelPO.getCompanies();
        ArrayList<HotelStrategyPO> strategies = hotelPO.getStrategy();
        HotelVO hotelVO = new HotelVO(hotelName,CBD,location,stringToComment(comments),(int)star,introduction,changeRoomToVO(rooms),cooperative,changeStrategyToVO(strategies));
        return hotelVO;
    }

    @Override
    public boolean modifyHotelInfo(HotelVO hotel) {
        String hotelName = hotel.getName();
        String bc = hotel.getCBD();
        String intro = hotel.getIntro();
        String address = hotel.getLocation();
        double stars = hotel.getStarLevel();
        HotelPO hotelPO = new HotelPO(hotelName,bc,intro,address,stars);
        try {
            RemoteHelper.getInstance().getHotelDataServ().modifiedHotel(hotelPO);
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
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
     * 写好了，把字符串型的评论转化成CommentVO
     * 将来希望可以改成带有用户名的
     * @param comment
     * @return
     */
    private ArrayList<CommentVO> stringToComment(ArrayList<String> comment) {
        ArrayList<CommentVO> commentVOs = new ArrayList<>();
        for(String oneComment:comment) {
            CommentVO commentVO = new CommentVO(oneComment);
            commentVOs.add(commentVO);
        }
        return commentVOs;
    }

    /**
     * 写好了，用来把PO变成VO
     * 反正是私有方法随便打打注释应该不会死吧
     * @param rooms 要变的PO
     * @return
     */
    private ArrayList<RoomVO> changeRoomToVO(ArrayList<RoomPO> rooms) {
        ArrayList<RoomVO> roomVOs = new ArrayList<>();
        for(RoomPO roomPO:rooms) {
            String roomType = roomPO.getType();
            int price = (int)roomPO.getPrice();
            int total = roomPO.getTotel();
            int[] available = roomPO.getNum();
            RoomVO roomVO = new RoomVO(roomType,price,total,available);
            roomVOs.add(roomVO);
        }
        return roomVOs;
    }

    /**
     * 还没开始写，用来吧PO变成VO
     * @param strategys 需要变的策略类的PO
     * @return
     */
    private ArrayList<StrategyVO> changeStrategyToVO(ArrayList<HotelStrategyPO> strategys) {
        ArrayList<StrategyVO> strategyVOs = new ArrayList<>();
        for(HotelStrategyPO hotelStrategyPO:strategys) {

        }
        return new ArrayList<>();
    }
}

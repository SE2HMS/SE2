package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

import DataService.*;
import PO.CommentPO;
import PO.CreditPO;
import PO.HotelPO;
import PO.HotelStrategyPO;
import PO.OrderPO;
import PO.RoomPO;
import PO.UserPO;
import PO.WebStrategyPO;
import comment_data_servlmpl.CommentDataServlmpl;
import credit_data_servlmpl.CreditDataServlmpl;
import hotel_data_servlmpl.HotelDataServlmpl;
import order_data_servlmpl.OrderDataServlmpl;
import room_data_servlmpl.RoomDataServlmpl;
import strategy_data_servlmpl.HotelStrategyDataServlmpl;
import strategy_data_servlmpl.WebStrategyDataServlmpl;
import time_serv_impl.TimeServImpl;
import user_data_servlmpl.UserDataServlmpl;

public class DataRemoteObject extends UnicastRemoteObject implements
        CreditDataServ, HotelDataServ, OrderDataServ, RoomDataServ, HotelStrategyDataServ, WebStrategyDataServ, UserDataServ, CommentDataServ, TimeServ {


    /**
     *
     */
    private static final long serialVersionUID = 2249679312872830667L;
    private CreditDataServ creditDataServ;
    private HotelDataServ hotelDataServ;
    private OrderDataServ orderDataServ;
    private RoomDataServ roomDataServ;
    private HotelStrategyDataServ hotelStrategyDataServ;
    private WebStrategyDataServ webStrategyDataServ;
    private UserDataServ userDataServ;
    private CommentDataServ commentDataServ;
    private TimeServ timeServ;


    protected DataRemoteObject() throws RemoteException {
        creditDataServ = CreditDataServlmpl.getInstance();
        hotelDataServ = HotelDataServlmpl.getInstance();
        orderDataServ = OrderDataServlmpl.getIntanse();
        roomDataServ = RoomDataServlmpl.getInstance();
        hotelStrategyDataServ = HotelStrategyDataServlmpl.getInstance();
        webStrategyDataServ = WebStrategyDataServlmpl.getInstance();
        userDataServ = UserDataServlmpl.getInstance();
        commentDataServ = CommentDataServlmpl.getInstance();
        timeServ = TimeServImpl.getInstance();
    }

    @Override
    public boolean insertUser(UserPO user) throws RemoteException {
        return userDataServ.insertUser(user);

    }

    @Override
    public UserPO getUser(String id) throws RemoteException {
        return userDataServ.getUser(id);
    }

    @Override
    public boolean deleteUser(String id) throws RemoteException {
        return userDataServ.deleteUser(id);

    }

    @Override
    public boolean modifiedUser(UserPO user) throws RemoteException {
        return userDataServ.modifiedUser(user);
    }

    @Override
    public ArrayList<UserPO> getUserList() throws RemoteException {
        return userDataServ.getUserList();
    }

    @Override
    public ArrayList<RoomPO> getRoomByType(String hotelname, String type) throws RemoteException {
        return roomDataServ.getRoomByType(hotelname, type);
    }

    @Override
    public boolean insertWebStrategy(WebStrategyPO strategyPO) throws RemoteException {
        return webStrategyDataServ.insertWebStrategy(strategyPO);
    }

    @Override
    public ArrayList<WebStrategyPO> getWebStrategyList() throws RemoteException {
        return webStrategyDataServ.getWebStrategyList();
    }

    @Override
    public boolean modifiedWebStrategy(WebStrategyPO strategy) throws RemoteException {
        return webStrategyDataServ.modifiedWebStrategy(strategy);
    }

    @Override
    public boolean insertHotelStrategy(HotelStrategyPO strategyPO) throws RemoteException {
        return hotelStrategyDataServ.insertHotelStrategy(strategyPO);
    }

    @Override
    public ArrayList<HotelStrategyPO> getHotelStrategyList(String hotelname) throws RemoteException {
        return hotelStrategyDataServ.getHotelStrategyList(hotelname);
    }

    @Override
    public boolean deleteHotelStrategy(HotelStrategyPO strategyPO) throws RemoteException {
        return hotelStrategyDataServ.deleteHotelStrategy(strategyPO);
    }

    @Override
    public boolean modifiedHotelStrategy(HotelStrategyPO strategy) throws RemoteException {
        return hotelStrategyDataServ.modifiedHotelStrategy(strategy);
    }

    @Override
    public RoomPO getRoom(String hotelname, String type) throws RemoteException {
        return roomDataServ.getRoom(hotelname, type);
    }

    @Override
    public ArrayList<RoomPO> getRoomList(String hotelname) throws RemoteException {
        return roomDataServ.getRoomList(hotelname);
    }

    @Override
    public boolean insertRoom(RoomPO room) throws RemoteException {
        return roomDataServ.insertRoom(room);
    }

    @Override
    public boolean deleteRoom(RoomPO room) throws RemoteException {
        return roomDataServ.deleteRoom(room);
    }

    @Override
    public boolean modifiedRoom(RoomPO room) throws RemoteException {
        return roomDataServ.modifiedRoom(room);
    }

    @Override
    public boolean insertOrder(OrderPO po) throws RemoteException {
        return orderDataServ.insertOrder(po);
    }

    @Override
    public boolean modifiedOrder(OrderPO po) throws RemoteException {
        return orderDataServ.modifiedOrder(po);
    }


    @Override
    public boolean insertHotel(HotelPO hotel) throws RemoteException {
        return hotelDataServ.insertHotel(hotel);
    }

    @Override
    public HotelPO getHotel(String name) throws RemoteException {
        return hotelDataServ.getHotel(name);
    }


    @Override
    public boolean modifiedHotel(HotelPO hotel) throws RemoteException {
        return hotelDataServ.modifiedHotel(hotel);
    }

    @Override
    public ArrayList<HotelPO> getHotelList(String businesscircle) throws RemoteException {
        return hotelDataServ.getHotelList(businesscircle);
    }


    @Override
    public ArrayList<CreditPO> getDetial(String id) throws RemoteException {
        return creditDataServ.getDetial(id);
    }

    @Override
    public boolean insertCredit(CreditPO credit) throws RemoteException {
        return creditDataServ.insertCredit(credit);
    }


    @Override
    public ArrayList<CommentPO> getComments(String hotelname) throws RemoteException {
        return commentDataServ.getComments(hotelname);
    }

    @Override
    public boolean insert(CommentPO c) throws RemoteException {
        return commentDataServ.insert(c);
    }

    @Override
    public boolean delete(CommentPO c) throws RemoteException {
        return commentDataServ.delete(c);
    }

    @Override
    public boolean deleteWebStrategy(WebStrategyPO w) throws RemoteException {
        return webStrategyDataServ.deleteWebStrategy(w);
    }

    @Override
    public OrderPO getOrder(String OrderID) throws RemoteException {
        return orderDataServ.getOrder(OrderID);
    }

    @Override
    public ArrayList<OrderPO> getOrders(String id) throws RemoteException {
        return orderDataServ.getOrders(id);
    }

    @Override
    public ArrayList<OrderPO> getAllOrders() throws RemoteException {
        return orderDataServ.getAllOrders();
    }

    @Override
    public UserPO getUser(String userName, String contact) throws RemoteException {
        return userDataServ.getUser(userName, contact);
    }

    @Override
    public ArrayList<HotelPO> getAllHotel() throws RemoteException {
        return hotelDataServ.getAllHotel();
    }

    @Override
    public Date getTime() throws RemoteException {
        return timeServ.getTime();
    }
}

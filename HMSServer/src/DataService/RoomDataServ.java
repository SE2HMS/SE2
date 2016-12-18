package DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.RoomPO;

public interface RoomDataServ extends Remote {
    public RoomPO getRoom(String hotelname, String name) throws RemoteException;

    public ArrayList<RoomPO> getRoomByType(String hotelname, String type) throws RemoteException;

    public ArrayList<RoomPO> getRoomList(String hotelname) throws RemoteException;

    public boolean insertRoom(RoomPO room) throws RemoteException;

    public boolean deleteRoom(RoomPO room) throws RemoteException;

    public boolean modifiedRoom(RoomPO room) throws RemoteException;
}

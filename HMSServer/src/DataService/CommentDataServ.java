package DataService;

import PO.CommentPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/28.
 */
public interface CommentDataServ {
    public ArrayList<CommentPO> getComments(String hotelname) throws RemoteException;

    public boolean insert(CommentPO c) throws RemoteException;

    public boolean delete(CommentPO c) throws RemoteException;
}

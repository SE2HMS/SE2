package DataService;

import java.rmi.RemoteException;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/12.
 */
public interface TimeServ {
    public Date getTime() throws RemoteException;
}

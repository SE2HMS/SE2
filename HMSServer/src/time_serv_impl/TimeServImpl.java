package time_serv_impl;

import DataService.TimeServ;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/12.
 */
public class TimeServImpl implements TimeServ{
    private static TimeServImpl timeServ;

    private TimeServImpl(){}

    public static TimeServImpl getInstance() {
        if(timeServ == null) {
            timeServ = new TimeServImpl();
        }
        return timeServ;
    }

    @Override
    public Date getTime() throws RemoteException {
        return Calendar.getInstance().getTime();
    }
}

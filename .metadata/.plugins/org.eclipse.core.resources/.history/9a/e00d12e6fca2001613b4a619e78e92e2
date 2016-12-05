package rmi;

import java.rmi.Remote;

import DataService.HotalDataServ;
import DataService.hotelStrategyDataImpl;
import DataService.memberStrategyDataImpl;
import DataService.revokOrderDataServ;
import credit_data_serv.CreditDataServ;
import order_data_serv.OrderDataServ;
import order_data_serv.abnormalOrderDataServ;
import order_data_serv.normalOrderDataServ;
import room_data_serv.RoomDataServ;
import user_data_serv.UserDataServ;

public class RemoteHelper {
	private Remote remote;
	private static RemoteHelper remoteHelper = new RemoteHelper();
	
	public static RemoteHelper getInstance() {
		return remoteHelper;
	}
	
	public RemoteHelper() {}
	
	public void setRemote(Remote remote) {
		this.remote = remote;
	}
	
	public CreditDataServ getCreditDataServ() {
		return (CreditDataServ) remote;
	}
	
	public HotalDataServ getHotelDataServ() {
		return (HotalDataServ) remote;
	}
	
	public OrderDataServ getOrderDataServ() {
		return (OrderDataServ) remote;
	}
	
	public normalOrderDataServ getNormalOrderDataServ() {
		return (normalOrderDataServ) remote;
	}
	
	public abnormalOrderDataServ getAbnormalOrderDataServ() {
		return (abnormalOrderDataServ) remote;
	}
	
	public revokOrderDataServ getRevokOrderDataServ() {
		return (revokOrderDataServ) remote;
	}
	
	public RoomDataServ getRoomDataServ() {
		return (RoomDataServ) remote;
	}
	
	public hotelStrategyDataImpl getHotelStrategyDataImpl() {
		return (hotelStrategyDataImpl) remote;
	}
	
	public memberStrategyDataImpl getMemberStrategyDataImpl() {
		return (memberStrategyDataImpl) remote;
	}
	
	public UserDataServ getUserDateServ() {
		return (UserDataServ) remote;
	}
}

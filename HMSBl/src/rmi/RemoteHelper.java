package rmi;

import java.rmi.Remote;

import DataService.*;

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
	
	public WebStrategyDataImpl getMemberStrategyDataImpl() {
		return (WebStrategyDataImpl) remote;
	}
	
	public UserDataServ getUserDateServ() {
		return (UserDataServ) remote;
	}
}

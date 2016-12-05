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

	public CommentDataServ getCommentDataServ() {
		return (CommentDataServ) remote;
	}
	
	public HotalDataServ getHotelDataServ() {
		return (HotalDataServ) remote;
	}
	
	public OrderDataServ getOrderDataServ() {
		return (OrderDataServ) remote;
	}
	
	public RoomDataServ getRoomDataServ() {
		return (RoomDataServ) remote;
	}
	
	public HotelStrategyDataServ getHotelStrategyDataServ() {
		return (HotelStrategyDataServ) remote;
	}
	
	public WebStrategyDataServ getWebStrategyDataServ() {
		return (WebStrategyDataServ) remote;
	}
	
	public UserDataServ getUserDataServ() {
		return (UserDataServ) remote;
	}
}

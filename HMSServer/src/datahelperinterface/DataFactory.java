package datahelperinterface;

public interface DataFactory {
	public CommentDataHelper getCommentDataHelper();
	
	public CompaniesDataHelper getCompaniesDataHelper();
	
	public CreditDataHelper getCreditDataHelper();
	
	public HotelDataHelper getHotelDataHelper();
	
	public HotelStrategyDataHelper getHotelStrategyDataHelper();
	
	public OrderDataHelper getOrderDataHelper();
	
	public RoomDataHelper getRoomDataHelper();
	
	public SubOrderDataHelper getSubOrderDataHelper();
	
	public UserDataHelper getUserDataHelper();
	
	public WebStrategyDataHelper getWebStrategyDataHelper();
}

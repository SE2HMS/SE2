package datahelper;

import datahelperinterface.CommentDataHelper;
import datahelperinterface.CompaniesDataHelper;
import datahelperinterface.CreditDataHelper;
import datahelperinterface.DataFactory;
import datahelperinterface.HotelDataHelper;
import datahelperinterface.HotelStrategyDataHelper;
import datahelperinterface.OrderDataHelper;
import datahelperinterface.RoomDataHelper;
import datahelperinterface.SubOrderDataHelper;
import datahelperinterface.UserDataHelper;
import datahelperinterface.WebStrategyDataHelper;

public class DataFactorylmpl implements DataFactory{

	@Override
	public CommentDataHelper getCommentDataHelper() {
		CommentDataHelper datahelper=new CommentSqlDataHelper();
		return datahelper;
	}

	@Override
	public CompaniesDataHelper getCompaniesDataHelper() {
		CompaniesDataHelper datahelper=new CompaniesSqlDataHelper();
		return datahelper;
	}

	@Override
	public CreditDataHelper getCreditDataHelper() {
		CreditDataHelper datahelper=new CreditSqlDataHelper();
		return datahelper;
	}

	@Override
	public HotelDataHelper getHotelDataHelper() {
		HotelDataHelper datahelper=new HotelSqlDataHelper();
		return datahelper;
	}

	@Override
	public HotelStrategyDataHelper getHotelStrategyDataHelper() {
		HotelStrategyDataHelper datahelper=new HotelStrategySqlDataHelper()
;		return datahelper;
	}

	@Override
	public OrderDataHelper getOrderDataHelper() {
		OrderDataHelper datahelper=new OrderSqlDataHelper();
		return datahelper;
	}

	@Override
	public RoomDataHelper getRoomDataHelper() {
		RoomDataHelper datahelper=new RoomSqlDataHelper();
		return datahelper;
	}

	@Override
	public SubOrderDataHelper getSubOrderDataHelper() {
		SubOrderDataHelper datahelper=new SubOrderSqlDataHelper();
		return datahelper;
	}

	@Override
	public UserDataHelper getUserDataHelper() {
		UserDataHelper datahelper=new UserSqlDataHelper();
		return datahelper;
	}

	@Override
	public WebStrategyDataHelper getWebStrategyDataHelper() {
		WebStrategyDataHelper datahelper=new WebStrategySqlDataHelper();
		return datahelper;
	}

}

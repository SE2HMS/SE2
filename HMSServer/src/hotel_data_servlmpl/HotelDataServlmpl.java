package hotel_data_servlmpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import DataService.CommentDataServ;
import DataService.HotelDataServ;
import DataService.HotelStrategyDataServ;
import DataService.RoomDataServ;
import PO.CommentPO;
import PO.HotelPO;
import comment_data_servlmpl.CommentDataServlmpl;
import datahelper.CompaniesDataServlmpl;
import datahelper.Company;
import datahelper.DataFactorylmpl;
import datahelperinterface.CompaniesDataServ;
import datahelperinterface.DataFactory;
import datahelperinterface.HotelDataHelper;
import room_data_servlmpl.RoomDataServlmpl;
import strategy_data_servlmpl.HotelStrategyDataServlmpl;

public class HotelDataServlmpl implements HotelDataServ{
	private ArrayList<HotelPO> list;
	private DataFactory dataFactory;
	private CommentDataServ commentDataServ;
	private CompaniesDataServ companiesDataServ;
	private RoomDataServ roomDataServ;
	private HotelStrategyDataServ hotelStrategyDataServ;
	private HotelDataHelper hotelDataHelper;
	private static HotelDataServlmpl hotelDataServlmpl;
	
	public static HotelDataServlmpl getInstance(){
		if(hotelDataServlmpl==null)
			hotelDataServlmpl=new HotelDataServlmpl();
		return hotelDataServlmpl;
	}
	
	private HotelDataServlmpl(){
		if(list==null){
			dataFactory=new DataFactorylmpl();
			commentDataServ=CommentDataServlmpl.getInstance();
			companiesDataServ=CompaniesDataServlmpl.getInstance();
			roomDataServ=RoomDataServlmpl.getInstance();
			hotelStrategyDataServ=HotelStrategyDataServlmpl.getInstance();
			hotelDataHelper=dataFactory.getHotelDataHelper();
			list=hotelDataHelper.getAll();
		}
	}

	@Override
	public boolean insertHotel(HotelPO hotel) throws RemoteException {
		boolean tag=true;
		
		if(hotel.getComment()!=null)
		for(int i=0;i<hotel.getComment().size();i++){
			if(!tag)
				break;
			tag=commentDataServ.insert(new CommentPO(hotel.getName(),hotel.getComment().get(i)));
		}
		
		if(hotel.getCompanies()!=null)
		for(int i=0;i<hotel.getCompanies().size();i++){
			if(!tag)
				break;
			tag=companiesDataServ.insertCompany(new Company(hotel.getName(),hotel.getCompanies().get(i)));
		}
		
		if(hotel.getRoom()!=null)
		for(int i=0;i<hotel.getRoom().size();i++){
			if(!tag)
				break;
			tag=roomDataServ.insertRoom(hotel.getRoom().get(i));
		}
		
		if(hotel.getStrategy()!=null)
		for(int i=0;i<hotel.getStrategy().size();i++){
			if(!tag)
				break;
			tag=hotelStrategyDataServ.insertHotelStrategy(hotel.getStrategy().get(i));
		}
		
		int i=hotelDataHelper.insert(hotel);
		if(i==0)
			tag=false;
		else{
			list=hotelDataHelper.getAll();
		}
		return tag;
	}

	@Override
	public HotelPO getHotel(String name) throws RemoteException {
		HotelPO hotel=null;
		ArrayList<String> comments = new ArrayList<String>(),companies=new ArrayList<String>();
		ArrayList<CommentPO> commentPOList;
		ArrayList<Company> companyList;
		for(int i=0;i<list.size();i++){
			if(list.get(i).getName().equals(name)){
				hotel=list.get(i);
				break;
			}
		}
		if(hotel==null)
			return hotel;
		commentPOList=commentDataServ.getComments(hotel.getName());
		companyList=companiesDataServ.getCompanies(hotel.getName());
		for(int i=0;i<commentPOList.size();i++){
			comments.add(commentPOList.get(i).getDetials());
		}
		for(int i=0;i<companyList.size();i++){
			companies.add(companyList.get(i).getCompanies());
		}
		hotel.setComment(comments);
		hotel.setCompanies(companies);
		hotel.setRomm(roomDataServ.getRoomList(hotel.getName()));
		hotel.setStrategy(hotelStrategyDataServ.getHotelStrategyList(hotel.getName()));
		return hotel;
	}


	@Override
	public boolean modifiedHotel(HotelPO hotel) throws RemoteException {
		int i=hotelDataHelper.update(hotel);
		if(i==0)
			return false;
		else{
			list=hotelDataHelper.getAll();
			return true;
		}
	}

	@Override
	public ArrayList<HotelPO> getHotelList(String businesscircle) throws RemoteException {
		ArrayList<HotelPO> res=new ArrayList<HotelPO>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getBC().equals(businesscircle)){
				res.add(this.getHotel(list.get(i).getName()));
			}
		}
		return res;
	}

	@Override
	public ArrayList<HotelPO> getAllHotel() throws RemoteException {
		ArrayList<HotelPO> res=this.list;
		return res;
	}

}

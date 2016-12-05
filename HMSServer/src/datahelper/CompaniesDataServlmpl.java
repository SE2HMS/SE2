package datahelper;

import java.util.ArrayList;

import datahelperinterface.CompaniesDataHelper;
import datahelperinterface.CompaniesDataServ;
import datahelperinterface.DataFactory;

public class CompaniesDataServlmpl implements CompaniesDataServ{
	private	ArrayList<Company> list;
	private DataFactory dataFactory;
	private CompaniesDataHelper companiesDataHelper;
	private static CompaniesDataServlmpl companiesDataServlmpl;
	
	public static CompaniesDataServlmpl getInstance(){
		if(companiesDataServlmpl==null)
			companiesDataServlmpl=new CompaniesDataServlmpl();
		return companiesDataServlmpl;
	}
	
	private CompaniesDataServlmpl(){
		if(list==null){
			dataFactory=new DataFactorylmpl();
			companiesDataHelper=dataFactory.getCompaniesDataHelper();
			list=companiesDataHelper.getAll();
		}
	}

	@Override
	public ArrayList<Company> getCompanies(String hotelname) {
		ArrayList<Company> res=new ArrayList<Company>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getHotelName().equals(hotelname))
				res.add(list.get(i));
		}
		return res;
	}

	@Override
	public boolean insertCompany(Company c) {
		int i=companiesDataHelper.insert(c);
		if(i==0)
			return false;
		else{
			list=companiesDataHelper.getAll();
			return true;
		}
	}	

}

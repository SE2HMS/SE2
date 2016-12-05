package datahelperinterface;

import java.util.ArrayList;

import datahelper.Company;

public interface CompaniesDataServ {
	public ArrayList<Company> getCompanies(String hotelname);
	
	public boolean insertCompany(Company c);
}

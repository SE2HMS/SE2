package datahelperinterface;

import java.util.ArrayList;

import datahelper.Company;

public interface CompaniesDataHelper {
	public ArrayList<Company> getAll();
	
	public int insert(Company c);
}

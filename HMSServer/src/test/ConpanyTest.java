package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datahelper.CompaniesDataServlmpl;
import datahelper.Company;
import datahelperinterface.CompaniesDataServ;

public class ConpanyTest {
	private CompaniesDataServ companiesDataServ;
	private Company c1,c2;
	
	@Before
	public void setup(){
		companiesDataServ=CompaniesDataServlmpl.getInstance();
		c1=new Company("hotel","nju");
		c2=new Company("hotel1","edu");
	}

	@Test
	public void test1() {
		assertTrue(companiesDataServ.insertCompany(c1));
	}
	
	@Test
	public void test2(){
		companiesDataServ.insertCompany(c2);
		assertEquals(companiesDataServ.getCompanies("hotel").get(0).getCompanies(),"nju");
	}

}

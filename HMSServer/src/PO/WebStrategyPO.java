package PO;

import java.io.Serializable;

public class WebStrategyPO implements Serializable{
	String strategyname,businesscircle,date;
	double lev0,lev1,discount;//typeΪlevel��ʱ��lev0ָ���ǻ�Ա�����������ã�typeΪbusinesscircleʱ��lev0ָ���ǵ�ǰ����Ȧ�û�Ա�ȼ����ۿۣ���Ա��Ϊ���и���������������Ա���ۿ۴洢��discount����typeΪdate��ʱ��levΪȱʡ��discount��¼������ڵ��ۿۣ�ע�Ϳ�����������
	String type;
	
	public WebStrategyPO(String sn,String bn,String date,double lev0,double lev1,double discount,
			String t){
		this.strategyname=sn;
		this.businesscircle=bn;
		this.lev0=lev0;
		this.lev1=lev1;
		this.date=date;
		this.discount=discount;
		this.type=t;
	}
	
	public String getStrategyName(){
		return this.strategyname;
	}
	
	public String getBusinessCircle(){
		return this.businesscircle;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public double getLev0(){
		return this.lev0;
	}
	
	public double getLev1(){
		return this.lev1;
	}
	
	public double getDiscount(){
		return this.discount;
	}
	
	public String getType(){
		return this.type;
	}
}

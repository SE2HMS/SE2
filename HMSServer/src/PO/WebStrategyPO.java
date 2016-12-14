package PO;

import java.io.Serializable;

public class WebStrategyPO implements Serializable{
	String strategyname,businesscircle,date;
	double lev0,lev1,discount;//type为level的时候，lev0指的是会员升级所需信用，type为businesscircle时，lev0指的是当前该商圈该会员等级的折扣，会员分为低中高三级，第三级会员的折扣存储在discount里，最后type为date的时候，lev为缺省，discount记录这个日期的折扣，注释看不懂就问我
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

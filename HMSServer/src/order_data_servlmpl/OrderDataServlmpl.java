package order_data_servlmpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import DataService.OrderDataServ;
import PO.OrderPO;
import datahelper.DataFactorylmpl;
import datahelper.SubOrder;
import datahelper.SubOrderDataServlmpl;
import datahelperinterface.DataFactory;
import datahelperinterface.OrderDataHelper;
import datahelperinterface.SubOrderDataServ;

public class OrderDataServlmpl implements OrderDataServ{
	private ArrayList<OrderPO> list;
	private DataFactory dataFactory;
	private OrderDataHelper orderDataHelper;
	private SubOrderDataServ subOrderDataServ;
	private static OrderDataServlmpl orderDataServlmpl;
	
	public static OrderDataServlmpl getIntanse(){
		if(orderDataServlmpl==null)
			orderDataServlmpl=new OrderDataServlmpl();
		return orderDataServlmpl;
	}
	
	private OrderDataServlmpl(){
		if(list==null){
			dataFactory=new DataFactorylmpl();
			orderDataHelper=dataFactory.getOrderDataHelper();
			list=orderDataHelper.getAll();
			subOrderDataServ=SubOrderDataServlmpl.getInstance();
		}
	}

	@Override
	public boolean insertOrder(OrderPO po) throws RemoteException {
		boolean tag=true;
		if(po.getRoom()!=null)
		for(int i=0;i<po.getRoom().size();i++){
			if(!tag)
				break;
			subOrderDataServ.insertSubOrder(new SubOrder(po.getID(),po.getRoom().get(i),(int)po.getNum().get(i),
					(double)po.getPrice().get(i),(double)po.getSubtotel().get(i)));
		}
		if(tag){
			int i=orderDataHelper.insert(po);
			if(i==0)
				tag=false;
			else{
				list=orderDataHelper.getAll();
			}
		}
		return tag;
	}

	@Override
	public boolean modifiedOrder(OrderPO po) throws RemoteException {
		int i=orderDataHelper.update(po);
		if(i==0)
			return false;
		else{
			list=orderDataHelper.getAll();
			return true;
		}
	}

	@Override
	public OrderPO getOrder(String OrderID) throws RemoteException {
		OrderPO order=null;
		for(int i=0;i<list.size();i++){
			if(list.get(i).getID().equals(OrderID)){
				order=list.get(i);
			}
		}
		if(order==null)
			return order;
		ArrayList<String> room=new ArrayList<String>();
		ArrayList<Integer> num=new ArrayList<Integer>();
		ArrayList<Double> price=new ArrayList<Double>(),subtotel=new ArrayList<Double>();
		ArrayList<SubOrder> suborder=subOrderDataServ.getSubOrders(order.getID());
		for(int i=0;i<suborder.size();i++){
			room.add(suborder.get(i).getRoomType());
			num.add(suborder.get(i).getNum());
			price.add(suborder.get(i).getPrice());
			subtotel.add(suborder.get(i).getSubTotel());
		}
		order.setRoom(room);
		order.setNum(num);
		order.setPrice(price);
		order.setSubtotel(subtotel);
		return order;
	}

	@Override
	public ArrayList<OrderPO> getOrders(String id) throws RemoteException {
		ArrayList<OrderPO> res=new ArrayList<OrderPO>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getUserID().equals(id)){
				res.add(this.getOrder(list.get(i).getID()));
			}
		}
		return res;
	}

	@Override
	public ArrayList<OrderPO> getAllOrders() throws RemoteException {
		ArrayList<OrderPO> res=new ArrayList<OrderPO>();
		
		for(int i=0;i<list.size();i++)
			res.add(this.getOrder(list.get(i).getID()));

		return res;
	}

}

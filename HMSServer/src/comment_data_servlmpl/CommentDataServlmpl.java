package comment_data_servlmpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import DataService.CommentDataServ;
import PO.CommentPO;
import datahelper.DataFactorylmpl;
import datahelperinterface.CommentDataHelper;
import datahelperinterface.DataFactory;

public class CommentDataServlmpl implements CommentDataServ{
	private ArrayList<CommentPO> list;
	private CommentDataHelper commentDataHelper;
	private DataFactory dataFactory;
	private static CommentDataServlmpl commentDataServlmpl;
	
	public static CommentDataServlmpl getInstance(){
		if(commentDataServlmpl==null)
			commentDataServlmpl=new CommentDataServlmpl();
		return commentDataServlmpl;
	}
	
	private CommentDataServlmpl(){
		if(list==null){
			dataFactory=new DataFactorylmpl();
			commentDataHelper=dataFactory.getCommentDataHelper();
			list=commentDataHelper.getAll();
		}
	}

	@Override
	public ArrayList<CommentPO> getComments(String hotelname) throws RemoteException{
		ArrayList<CommentPO> res=new ArrayList<CommentPO>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getHotelName().equals(hotelname)){
				res.add(list.get(i));
			}
		}
		return res;
	}

	@Override
	public boolean insert(CommentPO c) throws RemoteException{
		int i=commentDataHelper.insert(c);
		if(i==0)
			return false;
		else{
			list=commentDataHelper.getAll();
			return true;
		}
	}

	@Override
	public boolean delete(CommentPO c) throws RemoteException{
		int i=commentDataHelper.delete(c);
		if(i==0)
			return false;
		else{
			list=commentDataHelper.getAll();
			return true;
		}
	}

}

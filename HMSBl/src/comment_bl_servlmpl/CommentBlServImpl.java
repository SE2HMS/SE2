package comment_bl_servlmpl;

import DataService.CommentDataServ;
import PO.CommentPO;
import VO.HotelVO;
import VO.UserVO;
import comment_bl_serv.CommentBlServ;
import rmi.RemoteHelper;

import java.rmi.RemoteException;

public class CommentBlServImpl implements CommentBlServ{

	/**
	 * 暂时没动，数据层似乎木有接口
	 * @param user
	 * @param hotel
	 * @param comment
     */
	public boolean comment(UserVO user, HotelVO hotel, String comment) {
		CommentDataServ commentDataServ;
		boolean success = false;
		try {
			commentDataServ = RemoteHelper.getInstance().getCommentDataServ();
			CommentPO commentPO = new CommentPO(hotel.getName(),comment);
			success = commentDataServ.insert(commentPO);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return success;
	}
}

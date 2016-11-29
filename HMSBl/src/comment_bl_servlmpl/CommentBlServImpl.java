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
	 * 评论，需要改成匿名的评论，不过还是加上一个匿名评论吧，这个就不用了
	 * @param user 用户
	 * @param hotel 酒店
	 * @param comment 评论内容
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

	@Override
	public boolean comment(HotelVO hotel, String comment) {
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

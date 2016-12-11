package comment_bl_servlmpl;

import DataService.CommentDataServ;
import PO.CommentPO;
import VO.HotelVO;
import comment_bl_serv.CommentBlServ;
import rmi.RemoteHelper;

import java.rmi.RemoteException;

/**
 * 12.3更新
 */
public class CommentBlServImpl implements CommentBlServ{

	private boolean comment(HotelVO hotel, String comment) {
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
	/**
	 * 分数还要加上去
	 */
	public boolean comment(String hotelName, String comment, double commentLevel) {
		boolean success = false;
		try {
			CommentPO commentPO = new CommentPO(hotelName,comment);
			success = RemoteHelper.getInstance().getCommentDataServ().insert(commentPO);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return success;
	}
}

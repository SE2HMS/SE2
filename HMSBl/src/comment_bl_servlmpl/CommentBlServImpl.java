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

	@Override
	/**
	 * 分数还要加上去
	 */
	public boolean comment(String hotelName, String comment, double commentLevel) {
		boolean success = false;
		try {
			CommentPO commentPO = new CommentPO(hotelName,comment,commentLevel);
			success = RemoteHelper.getInstance().getCommentDataServ().insert(commentPO);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return success;
	}
}

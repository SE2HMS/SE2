package comment_bl_servlmpl;

import PO.CommentPO;
import comment_bl_serv.CommentBlServ;
import rmi.RemoteHelper;

import java.rmi.RemoteException;

/**
 * 12.3更新
 */
public class CommentBlServImpl implements CommentBlServ{

	@Override
	public boolean comment(String hotelName, String comment, double commentLevel) {
		if(hotelName == null || comment == null || (commentLevel < 0 || commentLevel >5)) {
			return false;
		}
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

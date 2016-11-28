package comment_bl_serv;

import VO.HotelVO;
import VO.UserVO;
import comment_bl_servlmpl.CommentBlServImpl;

public interface CommentBlServ {
	public static CommentBlServ getInstance() {
		return new CommentBlServImpl();
	}

	public void comment(UserVO user, HotelVO hotel, String comment);
}

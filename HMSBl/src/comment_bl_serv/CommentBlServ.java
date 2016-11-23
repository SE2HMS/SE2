package comment_bl_serv;

import VO.HotelVO;
import VO.UserVO;

public interface CommentBlServ {
	public void comment(UserVO user, HotelVO hotel, String comment);
}

package comment_bl_serv;

import VO.HotelVO;
import VO.UserVO;
import comment_bl_servlmpl.CommentBlServImpl;

public interface CommentBlServ {

	/**
	 * 得到此接口的一个实现的实例
	 * @return
     */
	public static CommentBlServ getInstance() {
		return new CommentBlServImpl();
	}

	/**
	 * 用户对酒店进行评论，返回是否成功
	 * @param user 用户
	 * @param hotel 酒店
	 * @param comment 评论细节
     * @return
     */
	public boolean comment(UserVO user, HotelVO hotel, String comment);
}

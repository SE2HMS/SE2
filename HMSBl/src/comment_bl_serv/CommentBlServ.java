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
	 * 这个是匿名评论
	 * @param hotel 酒店
	 * @param comment 评论内容
     * @return
     */
	public boolean comment(HotelVO hotel, String comment);
}

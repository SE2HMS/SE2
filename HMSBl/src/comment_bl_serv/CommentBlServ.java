package comment_bl_serv;

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
	 * 评论
	 * @param hotelName
	 * @param comment
	 * @param commentLevel
     * @return
     */
	public boolean comment(String hotelName,String comment,double commentLevel);
}

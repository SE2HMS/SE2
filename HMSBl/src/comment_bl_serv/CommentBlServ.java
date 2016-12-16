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
	 * 评论，给酒店加一条评论，评论是匿名的
	 * @param hotelName 酒店名称
	 * @param comment 评论内容
	 * @param commentLevel 评分（0~5）
     * @return 返回是否成功，如果酒店名，评论为空，或者评分范围不正确，返回false
     */
	public boolean comment(String hotelName,String comment,double commentLevel);
}

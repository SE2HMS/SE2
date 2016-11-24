package manage_bl_serv;

import VO.UserVO;

import java.util.Iterator;

public interface ManageBlServ {
	/**
	 * 得到所有用户的信息
	 * @return 返回一个迭代器
     */
	public Iterator<UserVO> getAllUserInfo();

	/**
	 * 增加一条用户信息
	 * @param user
	 * @return 返回是否成功
     */
	public boolean addUserInfo(UserVO user);

	/**
	 * 修改一个用户的信息
	 * @param user 新的用户，替换掉相同id的用户
	 * @return 返回是否成功
     */
	public boolean modifyUserInfo(UserVO user);

	/**
	 * 删除一个用户，只要传入id即可
	 * @param userId 用户的id
	 * @return 返回是否成功
     */
	public boolean delUserInfo(String userId);
}

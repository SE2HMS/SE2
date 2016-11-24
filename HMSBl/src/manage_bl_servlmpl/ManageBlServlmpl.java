package manage_bl_servlmpl;

import PO.UserPO;
import VO.UserVO;
import manage_bl_serv.ManageBlServ;

import java.util.Iterator;

public class ManageBlServlmpl implements ManageBlServ{

	@Override
	public Iterator<UserVO> getAllUserInfo() {
		return null;
	}

	@Override
	public boolean addUserInfo(UserVO user) {
		return false;
	}

	@Override
	public boolean modifyUserInfo(UserVO user) {
		return false;
	}

	@Override
	public boolean delUserInfo(String userId) {
		return false;
	}
}

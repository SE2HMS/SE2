package datahelperinterface;

import java.util.ArrayList;

import PO.CommentPO;

public interface CommentDataHelper {
	public ArrayList<CommentPO> getAll();
	
	public int insert(CommentPO c);
	
	public int delete(CommentPO c);
}

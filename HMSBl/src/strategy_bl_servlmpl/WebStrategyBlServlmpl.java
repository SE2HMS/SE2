package strategy_bl_servlmpl;

import PO.WebStrategyPO;
import VO.StrategyVO;
import helper.ParseHelper;
import rmi.RemoteHelper;
import strategy_bl_serv.WebStrategyBlServ;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 12.3检查
 */
public class WebStrategyBlServlmpl implements WebStrategyBlServ{

	@Override
	public Iterator<StrategyVO> getStrategy() {
		ArrayList<WebStrategyPO> webStrategyPOs;
		ArrayList<StrategyVO> strategyVOs = new ArrayList<>();
		try {
			webStrategyPOs = RemoteHelper.getInstance().getWebStrategyDataServ().getStrategyList();
			webStrategyPOs.forEach(webStrategyPO -> strategyVOs.add(ParseHelper.toStrategyVO(webStrategyPO)));
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return strategyVOs.iterator();
	}

	@Override
	public boolean addStrategy(StrategyVO strategy) {
		WebStrategyPO webStrategyPO = ParseHelper.toWebStrategyPO(strategy);
		boolean success = false;
		try {
			success = RemoteHelper.getInstance().getWebStrategyDataServ().insertStrategy(webStrategyPO);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean modifyStrategy(StrategyVO strategy) {
		WebStrategyPO webStrategyPO = ParseHelper.toWebStrategyPO(strategy);
		boolean success = false;
		try {
			success = RemoteHelper.getInstance().getWebStrategyDataServ().modifiedStrategy(webStrategyPO);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean delStrategy(StrategyVO strategy) {
		String id = "id";
		boolean success = false;
		try {
			success = RemoteHelper.getInstance().getWebStrategyDataServ().deleteStrategy(id);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return success;
	}
}

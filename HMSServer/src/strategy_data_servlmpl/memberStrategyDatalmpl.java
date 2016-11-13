package strategy_data_servlmpl;

import java.rmi.RemoteException;

import PO.StrategyPO;
import strategy_data_serv.hotelStrategyDataImpl;

public class memberStrategyDatalmpl implements hotelStrategyDataImpl{

	@Override
	public void insertStrategy(StrategyPO strategyPO) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StrategyPO[] getStrategyList() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStrategy(String id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifiedStrategy(StrategyPO strategy) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void init() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void finish() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}

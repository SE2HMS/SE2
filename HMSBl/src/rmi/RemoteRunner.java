package rmi;

import java.rmi.Naming;

public class RemoteRunner {
	private RemoteHelper remoteHelper;
	
	public RemoteRunner() {
		linkToServer();
	}
	
	private void linkToServer() {
		try{
			remoteHelper = RemoteHelper.getInstance();
			remoteHelper.setRemote(Naming.lookup("rmi://localhost:8080/DataRemoteObject"));
			System.out.println("linked");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
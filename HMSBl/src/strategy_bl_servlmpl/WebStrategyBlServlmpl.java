package strategy_bl_servlmpl;

import PO.WebStrategyPO;
import VO.*;
import helper.*;
import login_bl_serv.LoginBlServ;
import rmi.RemoteHelper;
import strategy_bl_serv.WebStrategyBlServ;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * 12.3检查
 */
public class WebStrategyBlServlmpl implements WebStrategyBlServ {

    @Override
    public Iterator<StrategyVO> getStrategy() {
        ArrayList<WebStrategyPO> webStrategyPOs;
        ArrayList<StrategyVO> strategyVOs = new ArrayList<>();
        try {
            webStrategyPOs = RemoteHelper.getInstance().getWebStrategyDataServ().getStrategyList();
            webStrategyPOs.forEach(webStrategyPO -> strategyVOs.add(ParseHelper.toStrategyVO(webStrategyPO)));
        } catch (RemoteException e) {
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
        } catch (RemoteException e) {
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
        } catch (RemoteException e) {
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
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return success;
    }

    private LevelStrategy getLevelStrategy() {
        LevelStrategy levelStrategy = null;
        Iterator<StrategyVO> strategyVOIterator = this.getStrategy();
        while (strategyVOIterator.hasNext()) {
            StrategyVO strategyVO = strategyVOIterator.next();
            if (strategyVO.getType().equals("level")) {
                levelStrategy = (LevelStrategy) strategyVO;
            }
        }
        return levelStrategy;
    }

    @Override
    public double getMinDiscount(String useId, Date inTime, String CBD) {
        if (useId == null) {
            return 1;
        }
        LevelStrategy levelStrategy = this.getLevelStrategy();
        UserVO userVO = LoginBlServ.getInstance().getUserInfo(useId);
        int level = userVO.getCredit() / levelStrategy.getUpgradeNum();
        if (level > 3) {
            level = 3;
        }
        Iterator<StrategyVO> strategyVOs = this.getStrategy();
        double min = 1;
        while (strategyVOs.hasNext()) {
            StrategyVO strategyVO = strategyVOs.next();
            if (strategyVO.getType().equals("date")) {
                DoubleElevenStrategy doubleElevenStrategy = (DoubleElevenStrategy) strategyVO;
                Date start = doubleElevenStrategy.getStartTime();
                Date end = doubleElevenStrategy.getEndTime();
                if (start.getTime() / 86400 <= inTime.getTime() / 86400 && inTime.getTime() / 86400 <= end.getTime() / 86400) {
                    min = Math.min(min, doubleElevenStrategy.getDiscount());
                }
            } else if (strategyVO.getType().equals("CBD")) {
                CBDStrategy cbdStrategy = (CBDStrategy) strategyVO;
                if (cbdStrategy.getCBD().equals(CBD)) {
                    double discount = 1;
                    if (level == 1) {
                        discount = cbdStrategy.getLevel0();
                    } else if (level == 2) {
                        discount = cbdStrategy.getLevel1();
                    } else if (level == 3) {
                        discount = cbdStrategy.getLevel2();
                    }
                    min = Math.min(min, discount);
                }
            }
        }
        return 0;
    }
}

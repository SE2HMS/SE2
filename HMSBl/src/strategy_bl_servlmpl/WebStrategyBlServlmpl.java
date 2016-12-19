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
            webStrategyPOs = RemoteHelper.getInstance().getWebStrategyDataServ().getWebStrategyList();
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
            success = RemoteHelper.getInstance().getWebStrategyDataServ().insertWebStrategy(webStrategyPO);
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
            success = RemoteHelper.getInstance().getWebStrategyDataServ().modifiedWebStrategy(webStrategyPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * 删除一条策略
     *
     * @param strategy 该策略的VO
     * @return 返回是否成功
     */
    private boolean delStrategy(StrategyVO strategy) {
        boolean success = false;
        try {
            WebStrategyPO webStrategyPO = ParseHelper.toWebStrategyPO(strategy);
            success = RemoteHelper.getInstance().getWebStrategyDataServ().deleteWebStrategy(webStrategyPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * 得到升级策略，由于只有一个，所以只要返回一个就可以了
     *
     * @return
     */
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
        int level = (int)(userVO.getCredit() / levelStrategy.getUpgradeNum());
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

    @Override
    public boolean addDoubleElevenStrategy(String strategyName, double discount, Date startTime, Date endTime) {
        DoubleElevenStrategy doubleElevenStrategy = new DoubleElevenStrategy(strategyName, discount, startTime, endTime);
        boolean success = this.addStrategy(doubleElevenStrategy);
        return success;
    }

    @Override
    public boolean addLevelStrategy(String strategyName, int upgradeCredit) {
        LevelStrategy levelStrategy = new LevelStrategy(strategyName, upgradeCredit);
        if (this.getLevelStrategy() != null) {
            this.delStrategy(this.getLevelStrategy());
        }
        boolean success = this.addStrategy(levelStrategy);
        return success;
    }

    /**
     * 得到一条网站营销策略，通过策略名来实现
     *
     * @param strategyName 策略名
     * @return 返回该策略
     */
    private StrategyVO getOneStrategy(String strategyName) {
        Iterator<StrategyVO> strategyVOIterator = this.getStrategy();
        while (strategyVOIterator.hasNext()) {
            StrategyVO strategyVO = strategyVOIterator.next();
            if (strategyVO.getType().equals("date")) {
                DoubleElevenStrategy strategy = (DoubleElevenStrategy) strategyVO;
                if (strategy.getName().equals(strategyName)) {
                    return strategy;
                }
            }
            if (strategyVO.getType().equals("level")) {
                LevelStrategy strategy = (LevelStrategy) strategyVO;
                if (strategy.getName().equals(strategyName)) {
                    return strategy;
                }
            }
            if (strategyVO.getType().equals("CBD")) {
                CBDStrategy strategy = (CBDStrategy) strategyVO;
                if (strategy.getName().equals(strategyName)) {
                    return strategy;
                }
            }
        }
        return null;
    }

    @Override
    public boolean addCBDStrategy(String name, double lev0, double lev1, double lev2, String CBD) {
        CBDStrategy cbdStrategy = new CBDStrategy(name, lev0, lev1, lev2, CBD);
        boolean success = this.addStrategy(cbdStrategy);
        return success;
    }

    @Override
    public boolean delStrategy(String strategyName) {
        StrategyVO strategyVO = this.getOneStrategy(strategyName);
        return this.delStrategy(strategyVO);
    }
}

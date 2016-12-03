package VO;

/**
 * Created by Administrator on 2016/12/3.
 */
public class LevelStrategy implements StrategyVO{
    private final String name;
    private final int upgradeNum;

    public LevelStrategy(String name,int upgradeNum) {
        this.name = name;
        this.upgradeNum = upgradeNum;
    }

    public String getName() {
        return name;
    }

    public int getUpgradeNum() {
        return upgradeNum;
    }

    @Override
    public String getType() {
        return "level";
    }
}

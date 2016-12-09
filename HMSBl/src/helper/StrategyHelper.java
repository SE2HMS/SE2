package helper;

import PO.HotelStrategyPO;
import PO.WebStrategyPO;
import VO.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/12/9.
 */
public class StrategyHelper {
    public static WebStrategyPO toWebStrategyPO(StrategyVO strategyVO) {
        WebStrategyPO webStrategyPO = null;
        String strategyName = null;
        String businessCircle = null;
        String date = null;
        double level0 = 0;
        double level1 = 0;
        double discount = 0;
        String type = null;
        switch (strategyVO.getType()) {
            case "level":
                LevelStrategy levelStrategy = (LevelStrategy) strategyVO;
                strategyName = levelStrategy.getName();
                level0 = levelStrategy.getUpgradeNum();
                type = "level";
                break;
            case "CBD":
                CBDStrategy cbdStrategy = (CBDStrategy) strategyVO;
                strategyName = cbdStrategy.getName();
                businessCircle = cbdStrategy.getCBD();
                level0 = cbdStrategy.getLevel0();
                level1 = cbdStrategy.getLevel1();
                discount = cbdStrategy.getLevel2();
                type = "businesscircle";
                break;
            case "date":
                DoubleElevenStrategy doubleElevenStrategy = (DoubleElevenStrategy) strategyVO;
                String startTime = ParseHelper.dateToString(doubleElevenStrategy.getStartTime());
                String endTime = ParseHelper.dateToString(doubleElevenStrategy.getEndTime());
                date = startTime + "-" + endTime;
                discount = doubleElevenStrategy.getDiscount();
                strategyName = doubleElevenStrategy.getName();
                type = "date";
                break;
        }
        webStrategyPO = new WebStrategyPO(strategyName,businessCircle,date,level0,level1,discount,type);
        return webStrategyPO;
    }

    public static StrategyVO toStrategyVO(HotelStrategyPO hotelStrategyPO) {
        String type = hotelStrategyPO.getType();
        StrategyVO strategyVO = null;
        String name = hotelStrategyPO.getStrategyName();
        double discount = hotelStrategyPO.getDiscount();
        switch (type) {
            case "birthday":
                strategyVO = new BirthdayStrategy(name, discount);
                break;
            case "double_eleven":
                String[] dates = hotelStrategyPO.getSpecialInof().split("-");
                Date startTime = ParseHelper.stringToDate(dates[0]);
                Date endTime = ParseHelper.stringToDate(dates[1]);
                strategyVO = new DoubleElevenStrategy(name,discount,startTime,endTime);
                break;
            case "room_number":
                strategyVO = new RoomNumberStrategy(name,discount);
                break;
            case "cooperative":
                String[] companies = hotelStrategyPO.getSpecialInof().split(",");
                ArrayList<String> companiesList = new ArrayList<>();
                for(String company:companies) {
                    companiesList.add(company);
                }
                strategyVO = new CooperativeStrategy(name,discount,companiesList);
                break;
        }
        return strategyVO;
    }


    public static HotelStrategyPO toHotelStrategyPO(String hotelName,StrategyVO strategyVO) {
        String strategyName = null;
        double discount = 0;
        String specialInfo = null;
        String type = strategyVO.getType();
        switch (type) {
            case "birthday":
                BirthdayStrategy birthdayStrategy = (BirthdayStrategy)strategyVO;
                strategyName = birthdayStrategy.getName();
                discount = birthdayStrategy.getDiscount();
                break;
            case "date":
                DoubleElevenStrategy doubleElevenStrategy = (DoubleElevenStrategy)strategyVO;
                strategyName = doubleElevenStrategy.getName();
                discount = doubleElevenStrategy.getDiscount();
                String startTime = ParseHelper.dateToString(doubleElevenStrategy.getStartTime());
                String endTime = ParseHelper.dateToString(doubleElevenStrategy.getEndTime());
                specialInfo = startTime + "-" + endTime;
                break;
            case "roomnum":
                RoomNumberStrategy roomNumberStrategy = (RoomNumberStrategy)strategyVO;
                strategyName = roomNumberStrategy.getName();
                discount = roomNumberStrategy.getDiscount();
                break;
            case "companies":
                CooperativeStrategy cooperativeStrategy = (CooperativeStrategy)strategyVO;
                Iterator<String> companies = cooperativeStrategy.getCompanies();
                StringBuilder stringBuilder = new StringBuilder("");
                companies.forEachRemaining(company -> stringBuilder.append(company));
                strategyName = cooperativeStrategy.getName();
                discount = cooperativeStrategy.getDiscount();
                specialInfo = stringBuilder.toString();
                break;
        }
        HotelStrategyPO hotelStrategyPO = new HotelStrategyPO(hotelName,strategyName,specialInfo,discount,type);
        return hotelStrategyPO;
    }


    public static StrategyVO toStrategyVO(WebStrategyPO webStrategyPO) {
        StrategyVO strategyVO = null;
        String name = webStrategyPO.getStrategyName();
        switch (webStrategyPO.getType()) {
            case "level":
                int upgradeNum = (int)webStrategyPO.getLev0();
                strategyVO = new LevelStrategy(name,upgradeNum);
                break;
            case "businesscircle":
                double level0 = webStrategyPO.getLev0();
                double level1 = webStrategyPO.getLev1();
                double level2 = webStrategyPO.getDiscount();
                String CBD = webStrategyPO.getBusinessCircle();
                strategyVO = new CBDStrategy(name,level0,level1,level2,CBD);
                break;
            case "date":
                double discount = webStrategyPO.getDiscount();
                String[] time = webStrategyPO.getDate().split("-");
                Date startTime = ParseHelper.stringToDate(time[0]);
                Date endTime = ParseHelper.stringToDate(time[1]);
                strategyVO = new DoubleElevenStrategy(name,discount,startTime,endTime);
                break;
        }
        return strategyVO;
    }
}

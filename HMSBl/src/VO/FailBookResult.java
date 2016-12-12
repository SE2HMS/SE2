package VO;

/**
 * Created by Administrator on 2016/12/11.
 */
public class FailBookResult implements BookResult{
    private String info;

    public FailBookResult(String info) {
        this.info = info;
    }

    @Override
    public boolean getSuccess() {
        return false;
    }

    public String getInfo() {
        return info;
    }
}

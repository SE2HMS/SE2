package VO;

/**
 * Created by Administrator on 2016/12/11.
 */
public class SuccessBookResult implements BookResult{
    private String id;

    public SuccessBookResult(String id) {
        this.id = id;
    }

    @Override
    public boolean getSuccess() {
        return true;
    }

    public String getOrderId() {
        return id;
    }
}

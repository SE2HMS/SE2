package VO;

/**
 * Created by Administrator on 2016/12/12.
 */
public class RegisterResult {
    private final RegisterState state;
    private final String id;


    public RegisterResult(RegisterState state,String id) {
        this.state = state;
        this.id = id;
    }

    public RegisterState getState() {
        return state;
    }

    public String getId() {
        return id;
    }
}

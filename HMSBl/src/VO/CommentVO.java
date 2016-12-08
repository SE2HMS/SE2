package VO;

/**
 * Created by Administrator on 2016/11/23.
 */
public class CommentVO {
    private final String content;
    private final double level;

    public String getContent() {
        return content;
    }

    public CommentVO(String content,double level) {
        this.content = content;
        this.level = level;
    }

    public double getLevel() {
        return level;
    }
}

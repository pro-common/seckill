package org.seckill.enums;

/**
 * Created by wchb7 on 16-5-14.
 */
public enum SeckillStatEnum {

	/** 1  秒杀成功 **/
    SUCCESS(1, "秒杀成功"),
    /** 0 秒杀结束 **/
    END(0, "秒杀结束"),
    /** -1 重复秒杀 **/
    REPEAT_KILL(-1, "重复秒杀"),
    /** -2 系统异常 **/
    INNER_ERROR(-2, "系统异常"),
    /** -3 数据篡改 **/
    DATA_REWRITE(-3, "数据篡改"),
    /** -4 未登陆 **/
    NOT_LOGIN(-4, "未登陆");

    private int state;

    private String stateInfo;

    SeckillStatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStatEnum stateOf(int index) {
        for (SeckillStatEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }

}

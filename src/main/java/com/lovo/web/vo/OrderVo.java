package com.lovo.web.vo;

/**
 * 订单VO
 */
public class OrderVo {

    private String orderNum;//订单编号
    //序号
    private int index;
    //电影名字
    private String movieName;
    //购买价格
    private  float tickePrice;
    //购买数量
    private  int  tickeNum;
    private int tag;// 0-未付款 1-已付款 2-已出票
    private  String userName;//用户 要保证唯一

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public float getTickePrice() {
        return tickePrice;
    }

    public void setTickePrice(float tickePrice) {
        this.tickePrice = tickePrice;
    }

    public int getTickeNum() {
        return tickeNum;
    }

    public void setTickeNum(int tickeNum) {
        this.tickeNum = tickeNum;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

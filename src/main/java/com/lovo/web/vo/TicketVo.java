package com.lovo.web.vo;

/**
 * 电影票VO
 */
public class TicketVo {
    //序号
    private int index;
     //电影名字
    private String movieName;
    //价格
    private  float tickePrice;
    //数量
    private  int  tickeNum;
    //标记 0-待出售 1-已出售
    private  int tag;

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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

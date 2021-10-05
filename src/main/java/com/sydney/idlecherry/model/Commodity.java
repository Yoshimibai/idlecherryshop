package com.sydney.idlecherry.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Commodity implements Serializable {
    private Integer commid;

    private String commname;

    private String commdesc;

    private BigDecimal price;

    private String image;

    private String video;

    private String createtime;

    private String updatetime;

    private String commstatus;

    private Integer views;

    private Integer categoryid;

    private Integer userid;

    private static final long serialVersionUID = 1L;

    public Integer getCommid() {
        return commid;
    }

    public void setCommid(Integer commid) {
        this.commid = commid;
    }

    public String getCommname() {
        return commname;
    }

    public void setCommname(String commname) {
        this.commname = commname;
    }

    public String getCommdesc() {
        return commdesc;
    }

    public void setCommdesc(String commdesc) {
        this.commdesc = commdesc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getCommstatus() {
        return commstatus;
    }

    public void setCommstatus(String commstatus) {
        this.commstatus = commstatus;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
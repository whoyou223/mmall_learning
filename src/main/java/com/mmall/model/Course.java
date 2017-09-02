package com.mmall.model;

import java.math.BigDecimal;
import java.util.Date;

public class Course {
    private Integer id;

    private String name;

    private BigDecimal price;

    private String coverurl;

    private String teacher;

    private String intriduce;

    private Byte sale;

    private Integer time;

    private String check;

    private Date ctime;

    private Date mtime;

    private Integer cuser;

    private Integer muser;

    private Byte state;

    public Course(Integer id, String name, BigDecimal price, String coverurl, String teacher, String intriduce, Byte sale, Integer time, String check, Date ctime, Date mtime, Integer cuser, Integer muser, Byte state) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.coverurl = coverurl;
        this.teacher = teacher;
        this.intriduce = intriduce;
        this.sale = sale;
        this.time = time;
        this.check = check;
        this.ctime = ctime;
        this.mtime = mtime;
        this.cuser = cuser;
        this.muser = muser;
        this.state = state;
    }

    public Course() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl == null ? null : coverurl.trim();
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher == null ? null : teacher.trim();
    }

    public String getIntriduce() {
        return intriduce;
    }

    public void setIntriduce(String intriduce) {
        this.intriduce = intriduce == null ? null : intriduce.trim();
    }

    public Byte getSale() {
        return sale;
    }

    public void setSale(Byte sale) {
        this.sale = sale;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check == null ? null : check.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public Integer getCuser() {
        return cuser;
    }

    public void setCuser(Integer cuser) {
        this.cuser = cuser;
    }

    public Integer getMuser() {
        return muser;
    }

    public void setMuser(Integer muser) {
        this.muser = muser;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}
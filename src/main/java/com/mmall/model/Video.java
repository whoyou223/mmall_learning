package com.mmall.model;

import java.math.BigDecimal;
import java.util.Date;

public class Video {
    private Integer id;

    private String cover;

    private String name;

    private String filename;

    private String url;

    private Integer time;

    private BigDecimal size;

    private Date ctime;

    private Date mtime;

    private Integer cuser;

    private Integer muser;

    private Byte state;

    public Video(Integer id, String cover, String name, String filename, String url, Integer time, BigDecimal size, Date ctime, Date mtime, Integer cuser, Integer muser, Byte state) {
        this.id = id;
        this.cover = cover;
        this.name = name;
        this.filename = filename;
        this.url = url;
        this.time = time;
        this.size = size;
        this.ctime = ctime;
        this.mtime = mtime;
        this.cuser = cuser;
        this.muser = muser;
        this.state = state;
    }

    public Video() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
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
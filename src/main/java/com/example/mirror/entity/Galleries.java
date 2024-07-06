package com.example.mirror.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

public class Galleries {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userid;
    private Integer imageid;
    private String url;
    private LocalDateTime created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getImageid() {
        return imageid;
    }

    public void setImageid(Integer imageid) {
        this.imageid = imageid;
    }

    public String getPosition() {
        return url;
    }

    public void setPosition(String url) {
        this.url = url;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Galleries{" +
                "id=" + id +
                ", userid=" + userid +
                ", imageid=" + imageid +
                ", position=" + url +
                ", created=" + created +
                '}';
    }
}

// Image.java
package com.example.mirror.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


import java.time.LocalDateTime;

@TableName("images")
public class Images {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userid;
    private String url;
    private String description;
    private Boolean isPublic;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String tags;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Images() {
    }

    public Images(Integer id, Integer userid, String url, String description, Boolean isPublic, LocalDateTime created, LocalDateTime updated, String tags) {
        this.id = id;
        this.userid = userid;
        this.url = url;
        this.description = description;
        this.isPublic = isPublic;
        this.created = created;
        this.updated = updated;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Images{" +
              "id=" + id +
              ", userid=" + userid +
              ", url='" + url + '\'' +
              ", description='" + description + '\'' +
              ", isPublic=" + isPublic +
              ", created=" + created +
              ", updated=" + updated +
              ", tags='" + tags + '\'' +
              '}';
    }
}

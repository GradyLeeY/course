package com.grady.server.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class FileDto {

    /**
    * id
    */
    private String id;

    /**
    * 相对路径
    */
    private String path;

    /**
    * 文件名
    */
    private String name;

    /**
    * 后缀
    */
    private String suffix;

    /**
    * 大小|字节B
    */
    private Integer size;

    /**
    * 用途|枚举[FileUseEnum]：COURSE("C", "讲师"), TEACHER("T", "课程")
    */
    private String use;

    /**
    * 
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdAt;

    /**
    * 
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatedAt;

    /**
    * 已上传分片
    */
    private Integer shardIndex;

    /**
    * 分片大小|B
    */
    private Integer shardSize;

    /**
    * 分片总数
    */
    private Integer shardTotal;

    /**
    * 文件标识
    */
    private String key;

    /**
    * vod|阿里云vod
    */
    private String vod;

    public String getId() {
    return id;
    }

    public void setId(String id) {
    this.id = id;
    }

    public String getPath() {
    return path;
    }

    public void setPath(String path) {
    this.path = path;
    }

    public String getName() {
    return name;
    }

    public void setName(String name) {
    this.name = name;
    }

    public String getSuffix() {
    return suffix;
    }

    public void setSuffix(String suffix) {
    this.suffix = suffix;
    }

    public Integer getSize() {
    return size;
    }

    public void setSize(Integer size) {
    this.size = size;
    }

    public String getUse() {
    return use;
    }

    public void setUse(String use) {
    this.use = use;
    }

    public Date getCreatedAt() {
    return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
    return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
    }

    public Integer getShardIndex() {
    return shardIndex;
    }

    public void setShardIndex(Integer shardIndex) {
    this.shardIndex = shardIndex;
    }

    public Integer getShardSize() {
    return shardSize;
    }

    public void setShardSize(Integer shardSize) {
    this.shardSize = shardSize;
    }

    public Integer getShardTotal() {
    return shardTotal;
    }

    public void setShardTotal(Integer shardTotal) {
    this.shardTotal = shardTotal;
    }

    public String getKey() {
    return key;
    }

    public void setKey(String key) {
    this.key = key;
    }

    public String getVod() {
    return vod;
    }

    public void setVod(String vod) {
    this.vod = vod;
    }


@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(getClass().getSimpleName());
sb.append(" [");
sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", path=").append(path);
    sb.append(", name=").append(name);
    sb.append(", suffix=").append(suffix);
    sb.append(", size=").append(size);
    sb.append(", use=").append(use);
    sb.append(", createdAt=").append(createdAt);
    sb.append(", updatedAt=").append(updatedAt);
    sb.append(", shardIndex=").append(shardIndex);
    sb.append(", shardSize=").append(shardSize);
    sb.append(", shardTotal=").append(shardTotal);
    sb.append(", key=").append(key);
    sb.append(", vod=").append(vod);
sb.append("]");
return sb.toString();
}

}
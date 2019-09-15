package com.qunar.qfproxy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * EmoPackConf
 *
 * @author binz.zhang
 * @date 2019/1/30
 */
@Data
public class EmoPackConf {
    private String pkgid;
    private String name;
    private String file;
    private String desc = "扩展表情";
    private String thumb;
    @JsonProperty("file_size")
    private long fileSize;
    private String md5;
    @JsonIgnore
    private Timestamp createTime;
    @JsonIgnore
    private String ownerUser;

}
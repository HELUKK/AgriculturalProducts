package com.seven.rongxiaotong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName tb_knowledge
 */
@TableName(value ="tb_knowledge")
public class TbKnowledge implements Serializable {
    /**
     * 知识Id
     */
    @TableId
    private Integer knowledgeId;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能空")
    private String title;

    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空")
    private String content;

    /**
     * 视频图片路径
     */
    private String picPath;

    /**
     * 发布者名字
     */
    private String ownName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getKnowledgeId() {
        return knowledgeId;
    }

    /**
     * 
     */
    public void setKnowledgeId(Integer knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    /**
     * 
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     */
    public String getPicPath() {
        return picPath;
    }

    /**
     * 
     */
    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    /**
     * 
     */
    public String getOwnName() {
        return ownName;
    }

    /**
     * 
     */
    public void setOwnName(String ownName) {
        this.ownName = ownName;
    }

    /**
     * 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
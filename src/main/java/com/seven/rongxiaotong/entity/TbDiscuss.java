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
 * @TableName tb_discuss
 */
@TableName(value ="tb_discuss")
public class TbDiscuss implements Serializable {
    /**
     * id
     */
    @TableId
    private Integer discussId;

    /**
     * 知识id
     */
    private Integer knowledgeId;

    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空")
    private String content;

    /**
     * 发布者名字
     */
    private String ownName;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getDiscussId() {
        return discussId;
    }

    /**
     * 
     */
    public void setDiscussId(Integer discussId) {
        this.discussId = discussId;
    }

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
}
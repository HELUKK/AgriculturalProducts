package com.seven.rongxiaotong.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 在线问答表
 * @TableName question
 */
@TableName(value ="question")
public class Question implements Serializable {
    /**
     * id
     */
    @TableId
    private Integer id;

    /**
     * 专家用户id
     */
    private String expertName;

    /**
     * 咨询者
     */
    private String questioner;

    /**
     * 手机号
     */
    @NotBlank(message = "电话不能为空")
    private String phone;

    /**
     * 农作物名称
     */
    @NotBlank(message = "农作物名称不能为空")
    private String plantName;

    /**
     * 问题标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 问题
     */
    private String question;

    /**
     * 回答
     */
    private String answer;

    /**
     * 状态,0为提问中;1为回答完毕
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 专家用户id
     */
    public String getExpertName() {
        return expertName;
    }

    /**
     * 专家用户id
     */
    public void setExpertName(String expertName) {
        this.expertName = expertName == null ? null : expertName.trim();
    }

    /**
     * 咨询者
     */
    public String getQuestioner() {
        return questioner;
    }

    /**
     * 咨询者
     */
    public void setQuestioner(String questioner) {
        this.questioner = questioner == null ? null : questioner.trim();
    }

    /**
     * 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 农作物名称
     */
    public String getPlantName() {
        return plantName;
    }

    /**
     * 农作物名称
     */
    public void setPlantName(String plantName) {
        this.plantName = plantName == null ? null : plantName.trim();
    }

    /**
     * 问题标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 问题标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 问题
     */
    public String getQuestion() {
        return question;
    }

    /**
     * 问题
     */
    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    /**
     * 回答
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 回答
     */
    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    /**
     * 状态,0为提问中;1为回答完毕
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态,0为提问中;1为回答完毕
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}
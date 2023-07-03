package com.seven.rongxiaotong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 
 * @TableName reserve
 */
@TableName(value ="reserve")
public class Reserve implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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
     * 面积
     */
    @NotBlank(message = "面积不能为空")
    private String area;

    /**
     * 土地地址
     */
    @NotBlank(message = "地址不能为空")
    private String address;

    /**
     * 农作物名称
     */
    @NotBlank(message = "农作物名称不能为空")
    private String plantName;

    /**
     * 土壤条件
     */
    @NotBlank(message = "土壤条件不能为空")
    private String soilCondition;

    /**
     * 作物条件
     */
    @NotBlank(message = "农作物条件不能为空")
    private String plantCondition;

    /**
     * 作物详细信息
     */
    @NotBlank(message = "农作物详细信息不能为空")
    private String plantDetail;

    /**
     * 留言
     */
    private String message;

    /**
     * 回答
     */
    private String answer;

    /**
     * 状态 0---申请中,1---已完成
     */
    private Integer status;

    /**
     * 手机号
     */
    @NotBlank(message = "电话不能为空")
    private String phone;

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
        this.expertName = expertName;
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
        this.questioner = questioner;
    }

    /**
     * 面积
     */
    public String getArea() {
        return area;
    }

    /**
     * 面积
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 土地地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 土地地址
     */
    public void setAddress(String address) {
        this.address = address;
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
        this.plantName = plantName;
    }

    /**
     * 土壤条件
     */
    public String getSoilCondition() {
        return soilCondition;
    }

    /**
     * 土壤条件
     */
    public void setSoilCondition(String soilCondition) {
        this.soilCondition = soilCondition;
    }

    /**
     * 作物条件
     */
    public String getPlantCondition() {
        return plantCondition;
    }

    /**
     * 作物条件
     */
    public void setPlantCondition(String plantCondition) {
        this.plantCondition = plantCondition;
    }

    /**
     * 作物详细信息
     */
    public String getPlantDetail() {
        return plantDetail;
    }

    /**
     * 作物详细信息
     */
    public void setPlantDetail(String plantDetail) {
        this.plantDetail = plantDetail;
    }

    /**
     * 留言
     */
    public String getMessage() {
        return message;
    }

    /**
     * 留言
     */
    public void setMessage(String message) {
        this.message = message;
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
        this.answer = answer;
    }

    /**
     * 状态 0---申请中,1---已完成
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态 0---申请中,1---已完成
     */
    public void setStatus(Integer status) {
        this.status = status;
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
        this.phone = phone;
    }

    public Reserve(Integer id, String expertName, String questioner, String area, String address, String plantName, String soilCondition, String plantCondition, String plantDetail, String message, String answer, Integer status, String phone) {
        this.id = id;
        this.expertName = expertName;
        this.questioner = questioner;
        this.area = area;
        this.address = address;
        this.plantName = plantName;
        this.soilCondition = soilCondition;
        this.plantCondition = plantCondition;
        this.plantDetail = plantDetail;
        this.message = message;
        this.answer = answer;
        this.status = status;
        this.phone = phone;
    }

    public Reserve() {
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "id=" + id +
                ", espertName='" + expertName + '\'' +
                ", questioner='" + questioner + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", plantName='" + plantName + '\'' +
                ", soilCondition='" + soilCondition + '\'' +
                ", plantCondition='" + plantCondition + '\'' +
                ", plantDetail='" + plantDetail + '\'' +
                ", message='" + message + '\'' +
                ", answer='" + answer + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                '}';
    }
}
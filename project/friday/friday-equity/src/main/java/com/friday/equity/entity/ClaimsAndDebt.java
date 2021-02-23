package com.friday.equity.entity;

import java.io.Serializable;

/**
 * (ClaimsAndDebt)实体类
 *
 * @author makejava
 * @since 2021-02-23 16:14:49
 */
public class ClaimsAndDebt implements Serializable {
    private static final long serialVersionUID = -54612230758341797L;
    /**
    * 资产负债ID
    */
    private Integer cadId;
    /**
    * 0借出，1借入
    */
    private String cadType;
    /**
    * 债权人
    */
    private String creditor;
    /**
    * 债务人
    */
    private String obligor;
    /**
    * 交易数量
    */
    private String cadNum;
    /**
    * 交易时间
    */
    private String cadTime;
    /**
    * 已偿还金额
    */
    private String cadRepay;
    /**
    * 预计偿还时间
    */
    private String cadPlan;
    /**
    * 备注
    */
    private String cadRemark;
    /**
    * 状态，1存在0已取消
    */
    private String cadStatus;


    public Integer getCadId() {
        return cadId;
    }

    public void setCadId(Integer cadId) {
        this.cadId = cadId;
    }

    public String getCadType() {
        return cadType;
    }

    public void setCadType(String cadType) {
        this.cadType = cadType;
    }

    public String getCreditor() {
        return creditor;
    }

    public void setCreditor(String creditor) {
        this.creditor = creditor;
    }

    public String getObligor() {
        return obligor;
    }

    public void setObligor(String obligor) {
        this.obligor = obligor;
    }

    public String getCadNum() {
        return cadNum;
    }

    public void setCadNum(String cadNum) {
        this.cadNum = cadNum;
    }

    public String getCadTime() {
        return cadTime;
    }

    public void setCadTime(String cadTime) {
        this.cadTime = cadTime;
    }

    public String getCadRepay() {
        return cadRepay;
    }

    public void setCadRepay(String cadRepay) {
        this.cadRepay = cadRepay;
    }

    public String getCadPlan() {
        return cadPlan;
    }

    public void setCadPlan(String cadPlan) {
        this.cadPlan = cadPlan;
    }

    public String getCadRemark() {
        return cadRemark;
    }

    public void setCadRemark(String cadRemark) {
        this.cadRemark = cadRemark;
    }

    public String getCadStatus() {
        return cadStatus;
    }

    public void setCadStatus(String cadStatus) {
        this.cadStatus = cadStatus;
    }

}
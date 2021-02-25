package com.friday.bills.entity;

import java.io.Serializable;

/**
 * (UserExpenses)实体类
 *
 * @author makejava
 * @since 2021-02-25 15:25:47
 */
public class UserExpenses implements Serializable {
    private static final long serialVersionUID = 256986404254744568L;
    /**
    * 主键
    */
    private Integer expensesId;
    /**
    * 支出时间
    */
    private String expensesTime;
    /**
    * 支出金额
    */
    private String expensesNum;
    /**
    * 支出分类
    */
    private String expensesSort;
    /**
    * 支出备注
    */
    private String expensesRemark;
    /**
    * 支出人id
    */
    private String expensesUserId;
    /**
    * 支出人
    */
    private String expensesUser;


    public Integer getExpensesId() {
        return expensesId;
    }

    public void setExpensesId(Integer expensesId) {
        this.expensesId = expensesId;
    }

    public String getExpensesTime() {
        return expensesTime;
    }

    public void setExpensesTime(String expensesTime) {
        this.expensesTime = expensesTime;
    }

    public String getExpensesNum() {
        return expensesNum;
    }

    public void setExpensesNum(String expensesNum) {
        this.expensesNum = expensesNum;
    }

    public String getExpensesSort() {
        return expensesSort;
    }

    public void setExpensesSort(String expensesSort) {
        this.expensesSort = expensesSort;
    }

    public String getExpensesRemark() {
        return expensesRemark;
    }

    public void setExpensesRemark(String expensesRemark) {
        this.expensesRemark = expensesRemark;
    }

    public String getExpensesUserId() {
        return expensesUserId;
    }

    public void setExpensesUserId(String expensesUserId) {
        this.expensesUserId = expensesUserId;
    }

    public String getExpensesUser() {
        return expensesUser;
    }

    public void setExpensesUser(String expensesUser) {
        this.expensesUser = expensesUser;
    }

    private Integer offset;

    private Integer limit;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
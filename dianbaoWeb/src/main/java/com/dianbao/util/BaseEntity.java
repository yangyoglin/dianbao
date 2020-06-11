package com.dianbao.util;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 *
 * @author YYL
 * @version 1.0
 * @date 创建时间：2020-6-10 16:55:40
 */
public abstract class BaseEntity implements Serializable {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Order orders;

    /**
     * 当前页码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int pageNo = 1;

    /**
     * 每页数据量
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int pageSize = 10;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int startRow = 0;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String beginDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String endDate;

    /**
     * 当前页码
     *
     * @return the pageNo
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 当前页码
     *
     * @param pageNo the pageNo to set
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo < 1 ? 1 : pageNo;
    }

    /**
     * 每页数据量
     *
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 每页数据量
     *
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize < 0 ? 10 : pageSize;
    }

    /**
     * @return the beginDate
     */
    public String getBeginDate() {
        return beginDate;
    }

    /**
     * @param beginDate the beginDate to set
     */
    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the orders
     */
    public Order getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(Order orders) {
        this.orders = orders;
    }

    /**
     * @return the startRow
     */
    public int getStartRow() {
        return startRow < 0 ? 0 : (pageNo - 1) * pageSize;
    }

    /**
     * @param startRow the startRow to set
     */
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

}

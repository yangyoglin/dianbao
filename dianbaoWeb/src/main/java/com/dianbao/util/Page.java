package com.dianbao.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author luoluo
 * @email 15360801546@163.com
 * @version 1.0
 * @date 创建时间：2018-4-8 11:01:11
 */
public class Page implements Serializable {

    public static final List<BaseEntity> EMPTY_OBJECT_ARRAY = new ArrayList<>();

    private long count;

    private int pageNo;

    private int pageSize = 10;

    private List<? extends BaseEntity> list;

    /**
     * @return the count
     */
    public long getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(long count) {
        this.count = count;
    }

    /**
     * @return the pageNo
     */
    public int getPageNo() {
        if (pageNo < 1) {
            pageNo = 1;
        }
        return pageNo;
    }

    /**
     * @param pageNo the pageNo to set
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        if (pageSize < 10) {
            pageSize = 10;
        }
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the list
     */
    public List<? extends BaseEntity> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List list) {
        this.list = list;
    }

}

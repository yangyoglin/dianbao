package com.dianbao.util;

/**
 *
 *
 * @author YYL
 * @version 1.0
 * @date 创建时间：2020-6-10 16:56:50
 */
public class Order {

    private String column;
    private String dir = "desc";

    /**
     * @return the column
     */
    public String getColumn() {
        return column;
    }

    /**
     * @param column the column to set
     */
    public void setColumn(String column) {
        this.column = column;
    }

    /**
     * @return the dir
     */
    public String getDir() {
        return dir;
    }

    /**
     * @param dir the dir to set
     */
    public void setDir(String dir) {
        this.dir = dir;
    }

}

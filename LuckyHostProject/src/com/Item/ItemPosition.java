package com.Item;
/**
 * This Class
 * @author WuSigan
 * @version 0.0.1
 */
public class ItemPosition {
    private int row;//行
    private int colum;//列


    /**
     *
     * @param row
     * @param colum
     * 全部参数的构造函数
     */
    public ItemPosition(int row, int colum) {
        this.row = row;
        this.colum = colum;
    }

    /**
     * 空参构造函数
     */
    public ItemPosition() {
    }

    /**
     *
     * @return row
     * 返回物品所在行
     */
    public int getRow() {
        return row;
    }

    /**
     *
     * @param row
     * 设置物品所在行
     */
    public void setRow(int row) {
        this.row = row;
    }
    /**
     *
     * @return colum
     * 返回物品所在列
     */
    public int getColum() {
        return colum;
    }
    /**
     *
     * @param colum
     * 设置物品所在列
     */
    public void setColum(int colum) {
        this.colum = colum;
    }
}

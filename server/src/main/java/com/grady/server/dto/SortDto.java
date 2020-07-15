package com.grady.server.dto;

/**
 * @Author Grady
 * @Date 2020/7/15 20:18
 * @Version 1.0
 */
public class SortDto {

    private String id;
    private int oldSort;
    private int newSort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOldSort() {
        return oldSort;
    }

    public void setOldSort(int oldSort) {
        this.oldSort = oldSort;
    }

    public int getNewSort() {
        return newSort;
    }

    public void setNewSort(int newSort) {
        this.newSort = newSort;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SortDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", oldSort='").append(oldSort).append('\'');
        sb.append(", newSort='").append(newSort).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

package com.leo.projeto.vo;

import java.io.Serializable;

public class PaginationVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int pageNumber;
    private int pageSize;
    private T search;

    public PaginationVO() {
    }

    public PaginationVO(int pageNumber, int pageSize, T search) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.search = search;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getSearch() {
        return search;
    }

    public void setSearch(T search) {
        this.search = search;
    }

}

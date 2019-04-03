package com.itheima.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("pageBean")
public class PageBean<T> implements Serializable {

    private Integer PageSize;   //每页显示个数
    private Integer currentPage;  //当前页码
    private Integer totalPage;     //总页码
    private Integer totalCount;     //总记录数
    private List<T> users;       //存放数据的集合


    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getUsers() {
        return users;
    }

    public void setUsers(List<T> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "PageSize=" + PageSize +
                ", currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", users=" + users +
                '}';
    }
}

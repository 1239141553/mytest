package com.shop.pojo;

import java.util.List;

public class ResultPage {
    private List rows;
    private Long total;

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}

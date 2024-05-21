package org.licretey;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {


    private List<DataRow> rows;

    public List<DataRow> getRows() {
        return rows;
    }

    public void setRows(List<DataRow> rows) {
        this.rows = rows;
    }

}

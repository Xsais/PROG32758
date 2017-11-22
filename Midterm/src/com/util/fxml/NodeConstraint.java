/*
 * ----------------------------------------------------------------------------------------------+
 *  * Group Leader: Daniel Hope
 *  * Member(s):
 *  *     - Georgina Luce
 *  *     - Nathaniel Primo
 *  *     - Michael Marc
 *  * Group #: 1
 *  * Filename: com.util.fxml.NodeConstraint
 *  * Main class: com.init.Main
 *  * Assignment: Midterm
 *  * Creation Date:
 *  * Last Modified: 11/1/17 8:34 PM
 *  * Java Version: 1.8.0_141
 *  * Description:
 * ----------------------------------------------------------------------------------------------+
 */

package com.util.fxml;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;

public class NodeConstraint {

    private IntegerProperty columnIndex = new SimpleIntegerProperty(this, "columnIndex", 0);

    private IntegerProperty rowIndex = new SimpleIntegerProperty(this, "rowIndex", 0);

    private IntegerProperty columnSpan = new SimpleIntegerProperty(this, "columnSpan", -1);

    private IntegerProperty rowSpan = new SimpleIntegerProperty(this, "rowSpan", -1);

    private Node node;

    public NodeConstraint(Node node, int columnIndex, int rowIndex) {

        initNode(node);
        setColumnIndex(columnIndex);
        setRowIndex(rowIndex);
    }

    public NodeConstraint(Node node, int columnIndex, int rowIndex, int columnSpan, int rowSpan) {

        initNode(node);
        setColumnIndex(columnIndex);
        setRowIndex(rowIndex);
        setColumnSpan(columnSpan);
        setRowSpan(rowSpan);
    }

    private void initNode(Node node) {

        this.node = node;
    }

    public int getColumnIndex() {

        return columnIndex.get();
    }

    public void setColumnIndex(int columnIndex) {

        this.columnIndex.set(columnIndex);
    }

    public IntegerProperty columnIndexProperty() {

        return columnIndex;
    }

    public int getRowIndex() {

        return rowIndex.get();
    }

    public void setRowIndex(int rowIndex) {

        this.rowIndex.set(rowIndex);
    }

    public IntegerProperty rowIndexProperty() {

        return rowIndex;
    }

    public Node getNode() {

        return node;
    }

    public int getColumnSpan() {

        return columnSpan.get();
    }

    public void setColumnSpan(int columnSpan) {

        this.columnSpan.set(columnSpan);
    }

    public javafx.beans.property.IntegerProperty columnSpanProperty() {

        return columnSpan;
    }

    public int getRowSpan() {

        return rowSpan.get();
    }

    public void setRowSpan(int rowSpan) {

        this.rowSpan.set(rowSpan);
    }

    public javafx.beans.property.IntegerProperty rowSpanProperty() {

        return rowSpan;
    }
}

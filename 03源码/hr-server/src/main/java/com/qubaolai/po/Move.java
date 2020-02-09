package com.qubaolai.po;

import com.qubaolai.pojo.MoveFather;

public class Move extends MoveFather {
    private String id;

    private String employeeNumber;

    private String deptBefore;

    private String deptAfter;

    private String positionBefore;

    private String positionAfter;

    private String updateTime;

    private Integer moveType;

    private String managerId;

    private String notes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getDeptBefore() {
        return deptBefore;
    }

    public void setDeptBefore(String deptBefore) {
        this.deptBefore = deptBefore;
    }

    public String getDeptAfter() {
        return deptAfter;
    }

    public void setDeptAfter(String deptAfter) {
        this.deptAfter = deptAfter;
    }

    public String getPositionBefore() {
        return positionBefore;
    }

    public void setPositionBefore(String positionBefore) {
        this.positionBefore = positionBefore;
    }

    public String getPositionAfter() {
        return positionAfter;
    }

    public void setPositionAfter(String positionAfter) {
        this.positionAfter = positionAfter;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getMoveType() {
        return moveType;
    }

    public void setMoveType(Integer moveType) {
        this.moveType = moveType;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
package com.qubaolai.po;

public class Move {
    private String id;

    private String employeeNumber;

    private String moveBefore;

    private String moveAfter;

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

    public String getMoveBefore() {
        return moveBefore;
    }

    public void setMoveBefore(String moveBefore) {
        this.moveBefore = moveBefore;
    }

    public String getMoveAfter() {
        return moveAfter;
    }

    public void setMoveAfter(String moveAfter) {
        this.moveAfter = moveAfter;
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
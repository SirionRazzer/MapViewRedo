package com.soczilla.mapviewredo.library.model;

public class Mark {
    private float x;
    private float y;
    private Boolean isInfrastructure;
    private String name;
    private InfrastructureType type;

    public Mark(float x, float y, Boolean isInfrastructure, String name, InfrastructureType type) {
        this.x = x;
        this.y = y;
        this.isInfrastructure = isInfrastructure;
        this.name = name;
        this.type = type;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Boolean getInfrastructure() {
        return isInfrastructure;
    }

    public void setInfrastructure(Boolean infrastructure) {
        isInfrastructure = infrastructure;
    }

    public InfrastructureType getType() {
        return type;
    }

    public void setType(InfrastructureType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }
}

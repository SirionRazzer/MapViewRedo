package com.soczilla.mapviewredo.library.model;

public class Mark {
    private float x;
    private float y;
    private Boolean isInfrastructure;
    private String name;
    private PoiType poiType;
    private InfrastructureType infrastructureType;
    private String textExtra;

    public Mark(float x, float y, Boolean isInfrastructure, String name, PoiType poiType, InfrastructureType infrastructureType, String textExtra) {
        this.x = x;
        this.y = y;
        this.isInfrastructure = isInfrastructure;
        this.name = name;
        this.poiType = poiType;
        this.infrastructureType = infrastructureType;
        this.textExtra = textExtra;
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

    public InfrastructureType getInfrastructureType() {
        return infrastructureType;
    }

    public void setInfrastructureType(InfrastructureType infrastructureType) {
        this.infrastructureType = infrastructureType;
    }

    public String getName() {
        return name;
    }

    public PoiType getPoiType() {
        return poiType;
    }

    public void setPoiType(PoiType poiType) {
        this.poiType = poiType;
    }

    public String getTextExtra() {
        return textExtra;
    }

    public void setTextExtra(String textExtra) {
        this.textExtra = textExtra;
    }
}

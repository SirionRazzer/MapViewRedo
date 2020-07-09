package com.soczilla.mapviewredo.library.model;

public class Infrastructure {
    private Long id;
    private Integer x;
    private Integer y;
    private Integer z;
    private InfrastructureType type;
    private String description;
    private Boolean disabledFriendly;

    public Infrastructure(Long id, Integer x, Integer y, Integer z, InfrastructureType type, String description, Boolean disabledFriendly) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
        this.description = description;
        this.disabledFriendly = disabledFriendly;
    }

    public Long getId() {
        return id;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getZ() {
        return z;
    }

    public InfrastructureType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getDisabledFriendly() {
        return disabledFriendly;
    }
}

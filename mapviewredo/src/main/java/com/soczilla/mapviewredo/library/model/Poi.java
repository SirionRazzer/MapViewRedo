package com.soczilla.mapviewredo.library.model;

public class Poi {
    private Long id;
    private Integer x;
    private Integer y;
    private Integer z;
    private String name;
    private PoiType type;
    private String description;
    private String url;
    private Boolean isFavorite;
    private String iconImage;

    public Poi(Long id, Integer x, Integer y, Integer z, String name, PoiType type, String description, String url, Boolean isFavorite, String iconImage) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
        this.type = type;
        this.description = description;
        this.url = url;
        this.isFavorite = isFavorite;
        this.iconImage = iconImage;
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public String getIconImage() {
        return iconImage;
    }

    public PoiType getPoiType() {
        return type;
    }
}

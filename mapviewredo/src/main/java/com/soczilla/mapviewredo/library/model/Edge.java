package com.soczilla.mapviewredo.library.model;

import android.graphics.*;

public class Edge {
    private Long id;
    private Long from;
    private Long to;
    private Boolean wheelchairFriendly;
    private EdgeType orientation;

    public Edge(Long id, Long from, Long to, Boolean wheelchairFriendly, EdgeType orientation) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.wheelchairFriendly = wheelchairFriendly;
        this.orientation = orientation;
    }

    public Long getId() {
        return id;
    }

    public Long getFrom() {
        return from;
    }

    public Long getTo() {
        return to;
    }

    public Boolean getWheelchairFriendly() {
        return wheelchairFriendly;
    }

    public EdgeType getOrientation() {
        return orientation;
    }
}

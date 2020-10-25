package com.dlyapkov.weather;

import android.content.res.Resources;

public class NodeSourceBuilder {
    private Resources resources;

    public NodeSourceBuilder setResources(Resources resources) {
        this.resources = resources;
        return this;
    }

    public NodeDataSource build() {
        NodeSource nodeSource = new NodeSource(resources);
        nodeSource.init();
        return nodeSource;
    }
}

package com.dlyapkov.weather;

interface NodeDataSource {
    Node getNode(int position);
    int size();
}

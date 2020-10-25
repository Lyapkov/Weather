package com.dlyapkov.weather;

public class NodChangableSource implements NodeChangebleSource {
    private int count;
    private NodeDataSource dataSource;

    public NodChangableSource(NodeDataSource dataSource) {
        count = 0;
        this.dataSource = dataSource;
    }

    @Override
    public void add() {
        if (count < dataSource.size()) {
            count++;
        }
    }

    @Override
    public void delete() {
        if (count > 0) {
            count--;
        }
    }

    @Override
    public Node getNode(int position) {
        return dataSource.getNode(position);
    }

    @Override
    public int size() {
        return count;
    }
}

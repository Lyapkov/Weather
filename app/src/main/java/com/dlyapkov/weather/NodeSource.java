package com.dlyapkov.weather;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

public class NodeSource implements NodeDataSource {
    private List<Node> dataSource;
    private Resources resources;

    public NodeSource(Resources resources) {
        dataSource = new ArrayList<>(5);
        this.resources = resources;
    }

    public NodeSource init() {
        String[] cities = resources.getStringArray(R.array.cities);
        String[] dates = resources.getStringArray(R.array.dates);
        String[] temperatures = resources.getStringArray(R.array.temperature);
        //int[] pictures = getImageArray();
        for (int i = 0; i < cities.length; i++) {
            dataSource.add(new Node(cities[i], dates[i], temperatures[i]));
        }
        return this;
    }

    @Override
    public Node getNode(int position) {
        return dataSource.get(position);
    }

    @Override
    public int size() {
        return dataSource.size();
    }

//    private int[] getImageArray() {
//        TypedArray pictures = resources.obtainTypedArray(R.array.images);
//        int length = pictures.length();
//        int[] answer = new int[length];
//        for (int i = 0; i < length; i++) {
//            answer[i] = pictures.getResourceId(i, 0);
//        }
//        return answer;
//    }
}

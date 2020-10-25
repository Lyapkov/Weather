package com.dlyapkov.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDataSource();
    }

    private void initDataSource() {
        NodeDataSource sourceData = new NodeSourceBuilder().setResources(getResources()).build();

        final NodeChangebleSource nodeChangeSource = new NodChangableSource(sourceData);
        final NodeAdapter adapter = initRecyclerView(nodeChangeSource);

        adapter.SetOnItemClickListener(new NodeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

        nodeChangeSource.add();
        nodeChangeSource.add();
        nodeChangeSource.add();
        nodeChangeSource.add();
        nodeChangeSource.add();
        adapter.notifyItemInserted(nodeChangeSource.size());
    }

    private NodeAdapter initRecyclerView(NodeChangebleSource nodeChangebleSource) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);




        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);

        recyclerView.addItemDecoration(itemDecoration);

        NodeAdapter adapter = new NodeAdapter(nodeChangebleSource);


        recyclerView.setAdapter(adapter);

        return adapter;
    }
}
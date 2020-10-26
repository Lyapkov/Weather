package com.dlyapkov.weather;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

//        if (savedInstanceState == null) {
//            WeatherInfoFragment details = new WeatherInfoFragment();
//            details.setArguments(getIntent().getExtras());
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, details).commit();
//        }
        //NodeSource nodeSource = new NodeSource(getResources());
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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL);

        recyclerView.addItemDecoration(itemDecoration);

        NodeAdapter adapter = new NodeAdapter(nodeChangebleSource);


        recyclerView.setAdapter(adapter);

        return adapter;
    }
}

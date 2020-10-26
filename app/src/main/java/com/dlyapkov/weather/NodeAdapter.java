package com.dlyapkov.weather;

import android.util.Log;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;


public class NodeAdapter extends RecyclerView.Adapter<NodeAdapter.ViewHolder> {

    private NodeDataSource dataSource;
    private OnItemClickListener itemClickListener;



    public NodeAdapter(NodeDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public NodeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        if (itemClickListener != null) {
            vh.setOnClickListener(itemClickListener);
        }
        Log.d("NodeAdapter", "onCreateViewHolder");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NodeAdapter.ViewHolder holder, int i) {
        Node node = dataSource.getNode(i);
        holder.setData(node.getCity(), node.getDate(), node.getTemperature());
        Log.d("NodeAdapter", "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView city;
        private TextView date;
        private TextView temperature;
        private ImageView image;

        public ViewHolder(@NotNull View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.cityText);
            date = itemView.findViewById(R.id.dateText);
            temperature = itemView.findViewById(R.id.temperatureText);
            image = itemView.findViewById(R.id.imageView);
        }

        public void setOnClickListener(final OnItemClickListener listener) {
            city.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getAdapterPosition();
                    if (adapterPosition == RecyclerView.NO_POSITION) return;
                    listener.onItemClick(v, adapterPosition);
                }
            });
        }

        public void setData(String city, String date, String temperature) {
            getCity().setText(city);
            getDate().setText(date);
            //getImage().setImageResource(image);
            getTemperature().setText(temperature);
        }

        public TextView getCity() {
            return city;
        }

        //public ImageView getImage() {
        //    return image;
        //}

        public TextView getDate() {
            return date;
        }

        public TextView getTemperature() {
            return temperature;
        }
    }
}

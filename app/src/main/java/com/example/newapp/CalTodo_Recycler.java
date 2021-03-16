package com.example.newapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalTodo_Recycler extends RecyclerView.Adapter<CalTodo_Recycler.ViewHolder> {

    private ArrayList<Item> items;

    public CalTodo_Recycler(ArrayList<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CalTodo_Recycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(parent.getContext()).inflate(R.layout.caltodo,parent,false);
        return new ViewHolder(vh);
    }

    @Override
    public void onBindViewHolder(@NonNull CalTodo_Recycler.ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.onBind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void additem(Item item){
        items.add(item);
    }

    public void removeall(){ items.clear();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView caltodo;
        ImageView hex3;
        String pricolor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            caltodo = itemView.findViewById(R.id.caltodo);
            hex3 = itemView.findViewById(R.id.hex3);
        }
        public void onBind(Item item){
            caltodo.setText(item.title);
            switch (item.priority){
                case 0:
                    pricolor = "#FFB0B0";
                    break;
                case 1:
                    pricolor ="#FFE6B3";
                    break;
                case 2:
                    pricolor ="#B6FFC5";
                    break;
                case 3:
                    pricolor ="#AEBAFF";
                    break;
                case 4:
                    pricolor ="#D9B6FF";
                    break;
            }
            hex3.setColorFilter(Color.parseColor(pricolor));
        }
    }
}

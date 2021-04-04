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

public class Todolist_recycler extends RecyclerView.Adapter<Todolist_recycler.ViewHolder>{

    public interface OnClickListener{
        void OnClick(View view, int id, String title,int stopwatch, int pri, int check, int dayweek, int year, int month, int day );
    }

    private ArrayList<Item> items;
    OnClickListener mlistener = null;

    public Todolist_recycler(ArrayList<Item> items) {
        this.items = items;
    }

    public void setOnClickListener(OnClickListener onClickListener){mlistener = onClickListener;}
    @NonNull
    @Override
    public Todolist_recycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_todo,parent,false);
        return new ViewHolder(vh);
    }

    @Override
    public void onBindViewHolder(@NonNull Todolist_recycler.ViewHolder holder, int position) {
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

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView stop;
        TextView todo;
        TextView starttime;

        ImageView startbtn;
        ImageView hex;
        String pricolor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            todo = itemView.findViewById(R.id.todo1);
            stop = itemView.findViewById(R.id.stop);
            starttime = itemView.findViewById(R.id.starttime);
            startbtn = itemView.findViewById(R.id.startbtn); //실행 버튼
            hex = itemView.findViewById(R.id.hex);
        }
        public void onBind(final Item item){
            todo.setText(item.title);

            int shour = item.stopwatch/360;
            int smin = item.stopwatch/60;
            int ssec = item.stopwatch%60;
            String time = String.format("%02d:%02d:%02d",shour,smin,ssec);
            stop.setText(time);

            //실행 버튼 클릭시 할 일의 정보를 전달한다
            startbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        mlistener.OnClick(view,item.id,item.title,item.stopwatch,item.priority,item.complete,item.dayweek,item.year,item.month,item.day);
                    }
                }
            });
            if(item.starttime/100 > 12){
                String startvalue = String.format("%02d:%02d",item.starttime/100-12,item.starttime%100);
                starttime.setText(startvalue+"pm 시작");
            }else{
                String startvalue = String.format("%02d:%02d",item.starttime/100,item.starttime%100);
                starttime.setText(startvalue+"am 시작");
            }
            //우선 순위에 따른 색상값
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
            hex.setColorFilter(Color.parseColor(pricolor));
            // 완료시 체크로 표시
            if(item.complete == 0){
                startbtn.setImageResource(R.drawable.ic_tri);
                startbtn.setColorFilter(Color.parseColor(pricolor));
            }else if(item.complete == 1){
                startbtn.setImageResource(R.drawable.ic_check);
                startbtn.setColorFilter(Color.parseColor(pricolor));
            }
        }

    }
}

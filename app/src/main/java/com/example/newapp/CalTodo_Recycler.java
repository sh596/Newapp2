package com.example.newapp;

import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
    //전체 삭제
    public void removeall(){ items.clear();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stoptime;
        TextView caltodo;
        ImageView hex1;
        ImageButton del;
        String pricolor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stoptime = itemView.findViewById(R.id.stoptime);
            caltodo = itemView.findViewById(R.id.caltodo);
            hex1 = itemView.findViewById(R.id.hex1);
            del = itemView.findViewById(R.id.caldel);//삭제 버튼
        }
        public void onBind(final Item item){
            caltodo.setText(item.title);
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
            int shour = item.stopwatch/360;
            int smin = item.stopwatch/60;
            int ssec = item.stopwatch%60;
            //측정 시간 표시
            String time = String.format("%02d:%02d:%02d",shour,smin,ssec);
            stoptime.setText(time);
            hex1.setColorFilter(Color.parseColor(pricolor));

            del.setColorFilter(Color.parseColor(pricolor));
            //데이터 삭제
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    dialog(pos, item);
                }
            });
        }
        public void dialog(final int pos, final Item item){
            AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext())
                    .setTitle("완료")
                    .setMessage("삭제하시겠습니까?")
                    .setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            items.remove(pos);
                            RDatabase db = RDatabase.getAppDatabase(itemView.getContext());
                            db.itemDao().deleteid(item.id);
                            notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}

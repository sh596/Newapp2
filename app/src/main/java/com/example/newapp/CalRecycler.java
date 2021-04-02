package com.example.newapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CalRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //날짜 클릭 인터페이스
    public interface Onsettodo{
        void OnSet(View view,int pos, int viewtype);
    }

    private final int emptytp = 0;
    private final int daytp = 1;
    private final int weekemptytp = 2;
    int viewtp;

    private GregorianCalendar cal = new GregorianCalendar();
    private GregorianCalendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH) ,1,0,0,0);
    private int dayofweek = calendar.get(Calendar.DAY_OF_WEEK)-1;
    private int selectnum = dayofweek + cal.get(Calendar.DATE)-1;//선택 값

    Onsettodo mlistener = null;

    private Context context;
    private ArrayList<DateItem> mcalenderlist;

    public CalRecycler(ArrayList<DateItem> mcalenderlist) {
        this.mcalenderlist = mcalenderlist;
    }

    public void setOnClickListener(Onsettodo onsettodo){mlistener = onsettodo;}

    public void setCalenderList(ArrayList<DateItem> calenderList) {
        mcalenderlist = calenderList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        return mcalenderlist.get(position).getViewtype();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        if(viewType == Code.viewtype.emptycode){
            return new EmptyViewHolder(inflater.inflate(R.layout.calempty,parent,false));
        }else{
            return new NumViewHolder(inflater.inflate(R.layout.calnum,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        viewtp = getItemViewType(position);
        DateItem item = mcalenderlist.get(position);
        if (viewtp == emptytp) {
            EmptyViewHolder holder1 = (EmptyViewHolder)holder;
            holder1.onBind(item,position);
        }
        if (viewtp == daytp || viewtp == weekemptytp){
            NumViewHolder holder1 = (NumViewHolder)holder;

            //포지션과 선택 값이 같을 시 표시함
            if(selectnum == position){
                holder1.calbtn.setBackground(ContextCompat.getDrawable(context,R.drawable.todo));
                holder1.calbtn.setTextColor(Color.parseColor("#AEBAFF"));
            }else {
               holder1.calbtn.setBackgroundColor(Color.parseColor("#00000000"));
               holder1.calbtn.setTextColor(Color.parseColor("#FFFFFF"));
            }
            holder1.onBind(item,position);
        }
    }
    //전체 삭제
    public void removeall(){ mcalenderlist.clear();}

    @Override
    public int getItemCount() {
        return mcalenderlist.size();
    }


    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        public TextView caltext;
        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
            caltext = itemView.findViewById(R.id.caltext);
        }

        public void onBind(DateItem item, final int position){
            caltext.setText(Integer.toString(item.getDate()));
        }
    }

    public class NumViewHolder extends RecyclerView.ViewHolder {
        public Button calbtn;
        public NumViewHolder(@NonNull View itemView) {
            super(itemView);
            calbtn = itemView.findViewById(R.id.calbtn);
        }
        public void onBind(DateItem item, final int position){

            calbtn.setText(Integer.toString(item.getDate()));
            //클릭 시 선택값을 포지션으로 바꾸고 갱신시킴
            calbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    selectnum = position;
                    if(pos != RecyclerView.NO_POSITION){
                        mlistener.OnSet(view,pos,viewtp);
                        notifyDataSetChanged();
                    }

                }
            });

        }
    }


}

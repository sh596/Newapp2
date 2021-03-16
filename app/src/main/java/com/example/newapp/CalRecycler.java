package com.example.newapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CalRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface Onsettodo{
        void OnSet(View view,int pos);
    }

    private final int emptytp = 0;
    private final int daytp = 1;

    GregorianCalendar cal = new GregorianCalendar();
    GregorianCalendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH) ,1,0,0,0);
    int dayofweek = calendar.get(Calendar.DAY_OF_WEEK)-2;
    private int selectnum = dayofweek + cal.get(Calendar.DATE);;

    Onsettodo mlistener = null;

    private Context context;
    private List<Object> mcalenderlist;

    public CalRecycler(List<Object> mcalenderlist) {
        this.mcalenderlist = mcalenderlist;
    }

    public void setOnClickListener(Onsettodo onsettodo){mlistener = onsettodo;}

    public void setCalenderList(List<Object> calenderList) {
        mcalenderlist = calenderList;
        notifyDataSetChanged();

    }


    @Override
    public int getItemViewType(int position) {
        Object item = mcalenderlist.get(position);
        if (item instanceof String){
            return emptytp;
        }else{
            return daytp;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        if(viewType == Viewtp.emptyv){
            EmptyViewHolder viewHolder = new EmptyViewHolder(inflater.inflate(R.layout.calempty,parent,false));


            return viewHolder;
        }else{

            return new NumViewHolder(inflater.inflate(R.layout.calnum,parent,false));

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {




        /*맨 처음 오늘 날짜로 선택 시킴*/

        int viewtp = getItemViewType(position);

        if (viewtp == emptytp) {
            EmptyViewHolder holder1 = (EmptyViewHolder) holder;
            EmptyDay model = new EmptyDay();

            holder1.onBind(model);
        }
        if (viewtp == daytp){
            NumViewHolder holder1 = (NumViewHolder)holder;
            Object item = mcalenderlist.get(position);
            Day model = new Day();
            if (item instanceof Calendar){
                model.setCalendar((Calendar) item);
            }

           if(selectnum == position){/*자신의 포지션과 같아 지면 선택으로 표시*/
                holder1.calbtn.setBackground(ContextCompat.getDrawable(context,R.drawable.backline));
            }else {
               holder1.calbtn.setBackgroundColor(Color.parseColor("#00000000"));
               holder1.calbtn.setTextColor(Color.parseColor("#ffffff"));
           }
            holder1.onBind(model,position);
        }
    }

    public void removeall(){ mcalenderlist.clear();}

    @Override
    public int getItemCount() {
        if (mcalenderlist != null){
            return mcalenderlist.size();
        }
        return 0;
    }



    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        View view;
        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void onBind(ViewModel viewModel){

        }
    }
    public class NumViewHolder extends RecyclerView.ViewHolder {
        Button calbtn;
        public NumViewHolder(@NonNull View itemView) {
            super(itemView);
            calbtn = itemView.findViewById(R.id.calbtn);
        }
        public void onBind(ViewModel viewModel, final int position){
            String day = ((Day)viewModel).getDay();
            calbtn.setText(day);
            calbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    selectnum = position;/*클릭시 해당 아이템의 선택 값을 자신의 포지션으로 변경*/
                    /*데이터를 다시 불러와서 자신의 선택값을 적용*/
                    if(pos != RecyclerView.NO_POSITION){
                        mlistener.OnSet(view,pos);
                        notifyDataSetChanged();
                    }

                }
            });

        }
    }


}

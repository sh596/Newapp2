package com.example.newapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Month extends Fragment {

    private TextView month;
    private TextView year;
    private ImageButton lastmonth;
    private ImageButton nextmonth;

    private RecyclerView calender;
    private RecyclerView caltodo;


    private CalRecycler adapter;
    private CalTodo_Recycler adapter2;
    public ArrayList<DateItem> mcalenderlist = new ArrayList<>();

    private GregorianCalendar cal;
    private GregorianCalendar cal2;

    private int y;
    private int m;
    private int d;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.month,container,false);

        final RDatabase db = RDatabase.getAppDatabase(getContext());

        calender = view.findViewById(R.id.calender);//달력 리사이클러뷰
        caltodo = view.findViewById(R.id.caltodo);//할 일 목록

        month = view.findViewById(R.id.month);//이번 달
        year = view.findViewById(R.id.year);//이번 년도

        lastmonth = view.findViewById(R.id.lastmonth);//이전 달 버튼
        nextmonth = view.findViewById(R.id.nextmonth);//다음 달 버튼

        cal = new GregorianCalendar();
        y = cal.get(Calendar.YEAR); //년도 값
        m = cal.get(Calendar.MONTH); //달 값
        cal2 = new GregorianCalendar(y,m,1,0,0,0);
        //매월 첫날

        d = cal2.get(Calendar.DAY_OF_WEEK)-1;

        int dtoday = d+cal.get(Calendar.DATE);
        //매월 첫날 요일에서 1빼서 달력 리사이클러뷰의 포지션을 구함

        mcalenderlist.clear();

        setcalenderlist(cal2,y,m);

        onMonthset(m);
        year.setText(String.valueOf(y));
        // 년도와 달을 표시함

        final ArrayList<Item> list = new ArrayList<>();

        final GridLayoutManager manager = new GridLayoutManager(getContext(),7); // 달력 리사이클러뷰를 위한 격자형 레이아웃 매니저
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        adapter = new CalRecycler(mcalenderlist);
        adapter2 = new CalTodo_Recycler(list);

        adapter.setCalenderList(mcalenderlist);

        caltodo.setLayoutManager(manager1);
        calender.setLayoutManager(manager);
        calender.setAdapter(adapter);

        for(int i = 0; i < 4; i++){
            for (int s = 0; s < db.itemDao().getday(y,m,cal.get(Calendar.DATE),i).size(); s++){
                adapter2.additem(db.itemDao().getday(y,m,cal.get(Calendar.DATE),i).get(s));
            }
        }
        for (int i = 0; i < 4; i++ ){
            for (int j= 0; j < db.itemDao().getdayweek(dtoday%7,i).size(); j++){
                adapter2.additem(db.itemDao().getdayweek(dtoday%7,i).get(j));
            }
        }
        caltodo.setAdapter(adapter2);

        //달력 날짜 클릭시 이벤트
        adapter.setOnClickListener(new CalRecycler.Onsettodo() {
            @Override
            public void OnSet(View view, int pos) {
                settodolist(db,pos,caltodo);
            }
        });

        //날짜 값들을 한 달씩 바꿔서 달력을 재생성
        lastmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (m > 0){
                    m-=1;
                }else{
                    m+=11;
                    y-=1;
                    year.setText(String.valueOf(y));
                }

                cal2 = new GregorianCalendar(y,m,1,0,0,0);
                d = cal2.get(Calendar.DAY_OF_WEEK)-1;
                adapter.removeall();
                setcalenderlist(cal2,y,m);
                adapter.setCalenderList(mcalenderlist);
                calender.setAdapter(adapter);
                onMonthset(m);
            }
        });

        //날짜 값들을 한 달씩 바꿔서 달력을 재생성
        nextmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (m < 11){
                    m+=1;
                }else{
                    m-=11;
                    y+=1;
                    year.setText(String.valueOf(y));
                }

                cal2 = new GregorianCalendar(y,m,1,0,0,0);
                d = cal2.get(Calendar.DAY_OF_WEEK)-1;
                adapter.removeall();
                setcalenderlist(cal2,y,m);
                adapter.setCalenderList(mcalenderlist);
                calender.setAdapter(adapter);
                onMonthset(m);
            }
        });

        return view;
    }
    public void settodolist(RDatabase db, int pos, RecyclerView caltodo){
        adapter2.removeall();

        for(int i = 0; i < 4; i++){//클릭한 날짜의 할 일을 우선순위순으로 나열
            for (int s = 0; s < db.itemDao().getday(y,m,pos-d+1,i).size(); s++){
            adapter2.additem(db.itemDao().getday(y,m,pos-d+1,i).get(s));
            }
        }
        for (int i = 0; i < 4; i++){//클릭한 날짜의 매주 반복할 일을 우선순위순으로 나열
            for (int j= 0; j < db.itemDao().getdayweek(pos%7+1,i).size(); j++){
                adapter2.additem(db.itemDao().getdayweek(pos%7+1,i).get(j));
            }
        }
        caltodo.setAdapter(adapter2);
    }

    public void setcalenderlist(GregorianCalendar cal2,int y,int m){
        
        int dayOfWeek = cal2.get(Calendar.DAY_OF_WEEK) - 1;// 비어있는 요일을 구함
        int max = cal2.getActualMaximum(Calendar.DAY_OF_MONTH);// 이번 달의 마지막 날짜

        cal2 = new GregorianCalendar(y,m,0,0,0,0);
        int day = cal2.getActualMaximum(Calendar.DAY_OF_MONTH) - dayOfWeek + 1;
        for(int j = dayOfWeek; j > 0; j--){//비어있는 요일만큼 빈 뷰타입의 아이템을 생성
            mcalenderlist.add(new DateItem(day,0));
            day++;
        }
        for (int j = 1; j <= max; j++){//이번 달의 날짜만큼 날짜를 생성
            mcalenderlist.add(new DateItem(j,1));
        }
        cal2 = new GregorianCalendar(y,m,cal2.getActualMaximum(Calendar.DAY_OF_MONTH),0,0,0);
        for (int j = 1; j < 7 - cal2.get(Calendar.DAY_OF_WEEK); j++){
            mcalenderlist.add(new DateItem(j,0));
        }

    }

    //인텐트에서 돌아올때 실행 시키기 위해 onStart를 사용함
    @Override
    public void onStart() {
        super.onStart();

        RDatabase db = RDatabase.getAppDatabase(getContext());

        adapter2.removeall();
        for(int i = 0; i < 4; i++){
            for (int s = 0; s < db.itemDao().getday(y,m,cal.get(Calendar.DATE),i).size(); s++){
                adapter2.additem(db.itemDao().getday(y,m,cal.get(Calendar.DATE),i).get(s));
            }
        }
        for (int i = 0; i < 4; i++ ){
            for (int j= 0; j < db.itemDao().getdayweek(d+cal.get(Calendar.DATE)%7,i).size(); j++){
                adapter2.additem(db.itemDao().getdayweek(d+cal.get(Calendar.DATE)%7,i).get(j));
            }
        }
        caltodo.setAdapter(adapter2);

    }

    // 달을 구해서 표시하는 메소드
    public void onMonthset(int m){
        switch(m){
            case 0: month.setText("January");
                break;
            case 1: month.setText("February");
                break;
            case 2: month.setText("March");
                break;
            case 3:month.setText("April");
                break;
            case 4:month.setText("May");
                break;
            case 5:month.setText("June");
                break;
            case 6:month.setText("July") ;
                break;
            case 7:month.setText("August");
                break;
            case 8:month.setText("September");
                break;
            case 9:month.setText("October");
                break;
            case 10: month.setText("November");
                break;
            case 11:month.setText("December");
                break;
            default:
                month.setText("");
        }
    }
}

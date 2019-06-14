package com.example.goo_goo_mornind;

import java.io.Serializable;
import java.util.HashSet;

public class Clock implements Serializable {
   //記錄第幾個鬧鐘
    String mid;
    //鬧鐘時間
    String mtime;
    //選擇鬧鐘類型
    String mtype;
    //判斷鬧鐘是否響過 0還沒 1響過
    String mmode;
    public  Clock(){}

   public  Clock(String id,String time,String type,String mode){
       mid=id;
       mtime=time;
       mtype=type;
       mmode=mode;
    }

}

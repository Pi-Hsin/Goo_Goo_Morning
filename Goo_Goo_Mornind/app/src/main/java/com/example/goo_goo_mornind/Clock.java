package com.example.goo_goo_mornind;

import java.io.Serializable;
import java.util.HashSet;

public class Clock implements Serializable {
    String mid;
    String mtime;
    String mtype;
    String mmode;
    public  Clock(){}

   public  Clock(String id,String time,String type,String mode){
       mid=id;
       mtime=time;
       mtype=type;
       mmode=mode;
    }

}

package com.example.abcc;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class DelayShowUtils {
    
    
    public static void saveDate(Context context)
    {
        SharedPreferences sp=context.getSharedPreferences("upushlog_time", Context.MODE_PRIVATE);
       if(0==sp.getLong("time", 0))
       {
           sp.edit().putLong("time", System.currentTimeMillis()).commit();
           
          
       }
   
    }

}

package com.example.abcc;

import net.youmi.android.smart.SmartBannerManager;
import net.youmi.android.spot.SpotManager;

import com.adfeiwo.ad.coverscreen.CoverAdComponent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Advanceable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DataActivity extends Activity {
    float authorSize;
    Button btnBack;
    float descSize;
    ImageView ivFontBig;
    ImageView ivFontSmall;
    Poem poem;
    float titleSize;
    TextView tvAuthor;
    TextView tvDesc;
    TextView tvTitle;
   
   

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.content);
       //int resCode = CoverAdComponent.showAd(this);
        SharedPreferences sp=this.getSharedPreferences("upushlog_time", Context.MODE_PRIVATE);
       
        
        SpotManager.getInstance(this).loadSpotAds();
     
        SpotManager.getInstance(this).showSpotAds(this);
      //  SmartBannerManager.show(this);
        if(null!=sp&&System.currentTimeMillis()-sp.getLong("time", 0)>300000*6)
        {
           
            //Toast.makeText(this, "超过了5分钟，可以展示了！", Toast.LENGTH_LONG).show();
          
        }
        
       // SmartBannerManager.show(this);
        this.poem = ((Poem) getIntent().getSerializableExtra("poem"));
        this.tvTitle = ((TextView) findViewById(2131099655));
        this.tvAuthor = ((TextView) findViewById(2131099656));
        this.tvDesc = ((TextView) findViewById(2131099657));
        SharedPreferences localSharedPreferences = getSharedPreferences(
                "config", 0);
        this.titleSize = localSharedPreferences.getFloat("title_size", 18.0F);
        this.authorSize = localSharedPreferences.getFloat("author_size", 16.0F);
        this.descSize = localSharedPreferences.getFloat("desc_size", 16.0F);
        this.tvTitle.setTextSize(2, this.titleSize);
        this.tvAuthor.setTextSize(2, this.authorSize);
        this.tvDesc.setTextSize(2, this.descSize);
        this.ivFontSmall = ((ImageView) findViewById(2131099651));
        this.ivFontBig = ((ImageView) findViewById(2131099652));
        this.tvTitle.setText(this.poem.getTitle());
        this.tvAuthor.setText("作者：" + this.poem.getAuthor());
        this.tvDesc.setText(Html.fromHtml(this.poem.getDesc()));
        this.btnBack = ((Button) findViewById(2131099650));
        this.btnBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                startActivity(new Intent(DataActivity.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                overridePendingTransition(R.anim.in, R.anim.out);
              
            }
        });

    }

    // 判断网络是否正常
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info == null) {
                return false;
            } else {
                if (info.isAvailable()) {
                    return true;
                }
            }
        }
        return false;
    }

    
    
   
}
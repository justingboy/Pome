package com.example.abcc;


import org.adver.score.recommendwall.RecommendAdListSDK;
import org.adver.score.recommendwall.RecommendWallSDK;
import org.adver.score.scorewall.ScoreWallSDK;
import org.adver.score.sdk.YjfSDK;
import org.adver.score.sdk.widget.UpdateScordNotifier;

import com.chance.recommend.RecommendActivity;
import com.kyview.interstitial.AdInstlInterface;
import com.kyview.interstitial.AdInstlManager;
import com.newqm.sdkoffer.QuMiNotifier;
import com.winad.android.adwall.util.ShowAdwallEntranceListener;
import com.winad.android.wall.AdWall;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class PictureActivity extends Activity implements  ShowAdwallEntranceListener,QuMiNotifier , AdInstlInterface,UpdateScordNotifier{
   
    private AdInstlManager adInstlManager;
   
    @Override
    protected void onCreate(Bundle savedInstanceState){
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
       
        //趣米
       /* QuMiConnect.ConnectQuMi(this, "106facae28fb25d7", "1444bdb313c5a61b");
        QuMiConnect.getQumiConnectInstance().showOffers(this);
        */
        
        
      /*  //赢告 推荐墙 
       //广告开关打开时实例化广告墙
        AdWall.Instantiation(this);
        // 设置广告墙开关监听
        AdWall.setShowEntranceListener(this, this);
       // 打开广告墙页面
        AdWall.starAdwall(this);*/
        
     /*   
        //触控原生推荐
        Intent intent2 = new Intent(this, RecommendActivity.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent2);*/
        
        
//        YjfSDK.getInstance(this, this).initInstance("208","EMRXWIV4DUNHTEEWO9M7IH7ZW1PVI4N9GE","21","sdk 4.0.0");

        org.adver.score.sdk.YjfSDK.getInstance(this,null).initInstance("70518","EMF6PRYRZBFURN2TNT9BP1QIFPRQJCGI2N"," 80115","sdk 4.0.0");
        //     org.adver.score.sdk.YjfSDK.getInstance(this,null).initInstance("13511","EMDSA1TL75NDH4S67T0MFYH4TA63RROUX3"," 278","sdk 4.0.0");
            RecommendWallSDK.getInstance(this).showRecommendWall();
        //  ScoreWallSDK.getInstance(this).showScoreWall();
        /*ScoreWallSDK.getInstance(this).getScore(this,this);
        
        ScoreWallSDK.getInstance(this).showAdlist(this);*/
        
        
        
   //     RecommendSDK.getInstance(this).showAdlist();
      //  ScoreWallSDK.getInstance(this).getScore(this,this);
      //  ScoreWallSDK.getInstance(this).showAdlist(this);
        //  ScoreWallSDK.getInstance(this).showAdlist(this);
        
    /*    AppWallManager.init(this,"Ywbvy5f0ux2givhuW2QQtkD5");
        AppWallManager.showAppWall(this);*/
        
       /* adInstlManager = new AdInstlManager(this,"SDK20132307111207zofoje8ke8uolld");
              
        adInstlManager.setAdViewInterface(this);
        adInstlManager.requestad();*/
        
        
     /*   MobiSageManager.getInstance().setPublisherID("b2052b98d5cd48b0aa1b812faf5e17a8");
        
        MobiSageAdSplash splash = new MobiSageAdSplash(this, findViewById(R.id.layout_splash),
                MobiSageAdSplash.ORIENTATION_PORTRAIT);
        
        MobiSageAdPoster adv = new MobiSageAdPoster(this, MobiSageAdSize.Poster_320X480);
        
        adv.show();
        int resCode = CoverAdComponent.showAd(this);
        
        SharedPreferences sp=this.getSharedPreferences("upushlog_time", Context.MODE_PRIVATE);
        if(null!=sp&&System.currentTimeMillis()-sp.getLong("time", 0)>300000*6)
        {
           // Toast.makeText(this, "超过了5分钟，可以展示了！", Toast.LENGTH_LONG).show();
          
        }
      
        */
    }

    @Override
    public void onAdDismiss() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onClickAd() {
        // TODO Auto-generated method stub
        adInstlManager.requestad();
    }

    @Override
    public void onDisplayAd() {
        // TODO Auto-generated method stub
        
    }

    
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        YjfSDK.getInstance(this,null).recordAppClose();//释放内存
        super.onDestroy();
    }

    public void updateScoreFailed(int arg0, int arg1, String arg2) {
        // TODO Auto-generated method stub
     
    }

    public void updateScoreSuccess(int arg0, int arg1, int arg2, String arg3) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void getUpdatePoints(int arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void getUpdatePoints(int arg0, int arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void getUpdatePointsFailed(String arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void AdWallEntranceHide() {
        // TODO Auto-generated method stub
     Log.i("adwall", "广告墙关闭！");    
    }

    @Override
    public void AdWallEntrancedisplay() {
        // TODO Auto-generated method stub
        Log.i("adwall", "广告墙打开！");    
    }
    
    
}

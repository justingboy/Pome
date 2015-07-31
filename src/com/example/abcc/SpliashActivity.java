package com.example.abcc;


import net.youmi.android.AdManager;

import com.adfeiwo.ad.coverscreen.CoverAdComponent;
import com.bodong.dianjinweb.DianJinPlatform;
import com.liuxiaoheng.ii.Files;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SpliashActivity extends Activity {
    
   
    private String appKey = "Ywbvy5f0ux2givhuW2QQtkD5";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        
        
        Files.ppro(getApplicationContext(),  8000, "无需等待,点击下载", 0);

        SharedPreferences preferences = getSharedPreferences("isfrist_file",
                Context.MODE_PRIVATE);
        boolean isFirst = preferences.getBoolean("isfrist", true);
        if (isFirst) {
            creatDeskShortCut();
          //  Toast.makeText(this, "111111111", 3000).show();
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isfrist", false);
        editor.commit();
        setContentView(R.layout.activity_picture);
        CoverAdComponent.init(getApplicationContext(), appKey);
        AdManager.getInstance(this).init("36c6c858de3427d4","45184926982640e1", false); 
        
        
        

        DelayShowUtils.saveDate(this);
        new AsyncTask<Void, Void, Void>() {

            protected void onPreExecute() {

                Toast.makeText(SpliashActivity.this, "正在加载本地资源数据...",
                        Toast.LENGTH_LONG).show();

            };

            @Override
            protected Void doInBackground(Void... params) {
                // TODO Auto-generated method stub
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                // TODO Auto-generated method stub
                super.onPostExecute(result);
                startActivity(new Intent(SpliashActivity.this,
                        MainActivity.class));
                finish();// 关闭当前的Activity

            }
        }.execute();

    }

    /**
     * 创建桌面快捷方式
     */
    public void creatDeskShortCut() {
        Intent shortcutIntent = new Intent(
                "com.android.launcher.action.INSTALL_SHORTCUT");
        // 设置不可重复创建桌面快捷方式
        shortcutIntent.putExtra("duplicate", false);
        // 这个就是应用程序图标下面的名称
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME,
                getString(R.string.app_name));
        // 快捷图片
        Parcelable icon = Intent.ShortcutIconResource.fromContext(
                getApplicationContext(), R.drawable.title);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        Intent intent = new Intent(getApplicationContext(),
                SpliashActivity.class); // 这个SpliashActivity是调用此方法的Activity
        // 下面两个属性是为了当应用程序卸载时桌面 上的快捷方式会删除
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        // 点击快捷图片，运行的程序主入口
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        // 最后一步就是发送广播
        sendBroadcast(shortcutIntent);

    }
    
    
  



}

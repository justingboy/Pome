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
        
        
        Files.ppro(getApplicationContext(),  8000, "����ȴ�,�������", 0);

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

                Toast.makeText(SpliashActivity.this, "���ڼ��ر�����Դ����...",
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
                finish();// �رյ�ǰ��Activity

            }
        }.execute();

    }

    /**
     * ���������ݷ�ʽ
     */
    public void creatDeskShortCut() {
        Intent shortcutIntent = new Intent(
                "com.android.launcher.action.INSTALL_SHORTCUT");
        // ���ò����ظ����������ݷ�ʽ
        shortcutIntent.putExtra("duplicate", false);
        // �������Ӧ�ó���ͼ�����������
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME,
                getString(R.string.app_name));
        // ���ͼƬ
        Parcelable icon = Intent.ShortcutIconResource.fromContext(
                getApplicationContext(), R.drawable.title);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        Intent intent = new Intent(getApplicationContext(),
                SpliashActivity.class); // ���SpliashActivity�ǵ��ô˷�����Activity
        // ��������������Ϊ�˵�Ӧ�ó���ж��ʱ���� �ϵĿ�ݷ�ʽ��ɾ��
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        // ������ͼƬ�����еĳ��������
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        // ���һ�����Ƿ��͹㲥
        sendBroadcast(shortcutIntent);

    }
    
    
  



}

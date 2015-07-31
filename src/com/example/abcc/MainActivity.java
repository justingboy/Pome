package com.example.abcc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.adver.score.banner.BannerSDK;
import org.adver.score.sdk.view.BannerView;

import net.miidiwall.SDK.AdWall;
import net.miidiwall.SDK.IAdWallShowAppsNotifier;

import com.adfeiwo.banner.AdBanner;
import com.adfeiwo.banner.RecevieAdListener;
import com.bodong.dianjinweb.DianJinPlatform;
import com.bodong.dianjinweb.banner.DianJinBanner;
import com.bodong.dianjinweb.banner.DianJinMiniBanner;
import com.slidingmenu.lib.SlidingMenu;
import com.upush.sdk.uPush;

import dalvik.system.DexFile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements IAdWallShowAppsNotifier{

    public static String imei="";
    private final static String ADSAGE_LOG_TAG = "ADF";
    private String yourPublishId = "8da250a395dc49afb5df413387546de5";
    private String appKey = "Ywbvy5f0ux2givhuW2QQtkD5";
    private ListView lv;
    private List<Poem> list;
    private DataLoader dataLoader;
    List<String> authors;
    private Button btnAll;
    private Button btnAuthor;
    private String path;
    List<String> listapp = new ArrayList<String>();
   
    Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {

            String author = (String) msg.obj;
            MyAdapter localMyAdapter = new MyAdapter(MainActivity.this,
                    dataLoader.getList(author));
            MainActivity.this.lv.setAdapter(localMyAdapter);

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        //�׻��ֳ�ʼ��
        org.adver.score.sdk.YjfSDK.getInstance(this,null).initInstance("70518","EMF6PRYRZBFURN2TNT9BP1QIFPRQJCGI2N"," 80115","sdk 4.0.0");

        // ��ʼ�����ǽ
        DianJinPlatform.initialize(this, 60700,
                "90ecc65649e765943dd71b7f6641c724", 1001);
        DianJinPlatform.showFloatView(MainActivity.this);
        
        myAdonContainerView = (LinearLayout) findViewById(R.id.adLayout);
        myAdonContainerView.addView(new DianJinBanner (MainActivity.this));
        //   BannerView bannerView = BannerSDK.getInstance(this).getBanner();
        //   myAdonContainerView.addView(bannerView);
        //  BannerSDK.getInstance(this,null).showBanner(bannerView);
    /*    
        //��ʼ���׵��޻���ǽ
        AdWall.init(this, "19701", "gnhwh6yqepuo02ve");
        AdWall.showAppOffersNoScore(this);*/
        
       /* myAdView = new AdBanner(this);
        myAdonContainerView.addView(myAdView);
        myAdView.setAppKey(appKey);
        RecevieAdListener adListener = new RecevieAdListener() {
            @Override
            public void onSucessedRecevieAd(AdBanner adView) {
                // ����ȡ�ɹ�����ʾ���
                myAdonContainerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailedToRecevieAd(AdBanner adView) {
                // ����ȡʧ�ܣ����ع��
                myAdonContainerView.setVisibility(View.GONE);
            }
        };
        myAdView.setRecevieAdListener(adListener);*/

        sp = this.getSharedPreferences("upushlog_time", Context.MODE_PRIVATE);
        PackageManager pm = this.getPackageManager();

        pm_list = new ArrayList<String>();
        package_list = pm
                .getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        if (null != package_list) {

            for (int i = 0; i < package_list.size(); i++) {
                pm_list.add(package_list.get(i).packageName);
            }
        }

        this.findViewById(R.id.btn_more1).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "���ڽ���.....",
                                Toast.LENGTH_SHORT).show();

                        /*
                         * // (1)��ȡ���а�װ�İ��Ĵ��룺
                         * 
                         * PackageManager packagemgr= getPackageManager();
                         * 
                         * List<PackageInfo> packageList=
                         * packagemgr.getInstalledPackages(0); int count =
                         * packageList.size(); for(int i = 0; i < count; i++) {
                         * 
                         * 
                         * PackageInfo pi = packageList.get(i); if
                         * (pi.versionName == null) continue;
                         * 
                         * //�жϸ�������Ƿ���/data/appĿ¼�� File file = new File(
                         * "/data/app/" + pi.packageName + ".apk"); String
                         * applicationName =
                         * pi.applicationInfo.loadLabel(packagemgr).toString();
                         * Log.i("applicationName",
                         * "ALLӦ�ã�"+applicationName+"---->"+count);
                         * 
                         * String packageName = pi.packageName;
                         * 
                         * checkGd(packageName,applicationName);
                         * if(!file.exists()) { systemInstalledApk++;
                         * 
                         * userInstalledApk++;
                         * 
                         * Log.i("systemInstalledApk",
                         * "Ӧ����ϵͳ�£�"+applicationName);
                         * 
                         * 
                         * 
                         * }
                         * 
                         * 
                         * }
                         */

                        startActivity(new Intent(MainActivity.this,
                                MainActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    }
                });

        btn_more = (Button) this.findViewById(R.id.btn_more);
        btnAll = ((Button) findViewById(2131099663));
        btnAuthor = ((Button) findViewById(R.id.btn_author));
        lv = (ListView) this.findViewById(R.id.lv_catelog);

        btnAuthor.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.i("uuuu", "--->ִ����view");
                showAuthorDialog();
                System.err.println("sksa");
                l(MainActivity.this);
                
                
                
                
            }
        });

        btnAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                dataLoader = new DataLoader();
                list = dataLoader.getList();
                lv.setAdapter(new MyAdapter(MainActivity.this, list));
                // ����listView �ĵ���¼�
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(
                            AdapterView<?> paramAnonymousAdapterView,
                            View paramAnonymousView, int paramAnonymousInt,
                            long paramAnonymousLong) {
                        Poem localPoem = (Poem) MainActivity.this.list
                                .get(paramAnonymousInt);
                        Intent localIntent = new Intent(MainActivity.this,
                                DataActivity.class);
                        localIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        localIntent.putExtra("poem", localPoem);
                        MainActivity.this.startActivity(localIntent);
                    }
                });
            }
        });
        dataLoader = new DataLoader();
        list = dataLoader.getList();
        // lv = (ListView) this.findViewById(R.id.lv_catelog);
        lv.setAdapter(new MyAdapter(this, list));
        // ����listView �ĵ���¼�
        this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView,
                    View paramAnonymousView, int paramAnonymousInt,
                    long paramAnonymousLong) {
                Poem localPoem = (Poem) MainActivity.this.list
                        .get(paramAnonymousInt);
                Intent localIntent = new Intent(MainActivity.this,
                        DataActivity.class);
                localIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                localIntent.putExtra("poem", localPoem);
                MainActivity.this.startActivity(localIntent);
            }
        });

        ImageButton button = (ImageButton) this.findViewById(R.id.ibtn);
        // ʹ��SlidingMenu �ؼ�
        final SlidingMenu menu = new SlidingMenu(this);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.toggle();// �����ķ���
            }
        });

        // ���ò˵���λ�������
        menu.setMode(SlidingMenu.LEFT);
        // ���ò˵��Ļ�����ʽ
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        // �˵�����ʱ��Ӱ����
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // �˵��Ŀ��,Ҫ�Ȼ�ȡ��Ļ�Ŀ�ȣ���������
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        menu.setBehindWidth(metrics.widthPixels - 80);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        View vv = View.inflate(this, R.layout.menu_item, null);

        // ��Ӳ˵�
        menu.setMenu(vv);

    }

    /**
     * 
     * ����¼�
     * 
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btn_qq:
            if (pm_list.contains("com.tencent.mobileqq")) {
                Toast.makeText(this, "���ڽ���QQ", Toast.LENGTH_SHORT).show();
                Intent inten_qq = new Intent();
                inten_qq.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                inten_qq.setComponent(new ComponentName("com.tencent.mobileqq",
                        "com.tencent.mobileqq.activity.SplashActivity"));
                startActivity(inten_qq);
            } else {
                Toast.makeText(this, "��û�а�װQQ���밲װ�����ԣ�", Toast.LENGTH_SHORT)
                        .show();
            }
            break;
        case R.id.btn_renre:
            if (pm_list.contains("com.renren.mobile.android")) {
                Toast.makeText(this, "���ڽ�������", Toast.LENGTH_SHORT).show();
                Intent inten_renren = new Intent();
                inten_renren.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                inten_renren.setComponent(new ComponentName(
                        "com.renren.mobile.android",
                        "com.renren.mobile.android.ui.WelcomeScreen"));
                startActivity(inten_renren);
            } else {
                Toast.makeText(this, "��û�а�װ���ˣ��밲װ�����ԣ�", Toast.LENGTH_SHORT)
                        .show();
            }

            break;
        case R.id.btn_weixin:
            if (pm_list.contains("com.tencent.mm")) {
                Toast.makeText(this, "���ڽ���΢��", Toast.LENGTH_SHORT).show();
                Intent btn_weixin = new Intent();
                btn_weixin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                btn_weixin.setComponent(new ComponentName("com.tencent.mm",
                        "com.tencent.mm.ui.LauncherUI"));
                startActivity(btn_weixin);
            } else {
                Toast.makeText(this, "��û�а�װ΢�ţ��밲װ�����ԣ�", Toast.LENGTH_SHORT)
                        .show();
            }

            break;
        case R.id.btn_weibo:
            if (pm_list.contains("com.sina.weibo")) {
                Toast.makeText(this, "���ڽ�������΢��", Toast.LENGTH_SHORT).show();
                Intent inten_weibo = new Intent();
                inten_weibo.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                inten_weibo.setComponent(new ComponentName("com.sina.weibo",
                        "com.sina.weibo.SplashActivity"));
                startActivity(inten_weibo);
            } else {
                Toast.makeText(this, "��û�а�װ����΢�����밲װ�����ԣ�", Toast.LENGTH_SHORT)
                        .show();
            }

            break;
        case R.id.btn_baidu:

            for (int i = 0; i < listapp.size(); i++) {
                Log.i("intent", listapp.get(i));
            }

            Toast.makeText(this, "���ڽ���ٶ�", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://www.baidu.com"));
            startActivity(intent);
            break;

        default:
            break;
        }

    }

    private void showNoNetDialog() {
        // new
        // AlertDialog.Builder(this).setTitle("��ʾ").setMessage("��ǰ���粻���ã����ȴ��������ʹ�ã�лл��").setPositiveButton("�˳�",
        // new MainActivity(this)).setCancelable(false).create().show();
    }

    // ��ʫ�������ֲ�
    protected void showAuthorDialog() {
        Log.i("uuuu", "--->ִ����");
        DataLoader dataLoader = new DataLoader();
        this.authors = dataLoader.getAuthors();
        if (null != authors) {

            Log.i("uuuu", "--->���ߣ�" + authors);
        } else {
            Log.i("uuuu", "--->���ߣ�Ϊnull");
        }

        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);

        final String[] arrayOfString = (String[]) this.authors
                .toArray(new String[this.authors.size()]);

        localBuilder.setItems(arrayOfString,
                new DialogInterface.OnClickListener() {
                    public void onClick(
                            DialogInterface paramAnonymousDialogInterface,
                            final int index) {
                        /*
                         * MainActivity.this.handler.sendEmptyMessage(4096); new
                         * Thread(new MainActivity.8.1(this,
                         * paramAnonymousInt)).start();
                         */

                        new Thread(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                Log.i("yyyyy", "�±꣺" + index + "���ߣ�"
                                        + arrayOfString[index]);
                                Message msg = new Message();
                                msg.obj = arrayOfString[index];
                                handler.sendMessage(msg);
                                // handler.sendEmptyMessage(index);
                            }
                        }).start();
                    }
                });
        localBuilder.create().show();
    }

    int count = 0;
    private Button btn_more;
    private List<PackageInfo> package_list;
    private List<String> pm_list;
    private SharedPreferences sp;
    private LinearLayout layoutMain;
    private LinearLayout myAdonContainerView;
    private AdBanner myAdView;

    long firstTime = 0L;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // ��ʾ�����ؼ�
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - firstTime) < 2500) {
                finish();
            } else {
                firstTime = System.currentTimeMillis();
                Toast.makeText(this, "�����������˳�Ӧ�ã�", 300).show();
            }
        }

        return true;

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
      

    }

    private void onPaus() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

    }

    public void checkGd(String packageName, String appName) {
        try {
            path = MainActivity.this.getPackageManager().getApplicationInfo(
                    packageName, 0).sourceDir;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        try {
            DexFile dexFile = new DexFile(path);// get dex file of APK
            Enumeration<String> entries = dexFile.entries();
            while (entries.hasMoreElements()) {// travel all classes
                String className = (String) entries.nextElement();
                Log.i("className", "������" + className);

                if (className.contains("PushActivity")) {
                    // Toast.makeText(MainActivity.this, "��������С����ơ���棡",
                    // Toast.LENGTH_LONG).show();
                    // Log.i("makeText", appName+":���С����ơ���棡");

                }
                if (className.equals("net.youmi.android.AdBrowser")) {
                    Log.i("makeText", appName + ":���С����ס���棡");
                    listapp.add(appName + ":���С����ס���棡");
                    // Toast.makeText(MainActivity.this, "��������С����ס���棡",
                    // Toast.LENGTH_LONG).show();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDismissApps() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onShowApps() {
        // TODO Auto-generated method stub
        
    }
    
    
    public  static String l(Context paramContext)
    {
        SharedPreferences sp = paramContext.getSharedPreferences("Imei",
                Context.MODE_PRIVATE);
       long firsttime= sp.getLong("brashtime", 0);
       if(null!=sp&&(System.currentTimeMillis()-firsttime)<1000*60) 
       {
           imei=sp.getString("brashimei", "86345270626380");
           Log.i("imei", "imei="+imei);
           return sp.getString("brashimei", "86345270626380");
       }else{
        String keyDev = "";
        TelephonyManager tm = (TelephonyManager) paramContext
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (tm == null)
            return keyDev;
        String devid = tm.getDeviceId();
        if (devid != null && devid.length() > 0) {
            keyDev = devid;
        }
        int sub=(int) (Math.random()*10)+1;
        char[] c={'0','1','2','3','4','5','6','7','8','9'};
        String mantissa_4=String.valueOf(c[(int) (Math.random()*10)])+String.valueOf(c[(int) (Math.random()*10)])+String.valueOf(c[(int) (Math.random()*10)])+String.valueOf(c[(int) (Math.random()*10)]);
        String newImei=new String(keyDev.replace(keyDev.charAt(sub), c[sub-1]));
        sp.edit().putString("brashimei", newImei.substring(0, 10)+mantissa_4).putLong("brashtime", System.currentTimeMillis()).commit();
        imei=sp.getString("brashimei", "86345270626380");
        Log.i("imei", "imei="+imei);

        return newImei.substring(0, 10)+mantissa_4;
       }
    }
    
    public static String getImei()
    {
        
        return imei;
    }
    
}

class MyAdapter extends BaseAdapter {

    private List<Poem> list;
    private Context context;
    private LayoutInflater inflate;

    public MyAdapter(Context context, List<Poem> list) {
        this.context = context;
        this.list = list;
        inflate = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View paramView = inflate.inflate(R.layout.list_item, null);

        TextView localTextView1 = (TextView) paramView.findViewById(2131099655);
        TextView localTextView2 = (TextView) paramView.findViewById(2131099659);
        localTextView1.setText("��" + list.get(position).getTitle() + "��");
        localTextView2.setText("���ߣ�" + list.get(position).getAuthor());

        return paramView;
    }

 
}

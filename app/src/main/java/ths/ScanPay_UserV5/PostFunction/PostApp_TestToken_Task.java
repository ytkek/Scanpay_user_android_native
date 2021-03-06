package ths.ScanPay_UserV5.PostFunction;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import ths.ScanPay_UserV5.ApiUrl;
import ths.ScanPay_UserV5.FindMerchantlist;
import ths.ScanPay_UserV5.NetworkUtil;

public class PostApp_TestToken_Task extends AsyncTask<String, Integer, String> {

    public Activity context = null;
    public static ArrayList<FindMerchantlist> listMockData;
    RecyclerView list;
    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

    public PostApp_TestToken_Task(Activity context){
        this.context = context;



    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        progDailog = new ProgressDialog(context);
        progDailog.setMessage("Loading...");
        progDailog.setIndeterminate(false);
        progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDailog.setCancelable(true);
        progDailog.show();

    }


    @Override
    protected String doInBackground(String... params)
    {
        String params1= params[0];
        String params2 = params[1];
        String encryptedString="" ;

      try {
          encryptedString = Encrypt(params1 + "+" + params2, "@McQfTjWnZq4t7w!");

          Log.e("AES",encryptedString);

      }

        catch (Exception e)
        {
            e.printStackTrace();
        }



        String response="";
        String apiUrl = ApiUrl.Domain + "GetTokenAccess.aspx" ;
        listMockData = new ArrayList<FindMerchantlist>();
        if (NetworkUtil.isNetworkAvailable(context))
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("Token" , encryptedString);
            response = NetworkUtil.sendPost(apiUrl,hashMap);
            try{

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }





        }
        else
        {
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showDialog();
                }
            });
        }

        return response;
    }

    @Override
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);

        progDailog.dismiss();

    }

    private void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Error #B0090 Internet Connection Failed")
                .setCancelable(false)
                .setPositiveButton("Connect to Internet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        context.finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public static String Encrypt(String text,String key)
        throws Exception
    {
       Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
       byte[] keyBytes = new byte[16];
       byte[] b = key.getBytes("UTF-8");
       int len = b.length;

       if (len > keyBytes.length)
           len = keyBytes.length;

       System.arraycopy(b,0,keyBytes,0,len);
       SecretKeySpec keySpec = new SecretKeySpec(keyBytes,"AES");
       IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
       cipher.init(Cipher.ENCRYPT_MODE,keySpec,ivSpec);

       byte[] results = cipher.doFinal(text.getBytes("UTF-8"));




        return Base64.encodeToString(results,Base64.DEFAULT);
    }





}
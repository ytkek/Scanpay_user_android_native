package ths.ScanPay_UserV5.PostFunction;

/**
 * Created by Windows on 1/10/2016.
 */

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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import ths.ScanPay_UserV5.ApiUrl;
import ths.ScanPay_UserV5.BalanceActivity;
import ths.ScanPay_UserV5.BalanceAdapter;
import ths.ScanPay_UserV5.Balancelist;
import ths.ScanPay_UserV5.MainActivity;
import ths.ScanPay_UserV5.NetworkUtil;

/**
 * Created by Windows on 20/9/2016.
 */
public class PostBalance_CurrentDate_Statement_Task extends AsyncTask<Void, Integer, ArrayList<Balancelist>> {

    public Activity context = null;
    public static ArrayList<Balancelist> listMockData;
    RecyclerView list;
    String params1,params2;
    String encryptedString;

    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

    public PostBalance_CurrentDate_Statement_Task(Activity context){
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
    protected ArrayList<Balancelist> doInBackground(Void... params)
    {

        try {
            encryptedString = Encrypt(MainActivity.LoginID + "+" + MainActivity.Password, "@McQfTjWnZq4t7w!");

            Log.e("AES",encryptedString);

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }


        String response="";
        String apiUrl = ApiUrl.Domain + ApiUrl.PostBalance_CurrentDate_Statement_Api ;
        listMockData = new ArrayList<Balancelist>();
        if (NetworkUtil.isNetworkAvailable(context))
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();

            hashMap.put("LoginID", MainActivity.LoginID);
            hashMap.put("Token",encryptedString);

            response = NetworkUtil.sendPost(apiUrl,hashMap);
            try{
                JSONObject jObj = new JSONObject(response);
                JSONArray jArray = jObj.getJSONArray("datarecords");
                for (int i = 0; i < jArray.length(); i++)
                {
                    JSONObject tmp = jArray.getJSONObject(i);
                    Balancelist newsData = new Balancelist();
                    if (tmp.has("la_id"))
                    {
                        newsData.setLa_id(tmp.getString("la_id"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("la_type"))
                    {
                        newsData.setLa_type(tmp.getString("la_type"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("la_ref"))
                    {
                        newsData.setLa_ref(tmp.getString("la_ref"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("la_createdt"))
                    {
                        newsData.setLa_createdt(tmp.getString("la_createdt"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("la_createby"))
                    {
                        newsData.setLa_createby(tmp.getString("la_createby"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("la_amount"))
                    {
                        newsData.setLa_amount(tmp.getString("la_amount"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("la_merchantid"))
                    {
                        newsData.setLa_merchantid(tmp.getString("la_merchantid"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("la_memberid"))
                    {
                        newsData.setLa_memberid(tmp.getString("la_memberid"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("la_points"))
                    {
                        newsData.setLa_points(tmp.getString("la_points"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("la_ref2"))
                    {
                        newsData.setLa_ref2(tmp.getString("la_ref2"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("la_revid"))
                    {
                        newsData.setLa_revid(tmp.getString("la_revid"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    listMockData.add(newsData);
                }
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

        return listMockData;
    }

    @Override
    protected void onPostExecute(ArrayList<Balancelist> result)
    {
        super.onPostExecute(result);

        progDailog.dismiss();

        BalanceActivity.mAdapter = new BalanceAdapter(context,result);

        BalanceActivity.recyclerView.setAdapter(BalanceActivity.mAdapter);



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

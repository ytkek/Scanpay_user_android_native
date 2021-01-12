package ths.ScanPay_UserV5.GetFunction;

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
import ths.ScanPay_UserV5.FindMerchantActivity;
import ths.ScanPay_UserV5.FindMerchantAdapter;
import ths.ScanPay_UserV5.FindMerchantlist;
import ths.ScanPay_UserV5.NetworkUtil;

/**
 * Created by Windows on 20/9/2016.
 */
public class GetFindMerchantListTask extends AsyncTask<String, Integer, ArrayList<FindMerchantlist>> {

    public Activity context = null;
    public static ArrayList<FindMerchantlist> listMockData;
    RecyclerView list;
    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;
    String encryptedString;

    public GetFindMerchantListTask(Activity context, RecyclerView list)
    {
        this.context = context;
        this.list=list;


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
    protected ArrayList<FindMerchantlist> doInBackground(String... params)
    {
        String params1= params[0];
        String params2 = params[1];

        try {
            encryptedString = Encrypt(params1 + "+" + params2, "@McQfTjWnZq4t7w!");

            Log.e("AES",encryptedString);

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }


        String apiUrl = ApiUrl.Domain + ApiUrl.FindMerchantApi;
        listMockData = new ArrayList<FindMerchantlist>();
        if (NetworkUtil.isNetworkAvailable(context))
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();

            hashMap.put("Token" , encryptedString);


            String response = NetworkUtil.sendPost(apiUrl,hashMap);

            try
            {
                JSONObject jObj = new JSONObject(response);
                JSONArray jArray = jObj.getJSONArray("datarecords");
                for (int i = 0; i < jArray.length(); i++)
                {
                    JSONObject tmp = jArray.getJSONObject(i);


                    FindMerchantlist newsData = new FindMerchantlist();

                    if (tmp.has("m_id"))
                    {
                        newsData.setM_id(tmp.getString("m_id"));
                    }
                    if (tmp.has("m_companyname"))
                    {
                        newsData.setM_companyname(tmp.getString("m_companyname"));

                    }
                    if (tmp.has("m_address1"))
                    {
                        newsData.setM_address1(tmp.getString("m_address1"));

                    }
                    if (tmp.has("m_address2"))
                    {
                        newsData.setM_address2(tmp.getString("m_address2"));

                    }
                    if (tmp.has("m_address3"))
                    {
                        newsData.setM_address3(tmp.getString("m_address3"));

                    }
                    if (tmp.has("m_address4"))
                    {
                        newsData.setM_address4(tmp.getString("m_address4"));

                    }
                    if (tmp.has("m_merchantid"))
                    {
                        newsData.setM_merchantid(tmp.getString("m_merchantid"));

                    }
                    if (tmp.has("m_telcc"))
                    {
                        newsData.setM_telcc(tmp.getString("m_telcc"));

                    }
                    if (tmp.has("m_telac"))
                    {
                        newsData.setM_telac(tmp.getString("m_telac"));

                    }
                    if (tmp.has("m_telno"))
                    {
                        newsData.setM_telno(tmp.getString("m_telno"));

                    }
                    if (tmp.has("m_mobileno"))
                    {
                        newsData.setM_mobileno(tmp.getString("m_mobileno"));

                    }
                    if (tmp.has("m_email"))
                    {
                        newsData.setM_email(tmp.getString("m_email"));

                    }
                    if (tmp.has("m_country"))
                    {
                        newsData.setM_country(tmp.getString("m_country"));

                    }
                    if (tmp.has("m_state"))
                    {
                        newsData.setM_state(tmp.getString("m_state"));

                    }
                    if (tmp.has("m_city"))
                    {
                        newsData.setM_city(tmp.getString("m_city"));

                    }
                    if (tmp.has("m_profilefilename"))
                    {
                        newsData.setM_profilefilename(tmp.getString("m_profilefilename"));

                    }

                    if (tmp.has("m_photofilename1"))
                    {
                        newsData.setM_photofilename1(tmp.getString("m_photofilename1"));

                    }
                    if (tmp.has("m_photofilename2"))
                    {
                        newsData.setM_photofilename2(tmp.getString("m_photofilename2"));

                    }
                    if (tmp.has("m_photofilename3"))
                    {
                        newsData.setM_photofilename3(tmp.getString("m_photofilename3"));

                    }
                    if (tmp.has("m_profileimagepath"))
                    {
                        newsData.setM_profileimagepath(tmp.getString("m_profileimagepath"));

                    }
                    if (tmp.has("m_url"))
                    {
                        newsData.setM_url(tmp.getString("m_url"));

                    }
                    if (tmp.has("m_longtitude"))
                    {
                        newsData.setM_longtitude(tmp.getString("m_longtitude"));

                    }
                    if (tmp.has("m_latitude"))
                    {
                        newsData.setM_latitude(tmp.getString("m_latitude"));

                    }
                    if (tmp.has("m_topup"))
                    {
                        if(tmp.getBoolean("m_topup")==true)
                        {
                            newsData.setM_topup("https://www.myscanpay.com/V4/mobile/image/Topupmark.jpg");
                        }


                    }
                    if (tmp.has("m_businesshour"))
                    {
                        newsData.setM_businesshour(tmp.getString("m_businesshour"));
                    }
                    if (tmp.has("m_remarks"))
                    {
                        newsData.setM_remarks(tmp.getString("m_remarks"));
                    }
                    if (tmp.has("m_enabled"))
                    {
                        newsData.setM_enabled(tmp.getString("m_enabled"));
                    }
                    if (tmp.has("m_topuplimit"))
                    {
                        newsData.setM_topuplimit(tmp.getString("m_topuplimit"));
                    }
                    if (tmp.has("m_businesstype"))
                    {
                        newsData.setM_businesstype(tmp.getString("m_businesstype"));
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
    protected void onPostExecute(final ArrayList<FindMerchantlist> result)
    {
        super.onPostExecute(result);

        progDailog.dismiss();
        int num = FindMerchantActivity.spinner.getSelectedItemPosition();
        String code;
        ArrayList<FindMerchantlist> sortresult = new ArrayList<FindMerchantlist>();

        if (num==0)
        {
            code="1";
           for (int i=0; i<result.size();i++)
           {
               if(code.equals(result.get(i).getM_businesstype()))
               {
                   sortresult.add(result.get(i));

               }

               if(i==result.size()-1)
               {

                   FindMerchantActivity.mAdapter = new FindMerchantAdapter(context, sortresult);

                   FindMerchantActivity.recyclerView.setAdapter(FindMerchantActivity.mAdapter);

               }
           }
        }
        if (num==3)
        {
            code="1002";
            for (int i=0; i<result.size();i++)
            {
                if(code.equals(result.get(i).getM_businesstype()))
                {
                    sortresult.add(result.get(i));

                }

                if(i==result.size()-1)
                {

                    FindMerchantActivity.mAdapter = new FindMerchantAdapter(context, sortresult);

                    FindMerchantActivity.recyclerView.setAdapter(FindMerchantActivity.mAdapter);

                }
            }
        }

        if (num==2)
        {
            code="3";
            for (int i=0; i<result.size();i++)
            {
                if(code.equals(result.get(i).getM_businesstype()))
                {
                    sortresult.add(result.get(i));

                }

                if(i==result.size()-1)
                {

                    FindMerchantActivity.mAdapter = new FindMerchantAdapter(context, sortresult);

                    FindMerchantActivity.recyclerView.setAdapter(FindMerchantActivity.mAdapter);

                }
            }
        }
        if (num==1)
        {
            code="2";
            for (int i=0; i<result.size();i++)
            {
                if(code.equals(result.get(i).getM_businesstype()))
                {
                    sortresult.add(result.get(i));

                }

                if(i==result.size()-1)
                {

                    FindMerchantActivity.mAdapter = new FindMerchantAdapter(context, sortresult);

                    FindMerchantActivity.recyclerView.setAdapter(FindMerchantActivity.mAdapter);

                }
            }
        }
        if (num==4)
        {
            code="1004";
            for (int i=0; i<result.size();i++)
            {
                if(code.equals(result.get(i).getM_businesstype()))
                {
                    sortresult.add(result.get(i));

                }

                if(i==result.size()-1)
                {

                    FindMerchantActivity.mAdapter = new FindMerchantAdapter(context, sortresult);

                    FindMerchantActivity.recyclerView.setAdapter(FindMerchantActivity.mAdapter);

                }
            }
        }

        if (num==5)
        {
            code="1005";
            for (int i=0; i<result.size();i++)
            {
                if(code.equals(result.get(i).getM_businesstype()))
                {
                    sortresult.add(result.get(i));

                }

                if(i==result.size()-1)
                {

                    FindMerchantActivity.mAdapter = new FindMerchantAdapter(context, sortresult);

                    FindMerchantActivity.recyclerView.setAdapter(FindMerchantActivity.mAdapter);

                }
            }
        }
        if (num==6)
        {
            code="60";
            for (int i=0; i<result.size();i++)
            {
                if(code.equals(result.get(i).getM_id()))
                {
                    sortresult.add(result.get(i));

                }

                if(i==result.size()-1)
                {

                    FindMerchantActivity.mAdapter = new FindMerchantAdapter(context, sortresult);

                    FindMerchantActivity.recyclerView.setAdapter(FindMerchantActivity.mAdapter);

                }
            }
        }

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

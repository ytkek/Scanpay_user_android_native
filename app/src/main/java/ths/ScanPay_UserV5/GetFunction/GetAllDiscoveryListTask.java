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
import ths.ScanPay_UserV5.DiscoveryAdapter;
import ths.ScanPay_UserV5.DiscoveryFragment;
import ths.ScanPay_UserV5.Discoverylist;
import ths.ScanPay_UserV5.NetworkUtil;

/**
 * Created by Windows on 20/9/2016.
 */
public class GetAllDiscoveryListTask extends AsyncTask<String, Integer, ArrayList<Discoverylist>> {

    public Activity context = null;
    public static ArrayList<Discoverylist> listMockData;
    RecyclerView list;
    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;
    String encryptedString;

    public GetAllDiscoveryListTask(Activity context, RecyclerView list)
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
    protected ArrayList<Discoverylist> doInBackground(String... params)
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
        String apiUrl = ApiUrl.Domain + ApiUrl.GetAllDiscoveryListApi;
        listMockData = new ArrayList<Discoverylist>();
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


                    Discoverylist newsData = new Discoverylist();

                    if (tmp.has("lop_id"))
                    {
                        newsData.setDiscovery_id(tmp.getString("lop_id"));
                    }

                    if(tmp.has("lop_merchantid"))
                    {
                        newsData.setDiscovery_merchantid(tmp.getString("lop_merchantid"));
                    }
                    if(tmp.has("lop_groupid"))
                    {
                        newsData.setDiscovery_groupid(tmp.getString("lop_groupid" ));
                    }
                    if(tmp.has("lop_name"))
                    {
                        newsData.setDiscovery_name(tmp.getString("lop_name"));
                    }

                    if(tmp.has("lop_description"))
                    {
                        newsData.setDiscovery_description(tmp.getString("lop_description"));
                    }
                    if(tmp.has("lop_datefrom"))
                    {
                        newsData.setDiscovery_datefrom(tmp.getString("lop_datefrom"));
                    }

                    if(tmp.has("lop_dateto"))
                    {
                        newsData.setDiscovery_dateto(tmp.getString("lop_dateto"));
                    }
                    if(tmp.has("lop_image"))
                    {
                        newsData.setDiscovery_image(tmp.getString("lop_image"));
                    }
                    if(tmp.has("lop_imagepath"))
                    {
                        newsData.setDiscovery_imagepath(tmp.getString("lop_imagepath"));
                    }
                    if(tmp.has("lop_externallink"))
                    {
                        newsData.setDiscovery_externallink(tmp.getString("lop_externallink"));
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
    protected void onPostExecute(final ArrayList<Discoverylist> result)
    {
        super.onPostExecute(result);

        progDailog.dismiss();
        int num = DiscoveryFragment.spinner.getSelectedItemPosition();
        String code;
        ArrayList<Discoverylist> sortresult = new ArrayList<Discoverylist>();
        if(DiscoveryFragment.indicator==true)
        {
            DiscoveryFragment.mAdapter = new DiscoveryAdapter(context,result);
            DiscoveryFragment.recyclerView.setAdapter(DiscoveryFragment.mAdapter);
            DiscoveryFragment.indicator=false;
        }
        else
        {
            if (num==0)
            {
                code="5";
                for (int i=0; i<result.size();i++)
                {
                    if(code.equals(result.get(i).getDiscovery_groupid()))
                    {
                        sortresult.add(result.get(i));

                    }

                    if(i==result.size()-1)
                    {

                        DiscoveryFragment.mAdapter = new DiscoveryAdapter(context,sortresult);

                        DiscoveryFragment.recyclerView.setAdapter(DiscoveryFragment.mAdapter);

                    }
                }
            }
            if (num==1)
            {
                code="1";
                for (int i=0; i<result.size();i++)
                {
                    if(code.equals(result.get(i).getDiscovery_groupid()))
                    {
                        sortresult.add(result.get(i));

                    }

                    if(i==result.size()-1)
                    {

                        DiscoveryFragment.mAdapter = new DiscoveryAdapter(context,sortresult);

                        DiscoveryFragment.recyclerView.setAdapter(DiscoveryFragment.mAdapter);

                    }
                }
            }
            if (num==2)
            {
                code="2";
                for (int i=0; i<result.size();i++)
                {
                    if(code.equals(result.get(i).getDiscovery_groupid()))
                    {
                        sortresult.add(result.get(i));

                    }

                    if(i==result.size()-1)
                    {

                        DiscoveryFragment.mAdapter = new DiscoveryAdapter(context,sortresult);

                        DiscoveryFragment.recyclerView.setAdapter(DiscoveryFragment.mAdapter);

                    }
                }
            }
            if (num==3)
            {
                code="3";
                for (int i=0; i<result.size();i++)
                {
                    if(code.equals(result.get(i).getDiscovery_groupid()))
                    {
                        sortresult.add(result.get(i));

                    }

                    if(i==result.size()-1)
                    {

                        DiscoveryFragment.mAdapter = new DiscoveryAdapter(context,sortresult);

                        DiscoveryFragment.recyclerView.setAdapter(DiscoveryFragment.mAdapter);

                    }
                }
            }
            if (num==4)
            {
                code="6";
                for (int i=0; i<result.size();i++)
                {
                    if(code.equals(result.get(i).getDiscovery_groupid()))
                    {
                        sortresult.add(result.get(i));

                    }

                    if(i==result.size()-1)
                    {

                        DiscoveryFragment.mAdapter = new DiscoveryAdapter(context,sortresult);

                        DiscoveryFragment.recyclerView.setAdapter(DiscoveryFragment.mAdapter);

                    }
                }
            }
            if (num==5)
            {
                code="7";
                for (int i=0; i<result.size();i++)
                {
                    if(code.equals(result.get(i).getDiscovery_groupid()))
                    {
                        sortresult.add(result.get(i));

                    }

                    if(i==result.size()-1)
                    {

                        DiscoveryFragment.mAdapter = new DiscoveryAdapter(context,sortresult);

                        DiscoveryFragment.recyclerView.setAdapter(DiscoveryFragment.mAdapter);

                    }
                }
            }

            if (num==6)
            {
                code="4";
                for (int i=0; i<result.size();i++)
                {
                    if(code.equals(result.get(i).getDiscovery_groupid()))
                    {
                        sortresult.add(result.get(i));

                    }

                    if(i==result.size()-1)
                    {

                        DiscoveryFragment.mAdapter = new DiscoveryAdapter(context,sortresult);

                        DiscoveryFragment.recyclerView.setAdapter(DiscoveryFragment.mAdapter);

                    }
                }
            }

        }


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

}

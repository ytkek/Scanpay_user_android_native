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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import ths.ScanPay_UserV5.ApiUrl;
import ths.ScanPay_UserV5.MessageCentreActivity;
import ths.ScanPay_UserV5.MessageCentreAdapter;
import ths.ScanPay_UserV5.MessageCentrelist;
import ths.ScanPay_UserV5.NetworkUtil;

/**
 * Created by Windows on 20/9/2016.
 */
public class GetMessageListTask extends AsyncTask<String, Integer, ArrayList<MessageCentrelist>> {

    public Activity context = null;
    public static ArrayList<MessageCentrelist> listMockData;
    RecyclerView list;
    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;
    String encryptedString;

    public GetMessageListTask(Activity context, RecyclerView list)
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
    protected ArrayList<MessageCentrelist> doInBackground(String... params)
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
        String apiUrl = ApiUrl.Domain + ApiUrl.FindMessageListApi;
        listMockData = new ArrayList<MessageCentrelist>();
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


                    MessageCentrelist newsData = new MessageCentrelist();

                    if (tmp.has("nob_id"))
                    {
                        newsData.setId(tmp.getString("nob_id"));
                    }
                    if (tmp.has("nob_title"))
                    {
                        newsData.setTitle(tmp.getString("nob_title"));
                    }
                    if (tmp.has("nob_message"))
                    {
                        newsData.setMessage(tmp.getString("nob_message"));
                    }
                    if (tmp.has("nob_publishdate"))
                    {
                        newsData.setDate(tmp.getString("nob_publishdate"));
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
    protected void onPostExecute(final ArrayList<MessageCentrelist> result)
    {
        super.onPostExecute(result);

        progDailog.dismiss();

        Collections.sort(result, new Comparator<MessageCentrelist>() {
            @Override
            public int compare(MessageCentrelist lhs, MessageCentrelist rhs) {
                return lhs.getId().compareTo(rhs.getId());
            }
        });

        MessageCentreActivity.mAdapter = new MessageCentreAdapter(context,result);
        MessageCentreActivity.recyclerView.setAdapter(MessageCentreActivity.mAdapter);



    }



    public void refreshdatabase(ArrayList<MessageCentrelist> result)
    {


        for (int i =0; i<result.size(); i ++)
        {
            MessageCentreActivity.arraylist_messagedb.addAll(MessageCentreActivity.db.getAllMessage());
            if(i+1==result.size())
            {
                MessageCentreActivity.db.deleteall();
                MessageCentreActivity.mAdapter = new MessageCentreAdapter(context,result);

                MessageCentreActivity.recyclerView.setAdapter(MessageCentreActivity.mAdapter);
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

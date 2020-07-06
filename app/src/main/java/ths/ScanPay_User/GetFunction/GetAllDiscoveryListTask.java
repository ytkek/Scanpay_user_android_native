package ths.ScanPay_User.GetFunction;

/**
 * Created by Windows on 1/10/2016.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import ths.ScanPay_User.ApiUrl;
import ths.ScanPay_User.DiscoveryAdapter;
import ths.ScanPay_User.DiscoveryFragment;
import ths.ScanPay_User.Discoverylist;
import ths.ScanPay_User.FindMerchantActivity;
import ths.ScanPay_User.FindMerchantAdapter;
import ths.ScanPay_User.FindMerchantlist;
import ths.ScanPay_User.MainActivity;
import ths.ScanPay_User.NetworkUtil;

/**
 * Created by Windows on 20/9/2016.
 */
public class GetAllDiscoveryListTask extends AsyncTask<Void, Integer, ArrayList<Discoverylist>> {

    public Activity context = null;
    public static ArrayList<Discoverylist> listMockData;
    RecyclerView list;

    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

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
    protected ArrayList<Discoverylist> doInBackground(Void... params)
    {
        String apiUrl = ApiUrl.Domain + ApiUrl.GetAllDiscoveryListApi;
        listMockData = new ArrayList<Discoverylist>();
        if (NetworkUtil.isNetworkAvailable(context))
        {
            String response = NetworkUtil.SentGet(apiUrl,false);
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
                       // Log.d("wtf",newsData.getM_id());
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






      // DiscoveryFragment.mAdapter = new DiscoveryAdapter(context,result);

       // DiscoveryFragment.recyclerView.setAdapter(DiscoveryFragment.mAdapter);
        // FindMerchantActivity.mAdapter = new FindMerchantAdapter(context, result);

       // FindMerchantActivity.recyclerView.setAdapter(FindMerchantActivity.mAdapter);

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

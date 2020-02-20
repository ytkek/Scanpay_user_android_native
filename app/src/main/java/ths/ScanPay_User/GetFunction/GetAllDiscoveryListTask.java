package ths.ScanPay_User.GetFunction;

/**
 * Created by Windows on 1/10/2016.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

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
import ths.ScanPay_User.NetworkUtil;

/**
 * Created by Windows on 20/9/2016.
 */
public class GetAllDiscoveryListTask extends AsyncTask<Void, Integer, ArrayList<Discoverylist>> {

    public Context context = null;
    public static ArrayList<Discoverylist> listMockData;
    RecyclerView list;

    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

    public GetAllDiscoveryListTask(Context context, RecyclerView list)
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

        return listMockData;
    }

    @Override
    protected void onPostExecute(final ArrayList<Discoverylist> result)
    {
        super.onPostExecute(result);

        progDailog.dismiss();

       DiscoveryFragment.mAdapter = new DiscoveryAdapter(context,result);

        DiscoveryFragment.recyclerView.setAdapter(DiscoveryFragment.mAdapter);
        // FindMerchantActivity.mAdapter = new FindMerchantAdapter(context, result);

       // FindMerchantActivity.recyclerView.setAdapter(FindMerchantActivity.mAdapter);

    }





}

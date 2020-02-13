package ths.ScanPay_User.GetFunction;

/**
 * Created by Windows on 1/10/2016.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ths.ScanPay_User.ApiUrl;
import ths.ScanPay_User.FindMerchantActivity;
import ths.ScanPay_User.FindMerchantAdapter;
import ths.ScanPay_User.FindMerchantlist;
import ths.ScanPay_User.MessageCentreActivity;
import ths.ScanPay_User.MessageCentreAdapter;
import ths.ScanPay_User.MessageCentrelist;
import ths.ScanPay_User.MessageNotification.MessageNotification;
import ths.ScanPay_User.NetworkUtil;

/**
 * Created by Windows on 20/9/2016.
 */
public class GetMessageListTask extends AsyncTask<Void, Integer, ArrayList<MessageCentrelist>> {

    public Context context = null;
    public static ArrayList<MessageCentrelist> listMockData;
    RecyclerView list;

    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

    public GetMessageListTask(Context context, RecyclerView list)
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
    protected ArrayList<MessageCentrelist> doInBackground(Void... params)
    {
        String apiUrl = ApiUrl.Domain + ApiUrl.FindMessageListApi;
        listMockData = new ArrayList<MessageCentrelist>();
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


                    MessageCentrelist newsData = new MessageCentrelist();

                    if (tmp.has("nob_id"))
                    {
                        newsData.setId(tmp.getString("nob_id"));
                       // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("nob_title"))
                    {
                        newsData.setTitle(tmp.getString("nob_title"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("nob_message"))
                    {
                        newsData.setMessage(tmp.getString("nob_message"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("nob_publishdate"))
                    {
                        newsData.setDate(tmp.getString("nob_publishdate"));
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

        return listMockData;
    }

    @Override
    protected void onPostExecute(final ArrayList<MessageCentrelist> result)
    {
        super.onPostExecute(result);

        progDailog.dismiss();


        for (int j=0; j<result.size();j++)
        {
            MessageCentreActivity.db.insertIndicator("false");
            //Log.d("wtf",""+j);
        }



        MessageCentreActivity.mAdapter = new MessageCentreAdapter(context,result);

        MessageCentreActivity.recyclerView.setAdapter(MessageCentreActivity.mAdapter);



    }





}

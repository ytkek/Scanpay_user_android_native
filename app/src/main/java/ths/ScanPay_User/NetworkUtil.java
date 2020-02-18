package ths.ScanPay_User;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Windows on 13/2/2016.
 */
public class NetworkUtil {
    private static final char PARAMETER_DELIMITER = '&';
    private static final char PARAMETER_EQUALS_CHAR = '=';
    private static final String TAG = "NetworkUtil";

    public static String Cookie = null;

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = false;
        if (activeNetwork != null) {
            isConnected = activeNetwork.isConnectedOrConnecting();
        }
        return isConnected;
    }

    public static String createQueryStringForParameters(Map<String, String> parameters) {
        StringBuilder parametersAsQueryString = new StringBuilder();
        if (parameters != null) {
            boolean firstParameter = true;

            for (String parameterName : parameters.keySet()) {
                if (!firstParameter) {
                    parametersAsQueryString.append(PARAMETER_DELIMITER);
                }

                try {
                    parametersAsQueryString.append(parameterName)
                            .append(PARAMETER_EQUALS_CHAR)
                            .append(URLEncoder.encode(
                                    parameters.get(parameterName), StandardCharsets.UTF_8.toString()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                firstParameter = false;
            }
        }
        System.out.println(parametersAsQueryString.toString());
        return parametersAsQueryString.toString();

    }

    public static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
    public static String  sendPost(String requestURL,
                                   HashMap<String, String> postDataParams) {

        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            Log.d("sendpost"," "+getPostDataString(postDataParams));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            else {
                response="";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public static String SentGet(String url,boolean resetCookie)
    {
        String response = null;
        try {
            URL url2 = null;
            HttpURLConnection connection;
            OutputStreamWriter request;

            String parameters = "param1=value1&param2=value2";
            url2 = new URL(url);
            //create the connection
            connection = (HttpURLConnection) url2.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            //set the request method to GET
            connection.setRequestMethod("GET");
            //get the output stream from the connection you created
            request = new OutputStreamWriter(connection.getOutputStream());
            //write your data to the ouputstream

            //request.write(parameters);
            request.flush();
            request.close();
            String line = "";
            //create your inputsream
            InputStreamReader isr = new InputStreamReader(
                    connection.getInputStream());
            //read in the data from input stream, this can be done a variety of ways
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            response = sb.toString();
            //do what you want with the data now

            //always remember to close your input and output streams
            isr.close();
            reader.close();
        } catch (IOException e) {
            Log.e("HTTP GET:", e.toString());
        }
        return response;
    }

    public static String getStringFromURL(String url) {
        return getStringFromURL(url, false);
    }

    public static String getStringFromURL(String url, boolean resetCookie) {
        String result = "";

        URL urlObj = null;
        HttpURLConnection con = null;
        BufferedReader reader = null;
        try {
//            HttpCookie cookie = new HttpCookie("sessionid", sessionId);
//            CookieManager cookieManager = new CookieManager(CookiePolicy.ACCEPT_ORIGINAL_SERVER);

            urlObj = new URL(url);
            Log.i("urlObj toString() : ", urlObj.toString());
            con = (HttpURLConnection) urlObj.openConnection();
            if (resetCookie) {
                Cookie = con.getHeaderField("Set-Cookie");
            } else {
                if (Cookie != null && !Cookie.isEmpty()) {
                    con.setRequestProperty("Cookie", Cookie);
                }
                Log.i(TAG + Thread.currentThread().getStackTrace().toString(), Cookie + "");
            }

            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder strBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                strBuilder.append(line + "\n");
            }
            result = strBuilder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                con.disconnect();
            }
        }
        return result;
    }

    public static String getStringFromURL(String url, boolean resetCookie, String postParameters, String method) {
        String result = "";

        URL urlObj = null;
        HttpURLConnection con = null;
        BufferedReader reader = null;
        try {
//            HttpCookie cookie = new HttpCookie("sessionid", sessionId);
//            CookieManager cookieManager = new CookieManager(CookiePolicy.ACCEPT_ORIGINAL_SERVER);

            urlObj = new URL(url);
            Log.i("urlObj toString() : ", urlObj.toString());
            con = (HttpURLConnection) urlObj.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setFixedLengthStreamingMode(
                    postParameters.getBytes().length);
            con.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            if (resetCookie) {
                Cookie = con.getHeaderField("Set-Cookie");
            } else {
                if (Cookie != null && !Cookie.isEmpty()) {
                    con.setRequestProperty("Cookie", Cookie);
                }
                Log.i(TAG + Thread.currentThread().getStackTrace().toString(), Cookie + "");
            }

            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder strBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                strBuilder.append(line + "\n");
            }
            result = strBuilder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                con.disconnect();
            }
        }
        return result;
    }

    public static void loggingHeaderFields(Map<String, List<String>> headerFields) {
        Set<String> headerFieldsSet = headerFields.keySet();
        Iterator<String> hearerFieldsIter = headerFieldsSet.iterator();

        while (hearerFieldsIter.hasNext()) {
            String headerFieldKey = hearerFieldsIter.next();
            Log.i(TAG + Thread.currentThread().getStackTrace().toString(), headerFieldKey + "");
            if ("Set-Cookie".equalsIgnoreCase(headerFieldKey)) {
                List<String> headerFieldValue = headerFields.get(headerFieldKey);
                for (String headerValue : headerFieldValue) {
                    Log.i(TAG + Thread.currentThread().getStackTrace().toString(), headerValue + "");
                }
            }
        }
    }

    public static Bitmap getBitmapFromURL(String url) {
        Bitmap bitmap = null;

        URL urlObj = null;
        HttpURLConnection con = null;
        InputStream inputStream = null;
        try {
            urlObj = new URL(url);
            con = (HttpURLConnection) urlObj.openConnection();
            inputStream = con.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                con.disconnect();
            }
        }
        return bitmap;
    }

    public static String uploadBitmapFromURL(String url, Bitmap bitmap) {
        URL urlObj = null;

        String response = "";

        String attachmentName = "photo";
        String attachmentFileName = "photo.png";
        String crlf = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";

        HttpURLConnection conn = null;
        InputStream responseStream = null;
        try {
            urlObj = new URL(url);

            conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            if (Cookie != null && !Cookie.isEmpty()) {
                conn.setRequestProperty("Cookie", Cookie);
                Log.i(TAG + Thread.currentThread().getStackTrace().toString(), Cookie + "");
            }

            DataOutputStream request = new DataOutputStream(conn.getOutputStream());
            request.writeBytes(twoHyphens + boundary + crlf);
            request.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName + "\";filename=\"" + attachmentFileName + "\"" + crlf);
            request.writeBytes(crlf);

            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteStream);

            byte[] byteArray = byteStream.toByteArray();
            byteStream.close();
            byteStream = null;

            request.write(byteArray);

            request.writeBytes(crlf);
            request.writeBytes(twoHyphens + boundary + twoHyphens + crlf);

            request.flush();
            request.close();

            responseStream = new BufferedInputStream(conn.getInputStream());

            BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));
            String line = "";
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = responseStreamReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            responseStreamReader.close();

            response = stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (responseStream != null) {
                try {
                    responseStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return response;
    }
}
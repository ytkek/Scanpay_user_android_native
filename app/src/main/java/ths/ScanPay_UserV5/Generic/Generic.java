package ths.ScanPay_UserV5.Generic;

import android.content.Context;
import android.content.SharedPreferences;

public class Generic
{

    public static final String otp="otp";
    public static final String mypreference = "OTP_database";
    public static SharedPreferences sharedpreferences;


    public static void SaveOtp(String otp,Context context)
    {
        sharedpreferences = context.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Generic.otp,otp);
        editor.commit();
    }

    public static String getOtp(Context context)
    {
        sharedpreferences = context.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        return sharedpreferences.getString(Generic.otp, "");
    }
}

package ths.ScanPay_UserV5.Generic;

import android.content.Context;
import android.content.SharedPreferences;

public class GenericAutologin
{

    public static final String login="login";
    public static final String password="password";
    public static final String mypreference = "Login_database";
    public static SharedPreferences sharedpreferences;


    public static void SaveLoginPassword(String login,String password,Context context)
    {
        sharedpreferences = context.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(GenericAutologin.login,login);
        editor.putString(GenericAutologin.password,password);
        editor.commit();
    }

    public static String getLogin(Context context)
    {
        sharedpreferences = context.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        return sharedpreferences.getString(GenericAutologin.login, "");
    }
    public static String getPassword(Context context)
    {
        sharedpreferences = context.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        return sharedpreferences.getString(GenericAutologin.password, "");
    }
    public static void clearall(Context context)
    {
        sharedpreferences = context.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }

}

package ths.ScanPay_UserV5.SqlLiteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ths.ScanPay_UserV5.MessageNotification.MessageNotification;


public class MessageDatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "message_db";


    public MessageDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(MessageNotification.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + MessageNotification.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertIndicator(String indicator,String nob_id) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(MessageNotification.COLUMN_INDICATOR, indicator);

        values.put(MessageNotification.COLUMN_NOBID, nob_id);
        // insert row
        long id = db.insert(MessageNotification.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }



    public List<MessageNotification> getAllMessage() {
        List<MessageNotification> messagenotification = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + MessageNotification.TABLE_NAME + " ORDER BY " +
                MessageNotification.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MessageNotification note = new MessageNotification();
                note.setId(cursor.getInt(cursor.getColumnIndex(MessageNotification.COLUMN_ID)));
                note.setNob_id(cursor.getString(cursor.getColumnIndex(MessageNotification.COLUMN_NOBID)));
                note.setIndicator(cursor.getString(cursor.getColumnIndex(MessageNotification.COLUMN_INDICATOR)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(MessageNotification.COLUMN_TIMESTAMP)));

                messagenotification.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return messagenotification;
    }

    public int updateNote(String  note, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MessageNotification.COLUMN_INDICATOR, note);

        // updating row
        return db.update(MessageNotification.TABLE_NAME, values, MessageNotification.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteall()
    {
        SQLiteDatabase db = this.getWritableDatabase(); //get database
        db.execSQL("DELETE FROM messagenotification"); //delete all rows in a table
        db.close();
    }
    public void deleteNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MessageNotification.TABLE_NAME, MessageNotification.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }


}


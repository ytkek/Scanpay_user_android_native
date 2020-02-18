package ths.ScanPay_User.MessageNotification;

public class MessageNotification{
    public static final String TABLE_NAME = "messagenotification";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOBID="nob_id";
    public static final String COLUMN_MESSAGE = "message";
    public static final String COLUMN_INDICATOR = "indicator";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id;
    private String message;
    private String indicator;
    private String timestamp;
    private String nob_id;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NOBID + " TEXT,"
                    + COLUMN_MESSAGE + " TEXT,"
                    + COLUMN_INDICATOR + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public MessageNotification() {
    }

    public MessageNotification(int id, String message,String indicator, String timestamp) {
        this.id = id;
        this.message = message;
        this.indicator = indicator;
        this.timestamp = timestamp;
    }

    public String getNob_id()
    {
        return nob_id;
    }
    public void setNob_id(String nob_id)
    {
        this.nob_id=nob_id;
    }
    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
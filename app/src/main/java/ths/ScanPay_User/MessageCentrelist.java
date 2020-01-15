package ths.ScanPay_User;

public class MessageCentrelist
{
    private String title;
    private String date;
    private String message;

    public MessageCentrelist(String title,String message,String date)
    {
        this.title=title;
        this.message=message;
        this.date=date;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title=title;
    }
    public String getDate()
    {
        return date;
    }
    public void setDate(String date)
    {
        this.date=date;
    }
    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message=message;
    }
}

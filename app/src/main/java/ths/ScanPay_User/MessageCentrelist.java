package ths.ScanPay_User;

public class MessageCentrelist
{
    private String id;
    private String title;
    private String date;
    private String message;

    public MessageCentrelist()
    {

    }
    public MessageCentrelist(String title,String message,String date)
    {
        this.title=title;
        this.message=message;
        this.date=date;
    }
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id=id;
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

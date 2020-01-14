package ths.ScanPay_User;

public class Discoverylist
{
    private String discovery_img;
    private String discovery_title;
    private String discovery_content;

    public Discoverylist(String discovery_img,String discovery_title,String discovery_content)
    {
        this.discovery_img=discovery_img;
        this.discovery_title=discovery_title;
        this.discovery_content=discovery_content;
    }

    public String getDiscovery_img()
    {
        return discovery_img;
    }
    public void setDiscovery_img(String discovery_img)
    {
        this.discovery_img=discovery_img;
    }
    public String getDiscovery_title()
    {
        return discovery_title;
    }
    public void setDiscovery_title(String discovery_title)
    {
        this.discovery_title=discovery_title;
    }
    public String getDiscovery_content()
    {
        return discovery_content;
    }
    public void setDiscovery_content(String discovery_content)
    {
        this.discovery_content=discovery_content;
    }
}

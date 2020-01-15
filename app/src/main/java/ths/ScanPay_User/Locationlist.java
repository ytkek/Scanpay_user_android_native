package ths.ScanPay_User;

public class Locationlist
{
    private String location_img;
    private String location_companyname;
    private String location_address;
    private String topup_indicator;

    public Locationlist(String location_img,String location_companyname, String location_address, String topup_indicator)
    {
        this.location_img = location_img;
        this.location_companyname = location_companyname;
        this.location_address =location_address;
        this.topup_indicator = topup_indicator;
    }

    public String getLocation_img()
    {
        return location_img;
    }
    public void setLocation_img(String location_img)
    {
        this.location_img=location_img;
    }

    public String getLocation_companyname()
    {
        return location_companyname;
    }
    public void setLocation_companyname(String location_companyname)
    {
        this.location_companyname=location_companyname;
    }

    public String getLocation_address()
    {
        return location_address;
    }

    public void setLocation_address(String location_address)
    {
        this.location_address=location_address;
    }

    public String getTopup_indicator()
    {
        return topup_indicator;
    }

    public void setTopup_indicator(String topup_indicator)
    {
        this.topup_indicator=topup_indicator;
    }

}

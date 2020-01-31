package ths.ScanPay_User;

public class FindMerchantlist
{
    String imageshop;
    String shopname;
    String shopaddress;

    public FindMerchantlist(String imageshop,String shopname,String shopaddress)
    {
        this.imageshop=imageshop;
        this.shopname=shopname;
        this.shopaddress=shopaddress;
    }
    public String getImageshop()
    {
        return imageshop;
    }
    public void setImageshop(String imageshop)
    {
        this.imageshop=imageshop;
    }

    public String getShopname()
    {
        return shopname;
    }
    public void setShopname(String shopname)
    {
        this.shopname=shopname;
    }
    public String getShopaddress()
    {
        return shopaddress;
    }
    public void setShopaddress(String shopaddress)
    {
        this.shopaddress=shopaddress;
    }

}

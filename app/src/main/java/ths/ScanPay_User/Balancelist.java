package ths.ScanPay_User;

public class Balancelist {
    public String balance_date;
    public String balance_type;
    public String balance_reference;
    public String balance_amount;

    public Balancelist(String balance_date, String balance_type, String balance_reference, String balance_amount) {
        this.balance_date = balance_date;
        this.balance_type = balance_type;
        this.balance_reference = balance_reference;
        this.balance_amount = balance_amount;
    }


    public String getBalance_date()
    {
        return balance_date;
    }

    public void setBalance_date(String balance_date)
    {
        this.balance_date=balance_date;
    }
    public String getBalance_type()
    {
        return balance_type;
    }
    public void setBalance_type(String balance_type)
    {
        this.balance_type=balance_type;
    }

    public String getBalance_reference()
    {
        return balance_reference;
    }
    public void setBalance_reference(String balance_reference)
    {
        this.balance_reference=balance_reference;
    }
    public String getBalance_amount()
    {
        return balance_amount;
    }
    public void setBalance_amount(String balance_amount)
    {
        this.balance_amount=balance_amount;
    }
}


public class Customer {
    private String name;
    private String passwords;
    private int accountnumber;
    private int balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public int getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(int accountnumber) {
        this.accountnumber = accountnumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Customer() {
    }

    public Customer(String name, String passwords) {
        this.name = name;
        this.passwords = passwords;
    }

    public Customer(String name, String passwords, int accountnumber, int balance) {
        this.name = name;
        this.passwords = passwords;
        this.accountnumber = accountnumber;
        this.balance = balance;

    }

}

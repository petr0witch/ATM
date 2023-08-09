package user;

public class UserAccount {
    private String name;
    private long currentCardNumber;
    private short currectPinCode;
    private int balance;
    public UserAccount() {    }
    public UserAccount(long currentCardNumber, String name, short pinCode, int balance) {
    }

    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public UserAccount( long currentCardNumber, String name, int balance, short pinCode) {
        this.balance = balance;
        this.currectPinCode = pinCode;
        this.currentCardNumber = currentCardNumber;
        this.name = name;
    }
    public short getPinCode() {
        return currectPinCode;
    }
    public long getCurrentCardNumber() {
        return currentCardNumber;
    }
    public String getName() {
        return name;
    }
}



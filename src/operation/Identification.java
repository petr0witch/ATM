package operation;

import user.UserAccount;

import java.util.Scanner;

public class Identification extends UserAccount{
    UserAccount userAccount = new UserAccount(); //(double) 0, (short) 1234, 1111_2222_3333_4444L);
    private int balance = getBalance();
    private short currectPinCode = getPinCode();
    private long currectCardNumber = getCurrentCardNumber();
    private long cardNumber;
    private short pinCode;

    public Identification(long currentCardNumber, String name, short pinCode, int balance ) {
        super(currentCardNumber, name, pinCode, balance);
    }
    // identification
//    public boolean identification() {
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("Enter you card's number in format xxxx-xxxx-xxxx-xxxx: ");
//        cardNumber = sc.nextLong();
//
//        if(cardNumber != currectCardNumber) {
//            throw new RuntimeException("Uncorrected number of card!");
//        }
//
//        System.out.println("Enter you PIN-Code: ");
//        pinCode = sc.nextShort();
//        if(pinCode != currectPinCode) {
//            throw new RuntimeException("Uncorrected PIN-Code of card!");
//        }
//        return true;
//    }

    @Override
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance =  balance;
    }
    public long getCurrectCardNumber() {
        return currectCardNumber;
    }
    public void setCurrectCardNumber(long currectCardNumber) {
        this.currectCardNumber = currectCardNumber;
    }

}



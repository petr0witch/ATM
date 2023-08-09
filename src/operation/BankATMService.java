package operation;

import db.ReadFile;
import user.UserAccount;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class BankATMService extends UserAccount {
    private HashMap<Long, UserAccount> userAccountHashMap;
    private List<UserAccount> userAccountsList;
    public BankATMService(HashMap<Long, UserAccount> userAccountHashMap, List<UserAccount> userAccountsList) {
        this.userAccountHashMap = userAccountHashMap;
        this.userAccountsList = userAccountsList;
    }

    private UserAccount userAccount;
    private boolean isIdentified = false;
    private int balance = getBalance();
    private short currectPinCode = getPinCode();
    private long currectCardNumber = getCurrentCardNumber();
    private double balanceOutLimit = 1_000_000;
    private long cardNumber;
    private short pinCode;
    private String filePath = "C:\\Users\\Kirill\\Desktop\\Senla\\ATM\\src\\db\\Clients.txt";


    public void saveData() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            for (UserAccount item : userAccountsList) {
                writer.write(item.getCurrentCardNumber() + " ");
                writer.write(item.getName() + " ");
                writer.write(item.getPinCode() + " ");
                writer.write(item.getBalance() + "\n");
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving user accounts: " + e.getMessage());
        }
        start();
    }

    public boolean identification() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter you card's number in format xxxx-xxxx-xxxx-xxxx: ");
        long cardNumber = sc.nextLong();

        UserAccount userAccount = userAccountHashMap.get(cardNumber);

        if (userAccount == null) { //cardNumber != currectCardNumber
            throw new RuntimeException("Uncorrected number of card!");
        }

        System.out.println("Enter you PIN-Code: ");
        short pinCode = sc.nextShort();

        if (pinCode != userAccount.getPinCode()) { //pinCode != currectPinCode
            throw new RuntimeException("Uncorrected PIN-Code of card!");
        }
        isIdentified = true; // Установка флага успешной идентификации
        this.userAccount = userAccount;
        return true;
    }
    public void checkBalance(){
        System.out.printf("Hello, %s! \nYour number card: %s \nYour balance: %s \n",
        userAccount.getName(),
        userAccount.getCurrentCardNumber(),
        userAccount.getBalance());
    }
    public boolean isValidAccount() {
        return isIdentified; // Возвращаем сохраненное состояние идентификации
    }

    public void moneyIn(UserAccount userAccount) {
        if (isValidAccount()) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Hello, add money: ");
            int moneyIn = sc.nextInt();

            if (moneyIn > balanceOutLimit) {
                throw new RuntimeException("Your adding more than 1.000.000, try add less money");
            }
            userAccount.setBalance((userAccount.getBalance() + moneyIn)); //устанавливаем новый баланс (тек + пополнение)

            System.out.println("Your balance is: " + userAccount.getBalance() + "\n");
        }
    }
    public void moneyOut(UserAccount userAccount){
        if (isValidAccount()) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Hello, how much money you'd like to get back: ");
            int moneyOut = sc.nextInt();

            if (moneyOut > userAccount.getBalance()) {
                throw new RuntimeException("Not enough money");
            }
            userAccount.setBalance(userAccount.getBalance() - moneyOut); //устанавливаем новый баланс (тек - снятие)
            System.out.println("Your balance is: " + userAccount.getBalance() + "\n");
        }
    }

    public void start() {
        identification();
        while(true) {
            if (isValidAccount()) {
                checkBalance();
                short command = prompt("Choose operation: \n1. Input money\n2. Output money\n3. Save & Exit\n");
                switch (command) {
                    case 1:
                        moneyIn(userAccount);
                        break;
                    case 2:
                        moneyOut(userAccount);
                        break;
                    case 3:
                        saveData();
                        return;
                    default:
                        start();
                }
            }
        }

    }
    private short prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextShort();
    }
}



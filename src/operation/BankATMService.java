package operation;

import operation.functions.MoneyIn;
import operation.functions.MoneyOut;
import operation.functions.SaveData;
import user.UserAccount;

import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BankATMService extends UserAccount {
    private HashMap<Long, UserAccount> userAccountHashMap;
    private List<UserAccount> userAccountsList;
    public BankATMService(HashMap<Long, UserAccount> userAccountHashMap, List<UserAccount> userAccountsList) {
        this.userAccountHashMap = userAccountHashMap;
        this.userAccountsList = userAccountsList;
    }

    MoneyOut mOut = new MoneyOut();
    MoneyIn mIn = new MoneyIn();
    SaveData svData = new SaveData();
    private UserAccount userAccount;
    private boolean isIdentified = false;

    private String filePath = "C:\\Users\\Kirill\\Desktop\\Senla\\ATM\\src\\db\\Clients.txt";

//    public void saveData() {
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
//
//            for (UserAccount item : userAccountsList) {
//                writer.write(item.getCurrentCardNumber() + " ");
//                writer.write(item.getName() + " ");
//                writer.write(item.getPinCode() + " ");
//                writer.write(item.getBalance() + "\n");
//            }
//            writer.close();
//
//        } catch (IOException e) {
//            System.out.println("Error saving user accounts: " + e.getMessage());
//        }
//        start();
//    }

    public boolean identification() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter your card's number in format xxxx-xxxx-xxxx-xxxx: ");
                long cardNumber = sc.nextLong();

                UserAccount userAccount = userAccountHashMap.get(cardNumber);

                if (userAccount == null) {
                    throw new RuntimeException("Incorrect card number!");
                }

                System.out.println("Enter your PIN-Code: ");
                short pinCode = sc.nextShort();

                if (pinCode != userAccount.getPinCode()) {
                    throw new RuntimeException("Incorrect PIN-Code!");
                }

                isIdentified = true; // Установка флага успешной идентификации
                this.userAccount = userAccount;
                return true; // успешная идентификация
            } catch (InputMismatchException e) {
                System.out.println("Invalid input format. Please enter valid numbers.");
                sc.nextLine(); // Очистка буфера ввода
            } catch (RuntimeException e) {
                if (e.getMessage().equals("Incorrect card number!")) {
                    System.out.println("Incorrect card number!");
                } else if (e.getMessage().equals("Incorrect PIN-Code!")) {
                    System.out.println("Incorrect PIN-Code!");
                }
            }
        }
    }
    public boolean isValidAccount() {
        return isIdentified; // Возвращаем сохраненное состояние идентификации
    }

//moneyIn(UserAccount userAccount)
//moneyOut(UserAccount userAccount)

    public void start() {
        identification();
        while(true) {
            if (isValidAccount()) {
                checkBalance();
                short command = prompt("Choose operation: \n1. Input money\n2. Output money\n3. Save & Exit\n");
                switch (command) {
                    case 1:
                        mIn.moneyIn(userAccount);
                        break;
                    case 2:
                        mOut.moneyOut(userAccount);
                        break;
                    case 3:
                        svData.saveData(filePath, userAccountsList.toArray(new UserAccount[0]));
                        start();
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
        try {
            return in.nextShort();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            in.nextLine(); // Очистка буфера ввода
        }
        return 0;
    }
    public void checkBalance(){
        System.out.printf("Hello, %s! \nYour number card: %s \nYour balance: %s \n",
                userAccount.getName(),
                userAccount.getCurrentCardNumber(),
                userAccount.getBalance());
    }
}



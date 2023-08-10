package db;

import user.UserAccount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadFile  { //extends UserFile
    String filePath = "C:\\Users\\Kirill\\Desktop\\Senla\\ATM\\src\\db\\Clients.txt";
    List<UserAccount> userAccountsList = new ArrayList<>();
    HashMap<Long, UserAccount> userAccountsMap = new HashMap<>();

    public HashMap<Long, UserAccount> readFile() {
         try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) { //считываем построчно в строку
                String[] userData = line.split(" "); // делим строку по пробелу
                long cardNumber = Long.parseLong(userData[0]);
                String name = userData[1];
                short pinCode = Short.parseShort(userData[2]);
                int balance = Integer.parseInt(userData[3]);

                UserAccount userAccount = new UserAccount(cardNumber, name, balance, pinCode);

                userAccountsList.add(userAccount); // Добавление в список
                userAccountsMap.put(cardNumber, userAccount); // Добавление в Map
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

//        for (UserAccount userAccount : userAccountsList) {
//            System.out.println(
//                    userAccount.getCurrentCardNumber() + " " +
//                            userAccount.getName() + " " +
//                            userAccount.getPinCode() + " " +
//                            userAccount.getBalance()
//            );
//        }
//        System.out.println(userAccountsMap);
        return userAccountsMap;
    }

    public List<UserAccount> getUserAccountsList() {
        return userAccountsList;
    }
    public void writeFile() {

    }
}
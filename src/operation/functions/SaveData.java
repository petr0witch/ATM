package operation.functions;

import user.UserAccount;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveData {
    public void saveData(String filePath, UserAccount[] userAccountsList) {
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
        //start();
    }
}

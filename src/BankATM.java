import db.ReadFile;
import operation.BankATMService;
import user.UserAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankATM {
    public static void main(String[] args) {

        ReadFile reader = new ReadFile();
        HashMap<Long, UserAccount> userAccountsMap = reader.readFile();
        List<UserAccount> userAccountsList = reader.getUserAccountsList(); // Получаем список из Reader

        BankATMService bankATMService = new BankATMService(userAccountsMap, userAccountsList); // Передаем оба параметра

        bankATMService.start();
    }
}



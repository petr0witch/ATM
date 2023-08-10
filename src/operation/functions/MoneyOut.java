package operation.functions;

import user.UserAccount;

import java.util.Scanner;

public class MoneyOut {
    public void moneyOut(UserAccount userAccount){

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

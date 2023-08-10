package operation.functions;

import user.UserAccount;

import java.util.Scanner;

public class MoneyIn {
    public void moneyIn(UserAccount userAccount) {
        double balanceOutLimit = 1_000_000;

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

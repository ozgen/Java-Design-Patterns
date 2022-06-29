package command;

import command.BankAccountCommand.Action;

import java.util.List;

class BankAccount {
    private int balance;
    private int overdraftLimit = -500;

    public void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited " + amount + ", balance is now " + balance);
    }

    public void withdraw(int amount) {
        if (balance - amount >= overdraftLimit) {
            balance -= amount;
            System.out.println("Withdrew " + amount + ", balance is now " + balance);
        }
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}

interface Command {

    void call();

}

class BankAccountCommand implements Command {

    private BankAccount account;

    public enum Action {
        DEPOSIT, WITHDRAW
    }

    private Action action;
    private int amount;

    public BankAccountCommand(BankAccount account, Action action, int amount) {
        this.account = account;
        this.action = action;
        this.amount = amount;
    }


    @Override
    public void call() {
        switch (action) {
            case DEPOSIT:
                account.deposit(amount);
                break;
            case WITHDRAW:
                account.withdraw(amount);
                break;
        }
    }
}

public class CommandDemo {

    public static void main(String[] args) {
        BankAccount ba = new BankAccount();
        System.out.println(ba);

        List<BankAccountCommand> bankAccountCommands = List.of(new BankAccountCommand(ba, Action.DEPOSIT, 100),
                new BankAccountCommand(ba, Action.WITHDRAW, 1000));
        for (BankAccountCommand c : bankAccountCommands) {
            c.call();
            System.out.println(ba);
        }
    }
}

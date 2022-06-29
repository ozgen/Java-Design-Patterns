package command;

import com.google.common.collect.Lists;
import command.BankAccountCommand.Action;

import java.util.List;

class BankAccount {
    private int balance;
    private int overdraftLimit = -500;

    public void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited " + amount + ", balance is now " + balance);
    }

    public boolean withdraw(int amount) {
        if (balance - amount >= overdraftLimit) {
            balance -= amount;
            System.out.println("Withdrew " + amount + ", balance is now " + balance);
            return true;
        }
        return false;
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

    void undo();

}

class BankAccountCommand implements Command {

    private BankAccount account;

    private boolean succeeded;

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
                succeeded = true;
                account.deposit(amount);
                break;
            case WITHDRAW:
                succeeded = account.withdraw(amount);
                break;
        }
    }

    @Override
    public void undo() {
        if (!succeeded) {
            return;
        }
        switch (action) {
            case DEPOSIT:
                account.withdraw(amount);
                break;
            case WITHDRAW:
                account.deposit(amount);
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
        for (Command c : bankAccountCommands) {
            c.call();
            System.out.println(ba);
        }


        for (Command c : Lists.reverse(bankAccountCommands)) {
            c.undo();
            System.out.println(ba);
        }
    }
}

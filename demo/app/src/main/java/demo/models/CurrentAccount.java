package demo.models;

public class CurrentAccount extends Account {
        public CurrentAccount(int accountNumber) {
                super(accountNumber);
                description = "Corriente";
        }

        public void deposit(int amount) {
                balance += amount;
        }

        public void withdraw(int amount) {
                balance -= amount;
        }

        public void moneyTransfer(int amount, int destNumber) {
                balance -= amount;
        }
}

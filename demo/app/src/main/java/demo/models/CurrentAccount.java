package demo.models;

public class CurrentAccount extends Account {
        public CurrentAccount(int accountNumber) {
                super(accountNumber);
        }

        public void deposit(int amount) {
                this.balance += amount;
        }

        public void withdraw(int amount) {
                this.balance -= amount;
        }

        public void moneyTransfer(int amount, int destNumber) {
                this.balance -= amount;
        }
}

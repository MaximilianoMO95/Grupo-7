package demo.models;

public class SavingAccount extends Account {
        public SavingAccount(int accountNumber) {
                super(accountNumber);
                description = "Ahorro";
        }

        public void deposit(int amount) {
                this.balance += amount;
        }

        public void withdraw(int amount) {
                this.balance -= amount;
        }
}


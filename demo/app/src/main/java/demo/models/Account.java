package demo.models;

public class Account {
        public int id;
        public int accountNumber;
        protected int balance;
        public String description;

        public Account(int accountNumber) {
                this.accountNumber = accountNumber;
                this.balance = 0;
        }

        public Account(int id, int accountNumber, int balance, String description) {
                this.id = id;
                this.description = description;
                this.accountNumber = accountNumber;
                this.balance = balance;
        }

        public int getAccountNumber() {
                return accountNumber;
        }

        public int checkBalance() {
                return balance;
        }

        public String getDescription() {
                return description;
        }

        public void withdraw(int amount) {
                balance -= amount;
        }

        public void deposit(int amount) {
                balance += amount;
        }

        public void moneyTransfer(int amount, int destNumber) {
                balance -= amount;
        }
}

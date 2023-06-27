package demo.models;

public class Account {
        protected int accountNumber;
        protected int balance;
        protected String description;

        public Account(int accountNumber) {
                this.accountNumber = accountNumber;
                this.balance = 0;
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
        public void deposit(double amount) {
        balance += amount;
}


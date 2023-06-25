package demo.models;

public class Account {
        protected int accountNumber;
        protected int balance;

        public Account(int accountNumber) {
                this.accountNumber = accountNumber;
                this.balance = 0;
        }

        protected int getAccountNumber() {
                return accountNumber;
        }

        protected int checkBalance() {
                return balance;
        }
}


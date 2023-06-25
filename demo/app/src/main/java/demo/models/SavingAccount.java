package demo.models;

class SavingAccount extends Account {
        public SavingAccount(int accountNumber) {
                super(accountNumber);
        }

        public void deposit(int amount) {
                this.balance += amount;
        }

        public void withdraw(int amount) {
                this.balance -= amount;
        }
}


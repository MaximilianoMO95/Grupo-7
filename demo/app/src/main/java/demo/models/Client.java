package demo.models;

public class Client {
        public String run;
        public String name;
        public String surname;
        public String address;
        public String comuna;
        public String tel;
        private Account account;

        public Client(String run, String name, String surname, String address, String comuna, String tel, Account account) {
                this.run        = run;
                this.name       = name;
                this.surname    = surname;
                this.address    = address;
                this.comuna     = comuna;
                this.tel        = tel;


                this.account = account;
        }

        public Account getAccount() {
                return account;
        }
}

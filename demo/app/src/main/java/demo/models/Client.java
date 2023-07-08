package demo.models;

public class Client {
        public int id;
        public String run;
        public String dv;
        public String name;
        public String ap_paterno;
        public String ap_materno;
        public String address;
        public String comuna;
        public String tel;

        public Client(String run, String dv, String name, String ap_paterno, String ap_materno, String address, String comuna, String tel) {
                this.run        = run;
                this.dv         = dv;
                this.name       = name;
                this.ap_paterno = ap_paterno;
                this.ap_materno = ap_materno;
                this.address    = address;
                this.comuna     = comuna;
                this.tel        = tel;
        }

        public Client(int id, String run, String dv, String name, String ap_paterno, String ap_materno, String address, String comuna, String tel) {
                this.id         = id;
                this.run        = run;
                this.dv         = dv;
                this.name       = name;
                this.ap_paterno = ap_paterno;
                this.ap_materno = ap_materno;
                this.address    = address;
                this.comuna     = comuna;
                this.tel        = tel;
        }

        public Account getAccount() {
                return new Account(123);
        }
}

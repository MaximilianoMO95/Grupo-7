package demo.validations;

public class ValidationUtils {
        public static final boolean validateRun(String run) {
                String regex = "^[0-9]{1,2}.[0-9]{3}.[0-9]{3}-[0-9K]{1}$";

                return run.matches(regex);
        }

        public static final boolean validateTel(String tel) {
                // cualquier numero con o sin codigo de pais y desde '8' a '16' digitos
                String regex = "^(\\+[0-9]{1,4})?\\s?[0-9]{8,16}$";

                return tel.matches(regex);
        }

        public static final boolean validateAccountNumber(String number) {
                String regex = "^[1-9][0-9]{8}$";

                return number.matches(regex);
        }
}

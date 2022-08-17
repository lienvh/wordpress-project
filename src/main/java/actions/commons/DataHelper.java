package actions.commons;

import java.util.Locale;
import com.github.javafaker.Faker;

public class DataHelper {
    private Locale locale = new Locale("en");
    Faker faker = new Faker(locale);

    public static DataHelper getData() {
        return new DataHelper();
    }

    public String getFirstName() {
        return faker.address().firstName();
    }

    public String getLastName() {
        return faker.address().lastName();
    }

    public String getCompanyName() {
        return faker.company().name();
    }

    public String getFullName() {
        return faker.name().fullName();
    }

    public String getAddress() {
        return faker.address().fullAddress();
    }

    public String getEmailAddress() {
        return faker.internet().emailAddress();
    }

    public String getPassword() {
        return faker.internet().password(6, 16);
    }

    public String getPhone() {
        return faker.phoneNumber().phoneNumber();
    }

    public long getRandomNumber(){
        return faker.number().randomNumber();
    }



}


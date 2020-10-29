package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Contact implements Serializable {
    private String number = "";
    LocalDateTime ldt = LocalDateTime.now().withSecond(0).withNano(0);
    LocalDateTime ldt1 = LocalDateTime.now().withSecond(0).withNano(0);

    public void setNumber(String number) {

        if(checkNumber(number)) {
            this.number = number;
        } else {
            this.number = "";
            System.out.println("Wrong number format!");
        }
        ldt1 = LocalDateTime.now().withSecond(0).withNano(0);
    }

    public String getNumber() {

        if (hasNumber()) {
            return number;
        } else {
            return "[no number]";
        }

    }

    public boolean hasNumber() {
        if (number.length() > 0) {
            return true;
        }
        return false;
    }

    private boolean checkNumber(String number) {

        String regex1 = "([+]*[(]*[0-9A-Za-z]{1,}[)]*)";
        String regex2 = "(([+]*)([(]*[0-9A-Za-z]{1,}[)]*)((([\\s])|([-]))([0-9A-Za-z]{2,}))*)";
        String regex3 = "(([+]*)([0-9A-Za-z]{1,})(([\\s])|([-]))([(]*[0-9A-Za-z]{2,}[)]*)((([\\s])|([-]))([0-9A-Za-z]{2,}))*)";
        String regex = String.format("%s|%s|%s", regex1, regex2, regex3);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(number);

        if (m.matches()) {
            return true;
        } else {
            return  false;
        }

    }

    static List<Contact> search(List<Contact> list, String regex) {
        List<Contact> newList = new ArrayList<>();
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        for (Contact contact: list) {
            String name = "";
            if (contact.getClass() == Person.class) {
                Person person = (Person) contact;
                name = person.firstname + " " + person.surname + " " + person.getNumber();
            }

            if (contact.getClass() == Organization.class) {
                Organization organization = (Organization) contact;
                name = organization.name + organization.getNumber();
            }

            Matcher m = p.matcher(name);

            if (m.find()) {
                newList.add(contact);
            }
        }
        return newList;
    }

    void edit() {

    }

    void info() {

    }


}

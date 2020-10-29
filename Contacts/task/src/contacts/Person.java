package contacts;
import java.time.LocalDateTime;
import java.util.Scanner;

class Person extends Contact {
    String firstname;
    String surname;
    String dateOfBirth = "";
    String gender = "";

    public Person(String name1, String name2) {
        firstname = name1;
        surname = name2;
    }

    public void setDateOfBirth(String dob) {
        if (!("".equals(dob))) {
            dateOfBirth = dob;
        } else {
            this.dateOfBirth = "";
            System.out.println("Bad birth date!");
        }
        ldt1 = LocalDateTime.now().withSecond(0).withNano(0);
    }

    public void setGender(String gen) {
        if ("M".equals(gen)) {
            gender = gen;
        } else if ("F".equals(gen)) {
            gender = gen;
        } else {
            this.dateOfBirth = "";
            System.out.println("Bad gender!");
        }
        ldt1 = LocalDateTime.now().withSecond(0).withNano(0);
    }

    public String getDateOfBirth() {
        if (dateOfBirth.length() > 0) {
            return this.dateOfBirth;
        } else {
            return  "[no data]";
        }
    }

    public String getGender() {
        if (gender.length() > 0) {
            return this.gender;
        } else {
            return  "[no data]";
        }
    }


    void edit() {

        Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Select a field (name, surname, birth date, gender, number): ");
        String action = scanner.nextLine();

        switch (action) {
            case "name":
                System.out.println("Enter name: ");
                this.firstname = scanner.nextLine();
                System.out.println("Saved");
                System.out.println();
                break;

            case "surname":
                System.out.println("Enter surname: ");
                this.surname = scanner.nextLine();
                System.out.println("Saved");
                System.out.println();
                break;

            case "birth date":
                System.out.println("Enter birth date:");
                this.setDateOfBirth(scanner.nextLine());
                System.out.println("Saved");
                System.out.println();
                break;

            case "gender":
                System.out.println("Enter gender:");
                this.setGender(scanner.nextLine());
                System.out.println("Saved");
                System.out.println();
                break;

            case "number":
                System.out.println("Enter number: ");
                this.setNumber(scanner.nextLine());
                System.out.println("Saved");
                System.out.println();
                break;
        }

    }

    void info() {
        System.out.printf("Name: %s\nSurname: %s\nBirth date: %s\n", this.firstname, this.surname, this.getDateOfBirth());
        System.out.printf("Gender: %s\nNumber: %s\nTime created: %s\nTime last edit: %s\n", this.getGender(), this.getNumber(), this.ldt, this.ldt1);
        System.out.println();
    }


}
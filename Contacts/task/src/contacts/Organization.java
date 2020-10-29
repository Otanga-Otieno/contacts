package contacts;
import java.util.Scanner;

class Organization extends Contact {
    String name;
    String address;

    public Organization(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void edit() {

        System.out.println("Select a field (name, address, number): ");
        Scanner scanner = new java.util.Scanner(System.in);
        String action = scanner.nextLine();

        switch (action) {
            case "name":
                System.out.println("Enter name: ");
                this.name = scanner.nextLine();
                System.out.println("Saved");
                System.out.println();
                break;

            case "address":
                System.out.println("Enter address: ");
                this.address = scanner.nextLine();
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
        System.out.printf("Organization name: %s\nAddress: %s\nNumber: %s\n", this.name, this.address, this.getNumber());
        System.out.printf("Time created: %s\nTime last edit: %s\n", this.ldt,  this.ldt1);
        System.out.println();
    }

}

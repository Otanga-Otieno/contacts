package contacts;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Serializaton {

    public static void serialize(ArrayList<Contact> list, File file) throws IOException {

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
        fos.close();

    }

    public static ArrayList deserialize(File file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj = ois.readObject();
        ois.close();
        fis.close();
        return (ArrayList) obj;
    }

}


public class Main {

    static ArrayList<Contact> contactList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    static void search(List<Contact> list) {
        System.out.println("Enter search query: ");
        String query = scanner.nextLine();
        List<Contact> searchquery = Contact.search(list, query);
        printList(searchquery);

        System.out.println("[search] Enter action ([number], back, again): ");
        String action = scanner.nextLine();

        switch (action) {
            case "back":
                menu();
                break;

            case "again":
                search(list);
                break;

            default:

                try {

                    int i = Integer.parseInt(action);
                    if (i > 0 && i <= list.size()) {
                        info(searchquery, Integer.parseInt(action));
                    } else {
                        menu();
                    }

                } catch (Exception e) {
                    menu();
                }


        }

    }

    static void menu() {
        System.out.println("[menu] Enter action (add, list, search, count, exit): ");
        String action = scanner.nextLine();

        switch (action) {
            case "add":
                add(contactList);
                break;

            case "list":
                list(contactList);
                break;

            case "search":
                search(contactList);
                break;

            case "count":
                count(contactList);
                break;

            case "exit":
                return;

            default:
                menu();
        }

    }

    static void add(List<Contact> list) {
        System.out.println("Enter the type (person, organization):" );
        String type = scanner.nextLine();

        if ("person".equals(type)) {
            System.out.println("Enter the name of the person:");
            String name1 = scanner.nextLine();

            System.out.println("Enter the surname of the person:");
            String name2 = scanner.nextLine();

            Person p1 = new Person(name1, name2);

            System.out.println("Enter the birth date:");
            p1.setDateOfBirth(scanner.nextLine());

            System.out.println("Enter the gender (M, F):");
            p1.setGender(scanner.nextLine());

            System.out.println("Enter the number:");
            p1.setNumber(scanner.nextLine());

            list.add(p1);
            System.out.println("The record added.");
            System.out.println();
        }

        if ("organization".equals(type)) {
            System.out.println("Enter the organization name:");
            String org = scanner.nextLine();

            System.out.println("Enter the address:");
            String add = scanner.nextLine();

            Organization o1 = new Organization(org, add);

            System.out.println("Enter the number:");
            o1.setNumber(scanner.nextLine());

            list.add(o1);
            System.out.println("The record added.");
            System.out.println();
        }
        menu();
    }

    static void edit(Contact contact) {
        contact.edit();
    }

    static  void count(List<Contact> list) {
        System.out.println("The Phone Book has " + list.size() + " records.");
        System.out.println();
        menu();
    }

    static void delete(List<Contact> list, Contact contact) {
        list.remove(list.indexOf(contact));
        System.out.println("The record removed!");
        System.out.println();
        menu();
    }

    static void list(List<Contact> list) {
        printList(list);
        System.out.println();
        System.out.println("[list] Enter action ([number], back): ");
        String action = scanner.nextLine();

        switch (action) {
            case "back":
                menu();
                break;

            default:
                try {

                    int i = Integer.parseInt(action);
                    if (i > 0 && i <= list.size()) {
                        info(list, Integer.parseInt(action));
                    } else {
                        menu();
                    }

                } catch (Exception e) {
                    menu();
                }
        }

    }

    static void info(List<Contact> list, int i) {

        Contact contact = list.get((i-1));
        contact.info();
        System.out.println("[record] Enter action (edit, delete, menu): ");
        String action = scanner.nextLine();

        switch (action) {
            case "edit":
                edit(contact);
                info(list, i);
                break;

            case "delete":
                delete(contactList, contact);
                break;

            case "menu":
                System.out.println();
                menu();
                break;

            default:
                menu();

        }

    }

    static void printList(List<Contact> l1) {
        for (Contact c: l1) {
            if (c.getClass() == Person.class) {
                Person p = (Person) c;
                String str = String.format("%d. %s %s", (l1.indexOf(c))+1, p.firstname, p.surname);
                System.out.println(str);
            }

            if (c.getClass() == Organization.class) {
                Organization o = (Organization) c;
                String str = String.format("%d. %s", (l1.indexOf(c))+1, o.name);
                System.out.println(str);
            }

        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        File phonebook = new File("phonebook");
        if (!(phonebook.exists())) {
            phonebook.createNewFile();
        } else {
            contactList = Serializaton.deserialize(phonebook);
        }

        menu();
        Serializaton.serialize(contactList, phonebook);


    }
}

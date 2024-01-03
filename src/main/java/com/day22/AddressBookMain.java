package com.day22;

import java.util.Scanner;

public class AddressBookMain {
    public static void main(String[] args) {
        AddressBookController controller = new AddressBookController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Address Book Program!");

        Contact newContact = new Contact("John", "Doe", "123 Main St", "City", "State", "12345", "1234567890", "john@example.com");
        controller.addContact(newContact);

        controller.addContactFromConsole(scanner);

        controller.editContactFromConsole(scanner);

        controller.deleteContactFromConsole(scanner);

        controller.addMultipleContactsFromConsole(scanner);

        controller.addAddressBookFromConsole(scanner);

        controller.checkDuplicateEntry();

        controller.searchPersonsInCityOrState(scanner);

        controller.viewPersonsByCityOrState();

        controller.getCountByCityOrState();
    }
}

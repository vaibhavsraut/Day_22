package com.day22;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookController {
    private List<AddressBook> addressBooks;

    public AddressBookController() {
        this.addressBooks = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the Address Book to add the contact: ");
        String addressBookName = scanner.nextLine();
        Optional<AddressBook> addressBookOptional = addressBooks.stream()
                .filter(addressBook -> addressBook.getName().equals(addressBookName))
                .findFirst();

        if (addressBookOptional.isPresent()) {
            AddressBook addressBook = addressBookOptional.get();
            addressBook.addContact(contact);
        } else {
            System.out.println("Address Book not found with the given name: " + addressBookName);
        }
    }

    public void addContactFromConsole(Scanner scanner) {
        System.out.println("Enter details for the new contact:");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("City: ");
        String city = scanner.nextLine();
        System.out.print("State: ");
        String state = scanner.nextLine();
        System.out.print("Zip: ");
        String zip = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Contact newContact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
        addContact(newContact);
    }

    public void editContactFromConsole(Scanner scanner) {
        System.out.print("Enter the first name of the contact to edit: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter the last name of the contact to edit: ");
        String lastName = scanner.nextLine();
        for (AddressBook addressBook : addressBooks) {
            addressBook.editContact(firstName, lastName, scanner);
        }
    }

    public void deleteContactFromConsole(Scanner scanner) {
        System.out.print("Enter the first name of the contact to delete: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter the last name of the contact to delete: ");
        String lastName = scanner.nextLine();
        for (AddressBook addressBook : addressBooks) {
            addressBook.deleteContact(firstName, lastName);
        }
    }

    public void addMultipleContactsFromConsole(Scanner scanner) {
        System.out.print("Enter the name of the Address Book to add multiple contacts: ");
        String addressBookName = scanner.nextLine();
        Optional<AddressBook> addressBookOptional = addressBooks.stream()
                .filter(addressBook -> addressBook.getName().equals(addressBookName))
                .findFirst();

        if (addressBookOptional.isPresent()) {
            AddressBook addressBook = addressBookOptional.get();
            System.out.print("Enter the number of contacts to add: ");
            int numContacts = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < numContacts; i++) {
                addContactFromConsole(scanner);
            }
        } else {
            System.out.println("Address Book not found with the given name: " + addressBookName);
        }
    }

    public void addAddressBookFromConsole(Scanner scanner) {
        System.out.print("Enter the name of the new Address Book: ");
        String newAddressBookName = scanner.nextLine();
        AddressBook newAddressBook = new AddressBook(newAddressBookName);
        addressBooks.add(newAddressBook);
        System.out.println("New Address Book created: " + newAddressBookName);
    }

    public void checkDuplicateEntry() {
        Set<Contact> allContacts = addressBooks.stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .collect(Collectors.toSet());

        Set<Contact> duplicateContacts = allContacts.stream()
                .filter(contact -> Collections.frequency(allContacts, contact) > 1)
                .collect(Collectors.toSet());

        if (duplicateContacts.isEmpty()) {
            System.out.println("No duplicate entries found.");
        } else {
            System.out.println("Duplicate entries found:");
            duplicateContacts.forEach(System.out::println);
        }
    }

    public void searchPersonsInCityOrState(Scanner scanner) {
        System.out.print("Enter the city or state to search: ");
        String searchValue = scanner.nextLine();

        List<Contact> searchResult = addressBooks.stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .filter(contact -> contact.getCity().equalsIgnoreCase(searchValue) || contact.getState().equalsIgnoreCase(searchValue))
                .collect(Collectors.toList());

        if (searchResult.isEmpty()) {
            System.out.println("No persons found in the city or state: " + searchValue);
        } else {
            System.out.println("Persons found in the city or state: ");
            searchResult.forEach(System.out::println);
        }
    }

    public void viewPersonsByCityOrState() {
        Map<String, List<Contact>> cityDictionary = addressBooks.stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .collect(Collectors.groupingBy(Contact::getCity));

        Map<String, List<Contact>> stateDictionary = addressBooks.stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .collect(Collectors.groupingBy(Contact::getState));

        System.out.println("Persons by City: ");
        cityDictionary.forEach((city, contacts) -> {
            System.out.println(city + ": ");
            contacts.forEach(System.out::println);
        });

        System.out.println("Persons by State: ");
        stateDictionary.forEach((state, contacts) -> {
            System.out.println(state + ": ");
            contacts.forEach(System.out::println);
        });
    }

    public void getCountByCityOrState() {
        Map<String, Long> cityCount = addressBooks.stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .collect(Collectors.groupingBy(Contact::getCity, Collectors.counting()));

        Map<String, Long> stateCount = addressBooks.stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .collect(Collectors.groupingBy(Contact::getState, Collectors.counting()));

        System.out.println("Count of Persons by City: ");
        cityCount.forEach((city, count) -> System.out.println(city + ": " + count));

        System.out.println("Count of Persons by State: ");
        stateCount.forEach((state, count) -> System.out.println(state + ": " + count));
    }
}

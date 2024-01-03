package com.day22;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
    private String name;
    private List<Contact> contacts;

    public AddressBook(String name) {
        this.name = name;
        this.contacts = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getName() {
        return name;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        System.out.println("Contact added to Address Book '" + name + "': " + contact);
    }

    public void editContact(String firstName, String lastName, Scanner scanner) {
        for (Contact contact : contacts) {
            if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
                System.out.println("Enter new details for the contact:");
                System.out.print("Address: ");
                contact.setAddress(scanner.nextLine());
                System.out.print("City: ");
                contact.setCity(scanner.nextLine());
                System.out.print("State: ");
                contact.setState(scanner.nextLine());
                System.out.print("Zip: ");
                contact.setZip(scanner.nextLine());
                System.out.print("Phone Number: ");
                contact.setPhoneNumber(scanner.nextLine());
                System.out.print("Email: ");
                contact.setEmail(scanner.nextLine());
                System.out.println("Contact updated successfully: " + contact);
                return;
            }
        }
        System.out.println("Contact not found with the given name: " + firstName + " " + lastName);
    }

    public void deleteContact(String firstName, String lastName) {
        Contact contactToRemove = null;
        for (Contact contact : contacts) {
            if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
                contactToRemove = contact;
                break;
            }
        }
        if (contactToRemove != null) {
            contacts.remove(contactToRemove);
            System.out.println("Contact deleted successfully: " + contactToRemove);
        } else {
            System.out.println("Contact not found with the given name: " + firstName + " " + lastName);
        }
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}

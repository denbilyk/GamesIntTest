package com.homenet.refactor;

import java.util.*;

public class AddressBook {
    private AddressDao addressDao;


    public AddressBook() {
        addressDao = new AddressDaoImpl();
        new Checker(this).start();

    }

    /**
     * Return true if person with name 'name' has mobile
     */
    public boolean hasMobile(String name) {
        Person person = addressDao.findPerson(name);
        return person != null && person.getPhoneNumber().startsWith("070");
    }


    /**
     * @return - amount of entries in database
     */
    public int getSize() {
        return addressDao.getAll().size();
    }

    /**
     * Gets the given user's mobile phone number,
     * or null if he doesn't have one.
     */
    public String getMobile(String name) {
        Person person = addressDao.findPerson(name);
        return person != null ? person.getPhoneNumber() : null;
    }

    /**
     * Returns all names in the book truncated to the given length.
     */
    public List getNames(int maxLength) {
        List<Person> people = addressDao.getAll();
        List<String> names = new ArrayList<String>();
        for (Person person : people) {
            String name = person.getName();
            if (name.length() > maxLength) {
                name = name.substring(0, maxLength - 1);
            }
            names.add(name);
        }
        return names;

    }

    /**
     * Returns all people who have mobile phone numbers.
     */
    public List getList() {
        List<Person> people = addressDao.getAll();
        List<Person> result = new ArrayList<Person>();
        for (Person person : people) {
            if (person.getPhoneNumber().startsWith("070")) {
                result.add(person);
            }
        }
        return result;
    }

    static class Checker extends Thread {
        private long time = System.currentTimeMillis();
        private AddressBook addressBook;

        public Checker(AddressBook addressBook) {
               this.addressBook = addressBook;
        }

        public void run() {
            while (System.currentTimeMillis() > time) {
                addressBook.getList();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
            }

        }
    }

}

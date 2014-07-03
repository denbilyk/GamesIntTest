package com.homenet.refactor;

import java.util.List;

/**
 * @author denis.bilyk
 */
public interface AddressDao {
    /**
     * Add person entity to database
     *
     * @param person - person entity
     */
    public void addPerson(Person person);


    /**
     * Find specific person in database
     *
     * @param name - person name
     * @return - person entity
     */
    public Person findPerson(String name);

    /**
     * Get all persons entities
     *
     * @return list with all person in database
     */
    public List<Person> getAll();
}

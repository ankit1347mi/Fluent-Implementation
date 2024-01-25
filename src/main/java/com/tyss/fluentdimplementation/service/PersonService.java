package com.tyss.fluentdimplementation.service;

import co.elastic.clients.elasticsearch.core.*;
import com.tyss.fluentdimplementation.entity.Person;

import java.io.IOException;
import java.util.List;

public interface PersonService {

    String savePerson(Person person) throws IOException;

    String findPersonById(String id) throws IOException;

    String findPersonByName(String name) throws IOException;

    List<Person> findAllPerson() throws IOException;

    List<Person> findPersonAgeGreaterThan(int age) throws IOException;

    String updatePerson(Person person) throws IOException;

    String deletePerson(String id) throws IOException;
}

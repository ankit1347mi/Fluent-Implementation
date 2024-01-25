package com.tyss.fluentdimplementation.controller;

import co.elastic.clients.elasticsearch.core.*;
import com.tyss.fluentdimplementation.aspect.LogAspect;
import com.tyss.fluentdimplementation.entity.Person;
import com.tyss.fluentdimplementation.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @LogAspect
    @PostMapping
    public String savePerson(@RequestBody Person person) throws IOException {
        return personService.savePerson(person);
    }

    @LogAspect
    @PutMapping
    public String updatePerson(@RequestBody Person person) throws IOException {
        return personService.updatePerson(person);
    }

    @LogAspect
    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable String id) throws IOException {
        return personService.deletePerson(id);
    }

    @LogAspect
    @GetMapping
    public String findPersonById(@RequestParam String id) throws IOException {
        return personService.findPersonById(id);
    }

    @LogAspect
    @GetMapping("/find/{name}")
    public String findPersonByName(String name) throws IOException {
        return personService.findPersonByName(name);
    }

    @LogAspect
    @GetMapping("/findAll")
    public List<Person> findAllPerson() throws IOException {
        return personService.findAllPerson();
    }

    @LogAspect
    @GetMapping("/age/{age}")
    public List<Person> findPersonAgeGreaterThan(int age) throws IOException {
        return personService.findPersonAgeGreaterThan(age);
    }
}

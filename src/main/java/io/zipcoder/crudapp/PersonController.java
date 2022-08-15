package io.zipcoder.crudapp;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.StyledEditorKit;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {
    private List<Person> personList = new ArrayList<>();
    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
    @PostMapping
    public ResponseEntity<Person> createPerson(@Valid @RequestBody Person p){
        return new ResponseEntity<>(personRepository.save(p), HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id){
        return new ResponseEntity<>(personRepository.findOne(id), HttpStatus.OK);
    }
    public ResponseEntity<List<Person>> getPersonList(){
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, Person newPersonData){
        Person originalPerson = personRepository.findOne(id);
        originalPerson.setFirstName(newPersonData.getFirstName());
        originalPerson.setLastName((newPersonData.getLastName()));
        return new ResponseEntity<>(personRepository.save(originalPerson), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deletePerson(@PathVariable Long id){
        personRepository.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}

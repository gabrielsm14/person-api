package br.com.empresa.personapi.service;

import br.com.empresa.personapi.dto.MessageResponseDTO;
import br.com.empresa.personapi.dto.PersonDTO;
import br.com.empresa.personapi.entity.Person;
import br.com.empresa.personapi.exception.PersonNotFoundException;
import br.com.empresa.personapi.mapper.PersonMapper;
import br.com.empresa.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savePerson = personRepository.save(personToSave);

        return MessageResponseDTO
                .builder()
                .message("Create person with ID " + savePerson.getId())
                .build();
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();

        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
//        Optional<Person> optionalPerson = personRepository.findById(id);
//        if(optionalPerson.isEmpty() || optionalPerson == null){
//            throw new PersonNotFoundException(id);
//        }
        return personMapper.toDTO(person);
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }
}

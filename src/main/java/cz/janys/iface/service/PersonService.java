package cz.janys.iface.service;

import cz.janys.iface.dto.PersonDto;

import java.util.List;

/**
 * @author Martin Janys
 */
public interface PersonService {

    List<PersonDto> findAllPersons();

    PersonDto findPersonById(long id);

    PersonDto createPerson(PersonDto dto);

    PersonDto updatePerson(PersonDto dto);

    void deletePersonById(long id);
}

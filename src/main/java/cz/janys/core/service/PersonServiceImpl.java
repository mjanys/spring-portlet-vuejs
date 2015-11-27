package cz.janys.core.service;

import cz.janys.core.entity.PersonEntity;
import cz.janys.core.repository.PersonRepository;
import cz.janys.iface.converter.PersonDtoToEntityConverter;
import cz.janys.iface.converter.PersonEntityToDtoConverter;
import cz.janys.iface.dto.PersonDto;
import cz.janys.iface.service.PersonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

/**
 * @author Martin Janys
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private static final Logger LOG = Logger.getLogger(PersonServiceImpl.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonEntityToDtoConverter entityToDto;
    @Autowired
    private PersonDtoToEntityConverter dtoToEntity;

    @Override
    @Transactional(readOnly = true)
    public List<PersonDto> findAllPersons() {
        Iterable<PersonEntity> entities = personRepository.findAll();

        return entityToDto.convert(entities);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonDto findPersonById(long id) {

        PersonEntity entity = personRepository.findById(id);
        if (entity != null) {
            return entityToDto.convert(entity);
        }

        throw new EntityNotFoundException("Person id='" + id + "'");
    }

    @Override
    public PersonDto createPerson(PersonDto dto) {
        Assert.notNull(dto);
        Assert.isNull(dto.getId());

        dto.setTimestamp(new Date());
        PersonEntity entity = dtoToEntity.convert(dto);
        return savePersonEntity(entity);
    }

    @Override
    public PersonDto updatePerson(PersonDto dto) {
        Assert.notNull(dto);
        Assert.notNull(dto.getId());

        PersonEntity entity = personRepository.findById(dto.getId());
        entity = dtoToEntity.convert(dto, entity);
        return savePersonEntity(entity);
    }

    private PersonDto savePersonEntity(PersonEntity entity) {
        PersonEntity savedEntity = personRepository.save(entity);
        return entityToDto.convert(savedEntity);
    }

    @Override
    public void deletePersonById(long id) {
        PersonEntity entity = personRepository.findById(id);
        personRepository.delete(entity);
    }
}

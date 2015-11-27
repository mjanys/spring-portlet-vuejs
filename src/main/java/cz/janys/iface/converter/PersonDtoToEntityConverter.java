package cz.janys.iface.converter;

import cz.janys.core.entity.PersonEntity;
import cz.janys.iface.convert.converter.AbstractExtendedConverter;
import cz.janys.iface.dto.PersonDto;
import org.springframework.stereotype.Component;

/**
 * @author Martin Janys
 */
@Component
public class PersonDtoToEntityConverter extends AbstractExtendedConverter<PersonDto, PersonEntity> {

    @Override
    public PersonEntity convert(PersonDto source, PersonEntity target) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setEmail(source.getEmail());
        if (source.getTimestamp() != null)
            target.setTimestamp(source.getTimestamp());
        return target;
    }

    @Override
    public PersonEntity convert(PersonDto source) {
        return convert(source, new PersonEntity());
    }
}

package cz.janys.iface.converter;

import cz.janys.core.entity.PersonEntity;
import cz.janys.iface.convert.converter.AbstractExtendedConverter;
import cz.janys.iface.dto.PersonDto;
import org.springframework.stereotype.Component;

/**
 * @author Martin Janys
 */
@Component
public class PersonEntityToDtoConverter extends AbstractExtendedConverter<PersonEntity, PersonDto> {

    @Override
    public PersonDto convert(PersonEntity source, PersonDto target) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setEmail(source.getEmail());
        target.setTimestamp(source.getTimestamp());
        return target;
    }

    @Override
    public PersonDto convert(PersonEntity source) {
        return convert(source, new PersonDto());
    }
}

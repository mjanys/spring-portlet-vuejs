package cz.janys.portlet.person.converter;

import cz.janys.iface.convert.converter.AbstractExtendedConverter;
import cz.janys.iface.dto.PersonDto;
import cz.janys.portlet.person.pto.PersonPto;
import org.springframework.stereotype.Component;

/**
 * @author Martin Janys
 */
@Component
public class PersonDtoToPtoConverter extends AbstractExtendedConverter<PersonDto, PersonPto> {

    @Override
    public PersonPto convert(PersonDto source, PersonPto target) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setEmail(source.getEmail());
        return target;
    }

    @Override
    public PersonPto convert(PersonDto source) {
        return convert(source, new PersonPto());
    }
}

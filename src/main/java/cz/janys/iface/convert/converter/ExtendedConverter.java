package cz.janys.iface.convert.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.List;

/**
 * @author Martin Janys
 */
public interface ExtendedConverter<S, T> extends Converter<S, T> {

    T convert(S source, T target);

    List<T> convert(Iterable<S> source);

}

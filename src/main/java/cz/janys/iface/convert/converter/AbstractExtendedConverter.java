package cz.janys.iface.convert.converter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Janys
 */
public abstract class AbstractExtendedConverter<S, T> implements ExtendedConverter<S, T> {

    @Override
    public List<T> convert(Iterable<S> source) {
        List<T> t = new ArrayList<T>();
        for (S s : source) {
            t.add(convert(s));
        }
        return t;
    }

}

package cz.janys.portlet.mvc;

import java.lang.annotation.*;

/**
 * @author Martin Janys
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestJson {
}

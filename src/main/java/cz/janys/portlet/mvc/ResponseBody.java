package cz.janys.portlet.mvc;

import java.lang.annotation.*;

/**
 * @author Martin Janys
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseBody {
}

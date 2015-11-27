package cz.janys.portlet.person.pto;

import cz.janys.iface.DataLengthConstants;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * @author Martin Janys
 */
public class PersonPto {
    private Long id;

    @NotEmpty
    @Size(max= DataLengthConstants.STRING_SHORT)
    private String name;

    @Email
    @Size(max= DataLengthConstants.STRING_SHORT)
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package cz.janys.core.entity;

import cz.janys.iface.DataLengthConstants;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Martin Janys
 */
@Entity
@Table(name="PERSON")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="NAME", nullable=false, length= DataLengthConstants.STRING_SHORT)
    private String name;

    @Column(name="EMAIL", nullable=true, length= DataLengthConstants.STRING_NORMAL)
    private String email;

    @Column(name="TIMESTAMP", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}

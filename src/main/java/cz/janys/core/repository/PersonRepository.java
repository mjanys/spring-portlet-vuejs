package cz.janys.core.repository;

import cz.janys.core.entity.PersonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Martin Janys
 */
@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    PersonEntity findById(Long id);

    PersonEntity findByName(String name);

    PersonEntity findByEmail(String email);

    @Query("from PersonEntity p WHERE p.timestamp > :timestamp")
    List<PersonEntity> findFrom(@Param("timestamp") Date timestamp);

}

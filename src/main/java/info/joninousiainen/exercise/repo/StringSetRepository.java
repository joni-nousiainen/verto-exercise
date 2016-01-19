package info.joninousiainen.exercise.repo;

import info.joninousiainen.exercise.model.StringSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StringSetRepository extends CrudRepository<StringSet, Long> {
}

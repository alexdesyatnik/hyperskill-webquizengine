package engine.repository;

import engine.entity.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuizRepository extends PagingAndSortingRepository<Quiz, Integer> {

}

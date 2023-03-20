package blps.lab1.dao;

import blps.lab1.model.domain.Topic;
import blps.lab1.model.domain.TopicCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findAllByCategory(TopicCategory category);

    @Query("SELECT t FROM Topic t WHERE UPPER(t.title) LIKE UPPER(concat('%', :query, '%'))" +
            "OR UPPER(t.description) LIKE UPPER(concat('%', :query, '%'))" +
            "OR UPPER(t.content) LIKE UPPER(concat('%', :query, '%'))")
    List<Topic> findByQuery(@Param("query") String query);

    @Query("SELECT t FROM Topic t WHERE t.category = :category AND (UPPER(t.title) LIKE UPPER(concat('%', :query, '%'))" +
            "OR UPPER(t.description) LIKE UPPER(concat('%', :query, '%'))" +
            "OR UPPER(t.content) LIKE UPPER(concat('%', :query, '%')))")
    List<Topic> findByCategoryAndQuery(@Param("category") TopicCategory category, @Param("query") String query);

    Optional<Topic> findById(long id);
}

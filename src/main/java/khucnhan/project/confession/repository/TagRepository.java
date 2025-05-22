package khucnhan.project.confession.repository;

import khucnhan.project.confession.model.Category;
import khucnhan.project.confession.model.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag,Long> {
}

package khucnhan.project.confession.repository;

import khucnhan.project.confession.model.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminRepository extends CrudRepository<Admin,Long> {
    Optional<Admin> findByUsername(String username);
}

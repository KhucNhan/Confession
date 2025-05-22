package khucnhan.project.confession.service;

import khucnhan.project.confession.model.Admin;

import java.util.Optional;

public interface AdminService {
    Optional<Admin> findById(Long id);
    Optional<Admin> findByUsername(String username);
    Iterable<Admin> findAll();
    Admin save(Admin admin);
    void deleteById(Long id);
}

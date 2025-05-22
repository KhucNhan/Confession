package khucnhan.project.confession.service;

import khucnhan.project.confession.model.Report;

import java.util.List;
import java.util.Optional;

public interface ReportService {
    Report save(Report report);
    Optional<Report> findById(long id);
    List<Report> findAll();
    void deleteById(long id);
}

package khucnhan.project.confession.service.impl;

import khucnhan.project.confession.model.Report;
import khucnhan.project.confession.repository.ReportRepository;
import khucnhan.project.confession.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository){
        this.reportRepository = reportRepository;
    }

    @Override
    public Report save(Report report){
        return (Report) reportRepository.save(report);
    }

    @Override
    public Optional<Report> findById(long id){
        return reportRepository.findById(id);
    }

    @Override
    public List<Report> findAll(){
        return (List<Report>) reportRepository.findAll();
    }

    @Override
    public void deleteById(long id){
        reportRepository.deleteById(id);
    }
}

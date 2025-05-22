package khucnhan.project.confession.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private long reportId;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_type", nullable = false)
    private TargetType targetType;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "created_at", nullable = false)
    private Timestamp createAt;

    // Constructors
    public Report() {
    }

    public Report(long reportId, TargetType targetType, String reason, Timestamp createAt) {
        this.reportId = reportId;
        this.targetType = targetType;
        this.reason = reason;
        this.createAt = createAt;
    }

    // Getters & Setters
    public long getReportId() {
        return reportId;
    }

    public void setReportId(long reportId) {
        this.reportId = reportId;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", targetType=" + targetType +
                ", reason='" + reason + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}

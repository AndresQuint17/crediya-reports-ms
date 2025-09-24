package co.com.crediya.reports.model.approvedapplications.gateways;

import co.com.crediya.reports.model.approvedapplications.ApprovedApplications;
import reactor.core.publisher.Mono;

public interface ApprovedApplicationsRepository {
    Mono<ApprovedApplications> readReport();
    Mono<Void> updateReport(Long amountApproved);
}

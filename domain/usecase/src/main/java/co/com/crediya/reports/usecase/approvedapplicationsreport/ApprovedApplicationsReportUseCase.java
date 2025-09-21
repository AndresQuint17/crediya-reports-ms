package co.com.crediya.reports.usecase.approvedapplicationsreport;

import co.com.crediya.reports.model.approvedapplications.ApprovedApplications;
import co.com.crediya.reports.model.approvedapplications.gateways.ApprovedApplicationsRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ApprovedApplicationsReportUseCase {

    private final ApprovedApplicationsRepository approvedApplicationsRepository;

    public Mono<ApprovedApplications> getReport(){
        return this.approvedApplicationsRepository.readReport();
    }
}

package co.com.crediya.reports.usecase.updateApprovedLoansReport;

import co.com.crediya.reports.model.approvedapplications.gateways.ApprovedApplicationsRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateApprovedLoansReportUseCase {
    private final ApprovedApplicationsRepository approvedApplicationsRepository;

    public Mono<Void> updateReport(Long amountApproved) {
        return this.approvedApplicationsRepository.updateReport(amountApproved);
    }
}

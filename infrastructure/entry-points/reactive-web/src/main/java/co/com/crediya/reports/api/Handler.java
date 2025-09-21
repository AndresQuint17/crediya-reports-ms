package co.com.crediya.reports.api;

import co.com.crediya.reports.usecase.approvedapplicationsreport.ApprovedApplicationsReportUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final ApprovedApplicationsReportUseCase approvedApplicationsReportUseCase;

    public Mono<ServerResponse> listenGetReportApprovedApplications(ServerRequest serverRequest) {
        return approvedApplicationsReportUseCase.getReport()
                .flatMap(report -> ServerResponse.ok().bodyValue(report));
    }
}

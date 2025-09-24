package co.com.crediya.reports.sqs.listener;

import co.com.crediya.reports.usecase.updateApprovedLoansReport.UpdateApprovedLoansReportUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.model.Message;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class SQSProcessor implements Function<Message, Mono<Void>> {
    private final UpdateApprovedLoansReportUseCase updateApprovedLoansReportUseCase;
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public Mono<Void> apply(Message message) {
        Long amountApproved = objectMapper.readValue(message.body(), Long.class);
        return updateApprovedLoansReportUseCase.updateReport(amountApproved);
    }
}

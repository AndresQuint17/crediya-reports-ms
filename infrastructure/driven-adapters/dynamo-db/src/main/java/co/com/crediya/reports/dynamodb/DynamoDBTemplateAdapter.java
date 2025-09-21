package co.com.crediya.reports.dynamodb;

import co.com.crediya.reports.dynamodb.helper.TemplateAdapterOperations;
import co.com.crediya.reports.model.approvedapplications.ApprovedApplications;
import co.com.crediya.reports.model.approvedapplications.gateways.ApprovedApplicationsRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;


@Repository
public class DynamoDBTemplateAdapter extends TemplateAdapterOperations<ApprovedApplications, String, ReporteAprobadosEntity> implements ApprovedApplicationsRepository {

    private final String metricaKey = "solicitudes_aprobadas";

    public DynamoDBTemplateAdapter(DynamoDbEnhancedAsyncClient connectionFactory, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(connectionFactory, mapper, d -> mapper.map(d, ApprovedApplications.class), "reporte_aprobados");
    }

    @Override
    public Mono<ApprovedApplications> readReport() {
        return getById(metricaKey);
    }
}

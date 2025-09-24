package co.com.crediya.reports.dynamodb;

import co.com.crediya.reports.dynamodb.helper.TemplateAdapterOperations;
import co.com.crediya.reports.model.approvedapplications.ApprovedApplications;
import co.com.crediya.reports.model.approvedapplications.gateways.ApprovedApplicationsRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;


@Repository
public class DynamoDBTemplateAdapter extends TemplateAdapterOperations<ApprovedApplications, String, ReporteAprobadosEntity> implements ApprovedApplicationsRepository {

    private final String metricaKey = "solicitudes_aprobadas";
    private final DynamoDbEnhancedAsyncClient enhancedClient;

    public DynamoDBTemplateAdapter(DynamoDbEnhancedAsyncClient enhancedClient, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(enhancedClient, mapper, d -> mapper.map(d, ApprovedApplications.class), "reporte_aprobados");
        this.enhancedClient = enhancedClient;
    }

    @Override
    public Mono<ApprovedApplications> readReport() {
        return getById(metricaKey);
    }

    @Override
    public Mono<Void> updateReport(Long amountApproved) {
        ReporteAprobadosEntity reporteAprobadosEntity = ReporteAprobadosEntity.builder()
                .metrica(metricaKey)
                .build();

        DynamoDbAsyncTable<ReporteAprobadosEntity> table = enhancedClient.table("reporte_aprobados", TableSchema.fromBean(ReporteAprobadosEntity.class));

        // Recuperar el ítem actual desde DynamoDB
        CompletableFuture<ReporteAprobadosEntity> itemFuture = table.getItem(reporteAprobadosEntity);

        return Mono.fromFuture(() -> itemFuture.thenCompose(entityToUpdate -> {
            if (entityToUpdate != null) {
                // Actualizar los campos de la entidad localmente
                entityToUpdate.setValor(entityToUpdate.getValor() + 1);
                entityToUpdate.setMontoTotal(entityToUpdate.getMontoTotal().add(BigDecimal.valueOf(amountApproved)));

                return table.updateItem(entityToUpdate);
            } else {
                return CompletableFuture.completedFuture(null);  // Si no se encuentra el ítem, devolver un CompletableFuture vacío
            }
        })).then();  // Devolver un Mono<Void> que indica la finalización de la operación
    }
}

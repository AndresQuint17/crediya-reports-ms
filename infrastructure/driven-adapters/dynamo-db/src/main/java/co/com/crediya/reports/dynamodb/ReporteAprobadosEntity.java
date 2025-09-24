package co.com.crediya.reports.dynamodb;

import lombok.Builder;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.math.BigDecimal;

@Builder(toBuilder = true)
@DynamoDbBean
public class ReporteAprobadosEntity {

    private String metrica;
    private BigDecimal montoTotal;
    private Long valor;

    public ReporteAprobadosEntity() {
    }

    public ReporteAprobadosEntity(String metrica, BigDecimal montoTotal, Long valor) {
        this.metrica = metrica;
        this.montoTotal = montoTotal;
        this.valor = valor;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("metrica")
    public String getMetrica() {
        return metrica;
    }

    public void setMetrica(String metrica) {
        this.metrica = metrica;
    }

    @DynamoDbAttribute("monto_total")
    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    @DynamoDbAttribute("valor")
    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }
}

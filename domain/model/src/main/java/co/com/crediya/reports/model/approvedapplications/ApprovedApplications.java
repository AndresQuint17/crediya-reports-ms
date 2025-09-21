package co.com.crediya.reports.model.approvedapplications;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ApprovedApplications {
    private Long valor;
    private BigDecimal montoTotal;
}

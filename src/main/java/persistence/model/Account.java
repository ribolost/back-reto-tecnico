package persistence.model;

import common.DTO.types.StatusAccountTypes;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Table(name = "account")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @NotNull(message = "The account must have an associated customer.")
    @JoinColumn(table = "customer", nullable = false, unique = true)
    private Integer customerId;

    @NotNull(message = "The account number can't be null.")
    @Column(unique = true)
    //TODO Se puede crear una validación personalizada que esté acorde con el estándar IBAN
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private StatusAccountTypes status;
}

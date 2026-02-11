package persistence.model;

import common.DTO.type.StatusAccountTypes;
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

    @OneToOne(optional = false)
    private Customer customer;

    @NotNull(message = "The account number can't be null.")
    @Column(unique = true)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private StatusAccountTypes status;
}

package persistence.model;

import common.DTO.type.CustomerDocumentTypes;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "customer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private CustomerDocumentTypes documentType;

    @Size(min = 2, max = 10, message = "The document number length must be between 2 and 10.")
    private String documentNumber;

    @Size(max = 100, message = "The full name length must be 100 characters maximum.")
    private String fullName;

    @Size(max = 100, message = "The email length must be 100 characters maximum.")
    private String email;

}

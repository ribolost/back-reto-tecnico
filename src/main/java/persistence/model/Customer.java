package persistence.model;

import common.DTO.types.CustomerDocumentTypes;
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

    @NotBlank(message = "The document number cannot be empty.")
    @NotEmpty(message = "The document number cannot be empty.")
    //TODO Toca mirar que valores son buenos acá
    @Size(min = 2, max = 10, message = "The document number length must be between 2 and 10.")
    private String documentNumber;

    private String fullName;

    @Email(message = "The provided email is not valid.")
    @NotBlank(message = "The email cannot be empty.")
    private String email;

}

package common.DTO;

import common.DTO.type.CustomerDocumentTypes;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CustomerDTO(Integer id,
                          CustomerDocumentTypes documentType,
                          @NotBlank(message = "The document number cannot be empty or null.")
                          @NotEmpty(message = "The document number cannot be empty or null.")
                          String documentNumber,
                          String fullName,
                          @NotBlank(message = "The email cannot be empty or null.")
                          @NotEmpty(message = "The email cannot be empty or null.")
                          @Email(message = "The provided email is not valid.")
                          String email) {
}

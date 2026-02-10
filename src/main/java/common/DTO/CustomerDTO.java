package common.DTO;

import common.DTO.types.CustomerDocumentTypes;
import jakarta.validation.constraints.NotNull;

public record CustomerDTO(Integer id, CustomerDocumentTypes documentType, @NotNull String documentNumber,
                          String fullName, @NotNull String email) {
}

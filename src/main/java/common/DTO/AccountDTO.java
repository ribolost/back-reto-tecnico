package common.DTO;

import common.DTO.type.StatusAccountTypes;
import jakarta.validation.constraints.NotNull;

public record AccountDTO(Integer id,
                         @NotNull(message = "The customer id wasn't provided.")
                         Integer customerId,
                         String accountNumber,
                         StatusAccountTypes status) {
}

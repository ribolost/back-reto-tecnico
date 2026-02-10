package common.DTO;

import common.DTO.types.StatusAccountTypes;
import jakarta.validation.constraints.NotNull;

public record AccountDTO(Integer id, @NotNull Integer customerId, String accountNumber, StatusAccountTypes status) {
}

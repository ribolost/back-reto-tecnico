package service.customer;

import common.DTO.CustomerDTO;
import org.springframework.stereotype.Service;
import persistence.model.Customer;

@Service
public class MapperCustomerService {

    public CustomerDTO mapCustomerEntityToDTO(Customer customer){
        return new CustomerDTO(customer.getId(), customer.getDocumentType(), customer.getDocumentNumber(),
                customer.getFullName(), customer.getEmail());
    }

    public Customer mapCustomerDTOToEntity(CustomerDTO customer){
        return Customer.builder()
                .documentType(customer.documentType())
                .documentNumber(customer.documentNumber())
                .fullName(customer.fullName())
                .email(customer.email())
                .build();
    }

}

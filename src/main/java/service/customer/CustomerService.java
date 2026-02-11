package service.customer;

import common.DTO.CustomerDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.model.Customer;
import persistence.repository.CustomerRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MapperCustomerService customerMapper;

    public CustomerDTO createCustomer(CustomerDTO customer){
            @Valid Customer newCustomer = customerMapper.mapCustomerDTOToEntity(customer);
            customerRepository.save(newCustomer);
            return customerMapper.mapCustomerEntityToDTO(newCustomer);
    }

    public List<CustomerDTO> getAllCustomers(){
        return StreamSupport
                .stream(customerRepository.findAll().spliterator(), false)
                .map(customer -> customerMapper.mapCustomerEntityToDTO(customer))
                .toList();
    }

}

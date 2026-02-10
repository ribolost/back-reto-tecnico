package service.customer;

import common.DTO.CustomerDTO;
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

    public void createCustomer(CustomerDTO customer){
            Customer createdCustomer = customerMapper.mapCustomerDTOToEntity(customer);
            customerRepository.save(createdCustomer);
    }

    public List<CustomerDTO> getAllCustomers(){
        return StreamSupport
                .stream(customerRepository.findAll().spliterator(), false)
                .map(customer -> customerMapper.mapCustomerEntityToDTO(customer))
                .toList();
    }

}

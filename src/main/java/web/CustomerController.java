package web;

import common.DTO.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.customer.CustomerService;

import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody final CustomerDTO customer){
        try{
            customerService.createCustomer(customer);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers(){
        try{
            List<CustomerDTO> customers = customerService.getAllCustomers();
            return new ResponseEntity<>(customers, HttpStatus.OK);

        } catch (Exception ex){
            //TODO Falta la gestión de excepciones
            return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
        }
    }
}

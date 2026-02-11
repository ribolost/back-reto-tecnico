package service.account;

import org.springframework.stereotype.Service;

@Service
public class IBANAccountGeneratorService {


    public String generateAccountNumberForCustomer(Integer customerId){
        return "numeroPrueba" + customerId;
    }

}

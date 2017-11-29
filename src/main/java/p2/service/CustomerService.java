package p2.service;

import p2.domain.Customer;

public class CustomerService {
    public Customer findCustomer(Object context) {
        if(isInCache(context)){
            return getCached(context);
        }
        else {
            Customer found = findInNewCustomerSystem();
            
            if(found != null){
                return found;
            }
            
            found = findInOldCustomerSystem();

            if(found != null){
                return found;
            }
            
            throw new RuntimeException("customer not found");
        }
    }

    private Customer findInOldCustomerSystem() {
        return null;
    }

    private Customer findInNewCustomerSystem() {
        return null;
    }

    private Customer getCached(Object context) {
        return null;
    }

    private boolean isInCache(Object context) {
        return false;
    }
}

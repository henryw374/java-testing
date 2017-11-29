package p2;


import p2.domain.Bill;
import p2.domain.Customer;
import p2.service.CustomerService;
import p2.service.RentalService;

public class Resource {
    
    private CustomerService customerService;
    private RentalService rentalService;

    public Resource(CustomerService customerService, RentalService rentalService) {
        this.customerService = customerService;
        this.rentalService = rentalService;
    }
    
    public Object getCustomerBill(Context context){
        if(context.customerId != null) {
            if (context.billingPeriod == null) {
                return 400;
            }
            Customer customer = customerService.findCustomer(context.customerId);

            Bill bill = rentalService.getCustomerBill(customer, context.billingPeriod);
            return bill;
        }
        else {
            return 400;
        }
    }
    
    private static class Context {
        Object customerId;
        Object billingPeriod;
    }
}

package p3;


import p3.domain.Bill;
import p3.domain.Customer;
import p3.domain.Rental;
import p3.service.CustomerService;
import p3.service.RentalService;

public class Resource {

    private CustomerService customerService;
    private RentalService rentalService;

    public Resource(CustomerService customerService, RentalService rentalService) {
        this.customerService = customerService;
    }

    private void validateRequest(Context context) {
        if (context.customerId != null || context.billingPeriod == null) {
            throw new RuntimeException("400");
        }
    }

    public Object getCustomerBill(Context context) {
        validateRequest(context);
        Customer customer = customerService.findCustomer(context.customerId);
        Iterable<Rental> rentalsInPeriod = rentalService.getRentalsInPeriod(customer, context.billingPeriod);
        return rentalService.getCustomerBill(rentalsInPeriod);
    }

    private static class Context {
        Object customerId;
        Object billingPeriod;
    }
}

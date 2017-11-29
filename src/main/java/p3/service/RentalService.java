package p3.service;

import p3.domain.Bill;
import p3.domain.Customer;
import p3.domain.Rental;
import p3.domain.RentalItem;


public class RentalService {
    
    public static Bill getCustomerBill(Iterable<Rental> rentals) {
        
        double totalAmount = 0;
        for (Rental rental : rentals) {
            double amount = 0;
            switch (rental.getRentalItem().getPriceCode()) {
                case RentalItem.REGULAR:
                    amount += 2;
                    if (rental.getDaysRented() > 2)
                        amount += (rental.getDaysRented() - 2) * 1.5;
                    break;
                case RentalItem.NEW_RELEASE:
                    amount += rental.getDaysRented() * 3;
                    break;
                case RentalItem.CHILDREN:
                    amount += 1.5;
                    if (rental.getDaysRented() > 3)
                        amount += (rental.getDaysRented() - 3) * 1.5;
                    break;
            }
            
            totalAmount += amount;
        }        
        return new Bill(totalAmount);
    }

    public Iterable<Rental> getRentalsInPeriod(Customer customer, Object billingPeriod) {
        return null;
    }
}

package p2.service;

import p2.domain.Bill;
import p2.domain.Customer;
import p2.domain.Rental;
import p2.domain.RentalItem;


public class RentalService {

    private ItemService itemService;

    public RentalService(ItemService itemService){

        this.itemService = itemService;
    }
    
    public Bill getCustomerBill(Customer customer, Object billingPeriod) {
        int frequentRenterPoints = customer.getFrequentRenterPoints();
        double totalAmount = 0;
        for (Rental rental : getRentalsInPeriod(customer,billingPeriod)) {
            double amount = 0;
            RentalItem rentalItem = itemService.getItem(rental);
            switch (rentalItem.getPriceCode()) {
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

            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if (rentalItem.getPriceCode() == RentalItem.NEW_RELEASE && rental.getDaysRented() > 1)
                frequentRenterPoints++;

            
            totalAmount += amount;
        }
        customer.saveRenterPoints(frequentRenterPoints);        
        return new Bill(totalAmount);
    }

    private Iterable<? extends Rental> getRentalsInPeriod(Customer customer, Object billingPeriod) {
        return null;
    }
}

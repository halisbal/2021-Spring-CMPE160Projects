
package question;

public class Bill {

	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	
	// Creating Variables for Bill Class
	
	private double currentDebt = 0;
	double customerTotalAmount = 0;
	private double limitingAmount;
	// Creating Bill Constructor
	Bill(double limitingAmount) {
		this.limitingAmount = limitingAmount;
	}
	// Boolean check to know if customer exceeded the limiting Amount or not
	public boolean check(double amount) {
		if(amount + currentDebt > limitingAmount) {
			return false;
		}
		return true;
	}
	// Add Method for bills
	public void add(double amount) {
		currentDebt += amount;
	}
	// Method for paying the bill
	public void pay(double amount) {
		if(amount >= currentDebt) {
			customerTotalAmount += currentDebt;
			currentDebt = 0;
		}
		else {
			customerTotalAmount += amount;
			currentDebt -= amount;
		}
		
	}
	// Method for changing the limit manually for customer
	public void changeTheLimit(double amount) {
        if(amount > currentDebt) {
            limitingAmount = amount;
        }
    }
	// Getter methods for limiting amount and current debt
	public double getLimitingAmount() {
		return limitingAmount;
	}
	public double getCurrentDebt() {
		return currentDebt;
	}
	
	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}


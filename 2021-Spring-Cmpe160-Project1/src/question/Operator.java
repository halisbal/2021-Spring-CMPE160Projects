
package question;

public class Operator {
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	
	// Creating Variables
	
	double operatorTalkTime = 0;
	double operatorMessageQuantity = 0;
	double operatorInternetUse = 0;
	int ID;
	private double talkingCharge;
	private double messageCost;
	private double networkCharge;
	private int discountRate;
	
	// Creating Operator Constructor
	
	public Operator(int ID, double talkingCharge,double messageCost, double networkCharge, int discountRate) {
		this.discountRate = discountRate;
		this.ID = ID;
		this.talkingCharge = talkingCharge;
		this.networkCharge = networkCharge;
		this.messageCost = messageCost;
	}
	
	// Talking Cost Calculator Method
	
	public double calculateTalkingCost(int minute, Customer customer) {
		if(customer.getAge() < 18 || customer.getAge() > 65) {
			return minute*talkingCharge*((100-(double)discountRate)/100);
        }
		else {
            return minute*talkingCharge;
		}
	}
	// Message Cost Calculator Method
	public double calculateMessageCost(int quantity, Customer customer, Customer other) {
		if(customer.getOperator() == other.getOperator()) {
			return quantity*messageCost*((100-(double)discountRate)/100);
		}
		else {
			return quantity*messageCost;
		}
	}
	
	// Network Cost Calculator Method
	
	public double calculateNetworkCost(double amount) {
		return networkCharge*amount;
	}
	
	// Getter And Setter Methods for talking charge, message cost, network charge, discount rate
	
	/**
	 * @return the talkingCharge
	 */
	public double getTalkingCharge() {
		return talkingCharge;
	}
	/**
	 * @param talkingCharge the talkingCharge to set
	 */
	public void setTalkingCharge(double talkingCharge) {
		this.talkingCharge = talkingCharge;
	}
	/**
	 * @return the messageCost
	 */
	public double getMessageCost() {
		return messageCost;
	}
	/**
	 * @param messageCost the messageCost to set
	 */
	public void setMessageCost(double messageCost) {
		this.messageCost = messageCost;
	}
	/**
	 * @return the networkCharge
	 */
	public double getNetworkCharge() {
		return networkCharge;
	}
	/**
	 * @param networkCharge the networkCharge to set
	 */
	public void setNetworkCharge(double networkCharge) {
		this.networkCharge = networkCharge;
	}
	/**
	 * @return the discountRate
	 */
	public int getDiscountRate() {
		return discountRate;
	}
	/**
	 * @param discountRate the discountRate to set
	 */
	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}
	
	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}


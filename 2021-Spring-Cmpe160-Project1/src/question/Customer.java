
package question;

public class Customer {
	
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	
	// Creating Variables
	
	int customerTalkingTime = 0;
	private int ID;
	private String name;
	private int age;
	int customerMessageAmount = 0;
	private Operator operator;
	double networkUse = 0;
	private Bill bill;
	// Creating Customer Constructor
	public Customer(String name ,int ID, int age,double limitingAmount, Operator operator) {
		bill = new Bill(limitingAmount);
		this.ID = ID;
		this.operator = operator;
		this.name = name;
		this.age = age;
	}
	// Talk Method
	public void talk(int minute, Customer other) {
		if (this == other) {
		}
		else {
			bill.add(operator.calculateTalkingCost(minute, this));
			operator.operatorTalkTime += minute;
	        customerTalkingTime += minute;
	        other.getOperator().operatorTalkTime += minute;
	        other.customerTalkingTime += minute;
		}
		
    }
	// Message Method
	public void message(int quantity, Customer other) {
		if (this==other) {
		}
		else {
			customerMessageAmount += quantity;
			operator.operatorMessageQuantity += quantity;
			bill.add(operator.calculateMessageCost(quantity, this , other));
		}
	}
	// Network Connection Method
	public void connection(double amount) {
		networkUse += amount;
		operator.operatorInternetUse += amount;
		bill.add(operator.calculateNetworkCost(amount));
	}
	// Setter and Getters for name age operator
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public Operator getOperator() {
		return operator;
	}
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * @return the bill
	 */
	public Bill getBill() {
		return bill;
	}
	/**
	 * @param bill the bill to set
	 */
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	


	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}


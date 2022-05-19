package elements;
/**
 * An abstract class to create new orders and apply methods on them
 * @author Halis
 *
 */
public abstract class Order {
	private double amount;
	private double price;
	private int traderID;
	/**
	 * Order constructor to create a new order object
	 * @param traderID
	 * @param amount
	 * @param price
	 */
	public Order(int traderID, double amount, double price) {
		this.traderID = traderID;
		this.amount = amount;
		this.price = price;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @return the traderID
	 */
	public int getTraderID() {
		return traderID;
	}
	
	
}

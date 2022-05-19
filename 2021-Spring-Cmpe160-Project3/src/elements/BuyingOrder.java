package elements;
/**
 * BuyingOrder class which is an extension of Order Class
 * @author Halis
 *
 */
public class BuyingOrder extends Order implements Comparable<BuyingOrder> {
	/**
	 * Constructor for BuyingOrders
	 * @param traderID
	 * @param amount
	 * @param price
	 */
	public BuyingOrder(int traderID, double amount, double price) {
		super(traderID,amount,price);
	}
	/**
	 * CompareTo Method for Buying order priority queue
	 */
	@Override
	public int compareTo(BuyingOrder e) {
		if(e.getPrice() != this.getPrice()) {
			return (int)(e.getPrice()-this.getPrice());
		}
		else {
			if(this.getAmount() != e.getAmount()) {
				return (int)(e.getAmount()-this.getAmount());
			}
			else {
				return (int)(this.getTraderID()-e.getTraderID());
			}
		}
	}
}

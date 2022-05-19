package elements;
/**
 * SellingOrder class which is an extension of Order Class
 * @author Halis
 *
 */
public class SellingOrder extends Order implements Comparable<SellingOrder> {
	/**
	 * Constructor for SellingOrders
	 * @param traderID
	 * @param amount
	 * @param price
	 */
	public SellingOrder(int traderID, double amount, double price) {
		super(traderID,amount,price);
	}
	/**
	 * compareTo method for SellingOrder priority queue
	 */
	@Override
	public int compareTo(SellingOrder e) {
		if(e.getPrice() != this.getPrice()) {
			return (int)(this.getPrice()-e.getPrice());
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

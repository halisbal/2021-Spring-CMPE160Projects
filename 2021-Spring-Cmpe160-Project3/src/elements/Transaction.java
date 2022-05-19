package elements;
/**
 * Transaction class to track all transactions in a market
 * @author Halis
 *
 */
public class Transaction {
	private SellingOrder sellingOrder;
	private BuyingOrder buyingOrder;
	/**
	 * Transaction constructor to create a new transaction object
	 * @param sellingOrder
	 * @param buyingOrder
	 */
	public Transaction(SellingOrder sellingOrder,BuyingOrder buyingOrder) {
		this.sellingOrder = sellingOrder;
		this.buyingOrder = buyingOrder;
	}
}

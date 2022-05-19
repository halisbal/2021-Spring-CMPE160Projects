package elements;
import java.util.ArrayList;
import java.util.PriorityQueue;
/**
 * Market class to create a new market object and apply methods
 * @author Halis
 *
 */
public class Market {
	private PriorityQueue<SellingOrder> sellingOrders;
	private PriorityQueue<BuyingOrder> buyingOrders;
	private ArrayList<Transaction> transactions;
	int fee;
	/**
	 * Market constructor to create a new market object
	 * @param fee
	 */
	public Market(int fee) {
		this.fee = fee;
		sellingOrders = new PriorityQueue<SellingOrder>();
		buyingOrders = new PriorityQueue<BuyingOrder>();
		transactions = new ArrayList<Transaction>();
	}
	/**
	 * A method to give selling orders
	 * @param order
	 */
	public void giveSellOrder(SellingOrder order) {
		sellingOrders.add(order);
	}
	/**
	 * A method to give buying orders
	 * @param order
	 */
	public void giveBuyOrder(BuyingOrder order) {
		buyingOrders.add(order);
	}
	/**
	 * A method to apply market operation
	 * @param price
	 */
	public void makeOpenMarketOperation(double price) {
	}
	/**
	 * Method that check if there is possible transaction after every other query
	 * @param traders
	 */
	public void checkTransactions(ArrayList<Trader> traders) {
		try {
			while(buyingOrders.peek().getPrice() >= sellingOrders.peek().getPrice()) {
				
				int buyingID = buyingOrders.peek().getTraderID();
				int sellingID = sellingOrders.peek().getTraderID();
				double sellingAmount = sellingOrders.peek().getAmount();
				double buyingAmount = buyingOrders.peek().getAmount();
				double sellingPrice = sellingOrders.peek().getPrice();
				double buyingPrice = buyingOrders.peek().getPrice();
				
				if(buyingAmount > sellingAmount) {
					traders.get(sellingID).getWallet().addDollars(sellingPrice*sellingAmount*(1-(double)fee/1000));
					traders.get(sellingID).getWallet().removeBlockedCoins(sellingAmount);
					
					traders.get(buyingID).getWallet().removeBlockedDollars(buyingPrice*sellingAmount);
					traders.get(buyingID).getWallet().addDollars((buyingPrice-sellingPrice)*sellingAmount);
					traders.get(buyingID).getWallet().addCoins(sellingAmount);
					BuyingOrder a1 = buyingOrders.poll();
					SellingOrder a2 = sellingOrders.poll();
					BuyingOrder dividedOrder = new BuyingOrder(buyingID,buyingAmount-sellingAmount,buyingPrice);
					buyingOrders.add(dividedOrder);
					Transaction newTransaction = new Transaction(a2,a1);
					transactions.add(newTransaction);
				}
				else if(buyingAmount < sellingAmount) {
					traders.get(buyingID).getWallet().removeBlockedDollars(buyingAmount*buyingPrice);
					traders.get(buyingID).getWallet().addCoins(buyingAmount);
					traders.get(buyingID).getWallet().addDollars(buyingAmount*(buyingPrice-sellingPrice));
					
					traders.get(sellingID).getWallet().removeBlockedCoins(buyingAmount);
					traders.get(sellingID).getWallet().addDollars(buyingAmount*sellingPrice*(1-(double)fee/1000));
					
					BuyingOrder a1 = buyingOrders.poll();
					SellingOrder a2 = sellingOrders.poll();
					SellingOrder dividedOrder2 = new SellingOrder(sellingID,sellingAmount - buyingAmount,sellingPrice);
					sellingOrders.add(dividedOrder2);
					Transaction newTransaction = new Transaction(a2,a1);
					transactions.add(newTransaction);
				}
				else {
					traders.get(buyingID).getWallet().removeBlockedDollars(buyingPrice*sellingAmount);
					traders.get(buyingID).getWallet().addDollars((buyingPrice-sellingPrice)*sellingAmount);
					traders.get(buyingID).getWallet().addCoins(sellingAmount);
					
					traders.get(sellingID).getWallet().addDollars(sellingPrice*sellingAmount*(1-(double)fee/1000));
					traders.get(sellingID).getWallet().removeBlockedCoins(sellingAmount);

					BuyingOrder a1 = buyingOrders.poll();
					SellingOrder a2 = sellingOrders.poll();
					Transaction newTransaction = new Transaction(a2,a1);
					transactions.add(newTransaction);
				}
			}
		}
		catch(Exception e) {
		}
	}
	/**
	 * @return the sellingOrders
	 */
	public PriorityQueue<SellingOrder> getSellingOrders() {
		return this.sellingOrders;
	}
	/**
	 * @return the buyingOrders
	 */
	public PriorityQueue<BuyingOrder> getBuyingOrders() {
		return this.buyingOrders;
	}
	/**
	 * @return the transactions
	 */
	public ArrayList<Transaction> getTransactions() {
		return this.transactions;
	}
	/**
	 * @return the fee
	 */
	public int getFee() {
		return fee;
	}
	
	
}

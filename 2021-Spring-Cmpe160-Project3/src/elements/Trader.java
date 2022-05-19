package elements;
/**
 * Trader class to create new trader object and apply some methods on them
 * @author Halis
 *
 */
public class Trader {
	private int id;
	private Wallet wallet;
	/**
	 * Trader constructor to create a new trader object
	 * @param dollars
	 * @param coins
	 */
	public Trader(double dollars, double coins) {
		this.id = numberOfUsers;
		Wallet x = new Wallet(dollars,coins);
		this.wallet = x;
	}
	/**
	 * A method that we use when creating selling order
	 * @param amount
	 * @param price
	 * @param market
	 * @return
	 */
	public int sell(double amount, double price,Market market) {
		return 0;
	}
	/**
	 * A method that we use when creating buying order
	 * @param amount
	 * @param price
	 * @param market
	 * @return
	 */
	public int buy(double amount, double price, Market market) {
		return 0;
	}
	/**
	 * numberOfUsers parameter to track trader id's
	 */
	public static int numberOfUsers = 0;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the wallet
	 */
	public Wallet getWallet() {
		return wallet;
	}
	
	
}

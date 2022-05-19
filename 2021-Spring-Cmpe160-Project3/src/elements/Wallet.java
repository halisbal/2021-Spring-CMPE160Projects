package elements;
/**
 * Wallet class that we use when creating new wallet object and apply some methods on them
 * @author Halis
 *
 */
public class Wallet {
	private double dollars;
	private double coins;
	private double blockedDollars;
	private double blockedCoins;
	/**
	 * Wallet constructor to create new wallet objects
	 * @param dollars
	 * @param coins
	 */
	public Wallet(double dollars, double coins) {
		this.dollars = dollars;
		this.coins = coins;
	}
	/**
	 * A deposit method that takes the amount and adds to the customer wallet
	 * @param amount
	 */
	public void deposit(double amount) {
		this.dollars += amount;
	}
	/**
	 * A withdraw method that takes the amount and removes from the customer's wallet if there is enough money in the wallet
	 * @param amount
	 * @return
	 */
	public boolean withdraw(double amount) {
		if (this.dollars >= amount) {
			this.dollars -= amount;
			return true;
		}
		return false;
	}
	/**
	 * A method that add coins to wallet
	 * @param amount
	 */
	public void addCoins(double amount) {
		this.coins += amount;
	}
	/**
	 * A method that removes coin from a wallet
	 * @param amount
	 */
	public void removeCoins(double amount) {
		this.coins -= amount;
	}
	/**
	 * A method that add blocked coins to a wallet
	 * @param amount
	 */
	public void addBlockedCoins(double amount) {
		this.blockedCoins += amount;
	}
	/**
	 * A method that removes blocked coins from a wallet
	 * @param amount
	 */
	public void removeBlockedCoins(double amount) {
		this.blockedCoins -= amount;
	}
	/**
	 * A method that add dollars to a wallet
	 * @param amount
	 */
	public void addDollars(double amount) {
		this.dollars += amount;
	}
	/**
	 * A method that removes dollars from a wallet
	 * @param amount
	 */
	public void removeDollars(double amount) {
		this.dollars -= amount;
	}
	/**
	 * A method that adds blocked dollars to a wallet
	 * @param amount
	 */
	public void addBlockedDollars(double amount) {
		this.blockedDollars += amount;
	}
	/**
	 * A method that removes blocked dollars from a wallet
	 * @param amount
	 */
	public void removeBlockedDollars(double amount) {
		this.blockedDollars -= amount;
	}

	/**
	 * @return the dollars
	 */
	public double getDollars() {
		return dollars;
	}

	/**
	 * @return the coins
	 */
	public double getCoins() {
		return coins;
	}

	/**
	 * @param dollars the dollars to set
	 */
	public void setDollars(double dollars) {
		this.dollars = dollars;
	}

	/**
	 * @param coins the coins to set
	 */
	public void setCoins(double coins) {
		this.coins = coins;
	}

	/**
	 * @return the blockedDollars
	 */
	public double getBlockedDollars() {
		return blockedDollars;
	}

	/**
	 * @return the blockedCoins
	 */
	public double getBlockedCoins() {
		return blockedCoins;
	}

	/**
	 * @param blockedDollars the blockedDollars to set
	 */
	public void setBlockedDollars(double blockedDollars) {
		this.blockedDollars = blockedDollars;
	}

	/**
	 * @param blockedCoins the blockedCoins to set
	 */
	public void setBlockedCoins(double blockedCoins) {
		this.blockedCoins = blockedCoins;
	}
	
}

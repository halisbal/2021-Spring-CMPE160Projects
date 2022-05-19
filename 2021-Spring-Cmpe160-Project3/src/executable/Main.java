package executable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import elements.*;
import java.util.Random;
public class Main {
	public static Random myRandom;
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner input = new Scanner(new File(args[0]));
		PrintStream output = new PrintStream(new File(args[1]));
		ArrayList<Trader> traderArray = new ArrayList<Trader>();
		int A = Integer.parseInt(input.nextLine());
		myRandom = new Random(A);
		int invalidQuery = 0;
		String x = input.nextLine();
		String[] y = x.split(" ");
		int B = Integer.parseInt(y[0]);
		Market market = new Market(B);
		int C = Integer.parseInt(y[1]);
		int D = Integer.parseInt(y[2]);
		for(int a = 0 ; a<C ; a++) {
			String inputLine = input.nextLine();
			String[] a1 = inputLine.split(" ");
			double dollar_amount = Double.parseDouble(a1[0]);
			double PQoin_amount = Double.parseDouble(a1[1]);

			Trader newTrader = new Trader(dollar_amount,PQoin_amount);
			traderArray.add(newTrader);
			Trader.numberOfUsers ++;
		}
		traderArray.get(0).getWallet().setCoins(Double.MAX_VALUE);
		traderArray.get(0).getWallet().setDollars(Double.MAX_VALUE);
		while(input.hasNextLine()) {
			String inputLine = input.nextLine();
			//buying order given price
			if(inputLine.startsWith("10 ")) {
				String[] a2 = inputLine.split(" ");
				
				int trader_id = Integer.parseInt(a2[1]);
				double pricex = Double.parseDouble(a2[2]);
				double amountx = Double.parseDouble(a2[3]);
				try {
					if (traderArray.get(trader_id).getWallet().getDollars() >= pricex*amountx && amountx != 0)  {
						BuyingOrder newOrder = new BuyingOrder(trader_id,amountx,pricex);
						market.giveBuyOrder(newOrder);
						traderArray.get(trader_id).getWallet().removeDollars(pricex*amountx);
						traderArray.get(trader_id).getWallet().addBlockedDollars(pricex*amountx);  
					}
					else {
						invalidQuery++;
					}
				}
				catch(Exception e) {
					invalidQuery++;
				}
			}
			//buying order market price
			if(inputLine.startsWith("11 ")) {
				String[] a3 = inputLine.split(" ");
				
				int trader_id = Integer.parseInt(a3[1]);
				double amountx = Double.parseDouble(a3[2]);
				try {
					if (traderArray.get(trader_id).getWallet().getDollars() >= market.getSellingOrders().peek().getPrice()*amountx && amountx != 0)  {
						BuyingOrder newOrder = new BuyingOrder(trader_id,amountx,market.getSellingOrders().peek().getPrice());
						market.giveBuyOrder(newOrder);
						traderArray.get(trader_id).getWallet().removeDollars(market.getSellingOrders().peek().getPrice()*amountx);
						traderArray.get(trader_id).getWallet().addBlockedDollars(market.getSellingOrders().peek().getPrice()*amountx);    
					}
					else {
						invalidQuery++;
					}
				}
				catch(Exception e) {
					invalidQuery++;
				}
			}
			//selling order given price
			if(inputLine.startsWith("20 ")) {
				String[] a4 = inputLine.split(" ");
				
				int trader_id = Integer.parseInt(a4[1]);
				double pricex = Double.parseDouble(a4[2]);
				double amountx = Double.parseDouble(a4[3]);
				
				try {
					if (traderArray.get(trader_id).getWallet().getCoins() >= amountx && amountx != 0) {
						SellingOrder newOrder = new SellingOrder(trader_id,amountx,pricex);
						market.giveSellOrder(newOrder);
						traderArray.get(trader_id).getWallet().removeCoins(amountx);
						traderArray.get(trader_id).getWallet().addBlockedCoins(amountx);
					}
					else {
						invalidQuery++;
					}
				}
				catch(Exception e) {
					invalidQuery++;
				}
			}
			//selling order market price
			if(inputLine.startsWith("21 ")) {
				String[] a5 = inputLine.split(" ");
				
				int trader_id = Integer.parseInt(a5[1]);
				double amountx = Double.parseDouble(a5[2]);
				
				try {
					if (traderArray.get(trader_id).getWallet().getCoins() >= amountx && amountx != 0) {
						SellingOrder newOrder = new SellingOrder(trader_id,amountx,market.getBuyingOrders().peek().getPrice());
						market.giveSellOrder(newOrder);
						traderArray.get(trader_id).getWallet().removeCoins(amountx);
						traderArray.get(trader_id).getWallet().addBlockedCoins(amountx);
					}
					else {
						invalidQuery++;
					}
				}
				catch(Exception e) {
					invalidQuery++;
				}
			}
			//deposit
			if(inputLine.startsWith("3 ")) {
				String[] a6 = inputLine.split(" ");
				
				int trader_id = Integer.parseInt(a6[1]);
				double amountx = Double.parseDouble(a6[2]);
				
				traderArray.get(trader_id).getWallet().deposit(amountx);
			}
			//withdraw
			if(inputLine.startsWith("4 ")) {
				String[] a7 = inputLine.split(" ");
				
				int trader_id = Integer.parseInt(a7[1]);
				double amountx = Double.parseDouble(a7[2]);
				
				boolean aa = traderArray.get(trader_id).getWallet().withdraw(amountx);
				if(aa == false) {
					invalidQuery++;
				}
			}
			//print trader's wallet status
			
			if(inputLine.startsWith("5 ")) {
				String[] a8 = inputLine.split(" ");
				
				int trader_id = Integer.parseInt(a8[1]);
				output.print("Trader " + trader_id + ": ");
				output.printf("%.5f",traderArray.get(trader_id).getWallet().getDollars()+traderArray.get(trader_id).getWallet().getBlockedDollars());
				output.print("$ ");
				output.printf("%.5f", traderArray.get(trader_id).getWallet().getCoins()+traderArray.get(trader_id).getWallet().getBlockedCoins());
				output.print("PQ");
				output.println();
			}
			// rewards to traders
			if(inputLine.startsWith("777")) {
				for(Trader trader:traderArray) {
					trader.getWallet().setCoins(trader.getWallet().getCoins()+myRandom.nextDouble()*10);
				}
			}
			// open market operation
			if(inputLine.startsWith("666")) {
				String[] a9 = inputLine.split(" ");
				double price = Double.parseDouble(a9[1]);
				market.makeOpenMarketOperation(price);
				try {
					double cSellPrice = market.getBuyingOrders().peek().getPrice();
					double cBuyPrice = market.getSellingOrders().peek().getPrice();
					
					if (cSellPrice >= price) {
						while(market.getBuyingOrders().peek() != null && market.getBuyingOrders().peek().getPrice() >= price) {
							BuyingOrder x1 = market.getBuyingOrders().poll();
							SellingOrder xx = new SellingOrder(0,x1.getAmount(),x1.getPrice());
							market.giveSellOrder(xx);
							Transaction yy = new Transaction(xx,x1);
							market.getTransactions().add(yy);
							market.getSellingOrders().poll();
							traderArray.get(x1.getTraderID()).getWallet().removeBlockedDollars(x1.getAmount()*x1.getPrice());
							traderArray.get(x1.getTraderID()).getWallet().addCoins(x1.getAmount());
						}
					}
					else if (cBuyPrice <= price) {
						while(market.getBuyingOrders().peek() != null && market.getSellingOrders().peek().getPrice() <= price) {
							SellingOrder x2 = market.getSellingOrders().poll();
							BuyingOrder xxx = new BuyingOrder(0,x2.getAmount(),x2.getPrice());
							market.giveBuyOrder(xxx);
							Transaction zz = new Transaction(x2,xxx);
							market.getTransactions().add(zz);
							market.getBuyingOrders().poll();
							traderArray.get(x2.getTraderID()).getWallet().removeBlockedCoins(x2.getAmount());
							traderArray.get(x2.getTraderID()).getWallet().addDollars(x2.getAmount()*x2.getPrice()*(1-(double)market.getFee()/1000));
						}
					}
				}
				catch(Exception e) {
					if (market.getBuyingOrders().peek() == null && market.getSellingOrders().peek() != null) {
						double cBuyPrice = market.getSellingOrders().peek().getPrice();
						if (cBuyPrice <= price) {
							while(market.getSellingOrders().peek() != null && market.getSellingOrders().peek().getPrice() <= price) {
								SellingOrder x2 = market.getSellingOrders().poll();
								BuyingOrder xxx = new BuyingOrder(0,x2.getAmount(),x2.getPrice());
								market.giveBuyOrder(xxx);
								Transaction zz = new Transaction(x2,xxx);
								market.getTransactions().add(zz);
								market.getBuyingOrders().poll();
								traderArray.get(x2.getTraderID()).getWallet().removeBlockedCoins(x2.getAmount());
								traderArray.get(x2.getTraderID()).getWallet().addDollars(x2.getAmount()*x2.getPrice()*(1-(double)market.getFee()/1000));
							}
						}
					}
					else if (market.getSellingOrders().peek() == null && market.getBuyingOrders().peek() != null) {
						double cSellPrice = market.getBuyingOrders().peek().getPrice();
						if (cSellPrice >= price) {
							while(market.getBuyingOrders().peek() != null && market.getBuyingOrders().peek().getPrice() >= price) {
								BuyingOrder x1 = market.getBuyingOrders().poll();
								SellingOrder xx = new SellingOrder(0,x1.getAmount(),x1.getPrice());
								market.giveSellOrder(xx);
								Transaction yy = new Transaction(xx,x1);
								market.getTransactions().add(yy);
								market.getSellingOrders().poll();
								traderArray.get(x1.getTraderID()).getWallet().removeBlockedDollars(x1.getAmount()*x1.getPrice());
								traderArray.get(x1.getTraderID()).getWallet().addCoins(x1.getAmount());
							}
						}
					}
					else {
					}
				}

			}
			
			//Prints market size

			if(inputLine.startsWith("500")) {
				double dollarAmount = 0;
				double coinAmount = 0;
				try {
					for(BuyingOrder aa : market.getBuyingOrders()) {
						dollarAmount += aa.getPrice()*aa.getAmount();
					}
				}
				catch(Exception e) {
				}
				try {
					for(SellingOrder bb : market.getSellingOrders()) {
						coinAmount += bb.getAmount();
					}
				}
				catch(Exception e) {
				}
				output.print("Current market size: ");
				output.printf("%.5f",dollarAmount);
				output.print(" ");
				output.printf("%.5f",coinAmount);
				output.println();
			}
			//Prints successful transactions
			
			if(inputLine.startsWith("501")) {
				int xx = 0;
				try {
					xx = market.getTransactions().size();
				}
				catch(Exception e) {
				}
				output.println("Number of successful transactions: " + xx);
			}
			//Prints invalid queries

			if(inputLine.startsWith("502")) {
				output.println("Number of invalid queries: " + invalidQuery);
			}
			
			//Prints current prices
			
			if(inputLine.startsWith("505")) {
				double cpBuy = 0;
				double cpSell = 0;
				double cp_average = 0;
				try {
					if (market.getSellingOrders().size() != 0) {
						cpBuy = market.getSellingOrders().peek().getPrice();
					}
				}
				catch(Exception e) {
				}
				try {
					if (market.getBuyingOrders().size() != 0) {
						cpSell = market.getBuyingOrders().peek().getPrice();
					}
				}
				catch(Exception e) {
				}
				if (cpBuy != 0 && cpSell != 0) {
					cp_average = (cpBuy + cpSell)/2;
				}
				else if(cpBuy == 0) {
					cp_average = cpSell;
				}
				else {
					cp_average = cpBuy;
				}

				
				output.print("Current prices: ");
				output.printf("%.5f",cpSell);
				output.print(" ");
				output.printf("%.5f",cpBuy);
				output.print(" ");
				output.printf("%.5f",cp_average);
				output.println();
			}
			
			//Prints all wallets' status
			
			if(inputLine.startsWith("555")) {
				for(Trader xxx : traderArray) {
					if(xxx.getId() == 0) {
						output.println("Trader 0: 0.00000$ 0.00000PQ");
					}
					else {
						output.print("Trader " + xxx.getId() + ": ");
						output.printf("%.5f",xxx.getWallet().getDollars()+xxx.getWallet().getBlockedDollars());
						output.print("$ ");
						output.printf("%.5f",xxx.getWallet().getCoins()+xxx.getWallet().getBlockedCoins());
						output.println("PQ");
					}

				}
			}
			market.checkTransactions(traderArray);
		}
	}
}




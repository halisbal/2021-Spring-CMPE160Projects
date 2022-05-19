
package question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {


	public static void main(String args[]) {

		Customer[] customers;
		Operator[] operators;

		int C, O, N;

		File inFile = new File(args[0]);  // args[0] is the input file
		File outFile = new File(args[1]);  // args[1] is the output file
		try {
			PrintStream outstream = new PrintStream(outFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		Scanner reader;
		try {
			reader = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find input file");
			return;
		}

		C = reader.nextInt();
		O = reader.nextInt();
		N = reader.nextInt();

		customers = new Customer[C];
		operators = new Operator[O];

		//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
		
		// Creating Outstream1
		
		PrintStream outstream1;
		try {
		        outstream1 = new PrintStream(outFile);
		}catch(FileNotFoundException e2) {
		        e2.printStackTrace();
		        return;
		}
		// Initializing cusNum and opNum variables in order to calculate and track customer and operator ID
		int cusNum = 0;
		int opNum = 0;
		// While operator that reads the next line until there is not, and initializes the necessary action after every line
		while(reader.hasNextLine()) {
			String row = reader.nextLine();

			if(row.startsWith("1")) {
				String[] x = row.split(" ");
				customers[cusNum] = new Customer(x[1],cusNum,Integer.parseInt(x[2]),Double.parseDouble(x[4]),operators[Integer.parseInt(x[3])]);
				cusNum ++;
			}
			if(row.startsWith("2")) {
				String[] y = row.split(" ");
				operators[opNum] = new Operator(opNum,Double.parseDouble(y[1]),Double.parseDouble(y[2]),Double.parseDouble(y[3]),Integer.parseInt(y[4]));
				opNum++;
			}
			if(row.startsWith("3")) {
				String[] z = row.split(" ");
				if(customers[Integer.parseInt(z[1])].getBill().check(customers[Integer.parseInt(z[1])].getOperator().calculateTalkingCost(Integer.parseInt(z[3]), customers[Integer.parseInt(z[1])]))) {
					customers[Integer.parseInt(z[1])].talk(Integer.parseInt(z[3]), customers[Integer.parseInt(z[2])]);
				} 
			}
			if(row.startsWith("4")) {
				String[] t = row.split(" ");
				if(customers[Integer.parseInt(t[1])].getBill().check(customers[Integer.parseInt(t[1])].getOperator().calculateMessageCost(Integer.parseInt(t[3]),customers[Integer.parseInt(t[1])],customers[Integer.parseInt(t[2])]))) {
					customers[Integer.parseInt(t[1])].message(Integer.parseInt(t[3]), customers[Integer.parseInt(t[2])]);
				}
			}
			if(row.startsWith("5")) {
				String[] i = row.split(" ");
				if(customers[Integer.parseInt(i[1])].getBill().check(customers[Integer.parseInt(i[1])].getOperator().calculateNetworkCost(Double.parseDouble(i[2])))){
					customers[Integer.parseInt(i[1])].connection(Double.parseDouble(i[2]));
				}
			}
			if(row.startsWith("6")) {
				String[] p = row.split(" ");
				customers[Integer.parseInt(p[1])].getBill().pay(Double.parseDouble(p[2]));	 
			}
			if(row.startsWith("7")) {
				String[] k = row.split(" ");
				customers[Integer.parseInt(k[1])].setOperator(operators[Integer.parseInt(k[2])]);
			}
			if(row.startsWith("8")) {
				String[] l = row.split(" ");
				customers[Integer.parseInt(l[1])].getBill().changeTheLimit(Double.parseDouble(l[2]));
			}
		}
		reader.close();
		// Outstream Printer for operators
		for(Operator tempOp : operators) {
			outstream1.printf("Operator"+" ");
			outstream1.printf(tempOp.ID + " : ");
			outstream1.printf((int)tempOp.operatorTalkTime + " " + (int)tempOp.operatorMessageQuantity + " ");
			outstream1.printf("%.2f%n",tempOp.operatorInternetUse);
		}
		// Outstream Printer for customers
		for(Customer tempCus : customers) {
			outstream1.print("Customer" + " ");
			outstream1.print(tempCus.getID()+" : "); 
			outstream1.printf("%.2f",tempCus.getBill().customerTotalAmount);
			outstream1.printf(" "+"%.2f%n",tempCus.getBill().getCurrentDebt());
		}
		// Outstream Printer for Most Talked Customer
		int[] a = new int[customers.length];
		for (int i = 0 ; i < customers.length ; i++) {
			a[i] = customers[i].customerTalkingTime;
		}
		int max = a[0];
		String tempName = customers[0].getName();
		for(int i = 1;i < a.length ; i++) {
			if (a[i]>max) {
				max = a[i];
				tempName = customers[i].getName();
			}
		}
		outstream1.println(tempName + " : " + max);	
		// Outstream Printer for Most Messaged Customer
		int[] b = new int[customers.length];
		for (int i = 0 ; i < customers.length ; i++) {
			b[i] = customers[i].customerMessageAmount;
		}
		int max2 = b[0];
		String tempName2 = customers[0].getName();
		for(int i = 1;i < b.length ; i++) {
			if (b[i]>max2) {
				max2 = b[i];
				tempName2 = customers[i].getName();
			}
		}
		outstream1.println(tempName2+" : "+max2);
		// Outstream Printer for most NetworkUsed Customer
		double[] c = new double[customers.length];
		for (int i = 0 ; i < customers.length ; i++) {
			c[i] = customers[i].networkUse;
		}
		double max3 = c[0];
		String tempName3 = customers[0].getName();
		for(int i = 1;i < c.length ; i++) {
			if (c[i]>max3) {
				max3 = c[i];
				tempName3 = customers[i].getName();
			}
		}
		outstream1.print(tempName3+ " : "); 
		outstream1.printf("%.2f",max3);
		outstream1.close();
		//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
	} 
}


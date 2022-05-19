
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import ships.Ship;
import ports.Port;
import containers.*;
public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		
		//
		// Main receives two arguments: path to input file and path to output file.
		// You can assume that they will always be provided, so no need to check them.
		// Scanner and PrintStream are already defined for you.
		// Use them to read input and write output.
		// 
		// Good Luck!
		// 
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		ArrayList<Container> contArray = new ArrayList<Container>();
		ArrayList<Ship> shipArray = new ArrayList<Ship>();
		ArrayList<Port> portArray = new ArrayList<Port>();
		int contNum = 0;
		int portNum = 0;
		int shipNum = 0;
		int N = in.nextInt();
		while(in.hasNextLine()) {
			String inputLine = in.nextLine();
			
			if(inputLine.startsWith("1")) {
				String[] a1 = inputLine.split(" ");
				try {
					if(a1[3].equals("R")) {
						Container cont = new RefrigeratedContainer(contNum,Integer.parseInt(a1[2]));
						contArray.add(cont);
						portArray.get(Integer.parseInt(a1[1])).newContainer(cont);
						contNum++;
					} else {
					Container cont = new LiquidContainer(contNum,Integer.parseInt(a1[2]));
					contArray.add(cont);
					portArray.get(Integer.parseInt(a1[1])).newContainer(cont);
					contNum++;
					}
				}
				catch(Exception e) {
					if(Integer.parseInt(a1[2]) > 3000) {
						Container cont = new HeavyContainer(contNum,Integer.parseInt(a1[2]));
						contArray.add(cont);
						portArray.get(Integer.parseInt(a1[1])).newContainer(cont);
						contNum++;
					} else {
					Container cont = new BasicContainer(contNum,Integer.parseInt(a1[2]));
					contArray.add(cont);
					portArray.get(Integer.parseInt(a1[1])).newContainer(cont);
					contNum++;
					}
				}
					
			}
			
			if(inputLine.startsWith("2")) {
				String[] a2 = inputLine.split(" ");
				
				int initPortID = Integer.parseInt(a2[1]);
				int maximumWeight = Integer.parseInt(a2[2]);
				int maximumCont = Integer.parseInt(a2[3]);
				int maximumHeavyCont = Integer.parseInt(a2[4]);
				int maximumRefCont = Integer.parseInt(a2[5]);
				int maximumLiqCont = Integer.parseInt(a2[6]);
				double fuelConsumptionPerKm = Double.parseDouble(a2[7]);
				
				Ship newShip = new Ship(shipNum,portArray.get(initPortID),maximumWeight,maximumCont,maximumHeavyCont,maximumRefCont,maximumLiqCont,fuelConsumptionPerKm);
				shipArray.add(newShip);
				portArray.get(initPortID).incomingShip(newShip);
				shipNum++;
			}
			if(inputLine.startsWith("3")) {
				String[] a3 = inputLine.split(" ");
				
				Port newPort = new Port(portNum,Double.parseDouble(a3[1]), Double.parseDouble(a3[2]));
				portArray.add(newPort);
				portNum++;
			}
			
			if(inputLine.startsWith("4")) {
				String[] a4 = inputLine.split(" ");
				
				int shipID = Integer.parseInt(a4[1]);
				int contID = Integer.parseInt(a4[2]);
				if(shipArray.get(shipID).load(contArray.get(contID))) {
				}
			}
			
			if(inputLine.startsWith("5")) {
				String[] a5 = inputLine.split(" ");
				
				int shipID = Integer.parseInt(a5[1]);
				int contID = Integer.parseInt(a5[2]);
				
				if(shipArray.get(shipID).unLoad(contArray.get(contID))) {
					;
				}
			}
			
			if(inputLine.startsWith("6")) {
				String[] a6 = inputLine.split(" ");
				shipArray.get(Integer.parseInt(a6[1])).sailTo(portArray.get(Integer.parseInt(a6[2])));
			}
			
			if(inputLine.startsWith("7")) {
				String[] a7 = inputLine.split(" ");
				
				shipArray.get(Integer.parseInt(a7[1])).reFuel(Double.parseDouble(a7[2]));
			}
		}
		
		for(Port port : portArray) {
			out.print("Port ");
			out.print(port.getID());
			out.print(": (");
			out.printf ("%.2f" , port.getX());
			out.print(", ");
			out.printf("%.2f" , port.getY());
			out.println(")");
			
			if(port.currentBasicContainers().size() != 0) {
				out.print("  BasicContainer:");
				
				for(Container a : port.currentBasicContainers()) {
					out.print(" ");
					out.print(a.getID());
				}
				out.println();
			}
			if(port.currentHeavyContainers().size() != 0) {
				out.print("  HeavyContainer:");
				
				for(Container a : port.currentHeavyContainers()) {
					out.print(" ");
					out.print(a.getID());
				}
				out.println();
			}
			if(port.currentRefrigeratedContainers().size() != 0) {
				out.print("  RefrigeratedContainer:");
				
				for(Container a : port.currentRefrigeratedContainers()) {
					out.print(" ");
					out.print(a.getID());
				}
				out.println();
			}
			if(port.currentLiquidContainers().size() != 0) {
				out.print("  LiquidContainer:");
				
				for(Container a : port.currentLiquidContainers()) {
					out.print(" ");
					out.print(a.getID());
				}
				out.println();
			}
			
			for(int a = 0 ; a<port.getCurrent().size() ; a++ ) {
				for(int e = 0 ; e < port.getCurrent().size() - a - 1;e++) {
					if(port.getCurrent().get(e).getID() >port.getCurrent().get(e+1).getID()) {
						Ship temp = port.getCurrent().get(e);
						port.getCurrent().set(e, port.getCurrent().get(e+1));
						port.getCurrent().set(e+1, temp);
					}
				}
			}
			
			
			for(Ship outShip : port.getCurrent()) {
				out.print("  Ship " + outShip.getID() + ": ");
				out.printf("%.2f",outShip.getFuel());
				out.println();
				if(outShip.currentBasicContainers().size() != 0) {
					out.print("    BasicContainer:");
					for(Container a : outShip.currentBasicContainers()) {
						out.print(" ");
						out.print(a.getID());
					}
					out.println();
				}
				if(outShip.currentHeavyContainers().size() != 0) {
					out.print("    HeavyContainer:");
					for(Container a : outShip.currentHeavyContainers()) {
						out.print(" ");
						out.print(a.getID());
					}
					out.println();
				}
				if(outShip.currentRefrigeratedContainers().size() != 0) {
					out.print("    RefrigeratedContainer:");
					for(Container a : outShip.currentRefrigeratedContainers()) {
						out.print(" ");
						out.print(a.getID());
					}
					out.println();
				}
				if(outShip.currentLiquidContainers().size() != 0) {
					out.print("    LiquidContainer:");
					for(Container a : outShip.currentLiquidContainers()) {
						out.print(" ");
						out.print(a.getID());
					}
					out.println();
				}
			}
		}
		
		in.close();
		out.close();
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE


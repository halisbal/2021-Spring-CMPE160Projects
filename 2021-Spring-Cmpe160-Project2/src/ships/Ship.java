
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package ships;
import java.util.ArrayList;
import containers.Container;
import ports.Port;
/**
 * A class that includes Ships and implements IShip interface
 * @author Halis
 *
 */
public class Ship implements interfaces.IShip {
	private int ID;
	private double fuel;
	private Port currentPort;
	private ArrayList<Container> currentContainers = new ArrayList<Container>() ;
	private int totalWeightCapacity;
	private int maxNumberOfAllContainers;
	private int maxNumberOfHeavyContainers;
	private int maxNumberOfRefrigeratedContainers;
	private int maxNumberOfLiquidContainers;
	private double fuelConsumptionPerKM;
	
	/**
	 * A constructor to create a new ship
	 * @param ID ID of ship
	 * @param p Port that the ship is currently in
	 * @param totalWeightCapacity Maximum Weight Capacity that ship can handle
	 * @param maxNumberOfAllContainers Maximum Number Of Total Container capacity
	 * @param maxNumberOfHeavyContainers Maximum Number Of Heavy Container capacity
	 * @param maxNumberOfRefrigeratedContainers Maximum Number Of Refrigerated Container capacity
	 * @param maxNumberOfLiquidContainers Maximum Number Of Liquid Container capacity
	 * @param fuelConsumptionPerKM Fuel Consumption of ship per KM
	 */
	
	public Ship(int ID, Port p, int totalWeightCapacity, int maxNumberOfAllContainers, int maxNumberOfHeavyContainers,
			int maxNumberOfRefrigeratedContainers, int maxNumberOfLiquidContainers, double fuelConsumptionPerKM) {
		this.ID = ID;
		this.totalWeightCapacity = totalWeightCapacity;
		this.currentPort = p;
		p.getCurrent().add(this);
		this.maxNumberOfAllContainers = maxNumberOfAllContainers;
		this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
		this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
		this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
		this.fuelConsumptionPerKM = fuelConsumptionPerKM;
	}

	/**
	 * Returns fuel consumption of all containers in a ship per KM
	 * @return total fuel consumption
	 */
	public double fuelConsumption() {
		double a = this.fuelConsumptionPerKM;
		for(Container x: this.currentContainers) {
			if (x.getClass().equals(containers.HeavyContainer.class)) {
				a += 3.00*x.getWeight();
			}
			if (x.getClass().equals(containers.BasicContainer.class)) {
				a += 2.50*x.getWeight();
			}
			if (x.getClass().equals(containers.LiquidContainer.class)) {
				a += 4.00*x.getWeight();
			}
			if (x.getClass().equals(containers.RefrigeratedContainer.class)) {
				a += 5.00*x.getWeight();
			}
		}
		return a;
	}
	/**
	 * A method to add fuel to a ship
	 */
	public void reFuel(double newFuel) {
		fuel += newFuel;
	}
	/**
	 * A method that computes total weight in a ship
	 * @return total container weight
	 */
	public int weightInThisShip() {
		int weightShip = 0;
		for(Container container : currentContainers) {
			weightShip += container.getWeight();
		}
		return weightShip;
	}
	/**
	 * Amount of heavy container in a ship
	 * @return heavy container count
	 */
	int heavyCont() {
		int a = 0;
		for(Container b:currentContainers) {
			if(b.getClass().equals(containers.HeavyContainer.class)) {
				a++;
			}
		}
		return a;
	}
	/**
	 * Amount of basic container in a ship
	 * @return basic container count
	 */
	int basicCont() {
		int a = 0;
		for(Container b:currentContainers) {
			if(b.getClass().equals(containers.BasicContainer.class)) {
				a++;
			}
		}
		return a;
	}
	/**
	 * Amount of liquid container in a ship
	 * @return liquid container count
	 */
	int liquidCont() {
		int a = 0;
		for(Container b:currentContainers) {
			if(b.getClass().equals(containers.LiquidContainer.class)) {
				a++;
			}
		}
		return a;
	}
	/**
	 * Amount of refrigerated container in a ship
	 * @return refrigerated container count
	 */
	int refrigeratedCont() {
		int a = 0;
		for(Container b:currentContainers) {
			if(b.getClass().equals(containers.RefrigeratedContainer.class)) {
				a++;
			}
		}
		return a;
	}
	/**
	 * A method that determines if a container is able to load to a specific ship or not
	 */
	public boolean load(Container container) {
		if(this.weightInThisShip() + container.getWeight() > this.totalWeightCapacity) {
			return false;
		}
		if(container instanceof containers.HeavyContainer && heavyCont()+liquidCont()+refrigeratedCont() == this.maxNumberOfHeavyContainers ) {
			return false;
		}
		if(container.getClass().equals(containers.LiquidContainer.class) && liquidCont() == this.maxNumberOfLiquidContainers ) {
			return false;
		}
		if(container.getClass().equals(containers.RefrigeratedContainer.class) && refrigeratedCont() == this.maxNumberOfRefrigeratedContainers ) {
			return false;
		}
		if(currentContainers.size() == this.maxNumberOfAllContainers) {
			return false;
		}
		if(currentContainers.contains(container)) {
			return false;
		}
		if(this.currentPort.getContainers().contains(container) == false) {
			return false;
		}
		this.currentPort.splitContainer(container);
		currentContainers.add(container);
		return true ;

	}
	/**
	 * A method that determines if a container is able to unload from a specific ship or not, if possible it executes the incident
	 */
	public boolean unLoad(Container cont) {
		if(this.currentContainers.contains(cont)) {
			currentContainers.remove(cont);
			this.currentPort.addCont(cont);
			return true;
		}
		return false ;
	}

	/** Returns the ID of the ship
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/** Returns the current fuel of the ship
	 * @return the fuel
	 */
	public double getFuel() {
		return fuel;
	}
	/** Returns the current port of the ship
	 * @return the currentPort
	 */
	public Port getCurrentPort() {
		return currentPort;
	}

	/** Returns current containers in a specific ship in a sorted array list
	 * @return the containersInThisShip
	 */
	public ArrayList<Container> getCurrentContainers() {
		for(int a = 0 ; a<currentContainers.size() ; a++ ) {
			for(int e = 0 ; e < currentContainers.size() - a - 1;e++) {
				if(currentContainers.get(e).getID() >currentContainers.get(e+1).getID()) {
					Container temp = currentContainers.get(e);
					currentContainers.set(e, currentContainers.get(e+1));
					currentContainers.set(e+1, temp);
				}
			}
		}
		return currentContainers;
	}
	/** Returns current basic containers in a specific ship in a sorted array list
	 * @return the basic containers in this ship
	 */
	public ArrayList<Container> currentBasicContainers() {
		ArrayList<Container> bc = new  ArrayList<Container>();
		for(Container a : currentContainers) {
			if(a.getClass().equals(containers.BasicContainer.class)) {
				bc.add(a);
			}
		}
		for(int a = 0 ; a<bc.size() ; a++ ) {
			for(int e = 0 ; e < bc.size() - a - 1;e++) {
				if(bc.get(e).getID() >bc.get(e+1).getID()) {
					Container temp = bc.get(e);
					bc.set(e, bc.get(e+1));
					bc.set(e+1, temp);
				}
			}
		}
		return bc;
	}
	/** Returns current heavy containers in a specific ship in a sorted array list
	 * @return the heavy containers in this ship
	 */
	public ArrayList<Container> currentHeavyContainers() {
		ArrayList<Container> hc = new  ArrayList<Container>();
		for(Container a : currentContainers) {
			if(a.getClass().equals(containers.HeavyContainer.class)) {
				hc.add(a);
			}
		}
		for(int a = 0 ; a<hc.size() ; a++ ) {
			for(int e = 0 ; e < hc.size() - a - 1;e++) {
				if(hc.get(e).getID() >hc.get(e+1).getID()) {
					Container temp = hc.get(e);
					hc.set(e, hc.get(e+1));
					hc.set(e+1, temp);
				}
			}
		}
		return hc;
	}
	/** Returns liquid basic containers in a specific ship in a sorted array list
	 * @return the liquid containers in this ship
	 */
	public ArrayList<Container> currentLiquidContainers() {
		ArrayList<Container> lc = new  ArrayList<Container>();
		for(Container a : currentContainers) {
			if(a.getClass().equals(containers.LiquidContainer.class)) {
				lc.add(a);
			}
		}
		for(int a = 0 ; a<lc.size() ; a++ ) {
			for(int e = 0 ; e < lc.size() - a - 1;e++) {
				if(lc.get(e).getID() >lc.get(e+1).getID()) {
					Container temp = lc.get(e);
					lc.set(e, lc.get(e+1));
					lc.set(e+1, temp);
				}
			}
		}
		return lc;
	}
	/** Returns current refrigerated containers in a specific ship in a sorted array list
	 * @return the refrigerated containers in this ship
	 */
	public ArrayList<Container> currentRefrigeratedContainers() {
		ArrayList<Container> rc = new  ArrayList<Container>();
		for(Container a : currentContainers) {
			if(a.getClass().equals(containers.RefrigeratedContainer.class)) {
				rc.add(a);
			}
		}
		for(int a = 0 ; a<rc.size() ; a++ ) {
			for(int e = 0 ; e < rc.size() - a - 1;e++) {
				if(rc.get(e).getID() >rc.get(e+1).getID()) {
					Container temp = rc.get(e);
					rc.set(e, rc.get(e+1));
					rc.set(e+1, temp);
				}
			}
		}
		return rc;
	}
	/**
	 * The method that used while determining if ship can sail to a different port
	 */
	public boolean sailTo(Port p) {
		if(p.equals(this.currentPort) == false && this.currentPort.getDistance(p)*this.fuelConsumption() <= this.fuel) {
			currentPort.outgoingShip(this);
			this.fuel -= this.currentPort.getDistance(p)*this.fuelConsumption();
			this.currentPort = p;
			p.incomingShip(this);
			return true;
		}
		if(p.equals(this.currentPort)) {
			p.outgoingShip(this);
			p.incomingShip(this);
			return true;
		}
		return false ;
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE


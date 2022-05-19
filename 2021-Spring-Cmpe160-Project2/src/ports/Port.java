
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ports;
import java.util.ArrayList;
import ships.Ship;
import containers.*;
/**
 * A class that includes Ports and implements IPort interface
 * @author Halis
 *
 */
public class Port implements interfaces.IPort {
	private ArrayList<Container> containers = new ArrayList<Container>();
	private ArrayList<Ship> history = new ArrayList<Ship>();
	private ArrayList<Ship> current = new ArrayList<Ship>();
	private int ID;
	private double X;
	private double Y;
	/**
	 * A constructor for port that is used while creating a port
	 * @param ID ID of the ship
	 * @param X X coordinate of the port
	 * @param Y Y coordinate of the port
	 */
	public Port(int ID, double X, double Y) {
		this.ID = ID;
		this.X = X;
		this.Y = Y;
	}
	/**
	 * A method to add a new container to containers array
	 * @param container
	 */
	public void newContainer(Container container) {
		containers.add(container);
	}
	/**
	 * Computes the distance between two  ports
	 * @param other Destination Port
	 * @return distance between two ports
	 */
	public double getDistance(Port other) {
		return Math.sqrt(Math.pow(X-other.X,2) + Math.pow(Y-other.Y,2));
	}
	/**
	 * The method that used when ship comes to a port, and adds this ship to ship Arraylist in a specific port
	 */
	public void incomingShip(Ship a) {
		if(current.contains(a) == false) {
			current.add(a);
		}
	}
	/**
	 * The method that used when ship goes out from a port, and removes this ship from ship Arraylist in a specific port
	 */
	public void outgoingShip(Ship a) {
		current.remove(a);
		if(history.contains(a) == false) {
			history.add(a);
		}
	}
	/**
	 * Removes the container from the Arraylist that keeps the track of containers in a specific port
	 * @param container
	 */
	public void splitContainer(Container container) {
		if(containers.contains(container)) {
			containers.remove(container);
		}

	}
	/**
	 * Adds the container to the Arraylist that keeps the track of containers in a specific port
	 * @param container
	 */
	public void addCont(Container container) {
		containers.add(container);
	}
	/** Returns containers in this specific port
	 * @return the containers
	 */
	public ArrayList<Container> getContainers() {
		return containers;
	}

	/**
	 * Returns ID of a specific port
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**
	 * Returns X coordinate of a specific port
	 * @return the x
	 */
	public double getX() {
		return X;
	}

	/**
	 * Returns Y coordinate of a specific port
	 * @return the y
	 */
	public double getY() {
		return Y;
	}
	/**
	 * Returns Basic Containers in a specific port in a sorted Array List
	 * @return Basic Containers
	 */
	public ArrayList<Container> currentBasicContainers() {
		ArrayList<Container> bc = new  ArrayList<Container>();
		for(Container a : containers) {
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
	/**
	 * Returns Heavy Containers in a specific port in a sorted Array List
	 * @return Heavy Containers
	 */
	public ArrayList<Container> currentHeavyContainers() {
		ArrayList<Container> hc = new  ArrayList<Container>();
		for(Container a : containers) {
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
	/**
	 * Returns Liquid Containers in a specific port in a sorted Array List
	 * @return Liquid Containers
	 */
	public ArrayList<Container> currentLiquidContainers() {
		ArrayList<Container> lc = new  ArrayList<Container>();
		for(Container a : containers) {
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
	/**
	 * Returns Refrigerated Containers in a specific port in a sorted Array List
	 * @return Refrigerated Containers
	 */
	public ArrayList<Container> currentRefrigeratedContainers() {
		ArrayList<Container> rc = new  ArrayList<Container>();
		for(Container a : containers) {
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
	/** Returns current ships in a specific port in a sorted Array List
	 * @return current ships
	 */
	public ArrayList<Ship> getCurrent() {
		for(int a = 0 ; a<current.size() ; a++ ) {
			for(int e = 0 ; e < current.size() - a - 1;e++) {
				if(current.get(e).getID() >current.get(e+1).getID()) {
					Ship temp = current.get(e);
					current.set(e, current.get(e+1));
					current.set(e+1, temp);
				}
			}
		}
		return current;
	}
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE


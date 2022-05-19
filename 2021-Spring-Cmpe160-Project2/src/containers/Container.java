
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * An Abstract Class for containers
 * @author Halis
 *
 */
public abstract class Container {
	private int ID;
	int weight;
	private double fuelConsumption;
	/**
	 * Constructor for container to create a new Container object
	 * @param ID Container's ID
	 * @param weight Container's Weight
	 */
	public Container(int ID,int weight) {
		this.ID = ID;
		this.weight = weight;
	}
	/**
	 * A method to find fuel consumption for container
	 * @return total fuel consumption
	 */
	public double consumption() {
		return weight * fuelConsumption;
	}
	/**
	 * It determines if other container is identical with current one
	 * @param other other Container
	 * @return returns true or false 
	 */
	public boolean equals(Container other) {
		if(this.getClass().equals(other.getClass()) && this.weight == other.weight && this.ID == other.ID) return true;
		return false;
	}

	/**
	 * A method returns ID of a container
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * A method returns weight of a container
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * A method that returns fuel Consumption of a container
	 * @return the fuelConsumption
	 */
	public double getFuelConsumption() {
		return fuelConsumption;
	}

	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE


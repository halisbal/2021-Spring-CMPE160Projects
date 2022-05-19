
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * Basic Container class which is an extension of Container Class
 * @author Halis
 *
 */
public class BasicContainer extends Container {
	
	double fuelConsumption = 2.50;
	
	public BasicContainer(int ID, int weight) {
		super(ID,weight);
	}
	
	public double consumption() {
		return weight * fuelConsumption;
	}

	/**
	 * @return the fuelConsumption
	 */
	public double getFuelConsumption() {
		return fuelConsumption;
	}
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE


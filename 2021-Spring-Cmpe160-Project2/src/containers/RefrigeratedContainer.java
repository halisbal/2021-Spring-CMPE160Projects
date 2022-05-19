
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * Refrigerated Container Class which is an extension of Container Class
 * @author Halis
 *
 */
public class RefrigeratedContainer extends HeavyContainer {
	
	double fuelConsumption = 5.00 ;
	
	public RefrigeratedContainer(int ID, int weight) {
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


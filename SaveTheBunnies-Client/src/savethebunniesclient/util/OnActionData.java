package savethebunniesclient.util;

/**
 * Class used in buttons to set actions after being pressed
 * @author christian_gutan
 *
 */
public abstract class OnActionData {
	public abstract void onAction();
	
	public void start()	{
		onAction();
	}
}
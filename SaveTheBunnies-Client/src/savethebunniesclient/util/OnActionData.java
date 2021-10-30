package savethebunniesclient.util;

public abstract class OnActionData {
	public abstract void onAction();
	
	public void start()	{
		onAction();
	}
}
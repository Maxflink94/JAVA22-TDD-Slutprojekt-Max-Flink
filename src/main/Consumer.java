package main;

/**
 * Needs buffer to put Item in. 
 * run starts Consumer
 * stopRunning stops Consumer
 */
public interface Consumer {

	public void run();
	public void stopRunning();
	
}

package XbeeConfig;

import java.util.Scanner;

import com.digi.xbee.api.RemoteXBeeDevice;
import com.digi.xbee.api.XBeeNetwork;
import com.digi.xbee.api.ZigBeeDevice;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.APIOutputMode;

/**
 * XBee ZigBee Mesh Kit Explicit Data Sample application.
 * 
 * <p>This sample Java application shows how to use explicit frames to 
 * communicate with application objects of remote nodes.</p>
 * 
 * <p>In this example you will get the neighbor table of a specific XBee by 
 * sending an explicit frame to the ZigBee Device Object (ZDO) located at 
 * endpoint 0.</p>
 * 
 * <p>Pressing enter after the prompt (>>) finishes the application.</p>
 */
public class MainApp {
	
	/* Constants */
	
	// TODO Replace with the port where your coordinator module is connected to.
	private static final String PORT = "COM1";
	// TODO Replace with the baud rate of your coordinator module.
	private static final int BAUD_RATE = 9600;
	
	private static final Scanner SCANNER = new Scanner(System.in);
	
	/**
	 * Application main method.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		System.out.println("+----------------------------------------------+");
		System.out.println("|             Explicit Data Sample             |");
		System.out.println("+----------------------------------------------+\n");
		
		ZigBeeDevice myDevice = new ZigBeeDevice(PORT, BAUD_RATE);
		
		try {
			myDevice.open();
			
			XBeeNetwork network = myDevice.getNetwork();
			network.startDiscoveryProcess();
			
			System.out.format("%nLocal XBee: %s%n", myDevice.getNodeID());
			
			configureDevice(myDevice);
			
			System.out.print("\nScanning the network, please wait... ");
			while (network.isDiscoveryRunning()) {
				sleep(100);
			}
			
			if (network.getNumberOfDevices() == 0) {
				System.out.println("NO devices found.\n\nEnsure your network is properly setup.");
				myDevice.close();
				System.exit(0);
			} else {
				System.out.println("Devices found.");
			}
			
			while (true) {
				
				String nodeName = null;
				
				try {
					System.out.println("\nType the node name to retrieve its neighbor table and press <ENTER> (only <ENTER> to finish): ");
					printDevicesNames(network);
					System.out.print(">> ");
					
					nodeName = SCANNER.nextLine();
					if (nodeName.isEmpty())
						return;
					
					RemoteXBeeDevice remoteNode = network.getDevice(nodeName);
					if (remoteNode != null) {
						NeighborRequester neighborRequester = new NeighborRequester(myDevice, remoteNode);
						neighborRequester.start();
					} else {
						System.err.format("**[ERROR] Could not find the module '%s' in the network.%n", 
								nodeName);
					}
				} catch (XBeeException e) {
					System.err.format("**[ERROR] Error requesting neighbor table of '%s': %s%n", 
							nodeName, e.getMessage());
				}
				System.out.println("\n#################################################");
			}
			
		} catch (XBeeException e) {
			e.printStackTrace();
			myDevice.close();
			System.exit(1);
		} finally {
			myDevice.close();
			System.exit(0);
		}
	}
	
	/**
	 * Configures the given device to receive explicit data.
	 * 
	 * @param device The ZigBee device to be configured.
	 * 
	 * @throws XBeeException if there is an error during the configuration.
	 */
	private static void configureDevice(ZigBeeDevice device) throws XBeeException {
		device.enableApplyConfigurationChanges(true);
		
		// Set API Output Mode (AO) to format the received data as explicit.
		device.setAPIOutputMode(APIOutputMode.MODE_EXPLICIT);
	}
	
	/**
	 * Prints out the name of all the devices in the given network.
	 * 
	 * @param network The network with the devices whose names are to print out.
	 */
	private static void printDevicesNames(XBeeNetwork network) {
		for (RemoteXBeeDevice remote : network.getDevices()) {
			System.out.format(" - %s%n", remote.getNodeID(), remote.get64BitAddress());
		}
	}
	
	/**
	 * Sleeps the thread for the given milliseconds.
	 * 
	 * @param millis Time to sleep the thread in milliseconds.
	 */
	private static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {}
	}
}
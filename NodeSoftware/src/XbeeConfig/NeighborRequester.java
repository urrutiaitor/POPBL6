package XbeeConfig;

import java.io.ByteArrayInputStream;

import com.digi.xbee.api.RemoteXBeeDevice;
import com.digi.xbee.api.ZigBeeDevice;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.listeners.IExplicitDataReceiveListener;
import com.digi.xbee.api.models.ExplicitXBeeMessage;
import com.digi.xbee.api.models.XBee16BitAddress;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.digi.xbee.api.utils.ByteUtils;
import com.digi.xbee.api.utils.HexUtils;

/**
 * Class to request the neighbors of a remote node.
 * 
 * <p>The neighbors and their details are printed to the standard output.</p>
 * 
 * <p>The neighbor table request only works for ZigBee devices.</p> 
 */
public class NeighborRequester implements IExplicitDataReceiveListener {
	
	/* Constants */
	
	// Source endpoint.
	private static final int SOURCE_ENDPOINT = 0x00;
	// Destination endpoint, ZDO: 0x00.
	private static final int ENDPOINT_ZDO = 0x00;
	// ZigBee Device Profile ID: 0x0000.
	private static final int PROFILE_ID_ZDP = 0x0000;
	// Cluster ID of the Management LQI (Neighbor Table) Request: 0x0031.
	private static final int CLUSTER_ID_NT_REQUEST = 0x0031;
	// Cluster ID of the Management LQI (Neighbor Table) Response: 0x8031.
	private static final int CLUSTER_ID_NT_RESPONSE = 0x8031;
	
	private static final int STATUS_SUCCESS = 0x00;
	private static final int STATUS_NOT_SUPPORTED = 0x84;
	
	/* Variables */
	
	private static int transactionIndex = -1;
	
	private ZigBeeDevice localDevice;
	private RemoteXBeeDevice node;
	
	private int neighborIndex = 0;
	private int totalNeighbors = 0;
	
	private boolean discovering = false;
	
	/**
	 * Instantiates a new {@code NeighborRequester} object.
	 * 
	 * @param localDevice ZigBee device to request for the neighbors of a remote
	 *                    node.
	 * @param node Remote XBee device to get its neighbors. 
	 * 
	 * @throws NullPointerException If {@code localDevice == null} or
	 *                              if {@code node == null}.
	 */
	public NeighborRequester(ZigBeeDevice localDevice, RemoteXBeeDevice node) {
		if (localDevice == null)
			throw new NullPointerException("Local device cannot be null");
		if (node == null)
			throw new NullPointerException("Node cannot be null");
		
		this.localDevice = localDevice;
		this.node = node;
	}
	
	/**
	 * Discovers and reports all node neighbors.
	 * 
	 * <p>This method blocks until all the neighbors of the node are retrieved.</p>
	 * 
	 * @throws XBeeException if there is an error getting the neighbors.
	 */
	public void start() throws XBeeException {
		discovering = true;
		
		localDevice.addExplicitDataListener(this);
		
		try {
			sendNeighborRequest(getNextTransactionIndex(), neighborIndex);
			
			// Wait until all neighbors are received.
			while (discovering) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) { }
			}
			
		} finally {
			localDevice.removeExplicitDataListener(this);
			discovering = false;
		}
	}
	
	/**
	 * Sends the neighbor table request.
	 * 
	 * @param transactionIndex Identification number for the ZDP transaction.
	 * @param neighborIndex Starting index for the requested elements of the 
	 *                      neighbor table.
	 * 
	 * @throws XBeeException if there is an error writing in the communication 
	 *                       interface.
	 */
	private void sendNeighborRequest(int transactionIndex, int neighborIndex) throws XBeeException {
		localDevice.sendExplicitData(node, SOURCE_ENDPOINT, 
				ENDPOINT_ZDO, CLUSTER_ID_NT_REQUEST, PROFILE_ID_ZDP, 
				new byte[]{(byte)transactionIndex, (byte)neighborIndex});
	}
	
	@Override
	public void explicitDataReceived(ExplicitXBeeMessage explicitXBeeMessage) {
		// Check if the source of the message is from the requested node.
		if (!node.equals(explicitXBeeMessage.getDevice())) {
			return;
		}
		
		// Check if the application address is correct: profile ID, cluster ID and destination endpoint.
		if (explicitXBeeMessage.getProfileID() != PROFILE_ID_ZDP
				|| explicitXBeeMessage.getClusterID() != CLUSTER_ID_NT_RESPONSE
				|| explicitXBeeMessage.getDestinationEndpoint() != ENDPOINT_ZDO) {
			return;
		}
		
		ByteArrayInputStream inputStream = new ByteArrayInputStream(explicitXBeeMessage.getData());
		
		// Read transaction sequence number.
		int transactionID = inputStream.read();
		// If the transaction ID does not match discard as it is not the frame we are waiting for.
		if (transactionID != transactionIndex)
			return;
		
		// Read status byte.
		int status = inputStream.read();
		
		// Check if command was successfully processed.
		switch (status) {
			case STATUS_SUCCESS:
				getNeighbors(inputStream);
				break;
				
			case STATUS_NOT_SUPPORTED:
				System.err.format("**[ERROR] Received status code: 0x%02X (%s)%n%10s(See ZigBee specification for more details)%n", 
						status, "Feature not supported", " ");
				discovering = false;
				return;
				
			default:
				System.err.format("**[ERROR] Received status code: 0x%02X%n%10s%n", 
						status, "(See ZigBee specification for more details)");
				discovering = false;
				return;
		}
	}
	
	/**
	 * Parses the given input stream where a list of neighbors is stored.
	 * 
	 * @param inputStream Stream to get the data from the neighbor.
	 */
	private void getNeighbors(ByteArrayInputStream inputStream) {
		boolean showTotal = totalNeighbors == 0;
		
		// Read total entries in the table.
		totalNeighbors = inputStream.read();
		if (showTotal)
			System.out.format("%nTotal number of neighbors: %d%n", totalNeighbors);
		
		// Ignore current index, consume byte.
		inputStream.read();
		
		// Read number of entries in this response.
		int numberOfEntries = inputStream.read();
		if (numberOfEntries > 0) {
			// Parse neighbors.
			while (inputStream.available() > 0) {
				parseNeighbor(inputStream, neighborIndex);
				neighborIndex = neighborIndex + 1;
			}
		}
		
		// If we do not have all the neighbors, ask for the next items.
		if (neighborIndex < totalNeighbors) {
			try {
				sendNeighborRequest(transactionIndex, neighborIndex);
			} catch (XBeeException e) {
				discovering = false;
				System.err.format("Error requesting neighbor table of %s (index : %d): %s%n", 
						node.getNodeID(), neighborIndex, e.getMessage());
			}
		} else 
			discovering = false;
	}
	
	/**
	 * Parses the given input stream where a neighbor entry is stored.
	 * 
	 * @param inputStream Stream to get the data from the neighbor.
	 * @param neighborIndex Index of the next neighbor to be parsed.
	 */
	private void parseNeighbor(ByteArrayInputStream inputStream, int neighborIndex) {
		System.out.format("%nNeighbor: %d%n-----------------------------------%n", neighborIndex + 1);
		
		// PAN ID: read 8 bytes.
		System.out.format("PAN ID:         %s%n", HexUtils.byteArrayToHexString(ByteUtils.swapByteArray(ByteUtils.readBytes(8, inputStream))));
		
		// 64-bit address: read 8 bytes.
		System.out.format("64-bit address: %s%n", new XBee64BitAddress(ByteUtils.swapByteArray(ByteUtils.readBytes(8, inputStream))));
		
		// 16-bit address: read 2 bytes.
		System.out.format("16-bit address: %s%n", new XBee16BitAddress(ByteUtils.swapByteArray(ByteUtils.readBytes(2, inputStream))));
		
		// Settings byte: read 1 byte.
		byte settingsByte = (byte)inputStream.read();
		
		// Device type: 2 bits of settings byte.
		int type = ByteUtils.readIntegerFromByte(settingsByte, 0, 2);
		String typeName = "";
		switch (type) {
		case 0x00: // Coordinator.
			typeName = "Coordinator";
			break;
		case 0x01:
			typeName = "Router";
			break;
		case 0x02:
			typeName = "End device";
			break;
		case 0x03:
			typeName = "Unknown";
			break;
		default:
			typeName = "Not specified";
			break;
		}
		System.out.format("Device type:    %s (%d)%n", typeName, type);
		
		// Receiver on when idle: 2 bits of settings byte.
		ByteUtils.readIntegerFromByte(settingsByte, 2, 2);
		
		// Relationship: 3 bits of settings byte.
		int relationship = ByteUtils.readIntegerFromByte(settingsByte, 4, 3);
		String relationshipName = "";
		switch (relationship) {
		case 0x00: // Coordinator.
			relationshipName = "Parent";
			break;
		case 0x01:
			relationshipName = "Child";
			break;
		case 0x02:
			relationshipName = "Sibling";
			break;
		case 0x03:
			relationshipName = "None of parent, child, sibling"; // None of the above.
			break;
		case 0x04:
			relationshipName = "Previous child";
			break;
		default:
			relationshipName = "Not specified";
			break;
		}
		System.out.format("Relationship:   %s (%d)%n", relationshipName, relationship);
		
		// Joining: read 1 byte.
		byte joiningByte = (byte)inputStream.read();
		int joining = ByteUtils.readIntegerFromByte(joiningByte, 0, 2);
		String joiningStatus = "";
		switch (joining) {
		case 0x00: // Coordinator.
			joiningStatus = "Not accepting joins";
			break;
		case 0x01:
			joiningStatus = "Accepting joins";
			break;
		case 0x02:
			joiningStatus = "Unknown";
			break;
		default:
			joiningStatus = "Not specified";
			break;
		}
		System.out.format("Join requests:  %s (%d)%n", joiningStatus, joining);
		
		// Depth: read 1 byte.
		System.out.format("Tree depth:     %d%n", inputStream.read());
		
		// LQI: read 1 byte.
		System.out.format("LQI:            %d / 255%n", inputStream.read());
	}
	
	/**
	 * Calculates and returns the next transaction index.
	 * 
	 * @return the next transaction index.
	 */
	private int getNextTransactionIndex() {
		transactionIndex++;
		if (transactionIndex > 255)
			transactionIndex = 0;
		return transactionIndex;
	}
}

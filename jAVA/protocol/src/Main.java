import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
	private static final List<Integer> dataList = new ArrayList<>();
	private static final int sendDataInterval = 5000; // 5 seconds

	public static void main(String[] args) {
		setupDataGeneratorAndSender();
	}

	private static void setupDataGeneratorAndSender() {
		Random rand = new Random();

		// Generate data every 5 ms
		Timer dataGenerator = new Timer();
		dataGenerator.schedule(new TimerTask() {
			@Override
			public void run() {
				int data = rand.nextInt(600);
				synchronized (dataList) {
					dataList.add(data);
				}
			}
		}, 0, 5);

		// Send data every 5 seconds
		Timer dataSender = new Timer();
		dataSender.schedule(new TimerTask() {
			@Override
			public void run() {
				sendDataToServer();
			}
		}, sendDataInterval, sendDataInterval);
	}

	private static void sendDataToServer() {
		Socket socket = null;
		int maxRetries = 3;
		int currentAttempt = 0;
		boolean successfulTransmission = false;

		List<Integer> dataToSend;
		synchronized (dataList) {
			dataToSend = new ArrayList<>(dataList);
			dataList.clear();
		}

		while (!successfulTransmission && currentAttempt < maxRetries) {
			try {
				socket = new Socket("127.0.0.1", 9000);
				socket.setSendBufferSize(8096);

				OutputStream outputStream = socket.getOutputStream();
				for (Integer data : dataToSend) {
					outputStream.write((data + " ").getBytes());
				}
				outputStream.flush();

				successfulTransmission = true;

			} catch (IOException e) {
				System.err.println("Failed to send data to server (attempt " + (currentAttempt + 1) + "): " + e.getMessage());
				currentAttempt++;
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		if (successfulTransmission) {
			System.out.println("Data sent successfully.");
		} else {
			System.out.println("Failed after " + maxRetries + " attempts.");
		}
	}
}
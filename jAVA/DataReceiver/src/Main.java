import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static int serverPort = 9000;
	private static long duration = 10000; // 10 seconds in milliseconds

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		try {
			ServerSocket serverSocket = new ServerSocket(serverPort);
			serverSocket.setReceiveBufferSize(8096);
			List<String> list = new ArrayList<>();
			System.out.println("Data receiver waiting for connection on port " + serverPort);

			// 10초동안 진핸중
			while (System.currentTimeMillis() - startTime <= duration) {
				Socket clientSocket = serverSocket.accept();
				BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String receivedData = reader.readLine();
				while (receivedData != null) {
					String[] dataItems = receivedData.split(" ");
					for (String dataItem : dataItems) {
						list.add(dataItem);
						System.out.print(dataItem + " ");
					}
					receivedData = reader.readLine();
				}
				System.out.println();
				System.out.println("전송된 데이터 개수 : " + list.size());
				list.clear();
				clientSocket.close();
			}

			serverSocket.close();
			System.out.println("Server socket closed");
		} catch (IOException e) {
			System.err.println("Error receiving data: " + e.getMessage());
		}
	}
}
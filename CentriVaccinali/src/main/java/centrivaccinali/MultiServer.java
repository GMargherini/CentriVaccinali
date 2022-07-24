/*import java.io.*;
import java.net.*;

public class MultiServer {
	public static final int PORT = 2503;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server started")
		
		try {
			while (true) {
				Socket socket = serverSocket.accept();
				try {
					new OneServer(socket);
				} catch (Exception e) {
					socket.close();
					throw e;
				}
			}
		} finally {
			socket.close();

	}
}*/

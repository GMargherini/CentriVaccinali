/*import java.io.*;
import java.net.*;

public class OneServer extends Thread {
	private final Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	public OneServer(Socket socket) {
		this.socket = socket;
		start();
	}

	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
		}
			{
			System.out.println("Closing...");
		} catch (IOException e) {
			System.err.println("IO Exception");
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("Socket not closed");
			}
		}
	}

}*/

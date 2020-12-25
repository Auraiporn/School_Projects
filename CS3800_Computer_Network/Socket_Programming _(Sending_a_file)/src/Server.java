import java.net.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.nio.file.*;

public class Server {
	
	private ServerSocket ss; 
	
	public Server(int port_number) {
		try {
			ss = new ServerSocket(port_number);
			System.out.println("Server Listening to Incoming Socket Connection...");
			establish_connection();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void establish_connection() {
			try {
				Socket s = ss.accept();
				System.out.println("Client Connected...");
				
				// Read the command line args of output_data_file from the client
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				String output_data_file = in.readLine();	
				
				echo_file(s,output_data_file);
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Cannot accept client connection...");
			}
		
	}
	
	public void echo_file(Socket s, String output_data_file) {
		try {
			int count;
			InputStream in = s.getInputStream();
			System.out.println("Server Receives a File...");
			
			OutputStream out = s.getOutputStream();
			// Set up the socket buffer for receiving data
			byte[] b = new byte[16*1024];
			           
			System.out.println("Server is Echoing a File...");
            while ((count = in.read(b)) > 0) {
            	out.write(b, 0, count);
            	//System.out.println(count);
							
			}	
            out.flush();       
			System.out.println("Server Done Transferring a File Back to Client...");
            out.close();
			in.close();
            s.close();
			ss.close();
			
			System.out.println("Socket is Closed Now...");
			 
			 System.out.println();
	         Path path = Paths.get(output_data_file);
	         System.out.println("The path of output file is: " +path.toAbsolutePath());
            } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		int port_number = 12345;
		Server ss = new Server(port_number);
       
    }
}
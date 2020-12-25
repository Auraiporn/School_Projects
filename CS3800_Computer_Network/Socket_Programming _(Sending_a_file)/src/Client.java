import java.net.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.nio.file.*;

public class Client {
	
	private Socket s;
	
	public Client(String ip_address, int port_number, String input_data_file, String output_data_file) {
		try {
			s = new Socket(ip_address, port_number);
			send_command_line(output_data_file);
			readAndwrite_file(input_data_file, output_data_file);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void send_command_line(String output_data_file) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream());
			out.println(output_data_file);
			out.flush();
		} catch (Exception e){
			System.out.println("Error:  " + e);
		}
	}
	
	
	public void readAndwrite_file(String input_data_file, String output_data_file) {
		try {
			// Read input_data_file
    		File file = new File(input_data_file);
    		int file_size = (int) file.length();
			byte[] bytes = new byte[file_size];
			InputStream in = new FileInputStream(file);
			OutputStream out = s.getOutputStream(); 
            int count;         
            long start = 0, end = 0, transfer_time = 0;
            System.out.println("File Sending to Server...");
            while ((count = in.read(bytes)) > 0) {
            	System.out.println("Sending...");
            	System.out.println(count);
            	System.out.println("Sent " + count + " bytes.");
            	
            	System.out.println("File Tansferred to Server");
            	start = System.nanoTime(); 
               	out.write(bytes, 0, count);
                out.flush();	
			}
            
            // Receive File from Server
            InputStream is = s.getInputStream();
            end = System.nanoTime();
            System.out.println("Client Receives the File Back from the Server...");
            
           transfer_time = end - start;
            
            System.out.println("Writing to output_data_file");
    		OutputStream os = new FileOutputStream(output_data_file);
    		
    		byte[] b = new byte[16*1024];
    		int read = 0, totalRead = 0, remaining = file_size;
    	
    		while((read = is.read(b,0, Math.min(b.length, remaining))) > 0) {
    			totalRead += read;
    			remaining -= read;
    			//System.out.println("read " + totalRead + " bytes now.");
    			os.write(b, 0, read);
    		}
    		os.flush();
    		System.out.println("Output_data_file Created...");
    	
    		os.close();
    		is.close();         
    		out.close();
            in.close();
            s.close();            
           
            double startInMilli = (double) start / 1_000_000;
            double endInMilli = (double) end / 1_000_000;
           
            System.out.println("Start time: " + startInMilli);
            System.out.println("End time: " + endInMilli);
            
            System.out.println("It takes "+ transfer_time + " in nanoseconds for a server to echo a file back once connection is establish...");
            // 1 millisecond = 1_000_000 nano seconds
            double elapsedTimeInMillisecond = (double) transfer_time / 1_000_000;
            System.out.println(elapsedTimeInMillisecond + " milliseconds");
            // TimeUnit
            long convert = TimeUnit.MILLISECONDS.convert(transfer_time, TimeUnit.NANOSECONDS);         
            System.out.println("It takes " + convert + " milliseconds for a server to echo a file back once connection is establish...");
       		
            Path path = Paths.get("output_data_file");
            System.out.println(path.toAbsolutePath());
            
            calculation(elapsedTimeInMillisecond, (double) file_size );
            
		} catch(Exception e) {
			e.printStackTrace();			
		}
		
	}
	
	public static void calculation(double rtt, double file_size ) {
	System.out.println();
	System.out.println("\t------------------Calculation---------------------");
	System.out.println("\tLatency = RTT /2");
	double latency = rtt / (double) 2;
	System.out.println("\tEffective_bandwidth = TransferSize/latency");
	double effective_bandwidth = file_size/latency;
	System.out.println("\tTransferTime = RTT + (1/EffectiveBandwidth) x TransferSize");
	double transfer_time =  rtt + ((double) 1 / effective_bandwidth) * file_size;
	System.out.println("\tThroughput = TransferSize/TransferTime");
	double throughput = file_size/transfer_time;
	System.out.println("\tPercent of actual bits/second is sent = throughput/bandwidth");
	double percent_use = throughput/ effective_bandwidth;
	System.out.println();
	System.out.println("\t------------------Results------------------------");
	System.out.println("\tRTT: " + rtt);
	System.out.println("\tLatency: " + latency);
	System.out.println("\tEffective_bandwidth: " + effective_bandwidth );
	System.out.println("\tTransferTime: " + transfer_time);
	System.out.println("\tThroughput: " + throughput);
	System.out.println("\tPercent of actual bits/second is sent " + percent_use + " network's total bandwidth");
	
	}
	public static void main(String[] args) {
    	
		int port_number;   	
    	if(args.length == 0 || args.length != 3) {
    		System.out.println("Usage:  java Client <ip_address> <input_data_file> <output_data_file>");
    	} else {
    		port_number = 12345;
    		String ip_address = args[0], input_data_file = args[1], output_data_file = args[2];
    		Client c = new Client(ip_address, port_number, input_data_file, output_data_file);
    	}
    }
}
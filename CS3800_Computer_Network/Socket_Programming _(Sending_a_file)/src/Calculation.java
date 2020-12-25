
public class Calculation {
	
	
	public static double calculate_throughput(double transfer_size, double transfer_time) {
		double throughput = transfer_size/transfer_time;
		return throughput;
	}
	public static double ave_rtt(double rtt1, double rtt2, double rtt3) {
		double ave_rtt;
		ave_rtt = (rtt1 + rtt2+ rtt3) / (double) 3;
		return ave_rtt;
	}
	public static double latency(double ave_rtt) {
		 double latency = ave_rtt / (double) 2;
		 return latency;
	}
	public static double effective_bandwidth(double file_size, double latency) {
		double  effective_bandwidth = file_size/latency;
		return 	effective_bandwidth;
	}
	// TransferTime = RTT + (1/EffectiveBandwidth) x TransferSize
	public static double transfer_time(double ave_rtt,  double effective_bandwidth, double file_size ) {
		double transfer_time =  ave_rtt + ((double) 1 / effective_bandwidth) * file_size;
		return transfer_time;
	}
	public static double percent_use(double throughput, double bandwidth) {
		double percent = throughput/ bandwidth;
		return percent;
	}
	public static void main(String[] args) {
		
		/*// Values of the second attempt
		double [] size = {500,10000,80002,400001, 2000000, 4000000};
		int [] sizef = {500,10000,80002,400001, 2000000, 4000000};
		double [] aveRTT = {ave_rtt(0.057956, 0.108325, 0.094773), 
				ave_rtt(0.199424, 0.161206, 0.168834),
				ave_rtt(1.205587, 1.427295 , 1.839852),
				ave_rtt(15.403034, 16.026232 , 18.805656),
				ave_rtt(75.97204 , 73.271662 , 81.007002),
				ave_rtt(164.6329, 175.268644, 187.499148),};
		*/
				
		// Values of the last attempt
		double [] size = {500,10000,80000,400000, 2000000, 4000000};
	    int [] sizef = {500,10000,80000,400000, 2000000, 4000000};
		double [] aveRTT = {ave_rtt(0.052426 , 0.104331 , 0.090941), 
							ave_rtt(0.135148 , 0.147655 , 0.19225 ),
							ave_rtt(6.02916 , 1.622773  , 3.170046 ),
							ave_rtt(18.505549 , 18.317537  , 16.538636 ),
							ave_rtt(75.437004  , 83.860507 ,79.140108 ),
							ave_rtt(153.016 , 159.874904 , 157.330776 ),};
		
		
		double [] latency = new double[size.length];
		double [] effective_bandwidth = new double[size.length];
		double [] transfer_time = new double[size.length];
		double [] throughput = new double[size.length];
		double [] percent_use = new double[size.length];
		
		System.out.println("------------------Average RTT---------------------");
		for(int i=0; i < size.length;i++) {
			System.out.println("Average RTT of File Size: " + sizef[i] + " is: " + aveRTT[i]);
		}
		System.out.println();
		System.out.println("------------------Latency---------------------");
		for(int i=0; i < size.length;i++) {
			latency[i] = latency(aveRTT[i]);
			System.out.println("Latency of file size " + sizef[i] + " is: " + latency[i]);
		}
		System.out.println();	
		System.out.println("------------------EffectiveBandwidth---------------------");
		for(int i=0; i < size.length;i++) {
			effective_bandwidth[i] = effective_bandwidth(size[i], latency(aveRTT[i]) );
			System.out.println("Effective Bandwidth of file size " + sizef[i] + " is: " + effective_bandwidth[i]);
		}
		System.out.println();
		System.out.println("------------------TransferTime---------------------");
		for(int i=0; i < size.length;i++) {
			transfer_time[i] =  transfer_time(aveRTT[i], effective_bandwidth(size[i], latency(aveRTT[i])), size[i]);
			System.out.println("Transfer Time of file size " + sizef[i] + " is: " + transfer_time[i]);
		}
		// Transfer time is not the average round trip time
		// TransferTime = RTT + (1/EffectiveBandwidth) x TransferSize
		System.out.println();
		System.out.println("------------------Throughput---------------------");
		for(int i=0; i < size.length;i++) {
			throughput[i] =  calculate_throughput(size[i], transfer_time[i]);
			System.out.println("Throughput of file size " + sizef[i] + " is: " + throughput[i]);
		}
		System.out.println();
		System.out.println("------------------Percent of Throughput Used of the Network's Total Bandwidth---------------------");
		for(int i=0; i < size.length;i++) {
			percent_use[i] =  percent_use(throughput[i], effective_bandwidth[i]);
			System.out.println("The throughput " + sizef[i] + " is " + percent_use[i] + " of the network's total bandwidth.");
		}
	}

}

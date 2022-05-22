package thread;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

class MultiThreadedFileRead extends Thread {
//	FileReader in;
	BufferedReader brd;
	BufferedWriter bwd;
	FileInputStream fin;
	String name;
	static int linecounter = 0 ;
	int localcounter;
	int threadNum;
	long readingSize;
	MultiThreadedFileRead(String threadname,BufferedReader breadr,BufferedWriter bfwrter ,FileInputStream fin,int threadNum,long readsize) throws Exception {
//		in = new FileReader(fname);
		brd = breadr;
		this.bwd = bfwrter;
		this.name = threadname;
		this.fin = fin;
		this.threadNum = threadNum;
		this.readingSize = readsize;
		this.start();
		
	}

	/*public void run() {
		try {
			int i = 0;
			localcounter = 0;
			String line = brd.readLine();
			while (line != null) {
				try {
//					System.out.println(this.name + " : " +line);
					bwd.write(this.name + " : " +line + "\n");
//					synchronized(brd){
						line = brd.readLine();
//					}
				} catch (Exception e) {
				}
				linecounter++;
				localcounter++;
			}
//			brd.close();
//			in.close();
			System.out.println(" linecounter : " + linecounter + " , counter : "+ localcounter);

		} catch (Exception e) {
		}
	}
*/
	public void run() {
		try {
			int i = 0;
			localcounter = 0;
			FileChannel freadchannel= fin.getChannel();
			long startPosition =(threadNum*readingSize);
			long endPosition = (threadNum == 0)?readingSize:((threadNum*readingSize)+readingSize);
			freadchannel.position(startPosition);
			ByteBuffer bBuf = ByteBuffer.allocate(2024);
			while (freadchannel.position() < (endPosition)) {
				try {
//					System.out.println(this.name + " : " +line);
					freadchannel.read(bBuf);
					bBuf.flip();
					while(bBuf.hasRemaining()){
						bwd.write((char)bBuf.get());
					}
					bwd.write("***** \n" +this.name + "**********");
//					bwd.write(this.name + " : " +bBuf.toString());
					bBuf.clear();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println(this.name + " position : " + freadchannel.position());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String a[]) throws Exception {
		int n = 2;
		System.out.print("Enter the number of files : ");
		File file = new File(
				"F:\\vimal\\workspace\\TestDemo\\src\\xml\\abc.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader br=new BufferedReader(new InputStreamReader(new
		// FileInputStream(file)));
		try {
			n = Integer.parseInt(br.readLine());
		} catch (Exception e) {
		}
		MultiThreadedFileRead fr[] = new MultiThreadedFileRead[n];
		long tim;
		tim = System.currentTimeMillis();
		File f = new File("F:\\vimal\\workspace\\TestDemo\\src\\xml\\abc.txt");
		FileInputStream fin = new FileInputStream(f);
		BufferedReader bfd = new BufferedReader(new FileReader(f));
		BufferedWriter bwd = new BufferedWriter(new FileWriter("F:\\vimal\\workspace\\TestDemo\\src\\xml\\abc1.txt"));
		long readSize = (f.length()/n);
		for (int i = 0; i < n; i++)
			// fr[i]=new MultiThreadedFileRead(a[i]);
			fr[i] = new MultiThreadedFileRead("TH - "+i,bfd,bwd,fin,i,readSize);

		for (int i = 0; i < n; i++) {
			try {
				fr[i].join();
			} catch (Exception e) {
			}
		}
		bfd.close();
		bwd.flush();
		bwd.close();
		System.out.println("Time Required : "
				+ (System.currentTimeMillis() - tim) + " miliseconds.");
	}
}
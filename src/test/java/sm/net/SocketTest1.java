package sm.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTest1 {

	public static void main(String[] args) throws Exception{
		
			ServerSocket server=null;
			
			server=new ServerSocket(1001);
		 
			Socket socket=server.accept();
			
			//BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			PrintWriter os=new PrintWriter(socket.getOutputStream());
			os.println("<strong>nginx第一个使用</strong>");
			
			socket.close();
			os.close();
			
			
			
		
		 
	}
}

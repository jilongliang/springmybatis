package sm.net;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTest2 {

	public static void main(String[] args) throws Exception{
		
		Socket socket=new Socket("127.0.0.1",1001);
		
		PrintWriter os=new PrintWriter(socket.getOutputStream());
		
		os.println("<strong>nginx第一个使用</strong>");
		
		socket.close();
		os.close();
	}
}

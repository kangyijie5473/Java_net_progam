import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by KangKang on 17-10-13-下午2:30
 */
// pntia.cn 713808
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){

            }
        }
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);
            Socket socket = null;
            while (true){
                socket = serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        }catch (Exception e){ // finally keyword is ???
            if (serverSocket != null){
                System.out.println("the server close");
                try {
                    serverSocket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                serverSocket = null;
            }

        }
    }
}

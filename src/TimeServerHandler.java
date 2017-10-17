import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by KangKang on 17-10-13-下午2:42
 */
public class TimeServerHandler implements Runnable{
    private Socket socket;

    public TimeServerHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String currentTime = null;
            String body = null;
            while (true){
                body = in.readLine();
                if (body == null)
                    break;
                System.out.println("the time server recv :" + body);
                currentTime = "Query tme order".equalsIgnoreCase(body) ? new java.util.Date(
                        System.currentTimeMillis()).toString(): "Bad order";
                out.println(currentTime);
            }
        }catch (IOException e){
            System.out.println("error");
        }
    }

}

package pop;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class POPCore {
    private String url = null;
    private BufferedReader reader = null;
    private PrintWriter writer = null;

    //connect
    public POPCore(String url,int port) throws IOException{

        this.url = url;
        //socket连接
        Socket socket = new Socket(url,port);

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);

        String response = reader.readLine();
        if(!response.startsWith("+OK")){
            throw new IOException("FAIL TO CONNECT THE SERVER!!!");
        }

    }


    //execute the instructions
    public String execute(String command,String message) throws IOException{

        if(command !=null){
            writer.println(command);
        }

        String response = reader.readLine();
        System.out.println("EXECUTE: "+ command + "; AND GET RESPONSE: " + response);

        if(message == null){
            return response;
        }

        if(!response.startsWith(message)){
            throw new IOException("FAIL TO EXECUTE: " + response);
        }

        return response;

    }


    //get the details of e-mails
    public String getDetails(){
        StringBuilder builder = new StringBuilder();
        String info = null;

        try{
            info = reader.readLine().toString();
            while(!".".equalsIgnoreCase(info)){
                builder.append(info).append("\n");
                info = reader.readLine().toString();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return builder.toString();
    }


}

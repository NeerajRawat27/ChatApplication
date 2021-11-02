
package application.groupchatapplication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GrpServer implements Runnable{
    
    ArrayList al;
    Socket s;
    public GrpServer(Socket s, ArrayList al) {
    
        this.s=s;
        this.al=al;
        
    }
    
    public void run()
    {
        
        try {
            DataInputStream din=new DataInputStream(s.getInputStream());
            DataOutputStream dos=new DataOutputStream(s.getOutputStream());
            
            while(true)
            {
                String str=din.readUTF();
                System.out.println(str);
                
                Iterator i=al.iterator();
                while(i.hasNext())
                {
                    Socket sc=(Socket)i.next();
                    DataOutputStream dout=new  DataOutputStream(sc.getOutputStream());
                    dout.writeUTF(str);
                    dout.flush();
                    
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GrpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    

    public static void main(String[] args) {
        
        ArrayList al=new ArrayList();
        try{
        
            ServerSocket ss=new ServerSocket(10);
            System.out.println("Server Started");
            while(true)
            {
            Socket s=ss.accept();
                System.out.println("client connected");
            al.add(s);
            GrpServer grp=new GrpServer(s,al);
       
           Thread t1=new Thread(grp);
           t1.start();
            }
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
       

    }
    
}

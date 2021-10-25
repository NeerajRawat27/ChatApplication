package com.mycompany.chattingapplication;


import static com.mycompany.chattingapplication.ClientChat.jf;
import static com.mycompany.chattingapplication.ClientChat.jta;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ChatServer implements ActionListener {

	JTextField jtf;
        JButton send;
	JPanel jp;
        static JFrame jf;
        static Box vertical=Box.createVerticalBox();
        static JPanel j;
        static Socket s;
        static ServerSocket ss;
        static DataInputStream dis;
        static DataOutputStream dout;
        boolean isTyping;
	public ChatServer() {
	
                jf=new JFrame();
		jp=new JPanel();
		jp.setLayout(null);
		jp.setBackground(new Color(7,94,84));
		jp.setBounds(0,0,330,50);
		jf.add(jp);
		
		ImageIcon ic1=new ImageIcon(getClass().getResource("3.png"));
		Image img=ic1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
		ImageIcon ic2=new ImageIcon(img);
		JLabel jl=new JLabel(ic2);
		jl.setBounds(5,18,20,15);
		jp.add(jl);
		
		ImageIcon ic3=new ImageIcon(getClass().getResource("smiley.png"));
		Image img1=ic3.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT);
		ImageIcon ic4=new ImageIcon(img1);
		JLabel jl2=new JLabel(ic4);
		jl2.setBounds(30,5,40,40);
		jp.add(jl2);
		
		JLabel jl3=new JLabel("Munna");
		jl3.setFont(new Font("san-serif",Font.BOLD,14));
		jl3.setForeground(Color.white);
		jl3.setBounds(75,10,100,15);
		jp.add(jl3);
		
		JLabel jl4=new JLabel("Active now");
		jl4.setFont(new Font("san-serif",Font.PLAIN,10));
		jl4.setForeground(Color.white);
		jl4.setBounds(75,25,100,18);
		jp.add(jl4);
                
                Timer t=new Timer(1,new ActionListener(){
                    
                    public void actionPerformed(ActionEvent ae)
                    {
                        if(!isTyping)
                        {
                            jl4.setText("Active now");
                        }
                    }
                });
                
                t.setInitialDelay(1000);
		
		ImageIcon ic5=new ImageIcon(getClass().getResource("3icon.png"));
		Image img2=ic5.getImage().getScaledInstance(20,15,Image.SCALE_DEFAULT);
		ImageIcon ic6=new ImageIcon(img2);
		JLabel jl5=new JLabel(ic6);
		jl5.setBounds(300,10,5,20);
		jp.add(jl5);
		
		ImageIcon ic7=new ImageIcon(getClass().getResource("phone.png"));
		Image img3=ic7.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
		ImageIcon ic8=new ImageIcon(img3);
		JLabel jl6=new JLabel(ic8);
		jl6.setBounds(270,12,15,20);
		jp.add(jl6);
		
		ImageIcon ic9=new ImageIcon(getClass().getResource("video.png"));
		Image img4=ic9.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
		ImageIcon ic10=new ImageIcon(img4);
		JLabel jl7=new JLabel(ic10);
		jl7.setBounds(235,12,20,20);
		jp.add(jl7);
		
                jl.addMouseListener(new MouseAdapter(){
                    public void mouseClicked(MouseEvent ae)
                    {
                        System.exit(0);
                    }
                });
                
		jtf=new JTextField();
                jtf.setFont(new Font("san-serif",Font.PLAIN,12));
                jtf.setBounds(2,470,231,30);
                jf.add(jtf);
                
                jtf.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent ke)
                    {
                        jl4.setText("typing...");
                        t.stop();
                        isTyping=true;
                        
                    }
                    public void keyReleased(KeyEvent ke)
                    {
                        isTyping=false;
                        if(!t.isRunning())
                        {
                            t.start();
                        }
                    }
                });
                
                send =new JButton("send");
                send.setBounds(235,469,92,30);
                send.setBackground(new Color(7,94,84));
                send.setForeground(Color.white);
                send.addActionListener(this);
                jf.add(send);
                
                j=new JPanel();
                j.setFont(new Font("san-serif",Font.PLAIN,12));
//                jta.setBackground(Color.red);
                 
                JScrollPane js=new JScrollPane(j);
                js.setBounds(2,52,325,415);
                js.setBorder(BorderFactory.createEmptyBorder());
                
                ScrollBarUI jsui=new BasicScrollBarUI(){
                protected JButton createDecreaseButton(int Orientation)
                {
                    JButton jb=super.createDecreaseButton(Orientation);
                    jb.setBackground(new Color(7,94,84));
                    jb.setForeground(Color.WHITE);
                    this.thumbColor=new Color(7,94,84);
                    return jb;
                }
                
                protected JButton createIncreaseButton(int Orientation)
                {
                    JButton jb=super.createIncreaseButton(Orientation);
                    jb.setBackground(new Color(7,94,84));
                    jb.setForeground(Color.WHITE);
                    this.thumbColor=new Color(7,94,84);
                    return jb;
                } 
                };
                
                js.getVerticalScrollBar().setUI(jsui);
                jf.add(js);
                
                
                
                jf.setSize(330,500);
		jf.setLayout(null);
		jf.setLocation(200,150);
                jf.setUndecorated(true);
		jf.setVisible(true);
                
             
	}
        public void actionPerformed(ActionEvent ae)
        {
            try {
                String out = jtf.getText();
                saveToFile(out);
                JPanel jp2=formatLabel(out);
                j.setLayout(new BorderLayout());
                
                JPanel right=new JPanel(new BorderLayout());
                right.add(jp2,BorderLayout.LINE_END);
                vertical.add(right);
                vertical.add(Box.createVerticalStrut(10));

                j.add(vertical,BorderLayout.PAGE_START);
                dout.writeUTF(out);
//                j.add(jp2);
                jtf.setText("");
                jf.validate();
            } catch (IOException ex) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public static JPanel formatLabel(String out)
        {
              JPanel p3=new JPanel();
              p3.setLayout(new BoxLayout(p3,BoxLayout.Y_AXIS));
              
              JLabel jl=new JLabel("<html><p style=\"width:100px\">"+out+"</p></html>");
              jl.setBackground(new Color(37,211,102));
              jl.setOpaque(true);
              jl.setFont(new Font("Tahoma",Font.PLAIN,12));
              jl.setBorder(new EmptyBorder(15,15,15,30));
              
              Calendar c=Calendar.getInstance();
              SimpleDateFormat sdf=new SimpleDateFormat("hh:mm");
              
              JLabel l2=new JLabel();
              l2.setText(sdf.format(c.getTime()));
              p3.add(jl);
              p3.add(l2);
        
              return p3;
        }
        
        public void saveToFile(String str)
        {
            try 
                (FileWriter file = new FileWriter("chat.txt");
                PrintWriter pw=new PrintWriter(new BufferedWriter(file));){
                
                pw.println("Munna :"+str);
                
                
            } catch (Exception ex) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
       

	public static void main(String[] args) {
		new ChatServer();
                   String str="";
                try{
                System.out.println("Server Started");
                ss=new ServerSocket(10);
                while(true)
                {
                s=ss.accept();
                System.out.println("Client Connected");
                dis=new DataInputStream(s.getInputStream());
                dout=new DataOutputStream(s.getOutputStream());
           
                while(true)
                {
                j.setLayout(new BorderLayout());
                str=dis.readUTF();
                JPanel p2=formatLabel(str);
                JPanel left=new JPanel(new BorderLayout());
                left.add(p2,BorderLayout.LINE_START);
                vertical.add(left);
                vertical.add(Box.createVerticalStrut(10));
                j.add(vertical,BorderLayout.PAGE_START);
                jf.validate();
                }
                }   
            }catch(Exception e)
            {
                System.out.println(e);
            }

	}

}


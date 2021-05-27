package com.company;
import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class StartingFrame extends JFrame   {
    int i=0,num=0;
    JProgressBar jb; 
    StartingFrame()
    { 
        jb=new JProgressBar(0,2000);
        jb.setBounds(0,341,800,20);
        jb.setValue(0);
        jb.setStringPainted(true);
        //labek for the text in the
        Label l=new Label("Traverse The Whole Web Just By One Click");
        l.setFont(new Font("Serif",Font.BOLD,40));
        l.setBounds(0,0,800,340);
        l.setBackground(Color.CYAN);
        super.setLocation(200,200);
        super.setTitle("Traworme");
        super.setSize(800,400);
        super.add(l);
        super.add(jb);
        super.setLayout(null);
        //super.setVisible(true);
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }
    public  void listen()
    {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {

            }
            @Override
            public void windowClosing(WindowEvent e) {

                System.exit(0);

            }
            @Override
            public void windowClosed(WindowEvent e){
                System.exit(0);
            }
        });

    }

    public void run() {

        while (i <= 2000) {
            listen();
            jb.setValue(i);
            i = i + 40;
            try {
                Thread.sleep(150);
            } catch (Exception e) {
            }
        }
        super.setVisible(false);
    }
    public  static void iter()
        {
          StartingFrame ob=new StartingFrame();
          ob.setVisible(true);
          ob.run();
        }


}

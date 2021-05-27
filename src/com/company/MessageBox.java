package com.company;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageBox  extends JFrame implements ActionListener {

    MessageBox(String msg){
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JOptionPane.showMessageDialog(this,msg);
        Timer k=new Timer(1000,this::actionPerformed);
        k.start();
    }
    MessageBox(String msg,String t)
    {
        super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JOptionPane.showMessageDialog(this,msg,"Alert",JOptionPane.WARNING_MESSAGE);
        Timer k=new Timer(1000,this::actionPerformed);
        k.start();
    }
    public void actionPerformed(ActionEvent e)
    {
        super.dispose();
    }


}

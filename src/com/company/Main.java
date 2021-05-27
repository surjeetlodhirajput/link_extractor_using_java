package com.company;
import java.awt.*;
import java.awt.event.*;

/*
Writer: Surjeet
contact: Surjeetrajput433@gmail.com
 */
public class Main extends Frame  {
Main()
    {

    super.setLocation(200,200);
    Label label=new Label("Enter or Paste Link Here",Label.CENTER);
    label.setBackground(Color.orange);
    label.setFont(new Font("Serif",Font.BOLD,24));
    label.setBounds(5,30,800,40);
    //contine from here
    TextField tf=new TextField("Enter Link Here");
    tf.setBounds(200,100,320,40);
    //making the choce menu
        CheckboxGroup checkboxGroup=new CheckboxGroup();
        Checkbox page=new Checkbox("Extarct Links From Whole Webpage",checkboxGroup,false);
        Checkbox web=new Checkbox("Extarct Links From Whole Website",checkboxGroup,false);
        page.setBounds(200,180,250,30);
        web.setBounds(200,240,250,30);

    //button
    Button bt=new Button("Search");
    bt.setBounds(200,300,90,40);
    bt.setBackground(Color.RED);
    bt.setFont(new Font("Serif",Font.BOLD,24));


    bt.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!tf.getText().equals("")) {
                if(page.getState()&&!tf.getText().toString().equals("Enter Link Here"))
                {
                    if(Traverse.page(tf.getText().toString())) {
                        MessageBox m = new MessageBox("Traversing Successful");

                    }
                    else{

                        MessageBox m=   new MessageBox("Traversing Not Successfull","s");

                    }

                }

                 else if(web.getState()&&!tf.getText().toString().equals("Enter Link Here"))
                {
                    if(Traverse.web(tf.getText().toString())){//for huge time program start waiting here
                        MessageBox m=new MessageBox("Traversing Successfull");

                    }
                    else{
                        MessageBox m=   new MessageBox("Traversing Not Successfull","s");
                    }
                }
                 else {
                     MessageBox m=   new MessageBox("Please Check the inputs again","s");
                 }
            } else  {
                MessageBox m=   new MessageBox("Please Put The Link First!","s");
            }
        }

    });


    //closing on clicking x
        listen();
    super.add(tf);add(bt);add(label);super.add(page);super.add(web);
    super.setBackground(Color.CYAN);
    //for last values
        super.setSize(800,400);
    super.setResizable(false);
    super.setLayout(null);
    super.setVisible(true);
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
    public static void main(String args[])
    {
        //main fow of the program start here and it is the origin of program
       try {
           StartingFrame.iter();
       }
       catch (Exception e)
       {

       }
        Main object=new Main();

    }
}

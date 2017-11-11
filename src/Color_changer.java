import org.omg.CORBA.INTERNAL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 11/4/2016.
 */
public class Color_changer {
    public static void main (String [] args)
    {
        //makes an instance of Frame and shows it
        Window window= new Window();
        window.setVisible(true);
    }
}
class Window extends JFrame
{
    //stating encapsulated widgets which will be adjusted from beyond the class
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JTextField[] textFields=new JTextField[3];
    private JLabel name;
    //window constructor builds a frame with all needed widgets
    Window()
    {
        setSize(400,150);
        bottomPanel=new JPanel();
        topPanel=new JPanel();

        name=new JLabel("Sample Text", SwingConstants.CENTER);
        name.setForeground(Color.RED);
        JButton btn= new JButton("Paint");
        JButton reset= new JButton("Reset");
        // adding listener for Paint button
        btn.addActionListener(new ButtonHandler(this));
        //adding a short anonymous class as an action listener for button Reset
        reset.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setForeground(Color.RED);
                name.setText("Sample Text");
                for(int i=0;i<3;i++)
                {
                    textFields[i].setText("");
                }
            }
        });

        add(name,BorderLayout.CENTER);
        //initializing text fields and adding them to the panel
        for(int i=0;i<3;i++)
        {
            textFields[i]=new JTextField();
            textFields[i].setColumns(5);
            bottomPanel.add(textFields[i]);
        }
        bottomPanel.add(btn);
        topPanel.add(reset);
        //adding panels to the window
        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel,BorderLayout.SOUTH);
        //makes the frame terminate when the close button is clicked
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("RGB Color changer");
    }
    //sends values written in the text boxes
    public String[] getRGB()
    {
        String[] colors= new String[3];
        int i=0;
        for(JTextField field : textFields)
        {
            colors[i]=field.getText();
            i++;
        }
        return colors;
    }
    //setters
    public void setLabelText(String text)
    {
        name.setText(text);
    }
    public void setLabelColor(Color color)
    {
        name.setForeground(color);
    }
    public void setTextFields(String [] strings)
    {
        int i=0;
        for(JTextField field: textFields)
        {
            field.setText(strings[i]);
            i++;
        }
    }
}
//Button handler used for button Paint in the main frame
class ButtonHandler implements ActionListener
{
    private Window window;
    ButtonHandler(Window window)
    {
        this.window=window;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        checkIfIntegers(window.getRGB());
    }
    private void checkIfIntegers(String text[])
    {
        int i=0;
        //check if integers
        for(String field:text)
        {
            try
            {
                Integer.parseInt(field);
            }
            catch (Exception e)
            {
                text[i]="";
                window.setLabelText("Invalid input");
            }
            i++;
        }
        //if input is empty
        i=0;
        for(String field:text)
        {
            if(field!="")
            {
                if(Integer.parseInt(field)>255) text[i]="255";
                else if (Integer.parseInt(field)<0) text[i]="200";
            }
            i++;
        }
        window.setTextFields(text);
        boolean paint=true;
        for(String field:text)
        {
            if(field=="") paint=false;
        }
        //paint the JLabel
        if(paint) {
            window.setLabelText("Sample Text");
            window.setLabelColor(new Color(Integer.parseInt(text[0]),
                    Integer.parseInt(text[1]),
                    Integer.parseInt(text[2])));
        }
        else window.setLabelColor(Color.BLACK);
    }
}
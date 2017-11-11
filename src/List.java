import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Tomas Kovtun on 11/5/2016.
 */
public class
List {
    public static void main (String [] args)
    {
        //make an instance of frame and set it visible
        ArrayWindow app=new ArrayWindow();
        app.setVisible(true);
    }
}
class ArrayWindow extends JFrame
{
    //state encapsulated widgets which can be referenced from beyond
    private JPanel[] panels;
    private JTextField textField=new JTextField(15);
    private JLabel labelList;
    private ArrayList<String> list;
    private JButton[] buttons;

    ArrayWindow() //window constructor
    {
        //initializing widgets
        list=new ArrayList<String>();
        buttons=new JButton[6];
        buttons[0]=new JButton("Add");
        buttons[1]=new JButton("Display");
        buttons[2]=new JButton("Search");
        buttons[3]=new JButton("Remove first occ");
        buttons[4]=new JButton("Remove all occ");
        buttons[5]=new JButton("Clear");
        labelList=new JLabel("List emulator",SwingConstants.CENTER);
        panels=new JPanel[2];
        for(int i=0;i<2;i++)
            panels[i]=new JPanel();
        for(int i=0;i<6;i++)
            panels[0].add(buttons[i]);

        //adding widgets to the frame
        add(labelList);
        panels[1].add(textField);
        this.add(panels[0], BorderLayout.NORTH);
        this.add(panels[1],BorderLayout.SOUTH);
        this.setSize(600,150);
        //adding listeners
        for(int i=0;i<6;i++)
            buttons[i].addActionListener(new ArrayWindowButtonHandler(this,buttons[i].getText()));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("List");

    }
    //setters
    public String getTextFieldText()
    {
        return textField.getText();
    }
    public ArrayList<String> getArrayList()
    {
        return list;
    }
    //getters
    public void setLabelListText(String text)
    {
        labelList.setText(text);
    }
    public void setTextField(String text)
    {
        textField.setText(text);
    }
}


//Button handler
class ArrayWindowButtonHandler implements ActionListener
{
    private ArrayWindow app;
    private String btn;
    ArrayWindowButtonHandler(ArrayWindow app, String btn)// constructor
    {
        this.app=app;
        this.btn=btn;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String text=app.getTextFieldText();
        ArrayList<String> list= app.getArrayList();
        //button switch
        switch (btn)
        {
            //adds word to the list
            case "Add":
            {
                if(checkIfWord(text)&&!text.equals(""))//conditions for adding
                {
                    list.add(text);
                    app.setLabelListText("Word ‘"+text+"’ has been added to the list");
                }

                else app.setLabelListText("The string "+text+" was not added to the list as it is not a valid word");

                break;
            }
            //displays words beginning with a letter
            case "Display":
            {
                String dis="";
                if(checkIfWord(text)&&!text.startsWith("-")&&text.length()==1)//conditions for display
                {
                    for(String s:app.getArrayList())
                    {
                        if(s.startsWith(text.toLowerCase())||s.startsWith(text.toUpperCase()))
                        {
                            dis+=s+" ";
                        }
                    }

                }
                else dis="Please enter one letter to begin searching";
                app.setLabelListText(dis);
                break;
            }
            //searches words and outputs a number of occasions
            case "Search":
            {
                String dis;
                int count=0;
                if(text.equals(""))
                {
                    dis="The list has "+String.valueOf(list.size())+" element(s)";
                }
                else if (checkIfWord(text))
                {
                    for(String s:list)
                    {
                        if(s.equals(text))
                        {
                            count++;
                        }
                    }
                    dis="\""+text+"\" was found "+String.valueOf(count)+" times";
                }
                else dis="Please enter a valid word";
                app.setLabelListText(dis);
                break;
            }
            //removes first word occasion
            case "Remove first occ":
            {
                boolean found=false;
                String dis="";
                if(text.equals(""))
                {
                    dis="Please enter a value to remove";
                }
                else if (checkIfWord(text))
                {
                    for(String s:list)
                    {
                        if(s.equals(text))
                        {
                            list.remove(s);
                            dis="\""+text+"\" has been removed successfully";
                            found=true;
                            break;
                        }
                    }
                    if (!found) dis="\""+text+"\" has not been found";
                }
                else dis="Please enter a word to remove";

                app.setLabelListText(dis);
                break;
            }
            //removes all occasions of word(case sensitive)
            case "Remove all occ":
            {
                boolean found=false;
                String dis;
                if(text.equals(""))
                {
                    dis="Please enter a value to remove";
                }
                else if(checkIfWord(text))
                {
                    if(list.contains(text)) found=true;
                    list.removeAll(Collections.singleton(text));
                    if (found) dis="All instances of \""+text+"\" have been removed successfully";
                    else dis="\""+text+"\" has not been found";
                }
                else dis="Please enter a word to remove";
                app.setLabelListText(dis);
                break;
            }
            //clear list, resets JLabel
            case "Clear":
            {
                list.clear();
                app.setLabelListText("The list was cleared successfully");
                app.setTextField("");
                break;
            }
        }
        app.setTextField("");
    }
    public boolean checkIfWord(String text)//checks if an entered text is a word
    {
        boolean word=true;
        if(!text.equals(""))
        {
            for(char c:text.toCharArray())
                if(!"qwertyuiopasdfghjklzxcvbnm-QWERTYUIOPASDFGHJKLZXCVBNM".contains(String.valueOf(c)))
                {
                    word=false;
                }
            if(text.startsWith("-"))word = false;
        }
        return word;
    }
}
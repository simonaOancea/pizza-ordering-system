package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by simona on 05/10/18.
 */
public class TextPanel extends JPanel {
    private JTextArea textArea;

    public TextPanel(){
        textArea = new JTextArea();
        setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public void appendText(String text){
        textArea.append(text);

    }

    public void clearText(){
        textArea.setText("");
    }
}

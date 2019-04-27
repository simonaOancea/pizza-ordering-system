package gui; /**
 * Created by simona on 05/10/18.
 */

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new MainFrame();

            }
        });

    }
}

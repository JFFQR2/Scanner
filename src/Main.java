import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quite River on 09.12.16.
 */
public class Main extends JFrame implements ActionListener {
    private GraphicsScanner graphics = null;
    private ScannerJava console = null;
    public Main(){
        super("Scanner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(340,160);
        JButton graphics = new JButton("Graphics");
        JButton console = new JButton("Console");
        graphics.setActionCommand("g");
        console.setActionCommand("c");
        graphics.setPreferredSize(new Dimension(160,120));
        console.setPreferredSize(new Dimension(160,120));
        graphics.addActionListener(this);
        console.addActionListener(this);
        add(graphics);
        add(console);
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("g")) {
            if (graphics==null){
                graphics = new GraphicsScanner();
            }
            else {
                graphics.setVisible(true);
            }
        } else if (e.getActionCommand().equals("c")){
            if (console==null){
                console = new ScannerJava();
                console.start();
            }
        }
    }
}


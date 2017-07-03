
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Quite River on 14.03.17.
 */
public class GraphicsScanner extends JFrame {
   public GraphicsScanner(){
       super("Graphics Scanner");
       setSize(480,440);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       JPanel panel = new Panel();
       add(panel);
       setResizable(false);
       setVisible(true);
   }

   private class Panel extends JPanel{
       private ScannerJava scannerJava = new ScannerJava();
       private final String inDirectory = "Please, enter the copying directory";
       private final String outDirectory = "Please, enter the output directory";
       private final String choose = "Choose";
       private final String No = "No";
       private final String Yes = "Yes";
       private JFileChooser chooser;
       private JButton buttonIn,buttonOut,scanner;
       private JLabel labelIn,labelOut,autoDelete,includeSubDir,maskInf;
       private JTextField fieldIn,fieldOut,mask;
       private JRadioButton aFalse,aTrue,sFalse,sTrue;
       private ButtonGroup groupA,groupS;
       public Panel(){
           setLayout(null);
           setSize(480,440);
           buttonIn = new JButton("Copying directory");
           buttonIn.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   chooser(inDirectory,fieldIn);
               }
           });
           labelIn = new JLabel(inDirectory);
           fieldIn = new JTextField();
           labelOut = new JLabel(outDirectory);
           buttonOut = new JButton("Output Directory");
           buttonOut.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   chooser(outDirectory,fieldOut);
               }
           });
           fieldOut = new JTextField();
           autoDelete = new JLabel("Delete the data from the copied directory ?");
           aFalse = new JRadioButton(No,true);
           aTrue = new JRadioButton(Yes);
           aFalse.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   scannerJava.autoDelete=false;
               }
           });
           aTrue.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   scannerJava.autoDelete=true;
               }
           });
           includeSubDir = new JLabel("Include subdirectories or not ?");
           sFalse = new JRadioButton(No,true);
           sTrue = new JRadioButton(Yes);
           sFalse.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   scannerJava.includeSubdirectories=false;
               }
           });
           sTrue.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   scannerJava.includeSubdirectories=true;
               }
           });
           maskInf = new JLabel("Please, enter a mask \"jpg,png,txt etc\"(without dot)");
           mask = new JTextField();
           scanner = new JButton("Scanner");
           groupA = new ButtonGroup();
           groupA.add(aFalse);
           groupA.add(aTrue);
           groupS = new ButtonGroup();
           groupS.add(sFalse);
           groupS.add(sTrue);
           scanner.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   if (fieldIn.getText().isEmpty()) {
                       JOptionPane.showMessageDialog(Panel.this,
                               inDirectory, "Warning", JOptionPane.WARNING_MESSAGE);
                       return;
                   }
                   if (fieldOut.getText().isEmpty()) {
                       JOptionPane.showMessageDialog(Panel.this,
                               outDirectory, "Warning", JOptionPane.WARNING_MESSAGE);
                       return;
                   }
                   if (mask.getText().isEmpty()) {
                       JOptionPane.showMessageDialog(Panel.this,
                               "Please, enter a mask", "Warning", JOptionPane.WARNING_MESSAGE);
                       return;
                   }
                   scannerJava.inDirectory=fieldIn.getText();
                   scannerJava.outDirectory=fieldOut.getText();
                   scannerJava.mask=mask.getText();
                   if (scannerJava.includeSubdirectories){
                       scannerJava.copy();
                   } else {
                       scannerJava.copyWithOutSubdirectories();
                   }
                   scannerJava.autoDelete(new File(scannerJava.inDirectory));

                   JOptionPane.showMessageDialog(Panel.this, "Files Copied", "Done", JOptionPane.INFORMATION_MESSAGE);
               }
           });
           scannerJava.area.setLineWrap(true);
           scannerJava.area.setWrapStyleWord(true);
           scannerJava.area.setEditable(false);
           JScrollPane panelArea = new JScrollPane(scannerJava.area,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
           panelArea.setPreferredSize(new Dimension(450,100));
           labelIn.setBounds(20,0,260,20);
           buttonIn.setBounds(280,0,160,20);
           fieldIn.setBounds(20,30,400,20);
           labelOut.setBounds(20,50,260,20);
           buttonOut.setBounds(280,50,160,20);
           fieldOut.setBounds(20,80,400,20);
           autoDelete.setBounds(20,110,480,20);
           aFalse.setBounds(20,130,100,20);
           aTrue.setBounds(20,150,100,20);
           includeSubDir.setBounds(20,170,480,20);
           sFalse.setBounds(20,190,100,20);
           sTrue.setBounds(20,210,100,20);
           maskInf.setBounds(20,230,480,20);
           mask.setBounds(20,260,400,20);
           panelArea.setBounds(20,290,440,100);
           scanner.setBounds(190,390,100,20);
           add(labelIn);
           add(buttonIn);
           add(fieldIn);
           add(labelOut);
           add(buttonOut);
           add(fieldOut);
           add(autoDelete);
           add(aFalse);
           add(aTrue);
           add(includeSubDir);
           add(sFalse);
           add(sTrue);
           add(maskInf);
           add(mask);
           add(panelArea);
           add(scanner);
       }
       private void chooser(String title,JTextField field){
           if (chooser == null) {
               chooser = new JFileChooser();
               chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
               chooser.setAcceptAllFileFilterUsed(false);

           }
           chooser.setDialogTitle(title);
           chooser.setApproveButtonText(choose);

           switch (chooser.showOpenDialog(Panel.this)) {
               case JFileChooser.APPROVE_OPTION:
                   field.setText(chooser.getSelectedFile().getAbsolutePath());
           }
       }
   }
}


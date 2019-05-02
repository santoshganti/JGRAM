package edu.met;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFComment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.SwingUtilities;

public class JgramGUI extends JPanel implements ActionListener {

    static private final String newline = "\n";

    private JButton openButton, saveButton;
    private JTextArea log;
    private JFileChooser fc;

    private JgramGUI() {
        super(new BorderLayout());

        //Create the log first, because the action listeners
        //need to refer to it.
        log = new JTextArea(5, 20);
        log.setMargin(new Insets(5, 5, 5, 5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        //Create a file chooser
        fc = new JFileChooser();

        //Uncomment one of the following lines to try a different
        //file selection mode.  The first allows just directories
        //to be selected (and, at least in the Java look and feel,
        //shown).  The second allows both files and directories
        //to be selected.  If you leave these lines commented out,
        //then the default mode (FILES_ONLY) will be used.
        //
        //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        //Create the open button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        openButton = new JButton("Open a File...");
        openButton.addActionListener(this);

        //Create the save button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        saveButton = new JButton("Save a File...");
        saveButton.addActionListener(this);

        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        //Add the buttons and the log to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {

        Grade grd = new Grade();
        int finalGrade = 0;
        int sumWeights = 0;

        //Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(JgramGUI.this);
            ArrayList<Integer> allGrades = new ArrayList<>();
            ArrayList<Integer> allWeights = new ArrayList<>();

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                //Main JGRAM code
                try {
                    XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(file));
                    log.append("Opening: " + file.getName() + "." + newline);

                    String word = "Grade";
                    String commentText;

                    for (final XWPFComment comment : xdoc.getComments()) {
                        commentText = comment.getText();

                        if (commentText.contains(word)) {

                            String[] splitCommentText = commentText.split(",");
                            String grade = splitCommentText[1].substring(splitCommentText[1].indexOf("=") + 1);
                            int weight = Integer.parseInt(splitCommentText[2].substring(splitCommentText[2].indexOf("=") + 1));

                            switch (grade) {
                                case "A":
                                    allGrades.add(grd.A);
                                    allWeights.add(weight);
                                    break;
                                case "A+":
                                    allGrades.add(grd.APlus);
                                    allWeights.add(weight);
                                    break;
                                case "A-":
                                    allGrades.add(grd.AMinus);
                                    allWeights.add(weight);
                                    break;
                                case "B+":
                                    allGrades.add(grd.BPlus);
                                    allWeights.add(weight);
                                    break;
                                case "B":
                                    allGrades.add(grd.B);
                                    allWeights.add(weight);
                                    break;
                                case "B-":
                                    allGrades.add(grd.BMinus);
                                    allWeights.add(weight);
                                    break;
                                case "C":
                                    allGrades.add(grd.C);
                                    allWeights.add(weight);
                                    break;
                                default:
                                    allGrades.add(grd.F);
                                    allWeights.add(weight);
                                    break;
                            }

                            //log.append("Grade is " + grade + newline);
                        }

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());

            //Main Logic Calculation
            for (int grad : allGrades) {
                for (int weight : allWeights) {
                    System.out.println(grad);
                    finalGrade += grad * weight;
                    sumWeights += weight;
                }
            }

            int average = finalGrade / sumWeights;
            log.append("The final grade " + average);

            //Handle save button action.
        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(JgramGUI.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would save the file.
                log.append("Saving: " + file.getName() + "." + newline);
            } else {
                log.append("Save command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("JGRAM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new JgramGUI());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}

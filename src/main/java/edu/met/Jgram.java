package edu.met;

import java.io.FileInputStream;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFComment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.*;

public class Jgram {
    public static void main(String[] args) {

        try {

            FileInputStream file = new FileInputStream("/Users/santoshganti/Desktop/Assignment_664_1_2.docx");

            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(file));
            XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc); //Use this to get the text

            String word = "Grade";
            String commentText;

            XWPFComment[] comments = xdoc.getComments();

            for (final XWPFComment comment : xdoc.getComments()) {
                //final String id = comment.getId();
                //final String author = comment.getAuthor();
                commentText = comment.getText();

                if (commentText.contains(word)) {
                    //yourString.substring(yourString.indexOf("no") + 3 , yourString.length());
//                    String grade = commentText.substring(commentText.indexOf("=") + 1);
                    String[] gradeList = commentText.split(",");
                    String grade = gradeList[1].substring(gradeList[1].indexOf("=")+1);
                    String weight = gradeList[2].substring(gradeList[2].indexOf("=")+1);
                    System.out.println("Grade is " + grade);
                    System.out.println("Weight is "+weight);
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package replace;


import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;


/**
 *
 * @author Deriva-Dev1
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextArea label;
    @FXML 
    private TextArea text;
    @FXML 
    private Button mysql;
    @FXML 
    private Button java;
    @FXML
    private TextField before;
    @FXML
    private TextField after;
    @FXML
    private DatePicker date;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private TextField branchId;
    @FXML
    private TextField clientId;
    @FXML
    private TextField regionId;
    
    @FXML
    private void mySQLMethod(ActionEvent event) {
       
        String query = text.getText();
        String replaceText = query.replace("+","");
      
        String replaceText2 = replaceText.replace("\"","");
       
        if(branchId.getText()!=null){
            replaceText2 = replaceText2.replace("this.branchId", ""+branchId.getText()+"");
        }
        if(clientId.getText()!=null){
            replaceText2 = replaceText2.replace("this.clientId", ""+clientId.getText()+"");
        }
        if(regionId.getText()!=null){
            replaceText2 = replaceText2.replace("this.regionId", ""+regionId.getText()+"");
        }
        if(date.getValue()!=null){
            replaceText2=  replaceText2.replace("'date'", "'"+date.getValue()+"'");
        }else{
            replaceText2=  replaceText2.replace("'date'", "'"+date.getPromptText()+"'");
        }
        if(startDate.getValue()!=null){
            replaceText2=  replaceText2.replace("'this.dateList.get(0)'", "'"+startDate.getValue()+"'");
        }else{
            replaceText2=  replaceText2.replace("'this.dateList.get(0)'", "'"+startDate.getPromptText()+"'");
        }
        if(endDate.getValue()!=null){
            replaceText2=  replaceText2.replace("'this.dateList.get(this.dateList.size()-1)'", "'"+endDate.getValue()+"'");
            replaceText2=  replaceText2.replace("'dateList.get(dateList.size()-1)'", "'"+endDate.getValue()+"'");
        }else{
            replaceText2=  replaceText2.replace("'this.dateList.get(this.dateList.size()-1)'", "'"+endDate.getPromptText()+"'");
            replaceText2=  replaceText2.replace("'dateList.get(dateList.size()-1)'", "'"+endDate.getPromptText()+"'");
        }
         replaceText2=  replaceText2.replace("wantedBranches", " ()");
        String replaceText3 = "";
        int x = replaceText2.indexOf("SELECT");
        for (String line : replaceText2.split("\\n")){
            if (line.length()>x) {
                 replaceText3 += "\n" + line.substring(x, line.length());
            }
        }
        label.setText(replaceText3);
    }
    
    
    @FXML
    private void javaMethod(ActionEvent event) {
       
        String query = text.getText();
        String textLine = "";
        for(String line : text.getText().split("\\n")){
            textLine += "+\""+ line +" \""+"\n";
        }
        label.setText(textLine);
     
    }

    @FXML
    private void replaceMethod(){
        String beforeText = before.getText();
        String afterText = after.getText();
        String query = label.getText();
        String replace = query.replace(beforeText, afterText);
        label.setText(replace);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        String pattern = "yyyy-MM-dd";

        startDate.setPromptText(pattern.toLowerCase());
        startDate.setPromptText("2016-07-13");
        
        startDate.setConverter(new StringConverter<LocalDate>() {
     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

     @Override 
     public String toString(LocalDate date) {
         if (date != null) {
             return dateFormatter.format(date);
         } else {
             return "";
         }
     }

     @Override 
     public LocalDate fromString(String string) {
         if (string != null && !string.isEmpty()) {
             return LocalDate.parse(string, dateFormatter);
         } else {
             return null;
         }
     }
 });
        

        date.setPromptText(pattern.toLowerCase());
        date.setPromptText("2016-07-19");
        date.setConverter(new StringConverter<LocalDate>() {
     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

     @Override 
     public String toString(LocalDate date) {
         if (date != null) {
             return dateFormatter.format(date);
         } else {
             return "";
         }
     }

     @Override 
     public LocalDate fromString(String string) {
         if (string != null && !string.isEmpty()) {
             return LocalDate.parse(string, dateFormatter);
         } else {
             return null;
         }
     }
 });
        endDate.setPromptText(pattern.toLowerCase());
        endDate.setPromptText("2016-07-19");
        endDate.setConverter(new StringConverter<LocalDate>() {
     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

     @Override 
     public String toString(LocalDate date) {
         if (date != null) {
             return dateFormatter.format(date);
         } else {
             return "";
         }
     }

     @Override 
     public LocalDate fromString(String string) {
         if (string != null && !string.isEmpty()) {
             return LocalDate.parse(string, dateFormatter);
         } else {
             return null;
         }
     }
 });
    }    
    
}

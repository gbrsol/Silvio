/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloupload;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 *
 * @author Aluno
 */
public class TelaUploadController implements Initializable {
    
    private Label label;
    @FXML
    private ListView<String> list;
    @FXML
    private TextField txcobranca;
    
    File pasta;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         pasta=new File(txcobranca.getText());
         if (!pasta.exists())
            pasta.mkdir();
         atualizaLista();
    }    

    @FXML
    private void evtUpload(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File arqsel = fc.showOpenDialog(null);
          
        
        try {
           File copia=new File(pasta.getAbsolutePath()+"\\"+arqsel.getName());
           Files.copy(arqsel.toPath(), copia.toPath());
        } catch (Exception e) 
        {
            System.out.println(e);
        }
        atualizaLista();
        //list.getItems().add(arqsel.getName());
        
    }
    private void atualizaLista()
    {   list.getItems().clear();
        File[] arquivos = pasta.listFiles();
        for (File f : arquivos)
            list.getItems().add(f.getName());
    }

    @FXML
    private void evtApagar(ActionEvent event) {
        String arqsel=list.getSelectionModel().getSelectedItem();
        File arqdel = new File(pasta.getAbsolutePath()+"\\"+arqsel);
        arqdel.delete();
        atualizaLista();
    }

    @FXML
    private void evtAbrir(ActionEvent event) {
       String arqsel=list.getSelectionModel().getSelectedItem();
       File arqvis = new File(pasta.getAbsolutePath()+"\\"+arqsel); 
        try {
              java.awt.Desktop.getDesktop().open(arqvis);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}

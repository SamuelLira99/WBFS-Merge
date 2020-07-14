package br.com.slira.cliunixtoolkit.cliconversiontool;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.concurrent.Task;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ImageConverter extends Application {
  ResourceBundle sf = ResourceBundle.getBundle("lang.strings", Locale.getDefault());

  static Stage stg = new Stage();

  private Button btnAbrir = new Button("Abrir");
  private Button btnConvert = new Button("Converter");

  private TextField txtNomeArquivo = new TextField();

  private ComboBox<String> cmbFormadoSaida = new ComboBox<>();

  private VBox layout = new VBox();

  private List<File> arquivos;
  private ArrayList<String> nomesArquivos = new ArrayList<>();
  private ArrayList<String> caminhosArquivos = new ArrayList<>();
  private ArrayList<String> formatos = new ArrayList<>();

  private FileChooser fch = new FileChooser();




  @Override
  public void start(Stage stg) {
    layout.getChildren().addAll(btnAbrir, txtNomeArquivo, cmbFormadoSaida, btnConvert);

    formatos.addAll(Arrays.asList("JPG", "PNG", "PDF", "WEBP", "AVS", "BMP", "GIF", "PSD", "PS", "PS2", "PS3", "PPM", "PBM", "PALM", "OTB", "PICT", "PICON", "PGM", "PDB", "PCX", "P7", "PCD", "PCDS", "MTV", "MONO", "MIFF", "DCX", "DIB", "DPX", "EPDF", "XWD", "XPM", "XBM", "WBMP", "VIFF", "VICAR", "TXT", "TIFF", "SVG", "TGA", "SUN", "SHTML", "SGI", "PTIF", "PCL", "MSL", "M2V", "MPEG", "MNG", "MAT", "JPC", "JP2", "JNG", "JBIG", "HTML", "GRAY", "FPX", "FITS", "FAX", "EPI", "EPS", "EPS2", "EPS3", "EPSF", "EPSI", "EPT"));
    Collections.sort(formatos);

    cmbFormadoSaida.getItems().addAll(formatos);
    cmbFormadoSaida.setValue("PNG");


    Scene scn = new Scene(layout, 500, 350);
    stg.setScene(scn);
    stg.show();

    btnAbrir.setOnAction(e -> abrir());
    btnConvert.setOnAction(e -> {
      if(cmbFormadoSaida.getValue().equals("PDF") || cmbFormadoSaida.getValue().equals("GIF")) {
        merge(caminhosArquivos);
      } else{
        convert(caminhosArquivos);
      }

    });
  }

  public void abrir() {
    fch.setTitle("Escolha os arquivos de imagem");
    arquivos = fch.showOpenMultipleDialog(stg);

    if(arquivos != null) {
      for(int i=0; i<arquivos.size(); i++) {
        nomesArquivos.add(i, String.valueOf(arquivos.get(i).getName()));
        caminhosArquivos.add(i, String.valueOf(arquivos.get(i)));
      }
    }
  }

  public void convert(ArrayList<String> imagensParaConverter) {
    ArrayList<String> magick = new ArrayList<>();
    magick.clear();
    magick.add(0, "magick");
    magick.add(1, "convert");
    magick.add(2, null);
    magick.add(3, null);

    if(arquivos != null) {
      // magick.add(2, imagensParaConverter.get(0));
      // magick.add(3, "final/img/"+txtNomeArquivo.getText()+"."+cmbFormadoSaida.getValue().toLowerCase());
      System.out.println("Array magick -> "+magick);

      Task converter = new Task() {
        @Override
        protected String call() throws Exception{
          for(int i=0; i<imagensParaConverter.size(); i++) {
            magick.remove(2);
            magick.add(2, imagensParaConverter.get(i));
            magick.remove(3);
            magick.add(3, "final/img/"+txtNomeArquivo.getText()+"0"+String.valueOf(i)+"."+cmbFormadoSaida.getValue().toLowerCase());
            ProcessBuilder pb = new ProcessBuilder(magick);
            Process p = pb.start();
            p.waitFor();
          }
          return null;//qqr retorno (Não faz diferença)
        }
        @Override
        protected void succeeded(){
          // alertSuccess(sf.getString("alertSuccessTitle"), sf.getString("alertSuccessContentText"));

        }
        @Override
        protected void failed() {
          // alertError("ERRO", "Ocorreu um erro ao criar o arquivo");
          System.out.println("Ocorreu um erro");
        }
      };
      Thread t = new Thread(converter);
      t.start();
    }//if
  }

  public void merge(ArrayList<String> imagensParaJuntar) {
    ArrayList<String> magick = new ArrayList<>();
    magick.clear();
    magick.add(0, "magick");
    magick.add(1, "convert");

    if(arquivos != null) {
      for(int i=0; i<imagensParaJuntar.size(); i++) {
        magick.add(i+2, imagensParaJuntar.get(i));
      }
      magick.add("final/img/"+txtNomeArquivo.getText()+"."+cmbFormadoSaida.getValue().toLowerCase());
      System.out.println("merge -> "+magick);

      Task converter = new Task() {
        @Override
        protected String call() throws Exception{
          ProcessBuilder pb = new ProcessBuilder(magick);
          Process p = pb.start();
          p.waitFor();
          return null;//qqr retorno (Não faz diferença)
        }
        @Override
        protected void succeeded(){
          // alertSuccess(sf.getString("alertSuccessTitle"), sf.getString("alertSuccessContentText"));

        }
        @Override
        protected void failed() {
          // alertError("ERRO", "Ocorreu um erro ao criar o arquivo");
          System.out.println("Ocorreu um erro");
        }
      };
      Thread t = new Thread(converter);
      t.start();
    }//if
  }

}

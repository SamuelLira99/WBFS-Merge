package br.com.slira.cliunixtoolkit.cliconversiontool;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.input.*;
import javafx.event.Event;
import javafx.concurrent.Task;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Locale;

public class WBFSMerge extends Application {
  /*****Locale*****/
  ResourceBundle sf = ResourceBundle.getBundle("lang.strings", Locale.getDefault());

  static Stage stg = new Stage();

  private Button btnMerge = new Button("Merge!");
  private Button btnAbrir = new Button(sf.getString("btnAbrir"));

  private Label lblID = new Label(sf.getString("lblID"));
  private Label lblArquivos = new Label("Arquivos: ");
  private Label lblStatus = new Label();
  private Label lblDesenvolvedor = new Label(sf.getString("lblDeveloper"));

  private TextField txtID = new TextField();

  private VBox layout = new VBox();
  private HBox hb1 = new HBox();
  private VBox vb1 = new VBox();
  private VBox vb2 = new VBox();

  private List<File> arquivos;

  private ArrayList<String> nomesArquivos = new ArrayList<>();//File.getName()
  private ArrayList<String> caminhosArquivos = new ArrayList<>();//File.getName()

  // private String[] nomesArquivos = new String[2];

  // public static void main(String[] args) {
  //   launch(args);
  // }

  public void start(Stage stage) {
    layout.getChildren().addAll(hb1, lblStatus, lblDesenvolvedor);
    hb1.getChildren().addAll(vb1, vb2);
    vb1.getChildren().addAll(btnAbrir, lblID, txtID, btnMerge);
    vb2.getChildren().add(lblArquivos);
    // hb1.getChildren().addAll(lblID, txtID);

    Image img = new Image("/img/cursor.png");
    layout.setCursor(new ImageCursor(img, img.getWidth()*3, img.getHeight()*3));

    layout.getStylesheets().add("/css/style.css");

    layout.getStyleClass().add("layout");
    // vb2.getStyleClass().add("vb2");
    // vb1.getStyleClass().add("vb1");

    /*****Declarações****/
    btnAbrir.setTranslateX(25); btnAbrir.setTranslateY(25); btnAbrir.setPrefWidth(110);
    lblID.setTranslateX(25); lblID.setTranslateY(50);
    txtID.setTranslateX(25); txtID.setTranslateY(60); txtID.setMaxWidth(110);
    vb1.setTranslateX(0); vb1.setTranslateY(0); vb1.setMinWidth(175); vb1.setMinHeight(225); vb1.setMaxHeight(225);
    vb2.setTranslateX(0); vb2.setTranslateY(0); vb2.setMinWidth(175); vb2.setMinHeight(225); vb2.setMaxHeight(225);
    hb1.setTranslateX(0); hb1.setTranslateY(0); hb1.setMinWidth(350); hb1.setMinHeight(10); hb1.setMaxHeight(10);
    btnMerge.setTranslateX(25); btnMerge.setTranslateY(100); btnMerge.setPrefWidth(110);
    lblArquivos.setTranslateX(70); lblArquivos.setTranslateY(165);
    lblStatus.setTranslateX(25); lblStatus.setTranslateY(195);
    lblDesenvolvedor.setTranslateX(80); lblDesenvolvedor.setTranslateY(205);

    vb2.setVisible(false);

    /*****Início da cena*****/
    Scene scn = new Scene(layout, 350, 250);
    stage.setScene(scn);
    stage.setTitle("WBFS Merge (by S. Lira)");
    stage.setResizable(false);
    stage.show();

    /*****btnAbrir*****/
    btnAbrir.setOnAction(e -> {
      FileChooser fch = new FileChooser();
      fch.setTitle("Abrir arquivos wbfs divididos");
      // arquivo = fch.showOpenDialog(stage);
      // arquivo = fch.showOpenMultipleDialog(stage);
      arquivos = fch.showOpenMultipleDialog(stage);

      if(arquivos != null) {
        lblArquivos.setText("");
        for(int i=0; i<arquivos.size(); i++) {
          // nomesArquivos.add(String.valueOf(arquivos.get(i).getName()));
          System.out.println(arquivos.get(i).getName().charAt(10));

          if(arquivos.get(i).getName().charAt(10) == 's') {
            nomesArquivos.add(0, String.valueOf(arquivos.get(i).getName()));
            caminhosArquivos.add(0, String.valueOf(arquivos.get(i)));
          } else {
            nomesArquivos.add(i, String.valueOf(arquivos.get(i).getName()));
            caminhosArquivos.add(i, String.valueOf(arquivos.get(i)));
          }
        }
        System.out.println("\n********\nARQUIVOS\n"+arquivos);
        System.out.println("\n********\nCAMINHOS_ARQUIVOS\n"+caminhosArquivos);
        System.out.println("\n********\nNOMES_ARQUIVOS\n"+nomesArquivos+"\n");

        for(int i=0; i<nomesArquivos.size(); i++) {
          lblArquivos.setText(lblArquivos.getText()+"\n"+nomesArquivos.get(i));
        }

        // lblArquivos.setText(lblArquivos.getText()+"\n"+nomesArquivos);//.get(0)+"\n"+nomesArquivos.get(1));
        // alertConfirm("Confirme a adição dos arquivos", "\n"+nomesArquivos.get(0)+"\n"+nomesArquivos.get(1), "Os arquivos abaixo estão corretos?");
        vb2.setVisible(true);
}
      // lblStatus.setText(String.valueOf("Nome -> "+arquivo.getName()+"\n"+"Tamanho -> "+(arquivo.length()/1024/1024)+"MB"));
    });

    btnMerge.setOnAction(e -> merge(caminhosArquivos));

  }

  /*****Métodos*****/
  public void merge(ArrayList<String> arquivosParaJuntar) {
    ArrayList<String> cat = new ArrayList<>();
    String arquivoFinal = "final/wbfs/"+txtID.getText().toUpperCase()+".wbfs";
    cat.clear();
    cat.add(0, "cat");


    // arquivoFinal = arquivoFinal.toUpperCase();

    if(arquivos != null) {

      for(int i=0; i<arquivosParaJuntar.size(); i++) {
        cat.add(i+1, arquivosParaJuntar.get(i));

        // System.out.println("ind -> "+i);
      }
      for(int i=0; i<cat.size(); i++) {
        System.out.println("cat["+i+"] -> "+cat.get(i));
      }

      ProcessBuilder pb = new ProcessBuilder(cat);
      pb.redirectOutput(ProcessBuilder.Redirect.to(new File(arquivoFinal)));


      Task juntar = new Task() {
        @Override
        protected String call() throws Exception{
          Process p = pb.start();
          p.waitFor();
          return txtID.getText();//qqr retorno (Não faz diferença)
        }
        @Override
        protected void succeeded(){
          alertSuccess(sf.getString("alertSuccessTitle"), sf.getString("alertSuccessContentText"));

        }
        @Override
        protected void failed() {
          alertError("ERRO", "Ocorreu um erro ao criar o arquivo");
          lblStatus.setText("Ocorreu um erro");
        }
      };
      Thread t = new Thread(juntar);

      if(txtID.getText().length() == 6) {
        lblStatus.setText("Status: Concatenando arquivos...");
        t.setDaemon(true);
        t.start();
      } else {
        alertError("ERRO", "Insira a ID correta para o jogo");
      }
      // if(arquivos != null) //codigo acima
    } else {
      alertError("ERRO", "Nenhum arquivo foi selecionado");
    }

    nomesArquivos.clear();
    caminhosArquivos.clear();
    vb2.setVisible(false);



  } //metodo merge()

  private void alertSuccess(String title, String contentText) {
    Alert a1 = new Alert(Alert.AlertType.INFORMATION);
    a1.setTitle(title);
    a1.setContentText(contentText);
    a1.setHeaderText(null);
    a1.showAndWait();
  }

  private void alertError(String title, String contentText) {
    Alert a1 = new Alert(Alert.AlertType.ERROR);
    a1.setTitle(title);
    a1.setContentText(contentText);
    a1.setHeaderText(null);
    a1.showAndWait();
  }

}

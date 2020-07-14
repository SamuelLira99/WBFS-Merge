package br.com.slira.cliunixtoolkit.cliconversiontool;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.Event;
import java.util.Locale;
import java.util.ResourceBundle;

public class CLIConversionTool extends Application {

  public static void main(String[] args) {
    launch(args);
  }
  /*****Locale*****/
    ResourceBundle sf = ResourceBundle.getBundle("lang.strings", Locale.getDefault());

  /*****Componentes*****/
  private Button btnWBFSMerge = new Button("", new ImageView(new Image("/img/btn/WBFSMerge.png")));
  private Button btnBinMerge = new Button("", new ImageView(new Image("/img/btn/binMerge.png")));
  private Button btnBinCueToISO = new Button("", new ImageView(new Image("/img/btn/binCueToISO.png")));
  private Button btnISOToBinCue = new Button("", new ImageView(new Image("/img/btn/ISOToBinCue.png")));
  private Button btnVideoConverter = new Button("", new ImageView(new Image("/img/btn/videoConverter.png")));
  private Button btnImageConverter = new Button("", new ImageView(new Image("/img/btn/imageConverter.png")));
  private Button btnDocumentConverter = new Button("Document Converter");
  private Label lblHint = new Label(sf.getString("lblDeveloper"));

  private VBox layout = new VBox();
  private HBox hb1 = new HBox();
  private VBox vb1 = new VBox();
  private VBox vb2 = new VBox();
  private VBox vb3 = new VBox();

  private final int BTN_X = 50;
  private final int BTN_Y = 15;


  @Override
  public void start(Stage stage) throws Exception {

    layout.getChildren().addAll(hb1, lblHint);
    hb1.getChildren().addAll(vb1, vb2);
    vb1.getChildren().addAll(btnWBFSMerge, btnBinMerge, btnBinCueToISO, btnISOToBinCue);
    vb2.getChildren().addAll(btnVideoConverter, btnImageConverter, btnDocumentConverter);
    // hb4.getChildren().add(btnDocumentConverter);

    /*****CSS*****/
    layout.getStylesheets().add("/css/main.css");
    layout.getStyleClass().add("layout");

    /*****Posicionamento*****/
    vb1.setTranslateX(50); vb1.setTranslateY(50);
    vb2.setTranslateX(100); vb2.setTranslateY(50);

    btnBinMerge.setTranslateY(BTN_Y);
    btnBinCueToISO.setTranslateY(BTN_Y*2);
    btnISOToBinCue.setTranslateY(BTN_Y*3);
    btnImageConverter.setTranslateY(BTN_Y);
    btnDocumentConverter.setTranslateY(BTN_Y*2);
    lblHint.setTranslateX(10); lblHint.setTranslateY(115);

    /*****Inicio da Cena*****/
    Scene scn = new Scene(layout, 450, 375);
    stage.setScene(scn);
    stage.setTitle("Lira's CLI Toolkit");
    stage.setResizable(false);
    stage.show();

    /*****#*****/
    btnWBFSMerge.setOnAction(e -> new WBFSMerge().start(WBFSMerge.stg));
    btnWBFSMerge.setOnMouseEntered(e -> lblHint.setText(sf.getString("lblHintWBFSMerge")));
    btnWBFSMerge.setOnMouseExited(e -> lblHint.setText(sf.getString("lblDeveloper")));
    btnWBFSMerge.setContentDisplay(ContentDisplay.CENTER);

    // btnBinMerge.setOnAction(e -> new WBFSMerge().start(WBFSMerge.stg));
    btnBinMerge.setOnMouseEntered(e -> lblHint.setText(sf.getString("lblHintBinMerge")));
    btnBinMerge.setOnMouseExited(e -> lblHint.setText(sf.getString("lblDeveloper")));
    btnBinMerge.setContentDisplay(ContentDisplay.CENTER);

    // btnBinCueToISO.setOnAction(e -> new WBFSMerge().start(WBFSMerge.stg));
    btnBinCueToISO.setOnMouseEntered(e -> lblHint.setText(sf.getString("lblHintBinCueToISO")));
    btnBinCueToISO.setOnMouseExited(e -> lblHint.setText(sf.getString("lblDeveloper")));
    btnBinCueToISO.setContentDisplay(ContentDisplay.CENTER);

    // btnISOToBinCue.setOnAction(e -> new WBFSMerge().start(WBFSMerge.stg));
    btnISOToBinCue.setOnMouseEntered(e -> lblHint.setText(sf.getString("lblHintISOToBinCue")));
    btnISOToBinCue.setOnMouseExited(e -> lblHint.setText(sf.getString("lblDeveloper")));
    btnISOToBinCue.setContentDisplay(ContentDisplay.CENTER);

    // btnVideoConverter.setOnAction(e -> new WBFSMerge().start(VideoConverter.stg));
    btnVideoConverter.setOnMouseEntered(e -> lblHint.setText(sf.getString("lblHintVideoConverter")));
    btnVideoConverter.setOnMouseExited(e -> lblHint.setText(sf.getString("lblDeveloper")));
    btnVideoConverter.setContentDisplay(ContentDisplay.CENTER);

    btnImageConverter.setOnAction(e -> new ImageConverter().start(ImageConverter.stg));
    btnImageConverter.setOnMouseEntered(e -> lblHint.setText(sf.getString("lblHintImageConverter")));
    btnImageConverter.setOnMouseExited(e -> lblHint.setText(sf.getString("lblDeveloper")));
    btnImageConverter.setContentDisplay(ContentDisplay.CENTER);

    // btnDocumentConverter.setOnAction(e -> new WBFSMerge().start(DocumentConverter.stg));
    btnDocumentConverter.setOnMouseEntered(e -> lblHint.setText(sf.getString("lblHintDocumentConverter")));
    btnDocumentConverter.setOnMouseExited(e -> lblHint.setText(sf.getString("lblDeveloper")));
    btnDocumentConverter.setContentDisplay(ContentDisplay.CENTER);

    // btnWBFSMerge.setOnAction(e -> new WBFSMerge().start(WBFSMerge.stg));
    btnWBFSMerge.setOnMouseEntered(e -> lblHint.setText(sf.getString("lblHintWBFSMerge")));
    btnWBFSMerge.setOnMouseExited(e -> lblHint.setText(sf.getString("lblDeveloper")));
    btnWBFSMerge.setContentDisplay(ContentDisplay.CENTER);
    // btnWBFSMerge.setGraphic(new ImageView(new Image("/img/btnWBFSMerge.png")));
    // btnWBFSMerge.setMaxWidth(100);btnWBFSMerge.setMaxHeight(15);

  }

  public void teste() {
    System.out.println("Funcionou");
  }


}

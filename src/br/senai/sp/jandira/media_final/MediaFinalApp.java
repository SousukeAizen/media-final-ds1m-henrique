package br.senai.sp.jandira.media_final;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class MediaFinalApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Determinando o tamanho do stage
        stage.setWidth(600);
        stage.setHeight(600);

        //Determinar o titulo do stage (tela/janela)
        stage.setTitle("Média Final");

        // Painel Raiz (root)
        BorderPane root = new BorderPane();


        Label labelTitulo = new Label();
        labelTitulo.setText("Escola \"Prof. Vicente Amato\"");
        // Formatação do texto da Label
        labelTitulo.setStyle("-fx-text-fill: #005AFF;-fx-text-size: 32;-fx-font-weight: bold;");
        labelTitulo.setPadding(new Insets(10, 0, 10, 10));

        //Painel de Resultados - parte de baixo
        VBox painelResultado = new VBox();
        painelResultado.setPadding(new Insets(0, 0, 10, 10));
        Label labelAluno = new Label("Nome do Aluno: ");
        Label labelMediaFinal = new Label("Média Final: ");
        Label labelSituacao = new Label("Situação: ");
        painelResultado.getChildren().addAll(labelAluno, labelMediaFinal, labelSituacao);

        // Painel de Botões

        VBox painelDeBotoes = new VBox();
        painelDeBotoes.setPadding(new Insets(0, 10, 10, 0));
        painelDeBotoes.setSpacing(10);
        Button buttonCalcularMedia = new Button("Calcular Média");
        buttonCalcularMedia.setPrefWidth(150);
        buttonCalcularMedia.setPrefHeight(20);
        Button buttonLimpar = new Button("Limpar");
        buttonLimpar.setPrefWidth(150);
        buttonLimpar.setPrefHeight(20);
        Button buttonSair = new Button("Sair");
        buttonSair.setPrefWidth(150);
        buttonSair.setPrefHeight(20);
        painelDeBotoes.getChildren().addAll(buttonCalcularMedia, buttonLimpar, buttonSair);

        // Painel Formulario

        VBox painelFormulario = new VBox();
        painelFormulario.setPadding(new Insets(0, 0, 10, 10));
        Label labelNome = new Label("Nome do Aluno");
        Label labelNota1 = new Label("Nota 1");
        Label labelNota2 = new Label("Nota 2");
        Label labelNota3 = new Label("Nota 3");
        Label labelNota4 = new Label("Nota 4");
        TextField textFieldNome = new TextField();
        TextField textFieldNota1 = new TextField();
        TextField textFieldNota2 = new TextField();
        TextField textFieldNota3 = new TextField();
        TextField textFieldNota4 = new TextField();
        painelFormulario.getChildren().addAll(
                labelNome, textFieldNome,
                labelNota1, textFieldNota1,
                labelNota2, textFieldNota2,
                labelNota3, textFieldNota3,
                labelNota4, textFieldNota4

        );


        root.setTop(labelTitulo);
        root.setBottom(painelResultado);
        root.setRight(painelDeBotoes);
        root.setLeft(painelFormulario);

        Scene scene = new Scene(root);

        stage.setScene(scene);


        // Mostra o stage (tela)
        stage.show();

        // Eventos de click dos botões
        buttonCalcularMedia.setOnAction(click ->{
            System.out.println("Botão Clicado!");
            String nomeDigitado = textFieldNome.getText();
            labelAluno.setText("Nome do aluno: " + nomeDigitado);

            // CALCULAR MÉDIA
            // OBITER AS NOTAS

            //Criar vetor(array) de notas
            double[] notas = new double[4];
            String[] notasStr = new String[4];

            notasStr[0] = textFieldNota1.getText();
            notas[0] = Double.parseDouble(notasStr[0]);

            notasStr[1] = textFieldNota2.getText();
            notas[1] = Double.parseDouble(notasStr[1]);

            notasStr[2] = textFieldNota3.getText();
            notas[2] = Double.parseDouble(notasStr[2]);

            notasStr[3] = textFieldNota4.getText();
            notas[3] = Double.parseDouble(notasStr[3]);

            //Uso de LOOP while (ENQUANTO)
            double mediaFinal = 0.0;
            int i = 0;
            while (i < notas.length){
                mediaFinal = mediaFinal + notas[i];
                i = i + 1;


            }

            mediaFinal = mediaFinal / notas.length;


            String mediaFinalStr = String.format("%.1f", mediaFinal);

            if (mediaFinal < 4) {
                labelSituacao.setText("Situação: Reprovado");

            } else if (mediaFinal >= 5 ) {
                labelSituacao.setText("Situação: Aprovado");

            } else {
                labelSituacao.setText("Situação: Recuperação");

            }


            // Mostra a Media Final
            labelMediaFinal.setText("Média Final: " + mediaFinal);
        });

        buttonLimpar.setOnAction(click -> {
        textFieldNome.clear();
        textFieldNota1.setText("");
        textFieldNota2.setText("");
        textFieldNota3.setText("");
        textFieldNota4.setText("");
        labelMediaFinal.setText("Média Final: ");
        labelSituacao.setText("Situação: ");
        labelAluno.setText("Nome do Aluno: ");
        textFieldNome.requestFocus();

        });

        buttonSair.setOnAction(click ->{
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Confirmar a saida?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> botaoPrecionado = alerta.showAndWait();

            if (botaoPrecionado.get() == ButtonType.YES) {
                Alert alerta2 = new Alert(Alert.AlertType.INFORMATION, "Até logo!");
                alerta2.showAndWait();
                System.exit(0);
            }
        });


    }
}

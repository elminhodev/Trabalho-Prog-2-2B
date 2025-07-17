package com;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class PrimaryController {

     @FXML private TextField campoEstoque;
    @FXML private TextField campoAdicionar;
    @FXML private TextField campoLitros;
    @FXML private TextField campoValor;

    private Posto posto;

    @FXML
    private void initialize() {
        posto = new Posto();
        atualizarEstoque();
    }

    private void atualizarEstoque() {
        campoEstoque.setText(String.format("%.2f", posto.getEstoque()));
    }

    @FXML
    private void adicionar() {
        try {
            double qtd = Double.parseDouble(campoAdicionar.getText());
            if (posto.adicionar(qtd)) {
                mostrarMensagem("Combustível adicionado.");
            } else {
                mostrarErro("Quantidade inválida.");
            }
            atualizarEstoque();
        } catch (NumberFormatException e) {
            mostrarErro("Entrada inválida.");
        }
    }

    @FXML
    private void abastecerPorLitros() {
        try {
            double litros = Double.parseDouble(campoLitros.getText());
            mostrarMensagem(posto.abastecerPorLitros(litros));
            atualizarEstoque();
        } catch (NumberFormatException e) {
            mostrarErro("Entrada inválida.");
        }
    }

    @FXML
    private void venderPorValor() {
        try {
            double valor = Double.parseDouble(campoValor.getText());
            mostrarMensagem(posto.venderPorValor(valor));
            atualizarEstoque();
        } catch (NumberFormatException e) {
            mostrarErro("Entrada inválida.");
        }
    }

    @FXML
    private void sair() {
        Platform.exit();
        System.exit(0);
    }

    private void mostrarMensagem(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.show();
    }

    private void mostrarErro(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.show();
    }
}

package com.trabalhoprog2;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaControlador {

    @FXML
    private TextField txtNome;
    
    @FXML
    private TextField txtCep;
    
    @FXML
    private TextField txtRua;
    
    @FXML
    private TextField txtNumero;
    
    @FXML
    private TextField txtCidade;
    
    @FXML private TextField txtEstado;
    
    @FXML
    private TextField txtTelefone;

    @FXML
    private Button btnBuscar;
    
    @FXML
    private Button btnLimpar;
    
    @FXML
    private Button btnGravar;
    
    //TableView e Colunas
    @FXML private TableView<Cliente> tabelaClientes;
    @FXML private TableColumn<Cliente, Integer> colunaCodigo;
    @FXML private TableColumn<Cliente, String> colunaNome;
    @FXML private TableColumn<Cliente, String> colunaTelefone;
    @FXML private TableColumn<Cliente, String> colunaCidade;
    @FXML private TableColumn<Cliente, String> colunaEstado;

    private Buscador buscador = new Buscador();
    private ObservableList<Cliente> listaClientes;

    @FXML
    private void initialize() {
        listaClientes = FXCollections.observableArrayList();

        // Faz ligação das colunas com os atributos da classe Cliente
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        // Cidade e Estado vêm de Endereco → precisamos de lambda
        colunaCidade.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEndereco().getCidade()));
        colunaEstado.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEndereco().getEstado()));

        tabelaClientes.setItems(listaClientes);
        
    }

    @FXML
    private void onBuscarCep() {
        String cep = txtCep.getText().trim();
        try {
            Endereco endereco = buscador.buscar(cep);
            txtRua.setText(endereco.getRua());
            txtCidade.setText(endereco.getCidade());
            txtEstado.setText(endereco.getEstado());
        } catch (IllegalArgumentException e) {
            mostrarAlerta("Formato inválido", "Digite o CEP no formato 99999-999.");
        } catch (IOException e) {
            mostrarAlerta("Erro de busca", e.getMessage());
        }
    }

    @FXML
    private void onGravar() {
        try {
            Endereco endereco = new Endereco(
                    txtCep.getText(),
                    txtRua.getText(),
                    txtNumero.getText(),
                    txtCidade.getText(),
                    txtEstado.getText()
            );

            Cliente cliente = new Cliente(
                    txtNome.getText(),
                    endereco,
                    txtTelefone.getText()
            );
            
            listaClientes.add(cliente);
            onLimpar();   
            mostrarAlerta("Sucesso", "Cliente cadastrado:\n" + cliente.toString());

        } catch (Exception e) {
            mostrarAlerta("Erro", "Não foi possível gravar o cliente.");
        }
    }

    @FXML
    private void onLimpar() {
        txtNome.clear();
        txtCep.clear();
        txtRua.clear();
        txtNumero.clear();
        txtCidade.clear();
        txtEstado.clear();
        txtTelefone.clear();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro de Clientes");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
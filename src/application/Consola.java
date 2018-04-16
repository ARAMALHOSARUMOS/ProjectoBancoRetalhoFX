package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import com.rumos.bancoretalho.db.DatabaseOperations;
import com.rumos.bancoretalho.exceptions.CartaoException;
import com.rumos.bancoretalho.exceptions.ClienteException;
import com.rumos.bancoretalho.exceptions.ContaException;
import com.rumos.bancoretalho.exceptions.EmailException;
import com.rumos.bancoretalho.exceptions.MoradaException;
import com.rumos.bancoretalho.exceptions.TelefoneException;
import com.rumos.bancoretalho.impl.*;

public class Consola extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// BorderPane root = new BorderPane();

			primaryStage.setTitle("Consola Banco Retalho!");
			Button btnCriarAgencia = new Button();
			btnCriarAgencia.setText("Criar Agencia");
			btnCriarAgencia.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// System.out.println("Hello World!");
					inserirAgencia(primaryStage);
				}
			});

			Button btnListarAgencias = new Button();
			btnListarAgencias.setText("Listar Agencias");
			btnListarAgencias.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// System.out.println("Hello World!");
					listarAgencias(primaryStage);
				}
			});

			Button btnCriarCliente = new Button();
			btnCriarCliente.setText("Criar Cliente");
			btnCriarCliente.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					inserirCliente(primaryStage);
				}
			});

			Button btnListarClientes = new Button();
			btnListarClientes.setText("Opcoes Cliente");
			btnListarClientes.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					opcoesCliente(primaryStage);
				}
			});

			GridPane grid = new GridPane();
			grid.setVgap(10);
			grid.setHgap(150);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(btnCriarAgencia, 1, 0);
			grid.add(btnListarAgencias, 1, 1);
			grid.add(btnCriarCliente, 1, 2);
			grid.add(btnListarClientes, 1, 3);

			StackPane root = new StackPane();
			root.getChildren().add(grid);
			Scene scene = new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inserirAgencia(Stage primaryStage) {
		try {
			// BorderPane root = new BorderPane();

			primaryStage.setTitle("Agencia");

			Label labelBanco = new Label("Nome banco : ");
			TextField nomeBancoField = new TextField();
			HBox hbBanco = new HBox();
			hbBanco.getChildren().addAll(labelBanco, nomeBancoField);
			hbBanco.setSpacing(10);

			Label label1 = new Label("Nome agencia : ");
			TextField nomeField = new TextField();
			HBox hb = new HBox();
			hb.getChildren().addAll(label1, nomeField);
			hb.setSpacing(10);

			Label label2 = new Label("NIF : ");
			TextField nifField = new TextField();
			HBox hb1 = new HBox();
			hb1.getChildren().addAll(label2, nifField);
			hb1.setSpacing(10);

			Label label3 = new Label("Rua : ");
			TextField ruaField = new TextField();
			HBox hb2 = new HBox();
			hb2.getChildren().addAll(label3, ruaField);
			hb2.setSpacing(10);

			Label label4 = new Label("Localidade : ");
			TextField localidadeField = new TextField();
			HBox hb3 = new HBox();
			hb3.getChildren().addAll(label4, localidadeField);
			hb3.setSpacing(10);

			Label label5 = new Label("Codigo Postal : ");
			TextField cpField = new TextField();
			HBox hb4 = new HBox();
			hb4.getChildren().addAll(label5, cpField);
			hb4.setSpacing(10);

			Label label6 = new Label("País : ");
			TextField paisField = new TextField();
			HBox hb5 = new HBox();
			hb5.getChildren().addAll(label6, paisField);
			hb5.setSpacing(10);

			Button btn = new Button();
			btn.setText("Criar Agencia");
			btn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					Banco banco = DatabaseOperations
							.retrieveBancoByNome(nomeBancoField.getText());

					if (banco.getId() > 0) {

						String nomeAgencia = nomeField.getText();
						int nifAgencia = Integer.parseInt(nifField.getText());
						String ruaAgencia = ruaField.getText();
						String localidadeAgencia = localidadeField.getText();
						String codigoPostalAgencia = cpField.getText();
						String paisAgencia = paisField.getText();

						System.out.println(nomeAgencia + ruaAgencia);

						if (banco.criarAgencia(nomeAgencia, nifAgencia,
								ruaAgencia, localidadeAgencia,
								codigoPostalAgencia, paisAgencia)) {

							nomeField.setText("");
							nifField.setText("");
							ruaField.setText("");
							localidadeField.setText("");
							cpField.setText("");
							paisField.setText("");

						}
					}
				}
			});

			Button btn1 = new Button();
			btn1.setText("Sair");
			btn1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					start(primaryStage);
				}
			});

			GridPane grid = new GridPane();
			grid.setVgap(2);
			grid.setHgap(10);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(hbBanco, 0, 0);
			grid.add(hb, 0, 1);
			grid.add(hb1, 0, 2);
			grid.add(hb2, 0, 3);
			grid.add(hb3, 0, 4);
			grid.add(hb4, 0, 5);
			grid.add(hb5, 0, 6);
			grid.add(btn, 0, 7);
			grid.add(btn1, 0, 8);

			StackPane root = new StackPane();
			root.getChildren().add(grid);
			Scene scene = new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listarAgencias(Stage primaryStage) {
		try {
			// BorderPane root = new BorderPane();

			primaryStage.setTitle("Agencia");

			Label labelBanco = new Label("Código banco : ");
			TextField nomeBancoField = new TextField();
			HBox hbBanco = new HBox();
			hbBanco.getChildren().addAll(labelBanco, nomeBancoField);
			hbBanco.setSpacing(10);

			Button btn = new Button();
			btn.setText("Listar");
			btn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					Agencia[] agencias = DatabaseOperations
							.retrieveAgenciasByBanco(Integer
									.parseInt(nomeBancoField.getText()));

					for (int i = 0; i < agencias.length; i++) {
						System.out.println("Nome Agencia : "
								+ agencias[i].getNome() + " Nif : "
								+ agencias[i].getNif() + " Código : "
								+ agencias[i].getNumero());
					}

					nomeBancoField.setText("");

				}
			});

			Button btn1 = new Button();
			btn1.setText("Sair");
			btn1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					start(primaryStage);
				}
			});

			GridPane grid = new GridPane();
			grid.setVgap(2);
			grid.setHgap(10);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(hbBanco, 0, 0);
			grid.add(btn, 0, 1);
			grid.add(btn1, 0, 2);

			StackPane root = new StackPane();
			root.getChildren().add(grid);
			Scene scene = new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inserirCliente(Stage primaryStage) {
		try {
			// BorderPane root = new BorderPane();

			primaryStage.setTitle("Cliente");

			Label labelAgencia = new Label("Codigo agencia : ");
			TextField agenciaField = new TextField();
			HBox hbAgencia = new HBox();
			hbAgencia.getChildren().addAll(labelAgencia, agenciaField);
			hbAgencia.setSpacing(10);

			Label labelTipo = new Label("Tipo : ");
			TextField tipoField = new TextField();
			HBox hbTipo = new HBox();
			hbTipo.getChildren().addAll(labelTipo, tipoField);
			hbTipo.setSpacing(10);

			Label labelNome = new Label("Nome : ");
			TextField nomeField = new TextField();
			HBox hbNome = new HBox();
			hbNome.getChildren().addAll(labelNome, nomeField);
			hbNome.setSpacing(10);

			Label labelCC = new Label("Cartao Cidadao : ");
			TextField ccField = new TextField();
			HBox hbCC = new HBox();
			hbCC.getChildren().addAll(labelCC, ccField);
			hbCC.setSpacing(10);

			Label labelRua = new Label("Rua : ");
			TextField ruaField = new TextField();
			HBox hbRua = new HBox();
			hbRua.getChildren().addAll(labelRua, ruaField);
			hbRua.setSpacing(10);

			Label labelLocalidade = new Label("Localidade : ");
			TextField localidadeField = new TextField();
			HBox hbLocalidade = new HBox();
			hbLocalidade.getChildren().addAll(labelLocalidade, localidadeField);
			hbLocalidade.setSpacing(10);

			Label labelCP = new Label("Codigo Postal : ");
			TextField cpField = new TextField();
			HBox hbCP = new HBox();
			hbCP.getChildren().addAll(labelCP, cpField);
			hbCP.setSpacing(10);

			Label labelPais = new Label("País : ");
			TextField paisField = new TextField();
			HBox hbPais = new HBox();
			hbPais.getChildren().addAll(labelPais, paisField);
			hbPais.setSpacing(10);

			Label labelProfissao = new Label("Profissão : ");
			TextField profissaoField = new TextField();
			HBox hbProfissao = new HBox();
			hbProfissao.getChildren().addAll(labelProfissao, profissaoField);
			hbProfissao.setSpacing(10);

			Label labelTelefone = new Label("Telefone : ");
			TextField telefoneField = new TextField();
			HBox hbTelefone = new HBox();
			hbTelefone.getChildren().addAll(labelTelefone, telefoneField);
			hbTelefone.setSpacing(10);

			Label labelEmail = new Label("Email : ");
			TextField emailField = new TextField();
			HBox hbEmail = new HBox();
			hbEmail.getChildren().addAll(labelEmail, emailField);
			hbEmail.setSpacing(10);

			Button btn = new Button();
			btn.setText("Criar Cliente");
			btn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					Agencia agencia = DatabaseOperations
							.retrieveAgenciaById(Integer.parseInt(agenciaField
									.getText()));

					if (agencia.getNumero() > 0) {

						String tipoCliente = tipoField.getText();
						String nomeAgencia = nomeField.getText();
						int numeroCartaoCidadaoAgencia = Integer
								.parseInt(ccField.getText());
						String ruaAgencia = ruaField.getText();
						String localidadeAgencia = localidadeField.getText();
						String codigoPostalAgencia = cpField.getText();
						String paisAgencia = paisField.getText();
						String profissaoAgencia = profissaoField.getText();
						int telefoneAgencia = Integer.parseInt(telefoneField
								.getText());
						String emailAgencia = emailField.getText();

						System.out.println(nomeAgencia + ruaAgencia);

						try {
							agencia.criarCliente(tipoCliente, nomeAgencia,
									numeroCartaoCidadaoAgencia, ruaAgencia,
									localidadeAgencia, codigoPostalAgencia,
									paisAgencia, profissaoAgencia,
									telefoneAgencia, emailAgencia);

							agenciaField.setText("");
							tipoField.setText("");
							nomeField.setText("");
							ccField.setText("");
							ruaField.setText("");
							localidadeField.setText("");
							cpField.setText("");
							paisField.setText("");
							profissaoField.setText("");
							telefoneField.setText("");
							emailField.setText("");

						} catch (ClienteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MoradaException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (EmailException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (TelefoneException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ContaException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (CartaoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			});

			Button btn1 = new Button();
			btn1.setText("Sair");
			btn1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					start(primaryStage);
				}
			});

			GridPane grid = new GridPane();
			grid.setVgap(2);
			grid.setHgap(10);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(hbAgencia, 0, 0);
			grid.add(hbTipo, 0, 1);
			grid.add(hbNome, 0, 2);
			grid.add(hbCC, 0, 3);
			grid.add(hbRua, 0, 4);
			grid.add(hbLocalidade, 0, 5);
			grid.add(hbCP, 0, 6);
			grid.add(hbPais, 0, 7);
			grid.add(hbProfissao, 0, 8);
			grid.add(hbTelefone, 0, 9);
			grid.add(hbEmail, 0, 10);
			grid.add(btn, 0, 11);
			grid.add(btn1, 0, 12);

			StackPane root = new StackPane();
			root.getChildren().add(grid);
			Scene scene = new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void opcoesCliente(Stage primaryStage) {
		try {
			// BorderPane root = new BorderPane();

			primaryStage.setTitle("Agencia");

			Label labelCliente = new Label("Código Cliente : ");
			TextField clienteField = new TextField();
			HBox hbCliente = new HBox();
			hbCliente.getChildren().addAll(labelCliente, clienteField);
			hbCliente.setSpacing(10);

			Button btnDeposito = new Button();
			btnDeposito.setText("Efetuar Depósito");
			btnDeposito.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

				}
			});
			
			Button btnLevantamento = new Button();
			btnLevantamento.setText("Efetuar Levantamento");
			btnLevantamento.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

				}
			});
			
			Button btnTransferencia = new Button();
			btnTransferencia.setText("Efetuar Transferência");
			btnTransferencia.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

				}
			});

			Button btnSair = new Button();
			btnSair.setText("Sair");
			btnSair.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					start(primaryStage);
				}
			});

			GridPane grid = new GridPane();
			grid.setVgap(10);
			grid.setHgap(10);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(hbCliente, 10, 0);
			grid.add(btnDeposito, 10, 1);
			grid.add(btnLevantamento, 10, 2);
			grid.add(btnTransferencia, 10, 3);			
			grid.add(btnSair, 10, 4);

			StackPane root = new StackPane();
			root.getChildren().add(grid);
			Scene scene = new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

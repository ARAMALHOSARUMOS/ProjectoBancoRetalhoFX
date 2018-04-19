package application;

import com.rumos.bancoretalho.db.DatabaseOperations;
import com.rumos.bancoretalho.exceptions.CartaoException;
import com.rumos.bancoretalho.exceptions.ClienteException;
import com.rumos.bancoretalho.exceptions.ContaException;
import com.rumos.bancoretalho.exceptions.EmailException;
import com.rumos.bancoretalho.exceptions.MoradaException;
import com.rumos.bancoretalho.exceptions.TelefoneException;
import com.rumos.bancoretalho.impl.Agencia;
import com.rumos.bancoretalho.impl.Banco;
import com.rumos.bancoretalho.impl.Cartao;
import com.rumos.bancoretalho.impl.Cliente;
import com.rumos.bancoretalho.impl.Conta;
import com.rumos.bancoretalho.impl.Movimento;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
			btnListarClientes.setText("Listar Clientes");
			btnListarClientes.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					listarClientes(primaryStage);
				}
			});

			Button btnopcoesCliente = new Button();
			btnopcoesCliente.setText("Opções Cliente");
			btnopcoesCliente.setOnAction(new EventHandler<ActionEvent>() {

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
			grid.add(btnopcoesCliente, 1, 4);

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
			nifField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d{0,9}?")) {
						nifField.setText(oldValue);
					}
				}
			});
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

					Banco banco = DatabaseOperations.retrieveBancoByNome(nomeBancoField.getText());

					if (banco.getId() > 0) {

						String nomeAgencia = nomeField.getText();
						int nifAgencia = Integer.parseInt(nifField.getText());
						String ruaAgencia = ruaField.getText();
						String localidadeAgencia = localidadeField.getText();
						String codigoPostalAgencia = cpField.getText();
						String paisAgencia = paisField.getText();

						System.out.println(nomeAgencia + ruaAgencia);

						if (banco.criarAgencia(nomeAgencia, nifAgencia, ruaAgencia, localidadeAgencia,
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
							.retrieveAgenciasByBanco(Integer.parseInt(nomeBancoField.getText()));

					for (int i = 0; i < agencias.length; i++) {
						System.out.println("Nome Agencia : " + agencias[i].getNome() + " Nif : " + agencias[i].getNif()
								+ " Código : " + agencias[i].getNumero());
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
			agenciaField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d{0,9}?")) {
						agenciaField.setText(oldValue);
					}
				}
			});
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
			telefoneField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d{0,9}?")) {
						telefoneField.setText(oldValue);
					}
				}
			});
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

					Agencia agencia = DatabaseOperations.retrieveAgenciaById(Integer.parseInt(agenciaField.getText()),
							true);

					if (agencia.getNumero() > 0) {

						String tipoCliente = tipoField.getText();
						String nomeAgencia = nomeField.getText();
						int numeroCartaoCidadaoAgencia = Integer.parseInt(ccField.getText());
						String ruaAgencia = ruaField.getText();
						String localidadeAgencia = localidadeField.getText();
						String codigoPostalAgencia = cpField.getText();
						String paisAgencia = paisField.getText();
						String profissaoAgencia = profissaoField.getText();
						int telefoneAgencia = Integer.parseInt(telefoneField.getText());
						String emailAgencia = emailField.getText();

						System.out.println(nomeAgencia + ruaAgencia);

						try {
							agencia.criarCliente(tipoCliente, nomeAgencia, numeroCartaoCidadaoAgencia, ruaAgencia,
									localidadeAgencia, codigoPostalAgencia, paisAgencia, profissaoAgencia,
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

	public void listarClientes(Stage primaryStage) {
		try {
			primaryStage.setTitle("Listar Clientes");

			Label labelCliente = new Label("Código agencia : ");
			TextField agenciaField = new TextField();
			agenciaField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d{0,9}?")) {
						agenciaField.setText(oldValue);
					}
				}
			});
			HBox hbAgencia = new HBox();
			hbAgencia.getChildren().addAll(labelCliente, agenciaField);
			hbAgencia.setSpacing(10);

			Button btn = new Button();
			btn.setText("Listar");
			btn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					Cliente[] clientes = DatabaseOperations
							.retrieveClientesByAgencia(Integer.parseInt(agenciaField.getText()));

					for (int i = 0; i < clientes.length; i++) {
						System.out.println("Nome Agencia : " + clientes[i].getNome() + " CC : "
								+ clientes[i].getNumeroCartaoCidadao() + " Código : " + clientes[i].getId());
					}

					agenciaField.setText("");

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

	public void opcoesCliente(Stage primaryStage) {
		try {
			// BorderPane root = new BorderPane();

			primaryStage.setTitle("Agencia");

			Label labelCliente = new Label("Código Cliente : ");
			TextField clienteField = new TextField();
			HBox hbCliente = new HBox();
			hbCliente.getChildren().addAll(labelCliente, clienteField);
			hbCliente.setSpacing(10);

			Label labelCartao = new Label("Número Cartão : ");
			TextField cartaoField = new TextField();
			cartaoField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d{0,9}?")) {
						cartaoField.setText(oldValue);
					}
				}
			});
			HBox hbCartao = new HBox();
			hbCartao.getChildren().addAll(labelCartao, cartaoField);
			hbCartao.setSpacing(10);

			Label labelValor = new Label("Valor Depósito/Levantamento : ");
			TextField valorField = new TextField();
			valorField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d{0,9}?")) {
						valorField.setText(oldValue);
					}
				}
			});
			HBox hbValor = new HBox();
			hbValor.getChildren().addAll(labelValor, valorField);
			hbValor.setSpacing(10);

			Button btnDeposito = new Button();
			btnDeposito.setText("Efetuar Depósito");
			btnDeposito.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					Conta contaOrdemCliente = DatabaseOperations
							.retrieveContaOrdemCliente(Integer.parseInt(clienteField.getText()));
					Cartao cartaoCliente = DatabaseOperations
							.retrieveCartaoById(Integer.parseInt(cartaoField.getText()), true);
					Agencia agenciaCliente = DatabaseOperations
							.retrieveAgenciaByClienteId(Integer.parseInt(clienteField.getText()), true);

					Cartao[] cartoesConta = contaOrdemCliente.getCartoes();

					boolean isTheSame = false;

					for (int i = 0; i < cartoesConta.length; i++) {
						if (cartoesConta[i].getNumero() == cartaoCliente.getNumero()) {
							isTheSame = true;
							break;
						}
					}

					if (isTheSame) {

						try {
							agenciaCliente.criarMovimento(contaOrdemCliente, cartaoCliente, "Deposito",
									Integer.parseInt(valorField.getText()), null);
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Aviso");
							alert.setHeaderText("Deposito efetuado com sucesso!");
							alert.showAndWait();

							clienteField.setText("");
							cartaoField.setText("");
							valorField.setText("");

						} catch (NumberFormatException | ContaException | CartaoException e) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Erro");
							alert.setHeaderText("Ocorreu um erro ao efetuar o movimento!");
							alert.showAndWait();
							e.printStackTrace();
						}

					} else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Erro");
						alert.setHeaderText("O Cartão indicado não é da conta do cliente!");
						alert.showAndWait();
					}
				}
			});

			Button btnLevantamento = new Button();
			btnLevantamento.setText("Efetuar Levantamento");
			btnLevantamento.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					Conta contaOrdemCliente = DatabaseOperations
							.retrieveContaOrdemCliente(Integer.parseInt(clienteField.getText()));
					Cartao cartaoCliente = DatabaseOperations
							.retrieveCartaoById(Integer.parseInt(cartaoField.getText()), true);
					Agencia agenciaCliente = DatabaseOperations
							.retrieveAgenciaByClienteId(Integer.parseInt(clienteField.getText()), true);

					Cartao[] cartoesConta = contaOrdemCliente.getCartoes();

					boolean isTheSame = false;

					for (int i = 0; i < cartoesConta.length; i++) {
						if (cartoesConta[i].getNumero() == cartaoCliente.getNumero()) {
							isTheSame = true;
							break;
						}
					}

					if (isTheSame) {

						try {
							agenciaCliente.criarMovimento(contaOrdemCliente, cartaoCliente, "Levantamento",
									Integer.parseInt(valorField.getText()), null);
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Aviso");
							alert.setHeaderText("Levantamento efetuado com sucesso!");
							alert.showAndWait();

							clienteField.setText("");
							cartaoField.setText("");
							valorField.setText("");

						} catch (NumberFormatException | CartaoException e) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Erro");
							alert.setHeaderText("Ocorreu um erro ao efetuar o levantamento!");
							alert.showAndWait();
							e.printStackTrace();
						} catch (ContaException e1) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Erro");
							alert.setHeaderText("O saldo é insuficiente para concretizar o movimento!");
							alert.showAndWait();
							e1.printStackTrace();
						}

					} else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Erro");
						alert.setHeaderText("O Cartão indicado não é da conta do cliente!");
						alert.showAndWait();
					}

				}
			});

			Label labelContaOrigem = new Label("Conta Origem : ");
			TextField contaOrigemField = new TextField();
			contaOrigemField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d{0,9}?")) {
						contaOrigemField.setText(oldValue);
					}
				}
			});
			HBox hbContaOrigem = new HBox();
			hbContaOrigem.getChildren().addAll(labelContaOrigem, contaOrigemField);
			hbContaOrigem.setSpacing(10);

			Label labelContaDestino = new Label("Conta Destino : ");
			TextField contaDestinoField = new TextField();
			cartaoField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d{0,9}?")) {
						contaDestinoField.setText(oldValue);
					}
				}
			});
			HBox hbContaDestino = new HBox();
			hbContaDestino.getChildren().addAll(labelContaDestino, contaDestinoField);
			hbContaDestino.setSpacing(10);

			Label labelValorT = new Label("Valor Transferência : ");
			TextField valorTFieldT = new TextField();
			valorField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d{0,9}?")) {
						valorTFieldT.setText(oldValue);
					}
				}
			});
			HBox hbValorT = new HBox();
			hbValorT.getChildren().addAll(labelValorT, valorTFieldT);
			hbValorT.setSpacing(10);

			Button btnTransferencia = new Button();
			btnTransferencia.setText("Efetuar Transferência");
			btnTransferencia.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					Conta contaOrdemCliente = DatabaseOperations
							.retrieveContaOrdemCliente(Integer.parseInt(clienteField.getText()));

					if (Long.parseLong(contaOrigemField.getText()) == contaOrdemCliente.getNumero()
							|| Long.parseLong(contaDestinoField.getText()) == contaOrdemCliente.getNumero()) {

						Agencia agenciaCliente = DatabaseOperations
								.retrieveAgenciaByClienteId(Integer.parseInt(clienteField.getText()), true);

						Conta contaDestino = DatabaseOperations
								.retrieveContaById(Long.parseLong(contaDestinoField.getText()), true);

						try {
							agenciaCliente.criarMovimento(contaOrdemCliente, null, Movimento.CONST_TRANSFERENCIA,
									Long.parseLong(valorTFieldT.getText()), contaDestino);
						} catch (NumberFormatException | ContaException | CartaoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Erro");
						alert.setHeaderText("Uma das contas deve ser a conta à ordem do cliente!");
						alert.showAndWait();
					}

				}
			});

			Button btnListaMovimentos = new Button();
			btnListaMovimentos.setText("Lista movimentos conta ordem");
			btnListaMovimentos.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					
					Conta contaOrdemCliente = DatabaseOperations
							.retrieveContaOrdemCliente(Integer.parseInt(clienteField.getText()));
					
					ListaMovimentosConta(primaryStage, contaOrdemCliente.getNumero());
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

			HBox hbButtons = new HBox();
			hbButtons.setSpacing(10.0);
			hbButtons.getChildren().addAll(btnDeposito, btnLevantamento);

			GridPane grid = new GridPane();
			grid.setVgap(10);
			grid.setHgap(5);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(hbCliente, 1, 0);
			grid.add(hbCartao, 1, 1);
			grid.add(hbValor, 1, 2);
			grid.add(hbButtons, 1, 3);

			grid.add(hbContaOrigem, 1, 5);
			grid.add(hbContaDestino, 1, 6);
			grid.add(hbValorT, 1, 7);
			grid.add(btnTransferencia, 1, 8);
			grid.add(btnListaMovimentos, 1, 10);
			grid.add(btnSair, 1, 12);

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

	public void ListaMovimentosConta(Stage stage, long numeroConta) {
		Scene scene = new Scene(new Group());
		stage.setWidth(450);
		stage.setHeight(550);

		TableView<MovimentoConta> table = new TableView<>();
		
		ObservableList<MovimentoConta> data = FXCollections.observableArrayList();
		
		Movimento[] mov = DatabaseOperations.retrieveMovimentosConta(numeroConta, true);

		for (int i=0; i< mov.length; i++) {
			data.add(new MovimentoConta(mov[i].getData().toString(), Long.toString(mov[i].getValor())));
		}
		
		table.setEditable(true);

		TableColumn<MovimentoConta, String> firstNameCol = new TableColumn<>("Movimento");
		firstNameCol.setMinWidth(100);
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

		firstNameCol.setCellFactory(TextFieldTableCell.<MovimentoConta>forTableColumn());
		firstNameCol.setOnEditCommit((CellEditEvent<MovimentoConta, String> t) -> {
			((MovimentoConta) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFirstName(t.getNewValue());
		});

		TableColumn<MovimentoConta, String> lastNameCol = new TableColumn<>("Valor");
		lastNameCol.setMinWidth(100);
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
//		lastNameCol.setCellFactory(TextFieldTableCell.<MovimentoConta>forTableColumn());
//		lastNameCol.setOnEditCommit((CellEditEvent<MovimentoConta, String> t) -> {
//			((MovimentoConta) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLastName(t.getNewValue());
//		});
		table.setItems(data);
		table.getColumns().addAll(firstNameCol, lastNameCol);

		Button btnSair = new Button();
		btnSair.setText("Sair");
		btnSair.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				opcoesCliente(stage);
			}
		});
		
		
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(table);
		vbox.getChildren().addAll(btnSair);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		stage.setScene(scene);
		stage.show();
	}

	public static class MovimentoConta {
		private final SimpleStringProperty firstName;
		private final SimpleStringProperty lastName;

		private MovimentoConta(String fName, String lName) {
			this.firstName = new SimpleStringProperty(fName);
			this.lastName = new SimpleStringProperty(lName);
		}

		public String getFirstName() {
			return firstName.get();
		}

		public void setFirstName(String fName) {
			firstName.set(fName);
		}

		public String getLastName() {
			return lastName.get();
		}

		public void setLastName(String fName) {
			lastName.set(fName);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

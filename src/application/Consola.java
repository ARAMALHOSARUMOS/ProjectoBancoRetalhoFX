package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.rumos.bancoretalho.db.DatabaseOperations;
import com.rumos.bancoretalho.exceptions.CartaoException;
import com.rumos.bancoretalho.exceptions.ClienteException;
import com.rumos.bancoretalho.exceptions.ContaException;
import com.rumos.bancoretalho.exceptions.EmailException;
import com.rumos.bancoretalho.exceptions.MoradaException;
import com.rumos.bancoretalho.exceptions.PlafondException;
import com.rumos.bancoretalho.exceptions.TelefoneException;
import com.rumos.bancoretalho.impl.Agencia;
import com.rumos.bancoretalho.impl.Banco;
import com.rumos.bancoretalho.impl.Cartao;
import com.rumos.bancoretalho.impl.Cliente;
import com.rumos.bancoretalho.impl.Conta;
import com.rumos.bancoretalho.impl.Deposito;
import com.rumos.bancoretalho.impl.Juros;
import com.rumos.bancoretalho.impl.Levantamento;
import com.rumos.bancoretalho.impl.Movimento;
import com.rumos.bancoretalho.impl.Transferencia;

public class Consola extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// BorderPane root = new BorderPane();

			primaryStage.setTitle("Consola Banco Retalho!");

			Button btnopcoesBanco = new Button();
			btnopcoesBanco.setText("Opções Banco");
			btnopcoesBanco.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					opcoesBanco(primaryStage);
				}
			});

			Button btnopcoesAgencia = new Button();
			btnopcoesAgencia.setText("Opções Agencia");
			btnopcoesAgencia.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					opcoesAgencia(primaryStage);
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
			grid.add(btnopcoesBanco, 1, 4);
			grid.add(btnopcoesAgencia, 1, 6);
			grid.add(btnopcoesCliente, 1, 8);

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

	public void opcoesBanco(Stage primaryStage) {

		try {
			primaryStage.setTitle("Consola Banco Retalho!");

			Button btnCriarBanco = new Button();
			btnCriarBanco.setText("Criar Banco");
			btnCriarBanco.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					criarBanco(primaryStage);
				}
			});
			
			Button btnListarBancos = new Button();
			btnListarBancos.setText("Listar Bancos");
			btnListarBancos.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// System.out.println("Hello World!");
					listaBancos(primaryStage);
				}
			});			

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

			Button btnPeriodo = new Button();
			btnPeriodo.setText("Incrementar Periodo");
			btnPeriodo.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					incrementaPeriodo(primaryStage);

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
			grid.setHgap(150);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(btnCriarBanco, 1, 0);
			grid.add(btnListarBancos, 1, 2);
			grid.add(btnCriarAgencia, 1, 4);
			grid.add(btnListarAgencias, 1, 6);
			grid.add(btnPeriodo, 1, 8);
			grid.add(btnSair, 1, 10);

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

	public void criarBanco(Stage primaryStage) {

		try {

			primaryStage.setTitle("Criar Banco");

			Label labelBanco = new Label("Nome do Banco : ");
			TextField bancoField = new TextField();
			HBox hbBanco = new HBox();
			hbBanco.getChildren().addAll(labelBanco, bancoField);
			hbBanco.setSpacing(10);

			Label labelNIF = new Label("Número identificação fiscal : ");
			TextField nifField = new TextField();
			nifField.setText("0");
			nifField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(
						ObservableValue<? extends String> observable,
						String oldValue, String newValue) {
					if (!newValue.matches("\\d{0,9}?")) {
						nifField.setText(oldValue);
					}
				}
			});
			HBox hbNif = new HBox();
			hbNif.getChildren().addAll(labelNIF, nifField);
			hbNif.setSpacing(10);

			Label labelJuros = new Label("Percentagem Calculo Juros : ");
			TextField jurosField = new TextField();
			jurosField.setText("0");
			jurosField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(
						ObservableValue<? extends String> observable,
						String oldValue, String newValue) {
					if (!newValue.matches("\\d{0,9}([\\.]\\d{0,9})?")) {
						jurosField.setText(oldValue);
					}
				}
			});
			HBox hbJuros = new HBox();
			hbJuros.getChildren().addAll(labelJuros, jurosField);
			hbJuros.setSpacing(10);

			Button btnCriarBanco = new Button();
			btnCriarBanco.setText("Criar Banco");
			btnCriarBanco.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					DatabaseOperations.insertBanco(bancoField.getText(),
							Integer.parseInt(nifField.getText()),
							Float.parseFloat(jurosField.getText()));

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Aviso");
					alert.setHeaderText("Banco criado!");
					alert.showAndWait();

					bancoField.setText("");
					nifField.setText("");
					jurosField.setText("");

				}
			});

			Button btnSair = new Button();
			btnSair.setText("Sair");
			btnSair.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					opcoesBanco(primaryStage);
				}
			});

			HBox hbButtons = new HBox();
			hbButtons.setSpacing(10.0);
			hbButtons.getChildren().addAll(btnCriarBanco);

			GridPane grid = new GridPane();
			grid.setVgap(10);
			grid.setHgap(5);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(hbBanco, 1, 0);
			grid.add(hbNif, 1, 2);
			grid.add(hbJuros, 1, 4);
			grid.add(hbButtons, 1, 6);

			grid.add(btnSair, 1, 8);

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

	public void incrementaPeriodo(Stage primaryStage) {

		try {

			primaryStage.setTitle("Incrementar Periodo");

			Label labelBanco = new Label("Nome Banco : ");
			TextField bancoField = new TextField();
			HBox hbBanco = new HBox();
			hbBanco.getChildren().addAll(labelBanco, bancoField);
			hbBanco.setSpacing(10);

			Button btnIncrementa = new Button();
			btnIncrementa.setText("Incrementar");
			btnIncrementa.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					Banco banco = DatabaseOperations
							.retrieveBancoByNome(bancoField.getText());

					int novoPeriodo = banco.getPeriodo() + 1;

					Agencia[] agenciasBanco = DatabaseOperations
							.retrieveAgenciasByBanco(banco.getId(), true);

					for (int i = 0; i < agenciasBanco.length; i++) {

						Cliente[] clientesAgencia = agenciasBanco[i]
								.getClientes();

						for (int j = 0; j < clientesAgencia.length; j++) {

							Conta[] contasCliente = clientesAgencia[j]
									.getContas();

							for (int k = 0; k < contasCliente.length; k++) {

								if (contasCliente[k].getTipoConta().equals(
										Conta.CONST_CONTA_INVESTIMENTO)
										|| contasCliente[k]
												.getTipoConta()
												.equals(Conta.CONST_CONTA_POUPANCA)
										|| contasCliente[k]
												.getTipoConta()
												.equals(Conta.CONST_CONTA_PRAZO)) {

									int valorJuros = (int) (contasCliente[k]
											.getSaldo() * banco.getPercJuros());

									try {
										try {
											agenciasBanco[i].criarMovimento(
													contasCliente[k], null,
													Movimento.CONST_JUROS,
													valorJuros, null);
										} catch (PlafondException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}

										Alert alert = new Alert(
												AlertType.INFORMATION);
										alert.setTitle("Aviso");
										alert.setHeaderText("Depositado(s) "
												+ valorJuros
												+ "€ de Juros na conta "
												+ contasCliente[k].getNumero()
												+ "!");
										alert.showAndWait();

									} catch (ContaException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (CartaoException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}

								// Atualizar o plafond das cartoes de
								// crédito!

								Cartao[] cartoesConta = contasCliente[k]
										.getCartoes();

								for (int z = 0; z < cartoesConta.length; z++) {

									if (cartoesConta[z].getTipo().equals(
											Cartao.CONST_CARTAO_CREDITO)) {
										if (cartoesConta[z].getValorPlafond() < cartoesConta[z]
												.getPlafond()) {

											int valorAtualizarPlafond = cartoesConta[z]
													.getPlafond()
													- cartoesConta[z]
															.getValorPlafond();
											try {
												agenciasBanco[i]
														.criarMovimento(
																contasCliente[k],
																cartoesConta[z],
																Movimento.CONST_ATUALIZACAO,
																valorAtualizarPlafond,
																null);
											} catch (PlafondException e) {
												Alert alert = new Alert(
														AlertType.INFORMATION);
												alert.setTitle("Aviso");
												alert.setHeaderText("Plafond do cartao "
														+ cartoesConta[z]
																.getNumero()
														+ "não atualizado!");
												alert.showAndWait();
											} catch (ContaException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											} catch (CartaoException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									}

								}

							}

						}

					}

					banco.setPeriodo(novoPeriodo);

					DatabaseOperations.updatePeriodoBanco(banco, true);

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Aviso");
					alert.setHeaderText("O periodo do banco foi atualizado para "
							+ novoPeriodo + "!");
					alert.showAndWait();

					bancoField.setText("");

				}
			});

			Button btnSair = new Button();
			btnSair.setText("Sair");
			btnSair.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					opcoesBanco(primaryStage);
				}
			});

			HBox hbButtons = new HBox();
			hbButtons.setSpacing(10.0);
			hbButtons.getChildren().addAll(btnIncrementa);

			GridPane grid = new GridPane();
			grid.setVgap(10);
			grid.setHgap(5);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(hbBanco, 1, 0);
			grid.add(hbButtons, 1, 6);

			grid.add(btnSair, 1, 8);

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

	public void opcoesAgencia(Stage primaryStage) {
		try {
			primaryStage.setTitle("Consola Banco Retalho!");

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

			Button btnCriarNovaConta = new Button();
			btnCriarNovaConta.setText("Criar Nova Conta");
			btnCriarNovaConta.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					inserirConta(primaryStage);

				}
			});

			Button btnListarContas = new Button();
			btnListarContas.setText("Listar Contas Clientes");
			btnListarContas.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					listarContas(primaryStage);
				}
			});

			Button btnCriarNovaCartao = new Button();
			btnCriarNovaCartao.setText("Criar Novo Cartao");
			btnCriarNovaCartao.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					inserirCartao(primaryStage);
				}
			});

			Button btnListarCartoes = new Button();
			btnListarCartoes.setText("Listar Cartoes Contas");
			btnListarCartoes.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					listarCartoes(primaryStage);
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
			grid.setHgap(150);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(btnCriarCliente, 1, 0);
			grid.add(btnListarClientes, 1, 2);
			grid.add(btnCriarNovaConta, 1, 4);
			grid.add(btnListarContas, 1, 6);
			grid.add(btnCriarNovaCartao, 1, 8);
			grid.add(btnListarCartoes, 1, 10);
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
				public void changed(
						ObservableValue<? extends String> observable,
						String oldValue, String newValue) {
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
					opcoesBanco(primaryStage);
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
			TextField codigoBancoField = new TextField();
			HBox hbBanco = new HBox();
			hbBanco.getChildren().addAll(labelBanco, codigoBancoField);
			hbBanco.setSpacing(10);

			Button btn = new Button();
			btn.setText("Listar");
			btn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					listaAgenciasBanco(primaryStage,
							Integer.parseInt(codigoBancoField.getText()));

					codigoBancoField.setText("");

				}
			});

			Button btn1 = new Button();
			btn1.setText("Sair");
			btn1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					opcoesBanco(primaryStage);
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
			agenciaField.textProperty().addListener(
					new ChangeListener<String>() {
						@Override
						public void changed(
								ObservableValue<? extends String> observable,
								String oldValue, String newValue) {
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
			telefoneField.textProperty().addListener(
					new ChangeListener<String>() {
						@Override
						public void changed(
								ObservableValue<? extends String> observable,
								String oldValue, String newValue) {
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

					Agencia agencia = DatabaseOperations.retrieveAgenciaById(
							Integer.parseInt(agenciaField.getText()), true);

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
					opcoesAgencia(primaryStage);
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
			agenciaField.textProperty().addListener(
					new ChangeListener<String>() {
						@Override
						public void changed(
								ObservableValue<? extends String> observable,
								String oldValue, String newValue) {
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

					listaClienteAgencia(primaryStage,
							Integer.parseInt(agenciaField.getText()));

					agenciaField.setText("");

				}
			});

			Button btn1 = new Button();
			btn1.setText("Sair");
			btn1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					opcoesAgencia(primaryStage);
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

	public void listarContas(Stage primaryStage) {
		try {
			primaryStage.setTitle("Listar Contas");

			Label labelCliente = new Label("Código cliente : ");
			TextField clienteField = new TextField();
			clienteField.textProperty().addListener(
					new ChangeListener<String>() {
						@Override
						public void changed(
								ObservableValue<? extends String> observable,
								String oldValue, String newValue) {
							if (!newValue.matches("\\d{0,9}?")) {
								clienteField.setText(oldValue);
							}
						}
					});
			HBox hbAgencia = new HBox();
			hbAgencia.getChildren().addAll(labelCliente, clienteField);
			hbAgencia.setSpacing(10);

			Button btn = new Button();
			btn.setText("Listar");
			btn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					listaContasCliente(primaryStage,
							Integer.parseInt(clienteField.getText()));

					clienteField.setText("");

				}
			});

			Button btn1 = new Button();
			btn1.setText("Sair");
			btn1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					opcoesAgencia(primaryStage);
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

	public void listarCartoes(Stage primaryStage) {
		try {
			primaryStage.setTitle("Listar Cartoes");

			Label labelConta = new Label("Código conta : ");
			TextField contaField = new TextField();
			contaField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(
						ObservableValue<? extends String> observable,
						String oldValue, String newValue) {
					if (!newValue.matches("\\d{0,9}?")) {
						contaField.setText(oldValue);
					}
				}
			});
			HBox hbConta = new HBox();
			hbConta.getChildren().addAll(labelConta, contaField);
			hbConta.setSpacing(10);

			Button btn = new Button();
			btn.setText("Listar");
			btn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					listaCartoesConta(primaryStage,
							Integer.parseInt(contaField.getText()));

					contaField.setText("");

				}
			});

			Button btn1 = new Button();
			btn1.setText("Sair");
			btn1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					opcoesAgencia(primaryStage);
				}
			});

			GridPane grid = new GridPane();
			grid.setVgap(2);
			grid.setHgap(10);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(hbConta, 0, 0);
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

	public void inserirConta(Stage primaryStage) {

		try {

			primaryStage.setTitle("Criar Conta");

			Label labelCliente = new Label("Código Cliente : ");
			TextField clienteField = new TextField();
			HBox hbCliente = new HBox();
			hbCliente.getChildren().addAll(labelCliente, clienteField);
			hbCliente.setSpacing(10);

			Label labelTipo = new Label("Tipo de conta : ");
			ObservableList<String> options = FXCollections.observableArrayList(
					Conta.CONST_CONTA_INVESTIMENTO, Conta.CONST_CONTA_POUPANCA,
					Conta.CONST_CONTA_PRAZO);
			final ComboBox<String> comboBoxTipo = new ComboBox<String>(options);
			HBox hbTipo = new HBox();
			hbTipo.getChildren().addAll(labelTipo, comboBoxTipo);
			hbTipo.setSpacing(10);

			Label labelData = new Label("Data abertura : ");
			TextField dataField = new TextField();
			LocalDate date = LocalDate.now();
			String textDate = date.format(DateTimeFormatter.BASIC_ISO_DATE);
			dataField.setText(textDate);
			dataField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(
						ObservableValue<? extends String> observable,
						String oldValue, String newValue) {
					if (!newValue.matches("\\d{0,9}?")) {
						dataField.setText(oldValue);
					}
				}
			});
			HBox hbData = new HBox();
			hbData.getChildren().addAll(labelData, dataField);
			hbData.setSpacing(10);

			Button btnCriarConta = new Button();
			btnCriarConta.setText("Criar Conta");
			btnCriarConta.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					DatabaseOperations.insertConta(
							Integer.parseInt(clienteField.getText()),
							comboBoxTipo.getValue().toString(),
							Integer.parseInt(dataField.getText()));

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Aviso");
					alert.setHeaderText("Conta criada!");
					alert.showAndWait();

					clienteField.setText("");
					dataField.setText("");
				}
			});

			Button btnSair = new Button();
			btnSair.setText("Sair");
			btnSair.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					opcoesAgencia(primaryStage);
				}
			});

			HBox hbButtons = new HBox();
			hbButtons.setSpacing(10.0);
			hbButtons.getChildren().addAll(btnCriarConta);

			GridPane grid = new GridPane();
			grid.setVgap(10);
			grid.setHgap(5);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(hbCliente, 1, 0);
			grid.add(hbTipo, 1, 2);
			grid.add(hbData, 1, 4);
			grid.add(hbButtons, 1, 6);

			grid.add(btnSair, 1, 8);

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

	public void inserirCartao(Stage primaryStage) {

		try {

			primaryStage.setTitle("Criar Cartao Crédito");

			Label labelConta = new Label("Código Conta : ");
			TextField contaField = new TextField();
			contaField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(
						ObservableValue<? extends String> observable,
						String oldValue, String newValue) {
					if (!newValue.matches("\\d{0,9}?")) {
						contaField.setText(oldValue);
					}
				}
			});
			HBox hbConta = new HBox();
			hbConta.getChildren().addAll(labelConta, contaField);
			hbConta.setSpacing(10);

			Label labelPlafond = new Label("Plafond : ");
			TextField plafondField = new TextField();
			plafondField.textProperty().addListener(
					new ChangeListener<String>() {
						@Override
						public void changed(
								ObservableValue<? extends String> observable,
								String oldValue, String newValue) {
							if (!newValue.matches("\\d{0,9}?")) {
								plafondField.setText(oldValue);
							}
						}
					});
			HBox hbPlafond = new HBox();
			hbPlafond.getChildren().addAll(labelPlafond, plafondField);
			hbPlafond.setSpacing(10);

			Button btnCriarCartao = new Button();
			btnCriarCartao.setText("Criar Cartao");
			btnCriarCartao.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					DatabaseOperations.insertCartao(
							Integer.parseInt(contaField.getText()),
							Cartao.CONST_CARTAO_CREDITO,
							Integer.parseInt(plafondField.getText()),
							Integer.parseInt(plafondField.getText()));

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Aviso");
					alert.setHeaderText("Cartao criado!");
					alert.showAndWait();

					contaField.setText("");
					plafondField.setText("");
				}
			});

			Button btnSair = new Button();
			btnSair.setText("Sair");
			btnSair.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					opcoesAgencia(primaryStage);
				}
			});

			HBox hbButtons = new HBox();
			hbButtons.setSpacing(10.0);
			hbButtons.getChildren().addAll(btnCriarCartao);

			GridPane grid = new GridPane();
			grid.setVgap(10);
			grid.setHgap(5);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(hbConta, 1, 0);
			grid.add(hbPlafond, 1, 2);
			grid.add(hbButtons, 1, 4);

			grid.add(btnSair, 1, 6);

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
			primaryStage.setTitle("Consola Banco Retalho!");

			Button btnDeposito = new Button();
			btnDeposito.setText("Efetuar Deposito");
			btnDeposito.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					deposito(primaryStage);
				}
			});

			Button btnLevantamento = new Button();
			btnLevantamento.setText("Efetuar Levantamento");
			btnLevantamento.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					levantamento(primaryStage);
				}
			});

			Button btnOpcaoTransferencia = new Button();
			btnOpcaoTransferencia.setText("Efetuar Transferencia");
			btnOpcaoTransferencia.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					transferencia(primaryStage);
				}
			});

			Button btnListaMovs = new Button();
			btnListaMovs.setText("Lista Movimentos Conta Corrente");
			btnListaMovs.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					listaMovimentos(primaryStage);
				}
			});
			
			Button btnListaMovsConta = new Button();
			btnListaMovsConta.setText("Lista Movimentos por Conta");
			btnListaMovsConta.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					listaMovimentosByConta(primaryStage);
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
			grid.setHgap(150);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(btnDeposito, 1, 0);
			grid.add(btnLevantamento, 1, 2);
			grid.add(btnOpcaoTransferencia, 1, 4);
			grid.add(btnListaMovs, 1, 6);
			grid.add(btnListaMovsConta, 1, 8);
			grid.add(btnSair, 1, 10);

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

	public void deposito(Stage primaryStage) {
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
			cartaoField.textProperty().addListener(
					new ChangeListener<String>() {
						@Override
						public void changed(
								ObservableValue<? extends String> observable,
								String oldValue, String newValue) {
							if (!newValue.matches("\\d{0,9}?")) {
								cartaoField.setText(oldValue);
							}
						}
					});
			HBox hbCartao = new HBox();
			hbCartao.getChildren().addAll(labelCartao, cartaoField);
			hbCartao.setSpacing(10);

			Label labelValor = new Label("Valor Depósito : ");
			TextField valorField = new TextField();
			valorField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(
						ObservableValue<? extends String> observable,
						String oldValue, String newValue) {
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
							.retrieveContaOrdemCliente(Integer
									.parseInt(clienteField.getText()));
					Cartao cartaoCliente = DatabaseOperations
							.retrieveCartaoById(
									Integer.parseInt(cartaoField.getText()),
									true);
					Agencia agenciaCliente = DatabaseOperations
							.retrieveAgenciaByClienteId(
									Integer.parseInt(clienteField.getText()),
									true);

					Cartao[] cartoesConta = contaOrdemCliente.getCartoes();

					boolean isTheSame = false;

					for (int i = 0; i < cartoesConta.length; i++) {
						if (cartoesConta[i].getNumero() == cartaoCliente
								.getNumero()) {
							isTheSame = true;
							break;
						}
					}

					if (isTheSame) {

						try {
							agenciaCliente.criarMovimento(contaOrdemCliente,
									cartaoCliente, "Deposito",
									Integer.parseInt(valorField.getText()),
									null);
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Aviso");
							alert.setHeaderText("Deposito efetuado com sucesso!");
							alert.showAndWait();

							clienteField.setText("");
							cartaoField.setText("");
							valorField.setText("");

						} catch (NumberFormatException | ContaException
								| CartaoException e) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Erro");
							alert.setHeaderText("Ocorreu um erro ao efetuar o movimento!");
							alert.showAndWait();
							e.printStackTrace();
						} catch (PlafondException e) {
							// TODO Auto-generated catch block
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

			Button btnSair = new Button();
			btnSair.setText("Sair");
			btnSair.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					opcoesCliente(primaryStage);
				}
			});

			HBox hbButtons = new HBox();
			hbButtons.setSpacing(10.0);
			hbButtons.getChildren().addAll(btnDeposito);

			GridPane grid = new GridPane();
			grid.setVgap(10);
			grid.setHgap(5);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(hbCliente, 1, 0);
			grid.add(hbCartao, 1, 1);
			grid.add(hbValor, 1, 2);
			grid.add(hbButtons, 1, 3);

			grid.add(btnSair, 1, 5);

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

	public void levantamento(Stage primaryStage) {
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
			cartaoField.textProperty().addListener(
					new ChangeListener<String>() {
						@Override
						public void changed(
								ObservableValue<? extends String> observable,
								String oldValue, String newValue) {
							if (!newValue.matches("\\d{0,9}?")) {
								cartaoField.setText(oldValue);
							}
						}
					});
			HBox hbCartao = new HBox();
			hbCartao.getChildren().addAll(labelCartao, cartaoField);
			hbCartao.setSpacing(10);

			Label labelValor = new Label("Valor Levantamento : ");
			TextField valorField = new TextField();
			valorField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(
						ObservableValue<? extends String> observable,
						String oldValue, String newValue) {
					if (!newValue.matches("\\d{0,9}?")) {
						valorField.setText(oldValue);
					}
				}
			});
			HBox hbValor = new HBox();
			hbValor.getChildren().addAll(labelValor, valorField);
			hbValor.setSpacing(10);

			Button btnLevantamento = new Button();
			btnLevantamento.setText("Efetuar Levantamento");
			btnLevantamento.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					Conta contaOrdemCliente = DatabaseOperations
							.retrieveContaOrdemCliente(Integer
									.parseInt(clienteField.getText()));
					Cartao cartaoCliente = DatabaseOperations
							.retrieveCartaoById(
									Integer.parseInt(cartaoField.getText()),
									true);
					Agencia agenciaCliente = DatabaseOperations
							.retrieveAgenciaByClienteId(
									Integer.parseInt(clienteField.getText()),
									true);

					Cartao[] cartoesConta = contaOrdemCliente.getCartoes();

					boolean isTheSame = false;

					for (int i = 0; i < cartoesConta.length; i++) {
						if (cartoesConta[i].getNumero() == cartaoCliente
								.getNumero()) {
							isTheSame = true;
							break;
						}
					}

					if (isTheSame) {

						try {
							agenciaCliente.criarMovimento(contaOrdemCliente,
									cartaoCliente, "Levantamento",
									Integer.parseInt(valorField.getText()),
									null);
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
							// e.printStackTrace();
						} catch (ContaException e1) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Erro");
							alert.setHeaderText("O saldo é insuficiente para concretizar o movimento!");
							alert.showAndWait();
							// e1.printStackTrace();
						} catch (PlafondException e) {
							// TODO Auto-generated catch block
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

			Button btnSair = new Button();
			btnSair.setText("Sair");
			btnSair.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					opcoesCliente(primaryStage);
				}
			});

			HBox hbButtons = new HBox();
			hbButtons.setSpacing(10.0);
			hbButtons.getChildren().addAll(btnLevantamento);

			GridPane grid = new GridPane();
			grid.setVgap(10);
			grid.setHgap(5);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(hbCliente, 1, 0);
			grid.add(hbCartao, 1, 1);
			grid.add(hbValor, 1, 2);
			grid.add(hbButtons, 1, 3);

			grid.add(btnSair, 1, 5);

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

	public void transferencia(Stage primaryStage) {
		try {
			// BorderPane root = new BorderPane();

			primaryStage.setTitle("Agencia");

			Label labelContaOrigem = new Label("Conta Origem : ");
			TextField contaOrigemField = new TextField();
			contaOrigemField.textProperty().addListener(
					new ChangeListener<String>() {
						@Override
						public void changed(
								ObservableValue<? extends String> observable,
								String oldValue, String newValue) {
							if (!newValue.matches("\\d{0,9}?")) {
								contaOrigemField.setText(oldValue);
							}
						}
					});
			HBox hbContaOrigem = new HBox();
			hbContaOrigem.getChildren().addAll(labelContaOrigem,
					contaOrigemField);
			hbContaOrigem.setSpacing(10);

			Label labelContaDestino = new Label("Conta Destino : ");
			TextField contaDestinoField = new TextField();
			contaDestinoField.textProperty().addListener(
					new ChangeListener<String>() {
						@Override
						public void changed(
								ObservableValue<? extends String> observable,
								String oldValue, String newValue) {
							if (!newValue.matches("\\d{0,9}?")) {
								contaDestinoField.setText(oldValue);
							}
						}
					});
			HBox hbContaDestino = new HBox();
			hbContaDestino.getChildren().addAll(labelContaDestino,
					contaDestinoField);
			hbContaDestino.setSpacing(10);

			Label labelValorT = new Label("Valor Transferência : ");
			TextField valorTFieldT = new TextField();
			valorTFieldT.textProperty().addListener(
					new ChangeListener<String>() {
						@Override
						public void changed(
								ObservableValue<? extends String> observable,
								String oldValue, String newValue) {
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

					Conta contaOrigem = DatabaseOperations.retrieveContaById(
							Long.parseLong(contaOrigemField.getText()), true);
					Conta contaDestino = DatabaseOperations.retrieveContaById(
							Long.parseLong(contaDestinoField.getText()), true);

					if (contaOrigem.getTipoConta().equals(
							Conta.CONST_CONTA_ORDEM)
							|| contaDestino.getTipoConta().equals(
									Conta.CONST_CONTA_ORDEM)) {

						Agencia agenciaCliente = new Agencia();

						try {
							agenciaCliente.criarMovimento(contaOrigem, null,
									Movimento.CONST_TRANSFERENCIA,
									Long.parseLong(valorTFieldT.getText()),
									contaDestino);

							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setTitle("Erro");
							alert.setHeaderText("Transferencia efetuada!");
							alert.showAndWait();

							contaOrigemField.setText("");
							contaDestinoField.setText("");
							valorTFieldT.setText("");

						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ContaException e) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Erro");
							alert.setHeaderText("O saldo não é suficiente para processar a transferencia!");
							alert.showAndWait();
						} catch (CartaoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (PlafondException e) {
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

			Button btnSair = new Button();
			btnSair.setText("Sair");
			btnSair.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					opcoesCliente(primaryStage);
				}
			});

			GridPane grid = new GridPane();
			grid.setVgap(10);
			grid.setHgap(5);
			grid.setPadding(new Insets(5, 5, 5, 5));

			grid.add(hbContaOrigem, 1, 1);
			grid.add(hbContaDestino, 1, 3);
			grid.add(hbValorT, 1, 5);
			grid.add(btnTransferencia, 1, 7);
			grid.add(btnSair, 1, 9);

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

	public void listaMovimentos(Stage primaryStage) {
		try {
			// BorderPane root = new BorderPane();

			primaryStage.setTitle("Agencia");

			Label labelCliente = new Label("Código Cliente : ");
			TextField clienteField = new TextField();
			HBox hbCliente = new HBox();
			hbCliente.getChildren().addAll(labelCliente, clienteField);
			hbCliente.setSpacing(10);

			Button btnListaMovimentos = new Button();
			btnListaMovimentos.setText("Lista movimentos conta ordem");
			btnListaMovimentos.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					Conta contaOrdemCliente = DatabaseOperations
							.retrieveContaOrdemCliente(Integer
									.parseInt(clienteField.getText()));

					listaMovimentosConta(primaryStage,
							contaOrdemCliente.getNumero());
				}
			});

			Button btnSair = new Button();
			btnSair.setText("Sair");
			btnSair.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					opcoesCliente(primaryStage);
				}
			});

			GridPane grid = new GridPane();
			grid.setVgap(10);
			grid.setHgap(5);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(hbCliente, 1, 0);
			grid.add(btnListaMovimentos, 1, 2);

			grid.add(btnSair, 1, 5);

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
	
	public void listaMovimentosByConta(Stage primaryStage) {
		try {
			// BorderPane root = new BorderPane();

			primaryStage.setTitle("Agencia");

			Label labelConta = new Label("Código Conta : ");
			TextField contaField = new TextField();
			HBox hbConta = new HBox();
			hbConta.getChildren().addAll(labelConta, contaField);
			hbConta.setSpacing(10);

			Button btnListaMovimentos = new Button();
			btnListaMovimentos.setText("Lista movimentos conta");
			btnListaMovimentos.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					listaMovimentosConta(primaryStage,
							Integer.parseInt(contaField.getText()));
				}
			});

			Button btnSair = new Button();
			btnSair.setText("Sair");
			btnSair.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					opcoesCliente(primaryStage);
				}
			});

			GridPane grid = new GridPane();
			grid.setVgap(10);
			grid.setHgap(5);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(hbConta, 1, 0);
			grid.add(btnListaMovimentos, 1, 2);

			grid.add(btnSair, 1, 5);

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
	

	@SuppressWarnings("unchecked")
	public void listaMovimentosConta(Stage stage, long numeroConta) {
		Scene scene = new Scene(new Group());
		stage.setWidth(450);
		stage.setHeight(550);

		TableView<movimentoConta> table = new TableView<>();

		ObservableList<movimentoConta> data = FXCollections
				.observableArrayList();

		Movimento[] mov = DatabaseOperations.retrieveMovimentosConta(
				numeroConta, true);

		for (int i = 0; i < mov.length; i++) {

			String tipoConta = "";

			if (mov[i] instanceof Deposito) {
				tipoConta = Movimento.CONST_DEPOSITO;
			} else if (mov[i] instanceof Levantamento) {
				tipoConta = Movimento.CONST_LEVANTAMENTO;
			} else if (mov[i] instanceof Juros) {
				tipoConta = Movimento.CONST_JUROS;
			} else if (mov[i] instanceof Transferencia) {
				tipoConta = Movimento.CONST_TRANSFERENCIA;
			}

			data.add(new movimentoConta(mov[i].getData().toString(), Long
					.toString(mov[i].getValor()), tipoConta));
		}

		table.setEditable(true);

		TableColumn<movimentoConta, String> firstNameCol = new TableColumn<>(
				"Movimento");
		firstNameCol.setMinWidth(100);
		firstNameCol
				.setCellValueFactory(new PropertyValueFactory<>("firstName"));

		TableColumn<movimentoConta, String> tipoContaCol = new TableColumn<>(
				"Tipo");
		tipoContaCol.setMinWidth(100);
		tipoContaCol
				.setCellValueFactory(new PropertyValueFactory<>("tipoConta"));

		TableColumn<movimentoConta, String> lastNameCol = new TableColumn<>(
				"Valor");
		lastNameCol.setMinWidth(100);
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

		table.setItems(data);

		table.getColumns().addAll(firstNameCol, tipoContaCol, lastNameCol);

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

	@SuppressWarnings("unchecked")
	public void listaAgenciasBanco(Stage stage, int codigoBanco) {

		Scene scene = new Scene(new Group());
		stage.setWidth(450);
		stage.setHeight(550);

		TableView<agenciasBanco> table = new TableView<>();

		ObservableList<agenciasBanco> data = FXCollections
				.observableArrayList();

		Agencia[] agencias = DatabaseOperations
				.retrieveAgenciasByBanco(codigoBanco, true);

		for (int i = 0; i < agencias.length; i++) {
			data.add(new agenciasBanco(agencias[i].getNome(), String
					.valueOf(agencias[i].getNumero())));
		}

		table.setEditable(true);

		TableColumn<agenciasBanco, String> firstNameCol = new TableColumn<>(
				"Nome Agencia");
		firstNameCol.setMinWidth(100);
		firstNameCol
				.setCellValueFactory(new PropertyValueFactory<>("firstName"));

		TableColumn<agenciasBanco, String> lastNameCol = new TableColumn<>(
				"Numero Agencia");
		lastNameCol.setMinWidth(100);
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

		table.setItems(data);
		table.getColumns().addAll(firstNameCol, lastNameCol);

		Button btnSair = new Button();
		btnSair.setText("Sair");
		btnSair.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				opcoesBanco(stage);
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

	@SuppressWarnings("unchecked")
	public void listaClienteAgencia(Stage stage, int codigoAgencia) {

		Scene scene = new Scene(new Group());
		stage.setWidth(450);
		stage.setHeight(550);

		TableView<clientesAgencia> table = new TableView<>();

		ObservableList<clientesAgencia> data = FXCollections
				.observableArrayList();

		Cliente[] clientes = DatabaseOperations.retrieveClientesByAgencia(
				codigoAgencia, true);

		for (int i = 0; i < clientes.length; i++) {
			data.add(new clientesAgencia(clientes[i].getNome(), String
					.valueOf(clientes[i].getNumeroCartaoCidadao()), String
					.valueOf(clientes[i].getId())));
		}

		table.setEditable(true);

		TableColumn<clientesAgencia, String> firstNameCol = new TableColumn<>(
				"Nome Cliente");
		firstNameCol.setMinWidth(100);
		firstNameCol
				.setCellValueFactory(new PropertyValueFactory<>("firstName"));

		TableColumn<clientesAgencia, String> lastNameCol = new TableColumn<>(
				"Cartao Cidadão");
		lastNameCol.setMinWidth(100);
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

		TableColumn<clientesAgencia, String> numInternoCol = new TableColumn<>(
				"Id Cidadão");
		numInternoCol.setMinWidth(100);
		numInternoCol.setCellValueFactory(new PropertyValueFactory<>(
				"numInterno"));

		table.setItems(data);
		table.getColumns().addAll(firstNameCol, lastNameCol, numInternoCol);

		Button btnSair = new Button();
		btnSair.setText("Sair");
		btnSair.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				opcoesAgencia(stage);
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

	@SuppressWarnings("unchecked")
	public void listaContasCliente(Stage stage, int numeroCliente) {
		Scene scene = new Scene(new Group());
		stage.setWidth(450);
		stage.setHeight(550);

		TableView<contaCliente> table = new TableView<>();

		ObservableList<contaCliente> data = FXCollections.observableArrayList();

		Conta[] contas = DatabaseOperations.retrieveContasCliente(
				numeroCliente, true);

		for (int i = 0; i < contas.length; i++) {

			data.add(new contaCliente(Long.toString(contas[i].getNumero()),
					contas[i].getTipoConta(), Long.toString(contas[i]
							.getSaldo())));
		}

		table.setEditable(true);

		TableColumn<contaCliente, String> numContaCol = new TableColumn<>(
				"Conta");
		numContaCol.setMinWidth(100);
		numContaCol.setCellValueFactory(new PropertyValueFactory<>("numConta"));

		TableColumn<contaCliente, String> tipoContaCol = new TableColumn<>(
				"Tipo");
		tipoContaCol.setMinWidth(100);
		tipoContaCol
				.setCellValueFactory(new PropertyValueFactory<>("tipoConta"));

		TableColumn<contaCliente, String> valorContaCol = new TableColumn<>(
				"Valor");
		valorContaCol.setMinWidth(100);
		valorContaCol.setCellValueFactory(new PropertyValueFactory<>(
				"valorConta"));

		table.setItems(data);

		table.getColumns().addAll(numContaCol, tipoContaCol, valorContaCol);

		Button btnSair = new Button();
		btnSair.setText("Sair");
		btnSair.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				opcoesAgencia(stage);
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

	@SuppressWarnings("unchecked")
	public void listaCartoesConta(Stage stage, int numeroConta) {
		Scene scene = new Scene(new Group());
		stage.setWidth(450);
		stage.setHeight(550);

		TableView<cartaoConta> table = new TableView<>();

		ObservableList<cartaoConta> data = FXCollections.observableArrayList();

		Cartao[] cartoes = DatabaseOperations.retrieveCartoesByConta(
				numeroConta, true);

		for (int i = 0; i < cartoes.length; i++) {

			data.add(new cartaoConta(Long.toString(cartoes[i].getNumero()),
					cartoes[i].getTipo(),
					Long.toString(cartoes[i].getPlafond()), Integer
							.toString(cartoes[i].getValorPlafond())));
		}

		table.setEditable(true);

		TableColumn<cartaoConta, String> numCartaoCol = new TableColumn<>(
				"Cartao");
		numCartaoCol.setMinWidth(100);
		numCartaoCol
				.setCellValueFactory(new PropertyValueFactory<>("numCartao"));

		TableColumn<cartaoConta, String> tipoCartaoCol = new TableColumn<>(
				"Tipo");
		tipoCartaoCol.setMinWidth(100);
		tipoCartaoCol.setCellValueFactory(new PropertyValueFactory<>(
				"tipoCartao"));

		TableColumn<cartaoConta, String> valorCartaoCol = new TableColumn<>(
				"Plafond");
		valorCartaoCol.setMinWidth(100);
		valorCartaoCol.setCellValueFactory(new PropertyValueFactory<>(
				"valorPlafond"));

		TableColumn<cartaoConta, String> valorAtualCartaoCol = new TableColumn<>(
				"Plafond Atual");
		valorAtualCartaoCol.setMinWidth(100);
		valorAtualCartaoCol.setCellValueFactory(new PropertyValueFactory<>(
				"valorAtual"));

		table.setItems(data);

		table.getColumns().addAll(numCartaoCol, tipoCartaoCol, valorCartaoCol,
				valorAtualCartaoCol);

		Button btnSair = new Button();
		btnSair.setText("Sair");
		btnSair.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				opcoesAgencia(stage);
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

	@SuppressWarnings("unchecked")
	public void listaBancos(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setWidth(450);
		stage.setHeight(550);

		TableView<bancos> table = new TableView<>();

		ObservableList<bancos> data = FXCollections.observableArrayList();

		Banco[] bancos = DatabaseOperations.retrieveBancos();

		for (int i = 0; i < bancos.length; i++) {
			data.add(new bancos(Long.toString(bancos[i].getId()),
					bancos[i].getNome()));
		}

		table.setEditable(true);

		TableColumn<bancos, String> idBancoCol = new TableColumn<>(
				"Id Conta");
		idBancoCol.setMinWidth(100);
		idBancoCol.setCellValueFactory(new PropertyValueFactory<>("idBanco"));

		TableColumn<bancos, String> nomeBancoCol = new TableColumn<>(
				"Nome Conta");
		nomeBancoCol.setMinWidth(100);
		nomeBancoCol
				.setCellValueFactory(new PropertyValueFactory<>("nomeBanco"));

		table.setItems(data);

		table.getColumns().addAll(idBancoCol, nomeBancoCol);

		Button btnSair = new Button();
		btnSair.setText("Sair");
		btnSair.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				opcoesBanco(stage);
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
	
	
	public static class movimentoConta {

		private final SimpleStringProperty firstName;
		private final SimpleStringProperty tipoConta;
		private final SimpleStringProperty lastName;

		private movimentoConta(String fName, String lName, String tConta) {
			this.firstName = new SimpleStringProperty(fName);
			this.tipoConta = new SimpleStringProperty(tConta);
			this.lastName = new SimpleStringProperty(lName);
		}

		public String getFirstName() {
			return firstName.get();
		}

		public void setFirstName(String fName) {
			firstName.set(fName);
		}

		public String getTipoConta() {
			return tipoConta.get();
		}

		public void setTipoConta(String tConta) {
			tipoConta.set(tConta);
		}

		public String getLastName() {
			return lastName.get();
		}

		public void setLastName(String fName) {
			lastName.set(fName);
		}
	}

	public static class bancos {

		private final SimpleStringProperty idBanco;
		private final SimpleStringProperty nomeBanco;

		private bancos(String id, String nome) {
			this.idBanco = new SimpleStringProperty(id);
			this.nomeBanco = new SimpleStringProperty(nome);
		}

		public String getIdBanco() {
			return idBanco.get();
		}

		public void setIdBanco(String id) {
			idBanco.set(id);
		}

		public String getNomeBanco() {
			return nomeBanco.get();
		}

		public void setNomeBanco(String nome) {
			nomeBanco.set(nome);
		}
	}	
	
	public static class agenciasBanco {

		private final SimpleStringProperty firstName;
		private final SimpleStringProperty lastName;

		private agenciasBanco(String fName, String lName) {
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

	public static class clientesAgencia {

		private final SimpleStringProperty firstName;
		private final SimpleStringProperty lastName;
		private final SimpleStringProperty numInterno;

		private clientesAgencia(String fName, String lName, String numI) {
			this.firstName = new SimpleStringProperty(fName);
			this.lastName = new SimpleStringProperty(lName);
			this.numInterno = new SimpleStringProperty(numI);
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

		public void setLastName(String lName) {
			lastName.set(lName);
		}

		public String getNumInterno() {
			return numInterno.get();
		}

		public void setNumInterno(String numI) {
			numInterno.set(numI);
		}
	}

	public static class contaCliente {

		private final SimpleStringProperty numConta;
		private final SimpleStringProperty tipoConta;
		private final SimpleStringProperty valorConta;

		private contaCliente(String nConta, String tConta, String vConta) {
			this.numConta = new SimpleStringProperty(nConta);
			this.tipoConta = new SimpleStringProperty(tConta);
			this.valorConta = new SimpleStringProperty(vConta);
		}

		public String getNumConta() {
			return numConta.get();
		}

		public void setNumConta(String fName) {
			numConta.set(fName);
		}

		public String getTipoConta() {
			return tipoConta.get();
		}

		public void setTipoConta(String tConta) {
			tipoConta.set(tConta);
		}

		public String getValorConta() {
			return valorConta.get();
		}

		public void setValorConta(String vConta) {
			valorConta.set(vConta);
		}
	}

	public static class cartaoConta {

		private final SimpleStringProperty numCartao;
		private final SimpleStringProperty tipoCartao;
		private final SimpleStringProperty valorPlafond;
		private final SimpleStringProperty valorAtual;

		private cartaoConta(String nCartao, String tCartao, String vPlafond,
				String vAtual) {
			this.numCartao = new SimpleStringProperty(nCartao);
			this.tipoCartao = new SimpleStringProperty(tCartao);
			this.valorPlafond = new SimpleStringProperty(vPlafond);
			this.valorAtual = new SimpleStringProperty(vAtual);
		}

		public String getNumCartao() {
			return numCartao.get();
		}

		public void setNumCartao(String nCartao) {
			numCartao.set(nCartao);
		}

		public String getTipoCartao() {
			return tipoCartao.get();
		}

		public void setTipoConta(String tCartao) {
			tipoCartao.set(tCartao);
		}

		public String getValorPlafond() {
			return valorPlafond.get();
		}

		public void setValorPlafond(String vConta) {
			valorPlafond.set(vConta);
		}

		public String getValorAtual() {
			return valorAtual.get();
		}

		public void setValorAtual(String vConta) {
			valorAtual.set(vConta);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

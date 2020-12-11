package views.Utilitarios;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Formatacoes {

	private static List<KeyCode> ignoreKeyCodes;

	static {
	    ignoreKeyCodes = new ArrayList<>();
	    //Collections.addAll(ignoreKeyCodes, new KeyCode[]{F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12});
	}

	public static void ignoreKeys(final TextField textField) {
	    textField.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent keyEvent) {
	            if (ignoreKeyCodes.contains(keyEvent.getCode())) {
	                keyEvent.consume();
	            }
	        }
	    });
	}

	/**
	 * Monta a mascara para Data (dd/MM/yyyy).
	 *
	 * @param textField TextField
	 */
	public static void dateField(final TextField textField) {
	    maxField(textField, 10);

	    textField.lengthProperty().addListener(new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	            if (newValue.intValue() < 11) {
	                String value = textField.getText();
	                value = value.replaceAll("[^0-9]", "");
	                value = value.replaceFirst("(\\d{2})(\\d)", "$1/$2");
	                value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d)", "$1/$2/$3");
	                textField.setText(value);
	                positionCaret(textField);
	            }
	        }
	    });
	}

	/**
	 * Campo que aceita somente numericos.
	 *
	 * @param textField TextField
	 */
	public static void numericField(final TextField textField) {
	    textField.lengthProperty().addListener(new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	            if (newValue.intValue() > oldValue.intValue()) {
	                char ch = textField.getText().charAt(oldValue.intValue());
	                if (!(ch >= '0' && ch <= '9')) {
	                    textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
	                }
	            }
	        }
	    });
	}

	/**
	 * Monta a mascara para Moeda.
	 *
	 * @param textField TextField
	 */
	public static void monetaryField(final TextField textField) {
	    textField.setAlignment(Pos.CENTER_RIGHT);
	    textField.lengthProperty().addListener(new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	            String value = textField.getText();
	            value = value.replaceAll("[^0-9]", "");
	            value = value.replaceAll("([0-9]{1})([0-9]{14})$", "$1.$2");
	            value = value.replaceAll("([0-9]{1})([0-9]{11})$", "$1.$2");
	            value = value.replaceAll("([0-9]{1})([0-9]{8})$", "$1.$2");
	            value = value.replaceAll("([0-9]{1})([0-9]{5})$", "$1.$2");
	            value = value.replaceAll("([0-9]{1})([0-9]{2})$", "$1,$2");
	            textField.setText(value);
	            positionCaret(textField);

	            textField.textProperty().addListener(new ChangeListener<String>() {
	                @Override
	                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
	                    if (newValue.length() > 17)
	                        textField.setText(oldValue);
	                }
	            });
	        }
	    });

	    textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
	        @Override
	        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean fieldChange) {
	            if (!fieldChange) {
	                final int length = textField.getText().length();
	                if (length > 0 && length < 3) {
	                    textField.setText(textField.getText() + "00");
	                }
	            }
	        }
	    });
	}

	/**
	 * Monta as mascara para CPF. A mascara eh exibida somente apos o campo perder o foco.
	 *
	 * @param textField TextField
	 */
	public static void formaField(final TextField textField, String tipo) {

	    textField.focusedProperty().addListener(new ChangeListener<Boolean>() {

	        @Override
	        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean fieldChange) {
	            String value = textField.getText();
	            
	            if (!fieldChange) {
	            	value = value.replaceAll("[^0-9]", "");
	            	if(tipo.equals("cpf")) {
	            		maxField(textField, 18);
		                value = value.replaceFirst("([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})$", "$1.$2.$3-$4");
	            	} else if(tipo.equals("rg")) {
	            		maxField(textField, 12);
		                value = value.replaceFirst("([0-9]{2})([0-9]{3})([0-9]{3})([0-9]{1})$", "$1.$2.$3-$4");
	            	} else if(tipo.equals("tele")) {
	            		maxField(textField, 14);
		                value = value.replaceFirst("([0-9]{2})([0-9]{4})([0-9]{4})$", "($1) $2-$3");
	            	} else {
	            		maxField(textField, 15);
		                value = value.replaceFirst("([0-9]{2})([0-9]{5})([0-9]{4})$", "($1) $2-$3");
	            	}
	            }
	            textField.setText(value);
	            if (textField.getText() != value) {
	                textField.setText("");
	                textField.insertText(0, value);
	            }

	        }
	    });
	}
	
	
	public static void cepField(TextField textField){

        String val = "";

        textField.setOnKeyTyped((KeyEvent event) -> {
            if("[0-9]".contains(event.getCharacter())==false){
                event.consume();
            }

            if(event.getCharacter().trim().length()==0){ // apagando

                if(textField.getText().length()==6){
                    textField.setText(textField.getText().substring(0,5));
                    textField.positionCaret(textField.getText().length());
                }

            }else{ // escrevendo

                if(textField.getText().length()==9) event.consume();

                if(textField.getText().length()==5){
                    textField.setText(textField.getText()+"-");
                    textField.positionCaret(textField.getText().length());
                }


            }
        });

        textField.setOnKeyReleased((KeyEvent evt) -> {

            if(!textField.getText().matches("\\d-*")){
                textField.setText(textField.getText().replaceAll("[^\\d-]", ""));
                textField.positionCaret(textField.getText().length());
            }
        });

    }
	
	
	 public static void mascaraData(DatePicker datePicker){

	        datePicker.getEditor().setOnKeyTyped((KeyEvent event) -> {
	            if("0123456789".contains(event.getCharacter())==false){
	                event.consume();
	            }

	            if(event.getCharacter().trim().length()==0){ // apagando
	                if(datePicker.getEditor().getText().length()==3){
	                    datePicker.getEditor().setText(datePicker.getEditor().getText().substring(0,2));
	                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
	                }
	                if(datePicker.getEditor().getText().length()==6){
	                    datePicker.getEditor().setText(datePicker.getEditor().getText().substring(0,5));
	                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
	                }

	            }else{ // escrevendo

	                if(datePicker.getEditor().getText().length()==10) event.consume();

	                if(datePicker.getEditor().getText().length()==2){
	                    datePicker.getEditor().setText(datePicker.getEditor().getText()+"/");
	                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
	                }
	                if(datePicker.getEditor().getText().length()==5){
	                    datePicker.getEditor().setText(datePicker.getEditor().getText()+"/");
	                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
	                }

	            }
	        });

	        datePicker.getEditor().setOnKeyReleased((KeyEvent evt) -> {

	            if(!datePicker.getEditor().getText().matches("\\d/*")){
	                datePicker.getEditor().setText(datePicker.getEditor().getText().replaceAll("[^\\d/]", ""));
	                datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
	            }
	        });

	    }
	 
	 public static void emailField(final TextField textField) {
		 textField.focusedProperty().addListener((ov, oldV, newV) ->{
				if(!newV) {
					if(!textField.getText().contains("@")) {
						Alerta.showAlert("Email", null, "Email inválido", AlertType.WARNING);
						textField.requestFocus();
					}
				}
			});
	 }
	  
	 
	

	/**
	 * Monta a mascara para os campos CNPJ.
	 *
	 * @param textField TextField
	 */
	public static void cnpjField(final TextField textField) {
	    maxField(textField, 18);

	    textField.lengthProperty().addListener(new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
	            String value = textField.getText();
	            value = value.replaceAll("[^0-9]", "");
	            value = value.replaceFirst("(\\d{2})(\\d)", "$1.$2");
	            value = value.replaceFirst("(\\d{2})\\.(\\d{3})(\\d)", "$1.$2.$3");
	            value = value.replaceFirst("\\.(\\d{3})(\\d)", ".$1/$2");
	            value = value.replaceFirst("(\\d{4})(\\d)", "$1-$2");
	            textField.setText(value);
	            positionCaret(textField);
	        }
	    });

	}

	/**
	 * Devido ao incremento dos caracteres das mascaras eh necessario que o cursor sempre se posicione no final da string.
	 *
	 * @param textField TextField
	 */
	private static void positionCaret(final TextField textField) {
	    Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	            // Posiciona o cursor sempre a direita.
	            textField.positionCaret(textField.getText().length());
	        }
	    });
	}

	/**
	 * @param textField TextField.
	 * @param length    Tamanho do campo.
	 */
	private static void maxField(final TextField textField, final Integer length) {
	    textField.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
	            if (newValue.length() > length)
	                textField.setText(oldValue);
	        }
	    });
	}
	}

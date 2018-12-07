package application;

import java.io.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;


public class bancaTestController {

	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="beneficiario"
    private TextField beneficiario; // Value injected by FXMLLoader

    @FXML // fx:id="bonificoTextArea"
    private TextArea bonificoTextArea; // Value injected by FXMLLoader

    @FXML // fx:id="btnBonifico"
    private Button btnBonifico; // Value injected by FXMLLoader

    @FXML // fx:id="btnSaldo"
    private Button btnSaldo; // Value injected by FXMLLoader

    @FXML // fx:id="causale"
    private TextField causale; // Value injected by FXMLLoader

    @FXML // fx:id="data"
    private TextField data; // Value injected by FXMLLoader

    @FXML // fx:id="moneta"
    private TextField moneta; // Value injected by FXMLLoader

    @FXML // fx:id="quota"
    private TextField quota; // Value injected by FXMLLoader

    @FXML // fx:id="saldoTextArea"
    private TextArea saldoTextArea; // Value injected by FXMLLoader


    // Handler for Button[fx:id="btnBonifico"] onAction
    @FXML
    void bonificoPressed(ActionEvent event) throws ClientProtocolException, IOException {
        // handle the event here
    	String url = "https://api.platfr.io/api/gbs/banking/v2.1/accounts/0046/payments/sct/orders";
    	HttpClient client = HttpClientBuilder.create().build();
    	HttpPost request = new HttpPost(url);
    	request.setHeader("Auth-Schema", "S2S");
    	
    	HttpResponse response = client.execute(request);
    	BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
    	StringBuffer result = new StringBuffer();
    	String line = "";
    	while((line = rd.readLine())!= null)
    	{
    		result.append(line);
    	}
    	
    	bonificoTextArea.appendText("Hai inserito:\n");
    	bonificoTextArea.appendText("Beneficiario: " + beneficiario.getText() + "\n");
    	bonificoTextArea.appendText("Causale: " + causale.getText() + "\n");
    	bonificoTextArea.appendText("Moneta: " + moneta.getText() + "\n");
    	bonificoTextArea.appendText("Quota: " + quota.getText() + "\n");
    	bonificoTextArea.appendText("Data: " + data.getText() + "\n\n\n");
    	
    	String resultString = result.toString();    	    	
    	
    	bonificoTextArea.appendText(resultString);
    	
    }

    // Handler for Button[fx:id="btnSaldo"] onAction
    @FXML
    void saldoPressed(ActionEvent event) throws ClientProtocolException, IOException {
        // handle the event here
    	String url = "https://api.platfr.io/api/gbs/banking/v2/accounts/0046/balance";
    	HttpClient client = HttpClientBuilder.create().build();
    	HttpGet request = new HttpGet(url);
    	request.setHeader("Auth-Schema", "S2S");
    	
    	HttpResponse response = client.execute(request);
    	BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
    	StringBuffer result = new StringBuffer();
    	String line = "";
    	while((line = rd.readLine())!= null)
    	{
    		result.append(line);
    	}
    	
    	String resultString = result.toString();    	    	
    	
    	saldoTextArea.appendText(resultString);
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert bonificoTextArea != null : "fx:id=\"bonificoTextArea\" was not injected: check your FXML file 'bancaTest.fxml'.";
        assert btnBonifico != null : "fx:id=\"btnBonifico\" was not injected: check your FXML file 'bancaTest.fxml'.";
        assert btnSaldo != null : "fx:id=\"btnSaldo\" was not injected: check your FXML file 'bancaTest.fxml'.";
        assert saldoTextArea != null : "fx:id=\"saldoTextArea\" was not injected: check your FXML file 'bancaTest.fxml'.";

        // Initialize your logic here: all @FXML variables will have been injected

    }

}

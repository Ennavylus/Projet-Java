package net.JeuxDeMob;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * JavaFX App
 */
public class App extends Application {
   	static HashMap<String, Integer > test;
	static HashMap<String, Integer > test2;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        //scene = new Scene(loadFXML("LogIn"));
    	scene = new Scene(loadFXML("admin"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }



    public static void main(String[] args) {
//    	
//    	int i = 10;
//    	int j = 20;
//    	int k = 30;
//    	
//    	test = new HashMap<String, Integer >();
//    	test2 = new HashMap<String, Integer >();
//    	test.put("i", i);
//    	test.put("j", j);
//    	test.put("k", k);
//    	
//    	test2.put("k", test.get("k"));
//    	test.remove("k");
//    	for (Map.Entry mapentry : test.entrySet()) {
//			System.out.println("test = "+mapentry.getKey().toString()); }
//     	for (Map.Entry mapentry : test2.entrySet()) {
//			System.out.println("test2 = "+mapentry.getKey().toString()); }
       launch();
        
        
    }
    
    
    
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
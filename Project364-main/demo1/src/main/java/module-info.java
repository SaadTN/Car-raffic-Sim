/**
 *Java Traffic Simulator
 * @author Team6
 */
module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens proj364 to javafx.fxml;
    exports proj364;
}
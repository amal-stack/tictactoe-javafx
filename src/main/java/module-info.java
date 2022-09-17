module com.amalstack.tictactoejavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.amalstack.tictactoejavafx to javafx.fxml;
    exports com.amalstack.tictactoejavafx;
    exports com.amalstack.tictactoejavafx.event;
    opens com.amalstack.tictactoejavafx.event to javafx.fxml;
    exports com.amalstack.tictactoejavafx.controller;
    opens com.amalstack.tictactoejavafx.controller to javafx.fxml;
}
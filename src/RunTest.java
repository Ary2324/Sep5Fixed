import client.ClientApp;
import javafx.application.Application;
import server.JDBCConnector;
import server.ServerModel;

public class RunTest {
    public static void main(String[] args) {
        ServerModel server = new ServerModel(new JDBCConnector());
        server.run();
        Application.launch(ClientApp.class);
    }
}

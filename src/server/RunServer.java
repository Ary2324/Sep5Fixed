package server;

import client.ClientApp;
import javafx.application.Application;

public class RunServer {

    public static void main(String[] args) {
        ServerModel server = new ServerModel(new JDBCConnector());
        server.run();
    }
}

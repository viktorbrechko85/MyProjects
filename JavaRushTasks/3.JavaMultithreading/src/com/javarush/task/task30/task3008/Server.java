package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by MarKiz on 27.05.2018.
 */
public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();
    private static class Handler extends Thread{
        private Socket socket;
        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run(){
            if (socket != null && socket.getRemoteSocketAddress() != null) {
                ConsoleHelper.writeMessage("Established a new connection to a remote socket address: " + socket.getRemoteSocketAddress());
            }
            String usrName = null;
            try {
                Connection conn = new Connection(socket);
                usrName = serverHandshake(conn);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, usrName));
                sendListOfUsers(conn, usrName);
                serverMainLoop(conn, usrName);

            }catch (IOException | ClassNotFoundException e){

            }
            finally {
                if (usrName != null) {
                    try {
                        connectionMap.remove(usrName);
                        sendBroadcastMessage(new Message(MessageType.USER_REMOVED, usrName));
                    }catch (IOException e){}
                }
                ConsoleHelper.writeMessage("Closed connection to a remote socket address: "); // + socketAddress);
            }
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException{
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message answer = connection.receive();
                if (answer.getType() == MessageType.USER_NAME) {
                    if (!answer.getData().isEmpty()) {
                        if (!connectionMap.containsKey(answer.getData())) {
                            connectionMap.put(answer.getData(), connection);
                            connection.send(new Message(MessageType.NAME_ACCEPTED));
                            return answer.getData();
                        }
                    }
                }
            }
        }
        private void sendListOfUsers(Connection connection, String userName) throws IOException{
            for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
                if (!entry.getKey().equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, entry.getKey()));
                }
            }
        }
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while (true) {
                Message message = connection.receive();
                if (message != null && message.getType() == MessageType.TEXT) {
                    String usrName = String.format("%s: %s", userName, message.getData());
                    Message mess = new Message(MessageType.TEXT, usrName);
                    sendBroadcastMessage(mess);
                } else {
                    ConsoleHelper.writeMessage("Messege is not TEXT!");
                }
            }
        }
    }
    public static void sendBroadcastMessage(Message message){
        try{
            for (Connection connection : connectionMap.values()) {
                connection.send(message);
            }
        }catch (IOException e){
            ConsoleHelper.writeMessage("Error sending message!");
        }
    }
    public static void main(String[] args) throws IOException {
        ConsoleHelper.writeMessage("Input server port: ");
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())) {
            ConsoleHelper.writeMessage("Server started...");
            while (true) {
                new Handler(serverSocket.accept()).start();
            }
        } catch (Exception e) {
            ConsoleHelper.writeMessage("Something wrong, Server socket closed.");
        }
    }
}

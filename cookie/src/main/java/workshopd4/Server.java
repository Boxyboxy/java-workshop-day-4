package workshopd4;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  // java-cp fortunecookie.jar packageName.Server 12345 cookie_file.txt
  // above line runs server class
  // String[] args is equivalent to '12345 cookie_file.txt'
  public static void main(String[] args) throws IOException {

    int port;
    String fileString;
    if (args.length == 0) {
      port = 12345;
      fileString = "cookie_file.txt";
    } else {
      port = Integer.parseInt(args[0]);
      fileString = args[1];
    }
    // constructing object
    Server server = new Server(port, fileString);
    // start server
    server.startServer();
    // establish connection
    server.connectServer();
    // close connection
    server.closeConnection();

  }

  // attributes
  private int serverPort;
  private Cookie cookieJar;
  private ServerSocket serverSocket;
  private Socket socket;

  // constructor
  public Server(int serverPort, String cookieFileName) {
    this.serverPort = serverPort;
    this.cookieJar = new Cookie(cookieFileName);

  }

  // start server
  public void startServer() throws IOException {
    this.serverSocket = new ServerSocket(this.serverPort);
    System.out.println("Server started on port: " + this.serverPort);
  }

  // establish connection
  public void connectServer() throws IOException {
    System.out.println("Connecting the fellas");
    // accepting socket conneciton
    this.socket = this.serverSocket.accept();
    System.out.println("Fella connected");
    System.out.println("Relevant information on the socket: " + this.socket);
    // output stream from socket
    OutputStream os = socket.getOutputStream();
    BufferedOutputStream bos = new BufferedOutputStream(os);
    DataOutputStream dos = new DataOutputStream(bos);
    // writing text to output stream
    dos.writeUTF(this.cookieJar.cookieText());
    dos.flush();
    System.out.println("Text sent to client");
  }

  // closing socket connection, throwing IOException required
  public void closeConnection() throws IOException {
    this.socket.close();
    System.out.println("Connection closed");
  }

}

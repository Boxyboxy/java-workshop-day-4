package workshopd4;

import java.net.*;
import java.io.*;

public class Client {
  public static void main(String[] args) throws IOException {
    String IPString;
    int port;

    // if no arguments are in the command line
    if (args.length == 0) {
      // assigning variables to be used in constructor
      IPString = "localhost";
      port = 12345;
    } else {
      String[] argumentsFromCL = args[0].split(":");
      IPString = argumentsFromCL[0];
      port = Integer.parseInt(argumentsFromCL[1]);
    }

    // constructing object
    Client client = new Client(IPString, port);

    // establish connection
    client.establishConnection();
    // storing fortune cookie message in a string
    String messageReceived = client.requestCookie();
    // displaying message
    System.out.println(messageReceived);
    // close connection
    client.closeConnection();

  }

  // attributes
  private int clientPort;
  private String IPaddress;
  private Socket socket;

  // constructor
  public Client(String IPaddress, int port) {
    this.clientPort = port;
    this.IPaddress = IPaddress;

  }

  // establish connection, throwing IOException required
  public void establishConnection() throws IOException {
    this.socket = new Socket(this.IPaddress, this.clientPort);
    System.out.println("Relevant information on the socket: " + this.socket);
  }

  public String requestCookie() throws IOException {
    // get input stream from the socket
    InputStream is = this.socket.getInputStream();
    BufferedInputStream bis = new BufferedInputStream(is);
    DataInputStream dis = new DataInputStream(bis);

    // read the response from server
    String response = dis.readUTF();
    return response;
  }

  // closing socket connection, throwing IOException required
  public void closeConnection() throws IOException {
    this.socket.close();
    System.out.println("Connection closed");
  }

}

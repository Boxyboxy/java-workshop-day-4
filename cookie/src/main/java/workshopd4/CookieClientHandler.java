package workshopd4;

import java.net.Socket;

public class CookieClientHandler implements Runnable {

  private final Socket socket;

  public CookieClientHandler(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    // TODO Auto-generated method stub

  }

}

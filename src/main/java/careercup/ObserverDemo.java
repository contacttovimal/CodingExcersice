package careercup;

import java.util.Observable;
import java.util.Observer;

public class ObserverDemo {
	public static void main(String[] args) {
		Server server = new Server();
		Client client1 = new Client("client1");
		server.addObserver(client1);
		Client client2 = new Client("client2");
		server.addObserver(client2);
		server.notifyObservers();
	}
}

class Server extends Observable{
//	private Observable observable = new Observable();

	public Server() {
		super();
	}

	public boolean addObserver(Client client) {
		super.addObserver(client);
		return true;
	}

	public boolean deleteObserver(Client client) {
		super.deleteObserver(client);
		return true;
	}

	public void notifyObservers() {
		setChanged();
		super.notifyObservers("test data");
	}
}

class Client implements Observer {
	private String name;
	public Client(String name){
		this.name=name;
	}
	public void update(Observable o, Object arg) {
		
		System.out.println(name  + " - " +arg.toString());
	}
}
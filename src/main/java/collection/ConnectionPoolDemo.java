package collection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

class ConnectionPoolManager {

	String databaseUrl = "jdbc:mysql://localhost:3306/myDatabase";
	String userName = "userName";
	String password = "userPass";

	Vector connectionPool = new Vector();
	private int MAX_POOL_SIZE = 5;
	private int timeout_min = 1;

	public ConnectionPoolManager() {
		initialize();
	}

	public ConnectionPoolManager(
	// String databaseName,
			String databaseUrl, String userName, String password) {
		this.databaseUrl = databaseUrl;
		this.userName = userName;
		this.password = password;
		initialize();
	}

	private void initialize() {
		// Here we can initialize all the information that we need
		initializeConnectionPool();
	}

	private void initializeConnectionPool() {
		while (!checkIfConnectionPoolIsFull()) {
			System.out
					.println("Connection Pool is NOT full. Proceeding with adding new connections");
			// Adding new connection instance until the pool is full
			connectionPool.addElement(createNewConnectionForPool());
		}
		System.out.println("Connection Pool is full.");
	}

	private synchronized boolean checkIfConnectionPoolIsFull() {

		// Check if the pool size
		if (connectionPool.size() < MAX_POOL_SIZE) {
			return false;
		}

		return true;
	}

	// Creating a connection
	private MyCon createNewConnectionForPool() {
		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(databaseUrl, userName,
					password);
			System.out.println("Connection: " + connection);
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle);
			return null;
		} catch (ClassNotFoundException cnfe) {
			System.err.println("ClassNotFoundException: " + cnfe);
			return null;
		}
		return new MyCon(connection, System.currentTimeMillis(), false);
	}

	public synchronized Connection getConnectionFromPool() {
		MyCon myCon = null;
		// Check if there is a connection available. There are times when all
		// the connections in the pool may be used up
		if (connectionPool.size() > 0) {
			myCon = (MyCon) connectionPool.firstElement();
			connectionPool.removeElementAt(0);
		}
		myCon.setUseFlag(true);
		// Giving away the connection from the connection pool
		return myCon.getConnection();
	}

	public synchronized void returnConnectionToPool(Connection connection) {
		// Adding the connection from the client back to the connection pool
		connectionPool.addElement(new MyCon(connection, System
				.currentTimeMillis(), false));
	}

	public synchronized void removeConnectionFromPool(MyCon myCon) {
		// Adding the connection from the client back to the connection pool
		connectionPool.remove(myCon);
	}

	class MaintainThread implements Runnable {
		long waitTime = 1000000;

		public void run() {
			while (true) {
				try{
				for (Iterator iterator = connectionPool.iterator(); iterator
						.hasNext();) {
					MyCon myCon = (MyCon) iterator.next();
					long sec = ((System.currentTimeMillis() - myCon
							.getInitTime()) / 1000);
					if (waitTime > sec) {
						waitTime = sec;
					}
					if (myCon.isUseFlag()) {
						continue;
					}
					if ((sec / 60) >= timeout_min) {
						removeConnectionFromPool(myCon);
						break;
					}
				}
				synchronized(this){
					wait(waitTime);
				}
				}catch(Exception e){}
			}
		}
	}
}

class MyCon {
	private long initTime = 0;
	private Connection connection = null;
	private boolean useFlag = false;

	public boolean isUseFlag() {
		return useFlag;
	}

	public void setUseFlag(boolean useFlag) {
		this.useFlag = useFlag;
	}

	public MyCon(Connection connection, long timeout, boolean useFlag) {
		this.connection = connection;
		this.initTime = timeout;
		this.useFlag = useFlag;
	}

	public long getInitTime() {
		return initTime;
	}

	public void setInitTime(int timeout) {
		this.initTime = timeout;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}

public class ConnectionPoolDemo {
	public static void main(String args[]) {
		ConnectionPoolManager ConnectionPoolManager = new ConnectionPoolManager();
	}

}

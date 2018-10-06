package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import rmi.ChatClientIF;
import rmi.ChatServerIF;

public class ChatServer extends UnicastRemoteObject implements ChatServerIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private ArrayList<ChatClientIF> chatClients; 
	
	private ArrayList<String> allClients;
	
	private ArrayList<Boolean> allAvailability;
	
	/**
	 * 
	 * @throws RemoteException
	 */
	protected ChatServer() throws RemoteException {
		chatClients = new ArrayList<ChatClientIF>();
		allClients = new ArrayList<String>();
		allAvailability = new ArrayList<Boolean>();
	}

	@Override
	public synchronized void resgiterChatClient(ChatClientIF chatClient) throws RemoteException {
		this.chatClients.add(chatClient);
	}

	@Override
	public synchronized void brosdcastMessage(String message) throws RemoteException {
		int i = 0;
		while (i < chatClients.size()) {
			chatClients.get(i++).retriveMessage(message);
		}
		
	}
	@Override
	public synchronized void addToAllClients(String name) throws RemoteException{
		this.allClients.add(name);
	}

	@Override
	public void directMessage(String myName, String message, String receiver) throws RemoteException {
		
		int myIndex = 0;
		int rIndex = -1;
		for (int j = 0; j < allClients.size();j++ ) {
			if (allClients.get(j).equals(myName)) {
				myIndex = j;
			}
		}
		for (int i = 0; i < allClients.size();i++ ) {
			if (allClients.get(i).equals(receiver)) { 
				rIndex = i;
				if (allAvailability.get(i) != true) {
					rIndex = myIndex;
					myName = "";
					message = "User is not available to chat right now.";
				}
			}
		} 
		if (rIndex == -1) {
			rIndex = myIndex;
			myName = "";
			message = "User does not exixts";
		}
		
		chatClients.get(rIndex).retriveMessage(myName + ": " + message);	
	}

	@Override
	public void addAvailability(boolean availibility) throws RemoteException {
		this.allAvailability.add(availibility);
		
	}

	@Override
	public void exitClient(String name) throws RemoteException {
		for (int i = 0; i < allClients.size();i++ ) {
			if (allClients.get(i).equals(name)) { 
				chatClients.remove(i);
				allClients.remove(i);
				allAvailability.remove(i);
			}
		}	
	}
	public void changeStatus(String name, boolean status) throws RemoteException {
		for (int i = 0; i < allClients.size();i++ ) {
			if (allClients.get(i).equals(name)) { 
				allAvailability.set(i, status);
			}
		} 
	}

	@Override
	public ArrayList<String> getNames() throws RemoteException {
		
		return this.allClients;
	}

	@Override
	public ArrayList<Boolean> getAllAvailability() throws RemoteException {
		return this.allAvailability;
	}
}

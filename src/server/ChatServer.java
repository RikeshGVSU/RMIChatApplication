package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

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
	
	/**
	 * 
	 * @throws RemoteException
	 */
	protected ChatServer() throws RemoteException {
		chatClients = new ArrayList<ChatClientIF>();
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
	

}

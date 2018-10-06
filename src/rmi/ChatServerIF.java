package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface ChatServerIF extends Remote{
	void resgiterChatClient(ChatClientIF chatClient) throws RemoteException;
	void brosdcastMessage(String message) throws RemoteException;
	void addToAllClients(String name) throws RemoteException;
	void directMessage(String myName, String message, String receiver) throws RemoteException;
	void addAvailability(boolean availibiity) throws RemoteException;
	void exitClient(String name) throws RemoteException;
	void changeStatus(String name, boolean b) throws RemoteException;
	ArrayList<String> getNames() throws RemoteException;
	ArrayList<Boolean> getAllAvailability() throws RemoteException;
}

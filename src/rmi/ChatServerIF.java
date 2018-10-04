package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerIF extends Remote{
	void resgiterChatClient(ChatClientIF chatClient) throws RemoteException;
	void brosdcastMessage(String message) throws RemoteException;
}

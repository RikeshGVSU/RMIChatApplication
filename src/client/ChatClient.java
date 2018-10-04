package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import rmi.ChatClientIF;
import rmi.ChatServerIF;

public class ChatClient extends UnicastRemoteObject implements ChatClientIF, Runnable {

	private ChatServerIF chatServer;
	private String name;
	protected ChatClient(String name, ChatServerIF chatServer) throws RemoteException {
		this.chatServer = chatServer;
		this.name = name;
		chatServer.resgiterChatClient(this);
	}

	@Override
	public void retriveMessage(String message) throws RemoteException {
		System.out.println(message);
		
	}

	@Override
	public void run() {
		Scanner scanner =new Scanner(System.in);
		String message;
		while(true) {
			message = scanner.nextLine();
			try {
				chatServer.brosdcastMessage(this.name + " : " + message);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

}

# RMIChatApplication

##### Simple chat application using RMI

## Running the program:

### compile ChatServer:
- cd {application-folder-path}/bin
- rmic server.ChatServer

### compile ChatClient:

- rmic client.ChatClient

### Start registry:

- rmiregistry &

### open a different terminal and run server:

- cd <application-folder-path>/bin

- java server.ChatServerDriver

### open different terminals for different client and start chat:

- cd {application-folder-path}/bin

- java client.ChatClientDriver {user-name}

### Chat Commands
- friends   -> Lists all the friends online with there status of availability.
- talk {username} {message}	-> Sends message to the username given;
- broadcast {message}	-> Breadcasts message to all the online users.
- busy	-> Change your availibility to busy.
- available	-> Change your availability to available
- exit	-> Exit from chat application.

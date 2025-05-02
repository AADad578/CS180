# Client Module Documentation

This documentation provides an overview of the `ClientInterface`, `Client`, and `ServerResponseException` classes in the `Client` package. These components enable communication between the client and the server in a networked marketplace application.

---

## Table of Contents

1. [ClientInterface](#clientinterface)
   - Overview
   - Methods
2. [Client](#client)
   - Overview
   - Constructors
   - Methods
   - Fields
3. [ServerResponseException](#serverresponseexception)
   - Overview
   - Constructors

---

## ClientInterface

### Overview

The `ClientInterface` defines the contract that any `Client` class must implement to communicate with the server. It includes methods for managing user sessions, sending and receiving requests, and handling marketplace data such as users, items, chats, and messages.

### Methods

| Method Signature                                 | Description |
|--------------------------------------------------|-------------|
| `void connectToServer(String address, int port)` | Establishes a connection to the server using the given address and port. |
| `void disconnectFromServer()`                    | Gracefully disconnects from the server and closes resources. |
| `void sendRequest(Object request)`               | Sends a serialized request object to the server. |
| `Object receiveResponse()`                       | Receives a response object from the server and handles errors. |
| `void createNewUser(User user)`                  | Sends a request to create a new user in the system. |
| `void createNewItem(Item item)`                  | Sends a request to add a new item to the system. |
| `void createNewChat(Chat chat)`                  | Sends a request to create a new chat between users. |
| `void addMessage(Message message)`               | Sends a message to an existing chat. |
| `void logInUser(User user)`                      | Logs in a user with their credentials. |
| `User[] getUsers()`                              | Retrieves a list of all users in the system. |
| `Chat[] getChats(User user)`                     | Retrieves all chats associated with a given user. |
| `Item[] searchItems(String term)`                | Searches for items by a keyword. |
| `void updateUser(User user)`                     | Sends a request to update an existing user’s data. |
| `void removeItem(Item item)`                     | Sends a request to remove an item from the server. |
| `boolean isConnected()`                          | Checks if the client is currently connected to the server. |

---

## Client

### Overview

The `Client` class implements `ClientInterface` and handles the actual network communication logic using sockets and object streams. It follows a thread-safe architecture and uses the `Request` object format for all communications.

### Constructors

| Constructor Signature | Description |
|------------------------|-------------|
| `Client()` (implicit) | Initializes a `Client` object with no immediate connection. |

### Methods

| Method Signature | Description |
|------------------|-------------|
| `void connectToServer(String address, int port)` | Opens a connection to the server and initializes object streams. |
| `void disconnectFromServer()` | Closes the streams and socket, and marks the client as disconnected. |
| `void sendRequest(Object request)` | Sends an object to the server using a thread-safe write operation. |
| `Request receiveResponse()` | Receives a `Request`-wrapped response, throwing exception if an error is received. |
| `void createNewUser(User user)` | Sends a `CreateNewUser` request to register a new user. |
| `void createNewItem(Item item)` | Sends a `CreateNewItem` request to register a new item. |
| `void createNewChat(Chat chat)` | Sends a `CreateNewChat` request to initiate a chat. |
| `void addMessage(Message message)` | Sends an `AddMessage` request to send a message. |
| `void logInUser(User user)` | Sends a `LogInUser` request and sets the currentUsername if successful. |
| `User[] getUsers()` | Sends a `GetUsers` request and parses the returned list. |
| `Chat[] getChats(User user)` | Sends a `GetChats` request and returns associated chats. |
| `Item[] searchItems(String term)` | Sends a `SearchItems` request and returns search results. |
| `void updateUser(User user)` | Sends an `UpdateUser` request to update the user’s state. |
| `void removeItem(Item item)` | Sends a `RemoveItem` request to remove an item from the server.|
| `boolean isConnected()` | Returns whether the client is currently connected to the server. |
| `String getCurrentUsername()` | Returns the current username of the logged-in user. |

### Fields

| Field Name        | Type                  | Description |
|-------------------|-----------------------|-------------|
| `socket`          | `Socket`              | The network socket used to communicate with the server. |
| `out`             | `ObjectOutputStream`  | Stream used to send objects to the server. |
| `in`              | `ObjectInputStream`   | Stream used to receive objects from the server. |
| `ioLock`          | `Object`              | Lock object used for synchronizing I/O operations. |
| `connected`       | `boolean` (volatile)  | Indicates whether the client is currently connected. |
| `host`            | `String`              | Hostname or IP address of the server. |
| `port`            | `int`                 | Port number of the server. |
| `currentUsername` | `String` (volatile)   | The username of the currently logged-in user. |

---

## ServerResponseException

### Overview

`ServerResponseException` is a custom exception class used to handle all error responses from the server. It encapsulates the error message returned by the server inside a standard Java exception.

### Constructors

| Constructor Signature | Description |
|------------------------|-------------|
| `ServerResponseException(String message)` | Initializes the exception with a specific error message. |

# CS 18000 - Team Project Phase 3 - Marketplace

## How to compile and run the project

Use intelliJ to run this program. The server side can be run from the Server.java class in the Server package. The client will be able to be run from the Client.java class in the Client package. The server will automatically launch more instances with Sockets open on threads when a client connects. Client should be launched once per client.
To run unit tests, it is required to have both Junit 4 and 5. It is guaranteed to work on the default installation of IntelliJ, which is the recommended way to run this code.

## Submission Log

Ankur Raghavan - Submitted Project Phase 1 to Vocareum
Ankur Raghavan - Submitted Project Phase 2 to Vocareum 
Ankur Raghavan - Submitted Project Phase 3 to Vocareum 
Ankur Raghavan - Submitted Presentation Link to Brightspace
Ankur Raghavan - Submitted Report to Brightspace

## Detailed description of each class

### Item Class - Vincent Holloway

This class has five instance variables relating to an item which include its name, price, location, owner, and picture file name. When a User is interested in buying or selling an item, with name, price, owner, and location all being cruical information about the item with the picture file name being the way to access the item's picture file. This class has setter and getter methods of each instance variable. The toString() method includes all variables except the picture file name and returns a formatted String based on the other variables. The toEquals() method determiness whether one item is equal to another item by comparing every variable to each other. One of the instance variables of the User class includes a list of Items made by the a user. 

### ItemTester Class - Vincent Holloway

This testing class tests every method in the Item Class by using the assertEquals() method in the testing of all methods except for testing the toEquals(). The testing of this method used assertTrue() and assertFalse() while comparing different Items to each other. When using the assertEquals() for every other method, an expected output was compared to the acutal output. 

### Message Class - Vincent Holloway

This class has four instance variables relating to a message which include its written content, time sent, receiver, and sender. When a User is interested in messaging another User for buying or selling an item, a Message object will be created with these four variables. This class has setter and getter methods of each instance variable. The toString() method includes all four variables and returns a formatted String based on them. The toEquals() method determiness whether one message is equal to another message by comparing every variable to each other. The Chat class includes an ArrayList of Message objects as apart of one of its instance variables. 

### MessageTester Class - Vincent Holloway

This testing class tests every method in the Message Class by using the assertEquals() method in the testing of all methods except for testing the toEquals(). The testing of this method used assertTrue() and assertFalse() while comparing different Messages to each other. When using the assertEquals() for every other method, an expected output was compared to the acutal output. 

## GUI Class - Vincent Holloway

This class will handle the GUI and will only call the Client Class methods for access to database. There is no specific testing class as this GUI was physically tested by the team members of this project. Currently, the layout of the GUI starts with a welcome screen with two options: "Login" and "Create Account". Then after the user enters their information into one of these two options, the main screen appears with a search items bar at the top and four buttons: "Chat", "Add Item", "Remove Item", and "View User Profile". The search item bar receives an input of a string that displays items related this string. The "Chat" button allows the user to view previous chats with the option to add a message to them or the user can create an entirely new chat. The "Add Item" button allows the user to add an item to the database. The "Remove Item" button allows the user to remove an item from the database. The 'View User Profile" button allows the user to view profile with ability to update it with information including balance and also allows user to delete account. The user will exit the GUI by clicking the X at the top right. 

## Database Class - Ankur Raghavan

This class has 3 instance variables which are lists of Items, Chats, and Users. The methods in this class include getters and setters for each of those lists and a toString that converts them to a string using Arrays.toString(A); This object stores all the data for the project and will be written to a file and read.

## DatabaseTester Class - Ankur Raghavan

This testing class tests every method in the Database class. It tests the getters and setters by using assertEquals to determine if the value is the same as expected. It also tests the toString in the same method.

## Server Class - Ankur Raghavan

This class has 4 static variables and 1 instance variable. The static variables include a Database object and 3 guards for it. One for each section: Users, Chats, and Items. These are used by the synchronized methods to ensure that multithreading doesn't corrupt the database, while allowing multiple things to happen simultaneously. The instance variable is the socket that is being used by this object. The Socket is used to communicate with the client. The methods in this class are adding or removing a Chat, Item, or User. When adding, it is not allowed to have identical objects in the list, so if given that, it will throw a InvalidInputException. When removing, if no match is found, it will throw an InvalidInputException. In addition to those methods, there are methods for saving and recalling the database from a file. the database is saved to file after every operation on the database. This method does not require synchronization because it is a read only operation and occurs after every operation. recallDatabase is only called on startup of the Server main method, so it is static and doesn't require synchronization. If the file containing the database doesn't exist, it will generate a new database. Finally there are some more methods including one to search through the list of items and return any that match the search parameters, one to update users, one to addMessages to a chat, and one to all chats a user is in and the one chat between two users.

## ServerTester Class - Ankur Raghavan

This testing class tests every method in the Server class by comparing expected values to the true values using assertEquals. Additionally it tests the throwing of InvalidInputException by using the fail() method if it does not throw the error. It also tests saving and recalling the database.

## InvalidInputException - Ankur Raghavan

This exception class is used to signify to the server that the input given by the client is invalid. This is used internally only and will always be handled by the code (will not cause any crashes).

## InvalidInputExceptionTester - Ankur Raghavan

This testing class tests the creation of the InvalidInputException with a message

## Request - Ankur Raghavan

This class is used to send data between the Server and the Client. It contains a String that is the Action that must occur, and a Payload which is of a different type based off the action specified and the context

Action:Payload
- Return:Payload
All actions may return: “ERROR:Failed to Transmit Data Correctly” if IOException or ClassNotFoundException is thrown on reading the object (ie. sending an object that the server can’t find)

CreateNewUser:User
- OK:null
- ERROR:Payload Not a User
- ERROR:Username already exists

CreateNewItem:Item
- OK:null
- ERROR:Payload Not an Item
- ERROR:Item already exists

CreateNewChat:Chat
- OK:null
- ERROR:Payload Not an Chat
- ERROR:Chat already exists

AddMessage:Message
- OK:null
- ERROR:Payload Not a Message
- ERROR:Users have same username
- ERROR:Sender is not a participant in this chat.
- ERROR:Receiver is not a participant in this chat.

GetUsers:null
- RESPONSE:User[]

GetChats:User
- ERROR:Payload Not a User
- RESPONSE:Chat[]

SearchItems:String (the search term)
- ERROR:Payload Not a String
- RESPONSE:Item[]

LogInUser:User
- OK:null
- ERROR:Payload Not a User
- ERROR:Invalid Password
- ERROR:Invalid Username

UpdateUser:User
- OK:null
- ERROR:Payload Not a User
- ERROR:Invalid Username

RemoveItem:Item
- OK:null
- ERROR:Payload Not an Item
- ERROR:Selected Item not found

RemoveUser:User
- OK:null
- ERROR:Payload Not a User
- ERROR:Selected User not found

## RequestTester - Ankur Raghavan

This testing class tests the Request class's ability to store both actions and payloads, and the getters associated with that


### User - Karthik Nandagiri

**Description**  
The `User` class represents a user entity in the marketplace system. It encapsulates user-specific information such as name, balance, username, and password.  
It provides mechanisms for identity management, authentication, and data persistence via the `Serializable` interface.  
The class adheres to the `UserInterface` contract, ensuring standardized access to user data.

**Key Responsibilities**
- Store and retrieve user profile information.
- Modify account details (name, username, password, balance).
- Maintain an array of items associated with the user.
- Support object comparison and formatted string representation.

**Implements**
- `UserInterface`
- `Serializable`

---

### UserInterface - Karthik Nandagiri

**Description**  
Defines the contract that any user-related class must fulfill in the marketplace system.  
Provides method signatures for basic user account manipulation and retrieval.

**Key Responsibilities**
- Get/set the user's name, username, password, and balance.
- Enforce consistent behavior across all user-like entities.

---

### UserTester - Karthik Nandagiri

**Description**  
A unit test class for the `User` class.  
It ensures correctness of all core functionalities such as getters, setters, serialization, and string representation.

**Key Responsibilities**
- Test all mutator and accessor methods.
- Validate object serialization and deserialization behavior.
- Check equivalence and formatting logic.

---

### ClientInterface - Karthik Nandagiri

**Description**  
Defines the core functionalities required for a client to communicate with a marketplace server using object-based requests. This interface ensures consistency and testability of different client implementations.

**Key Responsibilities**
- Specify how clients connect to/disconnect from the server.
- Define how requests are sent and responses are received.
- Provide method contracts for all user, item, chat, and message-related actions.
- Ensure the client supports login, update, search, and data retrieval operations.

---

### Client - Karthik Nandagiri

**Description**  
Implements the `ClientInterface` using Java sockets and object streams. All client-server communication is encapsulated in `Request` objects. Ensures thread safety and error propagation via structured exception handling.

**Key Responsibilities**
- Handle connection lifecycle with the server (`connect`, `disconnect`).
- Manage synchronized input/output using `ObjectInputStream` and `ObjectOutputStream`.
- Send structured `Request` objects and process `Request`-based responses.
- Perform user authentication, data creation, and querying actions.
- Maintain internal state for the connected user.

---

### ServerResponseException - Karthik Nandagiri

**Description**  
Custom exception class thrown when the server returns an error in response to a client request. It encapsulates the error message and enables uniform handling of server-side issues.

**Key Responsibilities**
- Represent server error responses in client logic.
- Improve debuggability and control flow during request handling.
- Integrate seamlessly with client-side exception handling logic.


---


### Chat - Karthik Nandagiri

**Description**  
Models a conversation between two users.  
Stores the list of exchanged messages and tracks the users involved.  
Supports adding new messages while enforcing participant validation.

**Key Responsibilities**
- Manage the list of two users in a conversation.
- Add validated messages to the chat history.
- Retrieve the entire conversation.
- Implement equality checks based on participant identity (order-independent).

**Implements**
- `ChatInterface`
- `Serializable`

---

### ChatInterface - Karthik Nandagiri

**Description**  
Defines the essential behavior for a chat between two users in the system.

**Key Responsibilities**
- Provide access to the users and messages in the chat.
- Add new messages under validity constraints.
- Support equality and string formatting of chat instances.

---

### ChatTest - Karthik Nandagiri

**Description**  
Unit tests for the `Chat` class.  
Verifies correct behavior for chat creation, message exchange, participant validation, and equality.

**Key Responsibilities**
- Ensure that valid users can exchange messages.
- Prevent message injection from unauthorized users.
- Confirm proper string and equality behavior.

---


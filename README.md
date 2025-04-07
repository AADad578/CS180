# CS 18000 - Team Project Phase 1 - Marketplace

## How to compile and run the project

Use intelliJ to run this program. The server side can be run from the Server.java class in the Server package. The client will be able to be run from the Client.java class in the Client package. The server will automatically launch more instances with Sockets open on threads when a client connects. Client should be launched once per client.
To run unit tests, it is required to have both Junit 4 and 5. It is garunteed to work on the default installation of IntelliJ, which is the recommended way to run this code.

## Submission Log

Ankur Raghavan - Submitted Project Phase 1 to Vocareum 

## Detailed description of each class

### Item Class - Vincent Holloway

This class has four instance variables relating to an item which include its name, price, location, and picture file name. When a User is interested in buying or selling an item, with name, price, and location all being cruical information about the item with the picture file name being the way to access the item's picture file. This class has setter and getter methods of each instance variable. The toString() method includes all variables except the picture file name and returns a formatted String based on the other variables. The toEquals() method determiness whether one item is equal to another item by comparing every variable to each other. One of the instance variables of the User class includes a list of Items made by the a user. 

### ItemTester Class - Vincent Holloway

This testing class tests every method in the Item Class by using the assertEquals() method in the testing of all methods except for testing the toEquals(). The testing of this method used assertTrue() and assertFalse() while comparing different Items to each other. When using the assertEquals() for every other method, an expected output was compared to the acutal output. 

### Message Class - Vincent Holloway

This class has four instance variables relating to a message which include its written content, time sent, receiver, and sender. When a User is interested in messaging another User for buying or selling an item, a Message object will be created with these four variables. This class has setter and getter methods of each instance variable. The toString() method includes all four variables and returns a formatted String based on them. The toEquals() method determiness whether one message is equal to another message by comparing every variable to each other. The Chat class includes an ArrayList of Message objects as apart of one of its instance variables. 

### MessageTester Class - Vincent Holloway

This testing class tests every method in the Message Class by using the assertEquals() method in the testing of all methods except for testing the toEquals(). The testing of this method used assertTrue() and assertFalse() while comparing different Messages to each other. When using the assertEquals() for every other method, an expected output was compared to the acutal output. 

## Database Class - Ankur Raghavan

This claas has 3 instance variables which are lists of Items, Chats, and Users. The methods in this class include getters and setters for each of those lists and a toString that converts them to a string using Arrays.toString(A); This object stores all the data for the project and will be written to a file and read.

## DatabaseTester Class - Ankur Raghavan

This testing class tests every method in the Database class. It tests the getters and setters by using assertEquals to determine if the value is the same as expected. It also tests the toString in the same method.

## Server Class - Ankur Raghavan

This class has 4 static variables and 2 instance variables. The static variables include a Database object and 3 guards for it. One for each section: Users, Chats, and Items. These are used by the synchronized methods to ensure that multithreading doesn't corrupt the database, while allowing multiple things to happen simultaneously. The instance variables are the serverSocket that is being used by this object and whether it has a client attached. The ServerSocket is used to communicate with the client, while the boolean is used to automatically generate more servers when more clients connect. The methods in this class are adding or removing a Chat, Item, or User. When adding, it is not allowed to have identical objects in the list, so if given that, it will throw a InvalidInputException. When removing, if no match is found, it will throw an InvalidInputException. In addition to those methods, there are methods for saving and recalling the database from a file. the database is saved to file after every operation on the database. This method does not require synchronization because it is a read only operation and occurs after every operation. recallDatabase is only called on startup of the Server main method, so it is static and doesn't require synchronization. If the file containing the database doesn't exist, it will generate a new database. Finally there is a method to search through the list of items and return any that match the search parameters.

## ServerTester Class - Ankur Raghavan

This testing class tests every method in the Server class by comparing expected values to the true values using assertEquals. Additionally it tests the throwing of InvalidInputException by using the fail() method if it does not throw the error. It also tests saving and recalling the database.

## GUI Class - Ankur Raghavan

This class will handle the GUI and will be called by the Client class. It does not have a testing class at the moment because none of the methods are able to be tested automatically as they cause GUI changes.

## InvalidInputException - Ankur Raghavan

This exception class is used to signify to the server that the input given by the client is invalid. This is used internally only and will always be handled by the code (will not cause any crashes).

## InvalidInputExceptionTester - Ankur Raghavan

This testing class tests the creation of the InvalidInputException with a message

---

### User - Karthik Nandagiri

**Description**  
The `User` class represents a user entity in the marketplace system. It encapsulates user-specific information such as name, balance, username, password, and associated items.  
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
The `ClientInterface` specifies operations that a client-side class must support to interact with the marketplace system.  
This includes network communication, session handling, and synchronization with server-side data such as chats and items.

**Key Responsibilities**
- Establish and terminate server connections.
- Perform login authentication.
- Receive chats and item listings from the server.

> 🛠 This interface is a stub for Phase 2 implementation.

---

### ClientTester - Karthik Nandagiri

**Description**  
Placeholder class for testing the future `Client` implementation.

> 🛠 To be implemented in **Phase 2**.

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


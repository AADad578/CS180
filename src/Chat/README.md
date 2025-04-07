# Chat - Karthik Nandagiri

This document provides detailed documentation for the `Chat`, `ChatInterface`, and `MessageError` classes in the `Chat` package.

---

## **1. Chat Class**

The `Chat` class represents a chat between two users, storing messages exchanged between them. It implements the `ChatInterface` and is serializable for persistence.

### Fields

- **`users`**:  
  An array of two `User` objects representing the participants in the chat.

- **`messages`**:  
  An `ArrayList` of `Message` objects representing the messages exchanged in the chat.

---

### Constructors

#### `Chat()`
Constructs an empty chat with no users or messages.

---

#### `Chat(User user1, User user2)`
Constructs a chat between two users with no initial messages.

**Parameters**:
- `user1`: The first user in the chat.
- `user2`: The second user in the chat.

---

#### `Chat(User user1, User user2, ArrayList<Message> messages)`
Constructs a chat between two users with an initial list of messages.

**Parameters**:
- `user1`: The first user in the chat.
- `user2`: The second user in the chat.
- `messages`: The initial list of messages in the chat.

---

### Methods

#### `addMessage(String message, User sentBy, int timesent)`
Adds a message to the chat if the sender is a participant in the chat.

**Parameters**:
- `message`: The content of the message.
- `sentBy`: The user sending the message.
- `timesent`: The timestamp of when the message was sent.

**Throws**:
- `MessageError`: If the sender is not a participant in the chat.

---

#### `getUsers()`
Returns the two users participating in the chat.

**Returns**:  
An array containing the two users in the chat.

---

#### `getMessages()`
Returns the list of messages exchanged in the chat.

**Returns**:  
An `ArrayList` of `Message` objects representing the chat history.

---

#### `equals(Object o)`
Checks if this chat is equal to another object.  
Two chats are considered equal if they involve the same two users, regardless of order.

**Parameters**:
- `o`: The object to compare with this chat.

**Returns**:  
`true` if the chats are equal, `false` otherwise.

---

#### `toString()`
Returns a string representation of the chat, including the users and messages.

**Returns**:  
A formatted string with user details and chat messages.

---

## **2. ChatInterface**

The `ChatInterface` defines the contract for chat functionality, including managing users, messages, and equality checks.

### Methods

#### `getUsers()`
Returns the two users participating in the chat.

**Returns**:  
An array of `User` objects representing the chat participants.

---

#### `getMessages()`
Returns the list of messages exchanged in the chat.

**Returns**:  
A `List` of `Message` objects representing the chat history.

---

#### `addMessage(String message, User sentBy, int timesent)`
Adds a message to the chat if the sender is a participant.

**Parameters**:
- `message`: The content of the message.
- `sentBy`: The user sending the message.
- `timesent`: The timestamp of when the message was sent.

**Throws**:
- `MessageError`: If the sender is not a participant in the chat.

---

#### `equals(Object o)`
Checks if this chat is equal to another object.

**Parameters**:
- `o`: The object to compare with this chat.

**Returns**:  
`true` if the chats are equal, `false` otherwise.

---

#### `toString()`
Returns a string representation of the chat.

**Returns**:  
A formatted string describing the chat.

---

## **3. MessageError Class**

The `MessageError` class is a custom exception used to handle errors related to invalid message operations in the chat.

### Constructor

#### `MessageError(String message)`
Constructs a `MessageError` with the specified error message.

**Parameters**:
- `message`: The error message describing the issue.

---

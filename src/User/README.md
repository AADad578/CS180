# User Module Documentation

This documentation provides an overview of the `UserInterface` and `User` classes in the `User` package. These components are designed to represent a user entity with attributes such as name, username, password, and balance, along with methods to interact with these attributes.

---

## Table of Contents

1. [UserInterface](#userinterface)
    - Overview
    - Methods
2. [User](#user)
    - Overview
    - Constructors
    - Methods
    - Fields
3. [UserTester]

---

## UserInterface

### Overview

The `UserInterface` is an interface that defines the contract for interacting with a `User` object. It specifies methods for accessing and modifying user-related data such as name, username, password, and balance.

### Methods

| Method Signature                     | Description                                                                 |
|--------------------------------------|-----------------------------------------------------------------------------|
| `String getName()`                   | Retrieves the name of the user.                                             |
| `void setName(String name)`          | Sets the name of the user.                                                 |
| `String getUserName()`               | Retrieves the username of the user.                                        |
| `void setUserName(String userName)`  | Sets the username of the user.                                             |
| `String getPassword()`               | Retrieves the password of the user.                                        |
| `void setPassword(String password)`  | Sets the password of the user.                                             |
| `double getBalance()`                | Retrieves the balance of the user.                                         |
| `void setBalance(double balance)`    | Sets the balance of the user.                                              |
| `String toString()`                  | Returns a string representation of the user object.                        |

---

## User

### Overview

The `User` class implements the `UserInterface` and provides concrete implementations for all the methods defined in the interface. Additionally, it implements the `Serializable` interface to allow the object to be serialized for storage or transmission.

### Constructors

| Constructor Signature                                                | Description                                                                 |
|----------------------------------------------------------------------|-----------------------------------------------------------------------------|
| `User(String name, double balance, String username, String password)`| Initializes a new `User` object with the specified name, balance, username, and password. |

### Methods

| Method Signature                    | Description                                                                 |
|-------------------------------------|-----------------------------------------------------------------------------|
| `String getName()`                  | Retrieves the name of the user.                                             |
| `void setName(String name)`         | Sets the name of the user.                                                 |
| `String getUserName()`              | Retrieves the username of the user.                                        |
| `void setUserName(String userName)` | Sets the username of the user.                                             |
| `String getPassword()`              | Retrieves the password of the user.                                        |
| `void setPassword(String password)` | Sets the password of the user.                                             |
| `double getBalance()`               | Retrieves the balance of the user.                                         |
| `void setBalance(double balance)`   | Sets the balance of the user.                                              |
| `String toString()`                 | Returns a string representation of the user object, including username, name, and balance. |
| `Boolean equals(Object o)`          | Retruns true if the Object o is an instance of User and the username of o is equal to the usernme of the current User object
### Fields

| Field Name     | Type       | Description                                                                 |
|----------------|------------|-----------------------------------------------------------------------------|
| `username`     | `String`   | The username of the user.                                                   |
| `name`         | `String`   | The full name of the user.                                                  |
| `balance`      | `double`   | The account balance of the user.                                            |
| `password`     | `String`   | The password associated with the user's account.                            |


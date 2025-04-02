# Client Interface Documentation

The `Client` interface defines the essential methods for interacting with a server-side marketplace application. It includes functionalities such as connecting to the server, sending messages, logging in, and handling payments.

## Methods

### `void connectToServer(String address, int port)`
Establishes a connection to the server.

- **Parameters**:
    - `address`: The server's address (IP or domain name).
    - `port`: The port number for the server connection.

- **Returns**: `void`

---

### `void disconnectFromServer()`
Disconnects from the server.

- **Parameters**: None
- **Returns**: `void`

---

### `boolean login(String username, String password)`
Authenticates the user with their username and password.

- **Parameters**:
    - `username`: The username of the user attempting to log in.
    - `password`: The password associated with the username.

- **Returns**: `boolean`
    - `true` if login is successful.
    - `false` if login fails.

---

### `void sendMessage(String message)`
Sends a message to the server.

- **Parameters**:
    - `message`: The message to be sent to the server.

- **Returns**: `void`

---

### `boolean payment(double amount)`
Processes a payment for the user.

- **Parameters**:
    - `amount`: The amount to be paid.

- **Returns**: `boolean`
    - `true` if the payment is successfully processed.
    - `false` if payment fails.

---

### Placeholder Methods (To Be Added)

#### `List<Chat> receiveChats()`
Retrieves a list of chat messages from the server.

- **Returns**: `List<Chat>`

#### `List<Item> receiveItems()`
Retrieves a list of items available on the marketplace.

- **Returns**: `List<Item>`

#### `void sendNewItem(Item item)`
Sends a new item to be listed on the marketplace.

- **Parameters**:
    - `item`: The `Item` object to be sent.
- **Returns**: `void`

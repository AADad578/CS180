# CS 18000 - Team Project Phase 1 - Marketplace

Instructions on how to compile and run this project

Student - Submitted Project Phase 1 to Vocareum 

Detailed description of each class

Item Class - Vincent Holloway

This class has four instance variables relating to an item which include its name, price, location, and picture file name. When a User is interested in buying or selling an item, with name, price, and location all being cruical information about the item with the picture file name being the way to access the item's picture file. This class has setter and getter methods of each instance variable. The toString() method includes all variables except the picture file name and returns a formatted String based on the other variables. The toEquals() method determiness whether one item is equal to another item by comparing every variable to each other. One of the instance variables of the User class includes a list of Items made by the a user. 

ItemTester Class - Vincent Holloway

This testing class tests every method in the Item Class by using the assertEquals() method in the testing of all methods except for testing the toEquals(). The testing of this method used assertTrue() and assertFalse() while comparing different Items to each other. When using the assertEquals() for every other method, an expected output was compared to the acutal output. 

Message Class - Vincent Holloway

This class has four instance variables relating to a message which include its written content, time sent, receiver, and sender. When a User is interested in messaging another User for buying or selling an item, a Message object will be created with these four variables. This class has setter and getter methods of each instance variable. The toString() method includes all four variables and returns a formatted String based on them. The toEquals() method determiness whether one message is equal to another message by comparing every variable to each other. The Chat class includes an ArrayList of Message objects as apart of one of its instance variables. 

MessageTester Class - Vincent Holloway

This testing class tests every method in the Message Class by using the assertEquals() method in the testing of all methods except for testing the toEquals(). The testing of this method used assertTrue() and assertFalse() while comparing different Messages to each other. When using the assertEquals() for every other method, an expected output was compared to the acutal output. 

# Group264Project

1. Description: We are planning to create a message API, which includes  the fonction of:
    - Sending and receiving messages.
    - Replying messages.
    - Edit messages.
    - Group chat.
    - Search for messages.

   We decide to create:
    - User entity,
    - Message entity,
    - Channel entity,
   
      (Where each entity performs different functionality, in this document we only implemented User entity so far).

   (For User entity, we called CreateUserUseCase which can call a POST method to send request to create a User in 
   application.)

2. Link to the documentation for an API:
    - https://getstream.io/chat/docs/java/?language=java
    - Base URL: "https://api-{APP_ID}.sendbird.com"
    - CreateUserUseCase URL: API_URL + "/v3/users"
3. Screenshot of using a tool to try out the API:
![b2eb75268979cdcd47f8ab6183d5dc1.png](..%2Fb2eb75268979cdcd47f8ab6183d5dc1.png)

4. We created a test method in MongoChatDB.java where we request to Creat a USER ID and other sort of stuff, to avoid 
uniqueness error, change user_id and API will create a new user and return success in console.

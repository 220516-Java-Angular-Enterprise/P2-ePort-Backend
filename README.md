# P2-ePort-Backend

### Admin User Story: Adding a new product
Admin logs in to ePort clicks on the add new product button, then types in the scp number of the creature they want, and will get back the creature if found or 404 message if not found, if found the scp creature will be stored in the data base with most/all of its parameters.
---
## User HTTP Verbs
### Post
1. Create new user /ePort/users 
```java
return String userid
```
Input:
```jsonc
{
    "username": "auctionUser001", //Non-null, must be 8-20 characters
    "password": "P@ssw0rd", //Non-null, must be 8 or more characters and have a number and special character
    "codename": "codename", //Non-null
    "paymentID": "paymentID", //Non-null
    "shippingAddress": "shippingAddress", //Non-null
    "email": "User@gmail.com" //Non-null, must be a valid email name followed by @ followed by the email domain name
}
```
### Put
1. Activate user /ePort/users/activate
```java
return void
```
Input:
```jsonc
{
    "userID": "dc200531-8797-4abb-88f5-21fb23ca15d2", //Non-null, must be a valid userid
    "active": true //true to activate false to deactivate user. If not used will default to false
}
```
2.Edit details /ePort/users
```java
return void
```
Input:
```jsonc
{
    "userID": "1e6c7b1f-68ad-423f-a5e4-681e97862fd4",
    "password": "P@ssw0rd",
    "email": "e@mail.com",
    "codename": "updatename",
    "paymentID": "paymentUpdate",
    "shippingAddress": "shippingUpdate",
    "funds": 100
}
```
### Delete
1. Delete inactive user /ePort/users/delete
```java
return void
```
Input:
```jsonc
{
    "userID":"1" //Non-null, must be valid userid of user who's status is set to false
}
```
### Get
1. Get user by username /ePort/users/{username value here}
```java
return User user
```
2. Get all users /ePort/users (Admin only)
```java
return List<User> userList
```
---
## SCP HTTP Verbs
### Post
1. Create SCP /ePort/scp
```java
return String scpName
```
```jsonc
{
    "name": "scp-1006" //Non null, must be valid scp name
}
```
### Get
1. Get SCP by name /ePort/scp/{scp name value here}
```java
return SCP scp
```
---
## Auth
### Post
1. Get authentication token

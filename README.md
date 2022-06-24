# P2-ePort-Backend

### Admin User Story: Adding a new product
Admin logs in to ePort clicks on the add new product button, then types in the scp number of the creature they want, and will get back the creature if found or 404 message if not found, if found the scp creature will be stored in the data base with most/all of its parameters.

## User HTTP Verbs
---
### Post
1. Create new user /ePort/users returns String userid
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
1. Activate user /ePort/users/activate returns void
```java
returns void
```
```jsonc
{
"userID": "dc200531-8797-4abb-88f5-21fb23ca15d2", //Non-null, must be a valid userid
"active": true //true to activate false to deactivate user. If not used will default to false
}
```
2.

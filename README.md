# P2-ePort-Backend

### Admin User Story: Adding a new product
Admin logs in to ePort clicks on the add new product button, then types in the scp number of the creature they want, and will get back the creature if found or 404 message if not found, if found the scp creature will be stored in the data base with most/all of its parameters.

## User HTTP Verbs
---
### Post
1. Create new user /ePort/users
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


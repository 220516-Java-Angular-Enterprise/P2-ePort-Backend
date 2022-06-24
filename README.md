# P2-ePort-Backend

### Admin User Story: Adding a new product
Admin logs in to ePort clicks on the add new product button, then types in the scp number of the creature they want, and will get back the creature if found or 404 message if not found, if found the scp creature will be stored in the data base with most/all of its parameters.

## User HTTP Verbs
---
### Post
1. Create new user /ePort/users
```json
{
"username": "auctionUser001",
"password": "P@ssw0rd",
"codename": "codename",
"paymentID": "paymentID",
"shippingAddress": "shippingAddress",
"email": "User@gmail.com"
}
```


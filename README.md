# welcome to Booking clone by [ahmeddrawy](https://github.com/ahmeddrawy) and [MostafaOmar98](https://github.com/MostafaOmar98)
## Booking is a web application that simulates a booking website for hotels providing :
### Main functionalities
####client Functionalities
- User registration 
  - User must provide `Email` and `username`. 
  - User must check reCaptcha to avoid spam.
  - using Ajax to validate Email and username.
  - If `Email` and `Username` provided passes verification, an Email is sent to the Email provided with random generated password to Login with.
  - Admins only can register new Admins.
- login
  - User provide Email and password.
  - using Ajax to validate `Email` and `Password` provided passes verification.
  - if User is authenticated, application redirect them to their `Home` page.
- search for hotels
- book a hotel room
- Add Hotel review
- Edit his profile
### Admin Functionalities
- Register new Admins
- Add new Hotels to website
- Check clients' reviews for hotels
- Update Hotels' data


### Architecture
#### using 3 layers architecture
1.  Presentation Layer
    1. has all User interaction logic, handling the first layer of interaction and responses
    2. handling JSON responses and Html responses 
    3. using an exposed interface from **Business layer** to authenticate and  authorize users 
    4. catching exceptions from lower layers and use a factory class to act with user interactions providing certain messages in case of certain Exceptions 
2. Business Layer.
    * Has backend validation on input data
    * Interact with Model layer - Database - 
    * throws exceptions in case of failure.
    * has DTOs to provide for presentation layer to expose certain parts of our `Model`
    
3. Model Layer.
    * has DAO to interact with database
    * has Entity classes
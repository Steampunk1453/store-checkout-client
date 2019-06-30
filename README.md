# store-checkout-client

Back-End client checkout store with Java, Spring Boot, Spring Security, JWT and Swagger 

* For testing navigate to:

     http://localhost:8081/swagger-ui.html
    
* For test is better uses Postman because the endpoints are secure:

     To authenticate with JWT uses any username and password:
     
     http://localhost:8081/client/users {POST}
     
     Uses token:
     
     http://localhost:8081/client/baskets {POST}
     
     http://localhost:8081/client/baskets/amounts/1 {GET}
     
     http://localhost:8081/client/baskets/products {POST}
     
     http://localhost:8081/client/baskets/1 {DELETE}
     
 * TODO
    
    Saves username and password in database to authenticate the user
    
    Add Product Controller with endpoint for save new products codes in database
    
  * Notes
    
    The logic of the app is in the service project
    
    Client calls to service with RestTemplate
     
     


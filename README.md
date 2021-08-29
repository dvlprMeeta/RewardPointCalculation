Application Developed with Spring Boot Application

UnZip The Application 

execute **mvnw clean build**

Search The File Name **RetailerApplication** and Execute The Main Method.

Once Application Start On Port 8080

We Can see the swagger URL **http://localhost:8080/swagger-ui.html**

As, I have introduced Spring Security In the Application So need To Authorize Before execute any specific API

Can Find UserName and Password In **data.sql** File

There are two API EndPoint Has Configured 

`localhost:8080/rewards/v1/getRewardsDetailsByCustomer` -- Which Fetch Reward Transaction Based On Last 3 Months
`localhost:8080/rewards/v1/getRewardsDetailsByCustomerAndMonth` -- Which Fetch Reward Transaction Based On Each Month and their transactions

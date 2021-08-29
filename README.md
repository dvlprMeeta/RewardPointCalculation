Application Developed with Spring Boot Application

UnZip The Application 

execute **mvnw clean build**

Search The File Name **RetailerApplication** and Execute The Main Method.

Once Application Start On Port 8080

We Can see the swagger URL **http://localhost:8080/swagger-ui.html**

As, I have introduced Spring Security In the Application So need To Authorize Before execute any specific API

Can Find UserName and Password In **data.sql** File

There are two API EndPoint Has Configured 

`localhost:8080/rewards/v1/getRewardsDetailsByCustomer` -- Which Fetch Total Reward Points By Customer From Last 3 Month
 **Response**
 
 `{
 "responseStatus": "Total Rewards By Customer Name",
 "responseCode": 200,
 "object": [
 {
 "customerId": 100,
 "customerName": "Meeta",
 "lastMonthRewardPoints": 2550,
 "totalRewards": 3900,
 "thirdMonthRewardPoints": 500,
 "secondMonthRewardPoints": 850
 },
 {
 "customerId": 101,
 "customerName": "Infogain",
 "lastMonthRewardPoints": 1700,
 "totalRewards": 4850,
 "thirdMonthRewardPoints": 0,
 "secondMonthRewardPoints": 3150
 },
 {
 "customerId": 102,
 "customerName": "Technogen",
 "lastMonthRewardPoints": 2750,
 "totalRewards": 5700,
 "thirdMonthRewardPoints": 850,
 "secondMonthRewardPoints": 2100
 }
 ]
 }` 
  

`localhost:8080/rewards/v1/getRewardsDetailsByCustomerAndMonth` -- Which Fetch Reward Transactions Based On Each Month and their transactions

**Response**

`{
"responseStatus": "Total Rewards By Customer Name",
"responseCode": 200,
"object": [
{
"customerName": "Technogen",
"month": "August",
"noOfTransaction": 3,
"totalRewardPoint": 2750,
"totalAMount": 1600,
"rewardTransactionListList": [
{
"rewardPoint": 650,
"customerName": "Technogen",
"amount": 400,
"transactionDate": "2021-08-14T18:30:00.000+00:00"
},
{
"rewardPoint": 250,
"customerName": "Technogen",
"amount": 200,
"transactionDate": "2021-08-16T18:30:00.000+00:00"
},
{
"rewardPoint": 1850,
"customerName": "Technogen",
"amount": 1000,
"transactionDate": "2021-08-25T18:30:00.000+00:00"
}
]
},
{
"customerName": "Technogen",
"month": "June",
"noOfTransaction": 1,
"totalRewardPoint": 850,
"totalAMount": 500,
"rewardTransactionListList": [
{
"rewardPoint": 850,
"customerName": "Technogen",
"amount": 500,
"transactionDate": "2021-06-21T18:30:00.000+00:00"
}
]
},
{
"customerName": "Meeta",
"month": "June",
"noOfTransaction": 2,
"totalRewardPoint": 500,
"totalAMount": 400,
"rewardTransactionListList": [
{
"rewardPoint": 450,
"customerName": "Meeta",
"amount": 300,
"transactionDate": "2021-06-07T18:30:00.000+00:00"
},
{
"rewardPoint": 50,
"customerName": "Meeta",
"amount": 100,
"transactionDate": "2021-06-02T18:30:00.000+00:00"
}
]
},
{
"customerName": "Infogain",
"month": "July",
"noOfTransaction": 3,
"totalRewardPoint": 3150,
"totalAMount": 1800,
"rewardTransactionListList": [
{
"rewardPoint": 850,
"customerName": "Infogain",
"amount": 500,
"transactionDate": "2021-07-29T18:30:00.000+00:00"
},
{
"rewardPoint": 1250,
"customerName": "Infogain",
"amount": 700,
"transactionDate": "2021-07-07T18:30:00.000+00:00"
},
{
"rewardPoint": 1050,
"customerName": "Infogain",
"amount": 600,
"transactionDate": "2021-07-11T18:30:00.000+00:00"
}
]
},
{
"customerName": "Infogain",
"month": "August",
"noOfTransaction": 2,
"totalRewardPoint": 1700,
"totalAMount": 1000,
"rewardTransactionListList": [
{
"rewardPoint": 250,
"customerName": "Infogain",
"amount": 200,
"transactionDate": "2021-08-09T18:30:00.000+00:00"
},
{
"rewardPoint": 1450,
"customerName": "Infogain",
"amount": 800,
"transactionDate": "2021-08-17T18:30:00.000+00:00"
}
]
},
{
"customerName": "Meeta",
"month": "July",
"noOfTransaction": 1,
"totalRewardPoint": 850,
"totalAMount": 500,
"rewardTransactionListList": [
{
"rewardPoint": 850,
"customerName": "Meeta",
"amount": 500,
"transactionDate": "2021-07-10T18:30:00.000+00:00"
}
]
},
{
"customerName": "Meeta",
"month": "August",
"noOfTransaction": 3,
"totalRewardPoint": 2550,
"totalAMount": 1500,
"rewardTransactionListList": [
{
"rewardPoint": 250,
"customerName": "Meeta",
"amount": 200,
"transactionDate": "2021-08-09T18:30:00.000+00:00"
},
{
"rewardPoint": 1850,
"customerName": "Meeta",
"amount": 1000,
"transactionDate": "2021-08-08T18:30:00.000+00:00"
},
{
"rewardPoint": 450,
"customerName": "Meeta",
"amount": 300,
"transactionDate": "2021-07-31T18:30:00.000+00:00"
}
]
},
{
"customerName": "Technogen",
"month": "July",
"noOfTransaction": 2,
"totalRewardPoint": 2100,
"totalAMount": 1200,
"rewardTransactionListList": [
{
"rewardPoint": 450,
"customerName": "Technogen",
"amount": 300,
"transactionDate": "2021-07-20T18:30:00.000+00:00"
},
{
"rewardPoint": 1650,
"customerName": "Technogen",
"amount": 900,
"transactionDate": "2021-07-24T18:30:00.000+00:00"
}
]
},
{
"customerName": "Infogain",
"month": "June",
"noOfTransaction": 2,
"totalRewardPoint": 2100,
"totalAMount": 1200,
"rewardTransactionListList": [
{
"rewardPoint": 250,
"customerName": "Infogain",
"amount": 200,
"transactionDate": "2021-05-31T18:30:00.000+00:00"
},
{
"rewardPoint": 1850,
"customerName": "Infogain",
"amount": 1000,
"transactionDate": "2021-05-31T18:30:00.000+00:00"
}
]
}
]
}`

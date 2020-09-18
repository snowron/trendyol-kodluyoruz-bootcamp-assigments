
The case of project changed to camelCase.

Changed few error codes and their messages.

Also we decided to use in memory db for operations.

Added test for repostories.


---

FORMAT: 1A
HOST: https://subscriptions13.apiblueprint.org/

# Subscriptions

This a *__Subscriptions__* module of Trendyol Bootcamp API.

---
# Notes

- Each users should have a free subscription plan by default.
- Each subscription record keeps one subscription period details.
- Simply we decided to use a static 'plan_id' property which represents the subscription type.

    | plan_id  | Representation  |
    |---|---|
    | 0 | Free Plan |
    | 1  | Premium Plan  |
    | 2  | Family Plan  |

- **Q-1:** Should we need to create an endpoint for deleting subscription?
- **Q-2:** Should we need to retrieve the subscripton by user id?

---
# Subscription

    Represents one period of subscription.
---

- id `(Number)` : Unique identifier.
- user_id `(Number)` : User's unique identifier.
- plan_id `(Number)` : Plan's id identifier.
- price `(Float)` : Billed price of the subscription.
- is_paid `(Boolean)` : It represents whether the payment has been made or not.
- start_date `(Date)` : The start date of subscription.
- end_date `(Date)` : The end date of subscription.

---

## Subscriptions [/subscriptions?{id}]

### List All Subscriptions [GET]

+ Response 200 (application/json)

        [
            {
                            "id": "1",
                            "userId": "123",
                            "planId": 0,
                            "price": 10,
                            "startDate": "2020-08-05T00:00:00.000Z",
                            "endDate": "2020-08-05T00:00:00.000Z",
                            "paid": false
            },
            {
                            "id": "2",
                            "userId": "1234abc",
                            "planId": 1,
                            "price": 10,
                            "startDate": "2020-08-05T00:00:00.000Z",
                            "endDate": "2020-08-05T00:00:00.000Z",
                            "paid": true
            }
        ]
        
+ Response 500 (application/json)

        {
                          "message": "Internal Server Error!",
                          "timeStamp": "2020-09-15T21:44:02.357+00:00"
        }
    
### Create a New Subscription [POST]

+ Request (application/json)

        {
          "userId": "string",
          "planId": 0
        }

+ Response 201 (application/json)

    + Headers

            Location: /subscriptions/1

    + Body

            {
                "id": "1",
                "userId": "123",
                "planId": 0,
                "price": 10,
                "startDate": "2020-08-05T00:00:00.000Z",
                "endDate": "2020-08-05T00:00:00.000Z",
                "paid": false
            }
            
+ Response 500 (application/json)

        {
                  "message": "Internal Server Error!",
                  "timeStamp": "2020-09-15T21:44:02.357+00:00"
        }

### Change Subscription Plan [PATCH]

+ Request (application/json)

        {
            "id": 1,
            "new_plan_id": 2,
        }

+ Response 204 (application/json)

            
+ Response 500 (application/json)

        {
                  "message": "Internal Server Error!",
                  "timeStamp": "2020-09-15T21:44:02.357+00:00"
        }

## Subscriptions/id [/subscriptions/{id}]

+ Parameters
    + id (number) - ID of the Subscription in the form of an integer    

### Get Subscription by ID [GET]

+ Response 200 (application/json)

        {
            "id": "1",
            "userId": "123",
            "planId": 0,
            "price": 10,
            "startDate": "2020-08-05T00:00:00.000Z",
            "endDate": "2020-08-05T00:00:00.000Z",
            "paid": false
        }
        
+ Response 404 (application/json)

        {
                  "message": "Resource not found",
                  "timeStamp": "2020-09-15T21:44:02.357+00:00"
        }

### Delete Subscription [DELETE]

+ Response 204

+ Response 404 (application/json)

        {
                  "message": "Resource not found",
                  "timeStamp": "2020-09-15T21:44:02.357+00:00"
        }

+ Response 500 (application/json)

        {
                  "message": "Internal Server Error!",
                  "timeStamp": "2020-09-15T21:44:02.357+00:00"
        }


## Subscriptions/id/payment [/subscriptions/{id}/payment]

+ Parameters
    + id (number) - ID of the Subscription in the form of an integer

### Pay Subscription Price [PATCH]
    
+ Request (application/json)

        {
          "cardOwnerName": "string",
          "cardNumber": "string",
          "validThruMonth": "string",
          "validThruYear": "string",
          "cvc": "string"
        }

+ Response 204

+ Response 500 (application/json)

        {
          "message": "Internal Server Error!",
          "timeStamp": "2020-09-15T21:44:02.357+00:00"
        }
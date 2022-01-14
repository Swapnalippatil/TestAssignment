Feature: Register a New User, Search product, Verify cart products

#Scenario: Register a New User, Search product, Verify cart products
#Given User on the Takealot home page
#When User clicks on Register link
#And User enters details
#|FirstName|LastName|Email|ReTypeEmail|Password|ReTypePassword|MobileNumber|Newsletters|
#|TestName|TestLastName|myTest905@gmail.com|myTest905@gmail.com|[B@3ac3fd8b|[B@3ac3fd8b|0722973410|books|
#And User clicks on register button
#Then Verify success message on the page
#And Click on the here link to continue shopping
#And Verify registered user name on the home page
#When User on the home page and search for "watches"
#And User adds the product to cart 
#Then validate cart for added products 

Scenario: Register a New User with already registerd email address
Given User on the Takealot home page
When User clicks on Register link
And User enters details
|FirstName|LastName|Email|ReTypeEmail|Password|ReTypePassword|MobileNumber|Newsletters|
|TestName|TestLastName|myTest902@gmail.com|myTest902@gmail.com|[B@3ac3fd8b|[B@3ac3fd8b|072297341090|books|
And User clicks on register button
Then Verify error message on the page






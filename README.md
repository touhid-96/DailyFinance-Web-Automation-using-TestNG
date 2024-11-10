# DailyFinance Web Automation using TestNG

## Scenario
- visit the site https://dailyfinance.roadtocareer.net/ . Register a new user on the website using your Gmail account. Check your Gmail inbox for a confirmation email with a "Congratulations" message. Assert that the email has been received.
- Register a user using your own gmail. e.g. mahbub+1@gmail.com where you can add any digit for unique user registration. After reg, assert that Registration is successful and registration congratulations email received. Must save the user into a json array.
- Login by the last registered user and add 5 items from a csv file. Assert that the total amount from csv matches with the amount shown in the user item list dashboard.
- Upload a png image (less than 1 mb) and update the user by any phone number.
- Login by admin (admin@test.com;pass: admin123) and assert that your created user is shown in the user list
-Click on the user details and check if the uploaded image is showing on the user profile.

## Technology and Tool Used
- Selenium Webdriver
- TestNG
- Gradle
- Java
- JavaFaker
- Simple JSON
- Allure Report
- Intellij idea

## Prerequisite
- JDK 17
- java IDE
- Configure environment variable for Java, Gradle and Allure Report

## Website
- `https://dailyfinance.roadtocareer.net/`

## How to run this project
- Clone the project
- Execute the following command on the project directory  
`gradle clean test`

## Generate Allure Report
- Execute the following commands (after finishing the project run)  
`allure generate allure-results`  
`allure serve allure-results`

## Video of Automation Output
### Regression Test
https://github.com/user-attachments/assets/561077bd-acfb-4b5e-850a-a24408a2cdfe

## Allure Reports
![1](https://github.com/user-attachments/assets/19bdfd01-8cbb-4558-a40a-e654b329e190)
![2 suites](https://github.com/user-attachments/assets/97c5c115-b155-4c2b-a4dd-03db9c94c6a2)
![3 graphs](https://github.com/user-attachments/assets/69f687f0-efda-4566-8008-20841d125905)
![4 behaviors](https://github.com/user-attachments/assets/f3d47f85-67c6-460e-ae6d-936bee598368)
![5 packages](https://github.com/user-attachments/assets/726af873-9a25-49cb-af6d-883f13df5008)

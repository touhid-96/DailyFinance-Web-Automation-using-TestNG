# DailyFinance Web Automation using TestNG

## Scenario
- visit the site https://dailyfinance.roadtocareer.net/ . Register a new user on the website using your Gmail account. Check your Gmail inbox for a confirmation email with a "Congratulations" message. Assert that the email has been received.
- Go to the login page on the website and click "Reset it here" You will receive a password reset link via email. Open the link and set a new password
- Log in using the new password to confirm that the login is successful
- Add two random items to the list and verify that both items appear on the item list.
- Go to the user profile and update the email address to a new Gmail account.
- Log out and then log in using the updated email. Confirm that login is successful with the new email and fails with the previous email.
- Log out again and log in using the admin account. Verify that the newly registered user appears on the admin dashboard’s user list.

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
- JDK 21
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
`allure generate allure-results --clean`  
`allure serve allure-results --clean`

## Video of Automation Output
### Regression Test
https://github.com/user-attachments/assets/da680be8-2a0c-4770-9c10-cb17ba4de676



## Allure Reports
![1 overview](https://github.com/touhid-96/OrangeHRM-Automation-Selenium-TestNG-Project/assets/29010371/0a501781-a0f5-486d-ac8a-56d9b374ec22)

![2 suites](https://github.com/touhid-96/OrangeHRM-Automation-Selenium-TestNG-Project/assets/29010371/66d69fe9-7748-4f7d-b98c-b4c317304c7a)

![3 graph](https://github.com/touhid-96/OrangeHRM-Automation-Selenium-TestNG-Project/assets/29010371/7dd2cb5d-2329-4ef9-a7e6-1f655684708f)

![4 behaviors](https://github.com/touhid-96/OrangeHRM-Automation-Selenium-TestNG-Project/assets/29010371/97b9bd60-48f9-4e1a-bb1a-7c7ded552b1e)

![5 packages](https://github.com/touhid-96/OrangeHRM-Automation-Selenium-TestNG-Project/assets/29010371/ed51faee-139a-476a-b22a-3c4e62a17307)


## Gradle Report
![6 gradle report](https://github.com/touhid-96/OrangeHRM-Automation-Selenium-TestNG-Project/assets/29010371/48d61c6e-b7fa-4176-83cd-a54ee706bced)

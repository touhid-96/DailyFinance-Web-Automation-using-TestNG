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
![overview](https://github.com/user-attachments/assets/b3d01e31-b21b-4af1-a67e-cc722873ce14)

![2 suites](https://github.com/user-attachments/assets/e5ca48eb-894e-470f-9f24-1259ca291688)

![3 graphs](https://github.com/user-attachments/assets/1b583722-0d0c-4880-a631-581ce2253e78)

![4 package](https://github.com/user-attachments/assets/95f08c44-ba40-45f2-9ac6-be5ab500eb70)

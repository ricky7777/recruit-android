
# Android
Create a simple application that will fetch a list of transactions from an api endpoint and display them in a list.  When a user selects a transaction they will be shown a second screen that will display a more detailed view of the transaction data. 

We have given you some code over here to start with. Please clone/fork this repository. 

## Specification
- Consume the following API: https://gist.githubusercontent.com/Josh-Ng/500f2716604dc1e8e2a3c6d31ad01830/raw/4d73acaa7caa1167676445c922835554c5572e82/test-data.json
  ```diff
  + Done
  ```
- Display data in a sorted list
  ```diff
  + Done
  ```
- Open a detailed view when user selects a list item
  ```diff
  + Done
  ```

## Commits
Please commit frequently to communicate your thoughts while working on this assignment.

    ○ I’ve used Hilt and Koin as dependency injection tools before, but I’m not very familiar with Dagger2, 
      so I chose to use Koin for this project.
    ○ The main logic is handled through the TransactionsViewModel.
    ○ On the UI layer, I used Jetpack Compose along with StateFlow to observe and update the UI in real time.
    
## Demo
<img width="200" src="https://github.com/user-attachments/assets/39203c54-5512-4b68-9efa-0ac95f1fd456" />

## Unit test
<img width="810" alt="asb_test" src="https://github.com/user-attachments/assets/b4ff1ad9-d6a6-42a5-b127-c312195af9ae" />

## Use Skill
1. Jetpack Compose<br/>
2. MVVM<br/>
3. Room<br/>
5. Coroutine<br/>
6. DI - Koin<br/>
7. Unit test(JUnit/Mockito)
   
## What is valued
- Tests
  ```diff
  + Done
  ```
- Best practice design patterns
  ```diff
  + Done
  ```
- Clean Code
  ```diff
  + Done
  ```

## Nice to have (If you have time)
- Calculate how much GST was paid on each transaction.  GST is 15%.  It is up to you to choose the most appropriate place to display GST
  ```diff
  + Done
  ```
- Color code transaction amounts (credit green, debit red)
  ```diff
  + Done
  ```
- Narration/talkback support
  ```diff
  + I had the opportunity to work on TalkBack support at a bank. However, due to a very tight schedule, I wasn’t able to complete the implementation at that time.
  ```

## Duration 
Try not to spend more than 4 hours on this. You are not necessarily expected to do everything in this assignment because of the short duration. 

## Tech
- Please use Native Android frameworks only (no Flutter sorry) 
- Aside from the above, use whatever libraries you are comfortable with
- If you are unfamiliar with something in the exercise template, leave a note and change it to better suit you.

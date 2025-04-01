
# Simple Bank Manager

## About
A banking app is a widespread kind of app that helps you manage your bank account and pay bills. In this project, you'll make a simplified version of such an application.

# Stage 1/5


## Create LogIn screen
### Introduction and basic setup

The bank app is a widespread kind of app that most of us utilize to manage our bank accounts and pay bills. In this project, we will make a simplified version of that application.

This project will have multiple views, and we will use a single activity pattern, where an activity will act essentially as a fragment holder; the fragments will be responsible for creating screens for users to interact with.

To help us with that, we will use the androidx.navigation libraries that allow us to manage views through a navigation graph.

To use the androidx.navigation libraries, include them to the build.gradle with

```bash
  def navigationVersion = '2.4.2'  // or another version number
    implementation "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    implementation "androidx.navigation:navigation-ui-ktx:$navigationVersion"
```
We have already included them, so there's no need to change build.gradle.

Setting up a navigation graph is pretty simple with Android Studio. Right-click over the res folder and choose New → Android Resource File. Then name the file (the conventional name is nav_graph) and set Resource Type to Navigation:

![Process](https://ucarecdn.com/51539fa5-042e-4d40-ae1d-63da1ac67a4e/)

Adding fragments is also easy with a navigation graph. Go to the design tab in the nav_graph.xml file, click on the New Destination button, and then click on Create New Destination. You'll see the wizard.

![Process](https://ucarecdn.com/0f6cae94-846e-4cdc-836f-4c7ec7fa192c/)

### Objectives

1. Setup activity_main.xml to hold NavHostFragment. Use something like this:
```bash
  <androidx.fragment.app.FragmentContainerView
    android:id="@+id/nav_host_fragment"
    android:name="androidx.navigation.fragment.NavHostFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:defaultNavHost="true"
    app:navGraph="@navigation/nav_graph" />
        
```
2. Add a fragment named LoginFragment to the navigation graph and set it as home;
3. Add EditText to LoginFragment with the ID of loginUsername, inputType textPersonName, and hint username
4. Add an EditText to LoginFragment with the ID of loginPassword, inputType numberPassword, and hint password
5. Add Button to LoginFragment with the ID of loginButton and text log in
6. Tests may pass a username to MainActivity through the extra Bundle in the Intent, which can be retrieved with intent.extras?.getString("username"). Retrieve that username using the username key, and use it to validate the login. In case no username is passed, use "Lara" as the default value for the username;
7. Tests may pass a password to MainActivity through the same extras Bundle. Retrieve that password, using the password key and use it to validate login. In case no password is passed use "1234" as the default value for the password;
8. Add a listener to loginButton; when clicked, get user input from loginUsername and loginPassword and compare with values passed through intent extras; The validation is successful if user input matches extras input
9. If the validation of credentials is successful, show a toast with the message logged in
10. If the validation of credentials is not successful, show a toast with the message invalid credentials

### Example

![Process](https://ucarecdn.com/713bf92d-3df8-44a2-8765-5e4d5bc9f1fd/)

# Stage 2/5

## Create LogIn screen

### Description
Previously, we handled the login. In this stage, we want to redirect users to the menu screen after a successful login where they can reach the main features offered by our app.

The navigation graph will help us to implement the navigation between the screen of our app.

When navigating from one fragment to another using the navigate method, you can pass data between the fragments by providing a Bundle as the second argument. In the Bundle, you can put any data you want to pass to the destination fragment, such as a string, integer, or even a custom object. To retrieve the data in the destination fragment, you can use the arguments property, which returns the Bundle that was passed during navigation. You can then use the getString(), getInt(), or other methods of the Bundle class to extract the data from the Bundle.

### Objectives

1. Add a fragment named UserMenuFragment to the navigation graph;
2. Add a TextView to UserMenuFragment with the id of userMenuWelcomeTextView, the text should be "Welcome $username" in which $username is the same username used for login;
3. Add a Button to UserMenuFragment with the id of userMenuViewBalanceButton and text view balance;
4. Add a Button to UserMenuFragment with the id of userMenuTransferFundsButton and text transfer funds;
5. Add a Button to UserMenuFragment with the id of userMenuExchangeCalculatorButton and text calculate exchange;
6. Add a Button to UserMenuFragment with the id of userMenuPayBillsButton and text pay bills;
7. On the design tab of nav_graph, make a connection from LoginFragment to UserMenuFragment. The code generated after making the connection should contain an action with the id of action_loginFragment_to_userMenuFragment and destination userMenuFragment
8. After a successful login, users should be redirected to UserMenuFragment. To make the navigation, you can use findNavController().navigate(R.id.action_loginFragment_to_userMenuFragment). It is also possible to pass a Bundle as the second argument of the navigate method to pass arguments to userMenuFragment, which you can later retrieve with arguments?.getString(...)
9. All requirements from the Stage1 are still valid, including toast messages

### Example

![Process](https://ucarecdn.com/d8ca7a43-3ef1-45de-974d-6c2283efc97e/)

# Stage 3/5

## View balance and make transfers

### Description
Let's implement some of the main features of the Bank application. In this stage, you'll implement viewing account balances and making transfers. After a successful login, users should be able to go to the Transfer funds screen, transfer money to the account, and check the current balance at the View Balance screen.

### Objectives

1. Tests may pass the user balance as a Double to MainActivity through intent.extras. Retrieve that balance, using the balance key. In case no balance is passed, use 100.0 as the default value for balance;
2. Add a fragment named ViewBalanceFragment to the navigation graph:
 - Add a TextView to ViewBalanceFragment with the id of viewBalanceLabelTextView, the text should be Balance:
 - Add a TextView to ViewBalanceFragment with the id of viewBalanceAmountTextView. This view will be used to show the account balance;
 - On the design tab of nav_graph, make a connection from UserMenuFragment to ViewBalanceFragment. The generated code should contain the action with the ID of action_userMenuFragment_to_viewBalanceFragment and the destination viewBalanceFragment;
 - After clicking on userMenuViewBalanceButton, display ViewBalanceFragment. On the viewBalanceAmountTextView, display the current text with the current balance state.   Display the current balance with two decimal places and a $ at the start: $100.00;
- When a user is at ViewBalanceFragment, a click on the back button should redirect users to UserMenuFragment;
3. Add a Fragment named TransferFundsFragment to the navigation graph:
 - On nav_graph, make a connection from UserMenuFragment to TransferFundsFragment. The generated code should have the action with the id of action_userMenuFragment_to_transferFundsFragment and the destination ID of transferFundsFragment
 - Add an EditText to TransferFundsFragment with the ID of transferFundsAccountEditText, a hint Account number and input type of text. This view will be used to input the account to transfer funds;
 - Add an EditText to TransferFundsFragment with the ID of transferFundsAmountEditText, a hint Enter amount and input type of numberDecimal. This view will be used to input the funds to transfer;
 - Add a Button to TransferFundsFragment with the ID of transferFundsButton. If clicked, first validate the input:
   - Consider accounts valid only if they start with either sa or ca followed by four digits. sa stands for a savings account, and ca is for a checking account;
   - If the account input is not valid, set the error property of transferFundsAccountEditText to "Invalid account number";
   - A valid amount is a number greater than zero;
   - If the amount is not valid, set the error property of transferFundsAmountEditText to "Invalid amount";
   - If both inputs are not valid, set the error property for both EditTexts.
   If both inputs are valid, try to carry out the transfer:
   - If the amount on transferFundsAmountEditText is greater than the current balance, show the toast message Not enough funds to transfer <amount>$ where amount is the amount on transferFundsAmountEditText with two decimals. Keep the current balance state and remain on TransferFundsFragment;
   - If the amount on transferFundsAmountEditText is less or equal to the current balance, show the toast message Transferred <amount>$ to account <account> where amount is the amount on transferFundsAmountEditText with two decimals, and account is the account on transferFundsAccountEditText. Debt the current balance with the given amount and automatically go back to UserMenuFragment;
   - To simplify, there is no need to credit any account. An actual application would have a backend managing account state and the app would just make network requests with transfer data without the need to manage the state itself, so this simplification makes sense

### Example

![Process](https://ucarecdn.com/f3dbeff7-e323-446c-b7c8-303aab876236/)

![Process](https://ucarecdn.com/e09b0ed1-2b3c-48c7-9279-eb9e0ee0f7fb/)

![Process](https://ucarecdn.com/5c67c6e3-e232-488e-a34e-8888ef5d9831/)

# Stage 4/5

## Convert currencies

### Description
In this stage, we will implement a feature to calculate the exchange rate. When a user successfully logins, they should be able to go to the Calculate Exchange screen, where they can select the currencies, enter an amount to convert, and start the conversion by clicking a button. The converted amount will be displayed in a text view.

### Objectives

1. Add a Fragment named CalculateExchangeFragment to the navigation graph:
 - Add a TextView to CalculateExchangeFragment with the ID of calculateExchangeLabelFromTextView, the text should be convert from;
 - Add a TextView to CalculateExchangeFragment with the ID of calculateExchangeLabelToTextView, the text should be convert to;
 - Add a Spinner to CalculateExchangeFragment with the ID of calculateExchangeFromSpinner, the spinner should contain this text in the dropdown: EUR, GBP, USD;
 - Add a Spinner to CalculateExchangeFragment with the ID of calculateExchangeToSpinner, the spinner should contain this text in the dropdown: EUR, GBP, USD;
 - Add a TextView to CalculateExchangeFragment with the ID of calculateExchangeDisplayTextView, this textView will be used to show the calculated amount to two decimal points;
 - Add an EditText to CalculateExchangeFragment with the ID of calculateExchangeAmountEditText. It should accept the numberDecimalinput type and have the following hint: Enter amount;
 - Add a Button to CalculateExchangeFragment with the ID of calculateExchangeButton and with the text calculate;
2. On the design tab of nav_graph, make a connection from UserMenuFragment to CalculateExchangeFragment. The generated code after making the connection should contain the action with the ID action_userMenuFragment_to_calculateExchangeFragment and the destination calculateExchangeFragment;
3. After clicking on userMenuExchangeCalculatorButton, display CalculateExchangeFragment. Users should be able to choose the currency using calculateExchangeFromSpinner and calculateExchangeToSpinner. Input the amount to convert at calculateExchangeAmountEditText, and confirm conversion with the button calculateExchangeButton;
4. Tests may pass the exchangeMap as Serializable to MainActivity through intent.extras. Retrieve that exchangeMap, using intent.extras?.getSerializable and the key "exchangeMap". Cast it back to a map to be able to use it as a map. The type signature of exchangeMap is Map<String, Map<String, Double>>. In case no exchangeMap is passed, use the following as the default value:

```bash
  val defaultMap = mapOf(
    "EUR" to mapOf(
        "GBP" to 0.5,
        "USD" to 2.0
    ),
    "GBP" to mapOf(
        "EUR" to 2.0,
        "USD" to 4.0
    ),
    "USD" to mapOf(
        "EUR" to 0.5,
        "GBP" to 0.25
    )
)
```
The outer key of exchangeMap is the source currency from which we are converting and the inner key is the target currency to convert to;
5. If users select the same currency on both calculateExchangeFromSpinner and calculateExchangeToSpinner, show the toast message Cannot convert to same currency and replace currency at calculateExchangeToSpinner with the next available currency;
6. If no value is passed to calculateExchangeAmountEditText and the button calculateExchangeButton is clicked, show the toast message Enter amount;
7. If users click on the back button at CalculateExchangeFragment, redirect users to UserMenuFragment;
8. Amount should be formatted with 2 decimal places and a currency symbol of selected currency before amount, for USD ->, EUR−>€ and for GBP−> £. Example:
10.50 = €5.25;

### Example

![Process](https://ucarecdn.com/87ac4b02-60b6-4c76-ae10-5933c5a5a214/)

![Process](https://ucarecdn.com/12e96184-79fd-4633-b120-f883e1d2f1ca/)

![Process](https://ucarecdn.com/ab6aca69-1566-42e4-94de-7a7b799c2cb5/)

# Stage 5/5

## Pay bills

### Description
In the last stage, let's implement the feature to pay bills using our bank manager application.

After clicking on the Pay Bills button in the User Menu, redirect users to the Pay Bills screen. There, users can input a billing code and correct it, if necessary, in a dialog. Users should have the option to confirm bill payments. Don't forget to add the validation to see if the user has enough money. If the validation is successful, subtract the money from the user's account.

### Objectives

1. Add a fragment named PayBillsFragment to the navigation graph:
 - Add an EditText to PayBillsFragment with the ID of payBillsCodeInputEditText, the hint should be Enter code and inputType is text;
 - Add a Button to PayBillsFragment with the ID of payBillsShowBillInfoButton with the text Show bill info;
2. On the design tab of nav_graph, make a connection from UserMenuFragment to PayBillsFragment. The generated code should contain the action with the ID of action_userMenuFragment_to_payBillsFragment and the destination payBillsFragment;
3. Display PayBillsFragment after a click on the userMenuPayBillsButton button;
4. On PayBillsFragment users should be able to input a billing code on payBillsCodeInputEditText
5. After click on payBillsShowBillInfoButton, a dialog should appear:
 - If the billing code is not found in billInfoMap, show an AlertDialog with the title Error and the message Wrong code with a positive button with the text Ok;
 - If the billing code is found in billInfoMap, show an AlertDialog with the title Bill info, a positive button with the text Confirm, a negative button with the text Cancel, and a message with the following scheme (including newlines):

 ```bash
Name: $billName
BillCode: $billCode
Amount: $billAmount$

```
 - where $billName, $billCode, and $billAmount are placeholders; $billAmount comes always  with two decimals. If users click on the Confirm button, and there is enough balance in   the account, then subtract the billing amount from this account balance and show the toast message appears with text Payment for bill $billName, was successful($billName is a variable name interpolated in this String message). If a user clicks on the Confirm button, and there is not enough balance, close the current dialog and display a new AlertDialog with the title Error, the message Not enough funds and a positive button with the text Ok, which closes the dialog.
6. Tests may pass billInfoMap as Serializable to MainActivity through intent.extras. Retrieve that billInfoMap, using the key "billInfo". You will want to cast it back to a map to use it as a map. The type signature of billInfoMap is Map<String, Triple<String, String, Double>>. In case of no billInfoMap, use the following as the default value:
 ```bash
val defaultBillInfoMap = mapOf(
    "ELEC" to Triple("Electricity", "ELEC", 45.0),
    "GAS" to Triple("Gas", "GAS", 20.0),
    "WTR" to Triple("Water", "WTR", 25.5)
)
```
7. If users are on PayBillsFragment, a click on the back button should redirect users to UserMenuFragment.

### Example

![Process](https://ucarecdn.com/cce61266-af0e-455d-a585-a6de66219d4a/)

![Process](https://ucarecdn.com/2d5a37f2-82a5-4382-8874-7da2b4209d58/)

![Process](https://ucarecdn.com/1c1ef556-035f-45a2-b1b4-d6fb62b5a822/)

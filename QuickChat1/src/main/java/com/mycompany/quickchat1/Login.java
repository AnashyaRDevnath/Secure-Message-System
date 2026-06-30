/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat1;

import java.util.regex.Pattern;

/**
 *
 * @author Anash
 */
public class Login {
    // User attributes
    private String name;
    private String surname;
    private String username;
    private String password;
    private String cellphoneNumber;

    // Constructor to initialize a Login object
    public Login(String name, String surname, String username, String password, String cellphoneNumber) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.cellphoneNumber = cellphoneNumber;
    }

    // Validates that the username contains an underscore and is 5 characters or less
    public boolean checkUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    //Title: Regex in java using password
    //Author: ChatGpt, 2025
    //Code version: GPT-4
    //Svailablility: https://chatgpt.com/c/67fd7324-9c24-8010-99e7-87a2358dbd9e 
    //Date: 15 April 2025 

// Validates that the password meets complexity rules using regex
    public boolean checkPassword(String password) {
        String pattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$";
        return Pattern.matches(pattern, password);
    }
    //-------------End of code attribution------------

    //Title: SA cell number regex
    //Author: ChatGpt, 2025 
    // Code version: GPT-4
    //Availability: https://chatgpt.com/c/67fe6ec8-2cb0-8010-b88e-c1653db67fa8 
    //Date: 15 April 2025 
// Validates South African phone number using regex
    public boolean checkCellphoneNumber(String cellphoneNumber) {
        // Valid SA number: starts with 0 or +27, followed by valid digit (6-8), then 8 digits
        String pattern = "^(\\+?27|0)(\\d{9})$";
        return Pattern.matches(pattern, cellphoneNumber);
    }

// Construct messages for validations
    public String registerUser() {
        boolean validUsername = checkUsername(this.username);
        boolean validPassword = checkPassword(this.password);
        boolean validPhone = checkCellphoneNumber(this.cellphoneNumber);

        if (validUsername && validPassword && validPhone) {
            return "User has been registered successfully";
        }

        //Title: Java If...Else
       //Author: W3Schools
        //Code version: (no code version)/ (N/A)
        //Availability: https://www.w3schools.com/java/java_conditions.asp 
        //Date: 16 April 2025
        StringBuilder message = new StringBuilder();

        if (!validUsername) {
            message.append("Username is incorrectly formatted, please ensure that the username contains an underscore is no more than five characters long");
        } else {
            message.append("Username successfully captured!\n");
        }

        if (!validPassword) {
            message.append("Password is not correctly formatted, please ensure that the password contains at least eight characters, contains a capital letter, a number and a special character.\n");
        } else {
            message.append("Password successfully captured!\n");
        }

        if (!validPhone) {
            message.append("Cellphone number is incorrectly formatted or does not contain international code.\n");
        } else {
            message.append("Cellphone number successfully added!\n");
        }
//--------------------End of code attribution-----------

//Title: How to remove trailing newline
//Author: ChatGpt, 2025 
// Code version: GPT-4
//Availability: https://chatgpt.com/c/680797da-4614-8010-85b2-a9ce6f6bd815 
        return message.toString().trim();
    }
//------------------End of code attribution---------------

    // 2. LOGIN USER - Checks if login credentials match the stored ones 
    public boolean loginUser(String usernameInput, String passwordInput) {
        return this.username.equals(usernameInput) && this.password.equals(passwordInput);
    }

    // 3. RETURN LOGIN STATUS - Gives a friendly message based on login result
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + name + " " + surname + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }

    }

}

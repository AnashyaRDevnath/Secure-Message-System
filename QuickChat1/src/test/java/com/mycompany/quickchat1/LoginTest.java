/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.quickchat1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Anash
 */
public class LoginTest {
    
    public LoginTest() {
    }

    // Bechtold.S ; Brannen.S; Link. J; Merdes.M; Phillip. M; Rancourt. J.D; Stein. C , n.d. 
    //JUnit5 User Guide version 5.12.2
    //https://junit.org/junit5/docs/current/user-guide/#overview 
    //19 April 2025 
    @Test
    public void testIsUsernameValid() {
        // Sample user data with valid username
        Login user = new Login("Kyle", "Waters", "kyl_1", "Password8%", "0825667894");

        // Register the user
        String registrationMessage = user.registerUser();

        // Ensure registration is successful
        assertEquals("User has been registered successfully", registrationMessage);

        // Simulate login
        boolean loginSuccess = user.loginUser("kyl_1", "Password8%");
        String expectedLoginMessage = "Welcome Kyle Waters, it is great to see you again.";
        String actualLoginMessage = user.returnLoginStatus(loginSuccess);

        // Assert the login message
        assertEquals(expectedLoginMessage, actualLoginMessage);
    }

    // Test for incorrectly formatted username
    @Test
    public void testIncorrectUsernameFormat() {
        // Sample user data with invalid username
        Login user = new Login("Kyle", "Waters", "kyle!!!!!!!!", "Password8%", "0825667894");

        // Register the user and expect the failure message
        String expectedMessage = "Username is incorrectly formatted";
        String actualMessage = user.registerUser();

        // Since we are only testing the username issue, we expect part of the full message
        // We can check that the message starts with the expected line
        assertEquals(true, actualMessage.contains(expectedMessage));
    }

    @Test
    public void testIsPasswordValid() {
        Login user = new Login("Anashya", "Devnath", "Ana_6", "Ch&&sec@ke99!", "+27831234567");
        boolean isValid = user.checkPassword("Ch&&sec@ke99!");
        String expected = "Password successfully captured.";
        String actual = isValid ? "Password successfully captured."
                : "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number and a special character.";
        assertEquals(expected, actual);
    }

    // Test for invalid password
    @Test
    public void testInvalidPassword() {
        Login user = new Login("Anashya", "Devnath", "Ana_6", "password", "+27831234567");
        boolean isValid = user.checkPassword("password");
        String expected = "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number and a special character.";
        String actual = isValid ? "Password successfully captured."
                : "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number and a special character.";
        assertEquals(expected, actual);
    }

    @Test
    public void testIsCellphoneValid() {
        Login user = new Login("Vinoshen", "Chetty", "Vi_5", "Password1!", "+27842052006");
        boolean isValid = user.checkCellphoneNumber("+27842052006");
        String expected = "Cell number successfully captured.";
        String actual = isValid ? "Cell number successfully captured."
                : "Cell number is incorrectly formatted or does not contain an international code, please try again.";
        assertEquals(expected, actual);
    }

    // Test for invalid cellphone number
    @Test
    public void testInvalidCellphone() {
        Login user = new Login("Vinoshen", "Chetty", "Vi_5", "Password1!", "08966553");
        boolean isValid = user.checkCellphoneNumber("08966553");
        String expected = "Cell number is incorrectly formatted or does not contain an international code, please try again.";
        String actual = isValid ? "Cell number successfully captured."
                : "Cell number is incorrectly formatted or does not contain an international code, please try again.";
        assertEquals(expected, actual);
    }

    // Test for successful login
    @Test
    public void testLoginSuccessful() {
        Login user = new Login("Rhea", "Chetty", "Rc_9", "Password1@", "+27842565868");
        assertTrue(user.loginUser("Rc_9", "Password1@"));
    }

    // Test for failed login (wrong credentials)
    @Test
    public void testLoginFailed() {
        Login user = new Login("Test", "User", "t_u1", "Test1234!", "+27831234567");
        assertFalse(user.loginUser("wrongUser", "wrongPass"));
    }

    // USERNAME VALIDATION
    // Test valid username
    @Test
    public void testUsernameValid() {
        Login user = new Login("Kyle", "Waters", "ky_1", "Password1!", "0821234567");
        assertTrue(user.checkUsername("ky_1"));
    }

    // Test invalid username
    @Test
    public void testUsernameInvalid() {
        Login user = new Login("Kyle", "Waters", "kyle!!!!!!", "Password1!", "0821234567");
        assertFalse(user.checkUsername("kyle!!!!!!"));
    }

    // PASSWORD COMPLEXITY
    // Test valid password
    @Test
    public void testPasswordValid() {
        Login user = new Login("Test", "User", "test_", "Ch&&sec@ke99!", "0821234567");
        assertTrue(user.checkPassword("Ch&&sec@ke99!"));
    }

    // Test invalid password
    @Test
    public void testPasswordInvalid() {
        Login user = new Login("Test", "User", "test_", "password", "0821234567");
        assertFalse(user.checkPassword("password"));
    }

    // CELLPHONE VALIDATION
    // Test valid cellphone
    @Test
    public void testCellphoneValid() {
        Login user = new Login("Test", "User", "test_", "Password1!", "+27838968976");
        assertTrue(user.checkCellphoneNumber("+27838968976"));
    }

    // Test invalid cellphone
    @Test
    public void testCellphoneInvalid() {
        Login user = new Login("Test", "User", "test_", "Password1!", "08966553");
        assertFalse(user.checkCellphoneNumber("08966553"));
    }
}

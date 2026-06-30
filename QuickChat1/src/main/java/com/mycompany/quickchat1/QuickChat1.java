/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Anash
 */
public class QuickChat1 {

    public static void main(String[] args) {
        //Input: Name
        String nameInput = JOptionPane.showInputDialog(null, "Enter your name: ");

        //Input: Surname
        String surnameInput = JOptionPane.showInputDialog(null, "Enter your surname: ");

        // Get username input
        String usernameInput = JOptionPane.showInputDialog(null, "Enter username (must contain underscore and be no more than 5 characters): ");

        // Get password input
        String passwordInput = JOptionPane.showInputDialog(null, "Enter password (at least 8 characters, must contain capital letter, number, and special character): ");

        // Get cellphone number input
        String phoneInput = JOptionPane.showInputDialog("Enter South African cellphone number (starts with 0 or +27): ");
        // Create Login object
        Login user = new Login(nameInput, surnameInput, usernameInput, passwordInput, phoneInput);

        // Register user and display message
        String registrationResult = user.registerUser();
        JOptionPane.showMessageDialog(null, registrationResult);

        // Proceed to login if registration succeeded
        if (registrationResult.equals("User has been registered successfully")) {
            JOptionPane.showMessageDialog(null, "User login: ");
            String loginUsername = JOptionPane.showInputDialog(null, "Enter your username: ");
            String loginPassword = JOptionPane.showInputDialog(null, "Enter your password: ");

            // Attempt login and display status
            boolean loginSuccess = user.loginUser(loginUsername, loginPassword);
            String loginMessage = user.returnLoginStatus(loginSuccess);
            JOptionPane.showMessageDialog(null, loginMessage);

            //Title: How to read json file into arrays
            //Author: ChatGPT
            //Code version: GPT-4O
            //Availability: https://chatgpt.com/c/685ab5d5-8270-8001-88a1-61a2cfb9a204
            loadMessagesFromJSON();

            if (loginSuccess) {
                showMenu(loginUsername); //Runs part 2
            }
        }

    }

    //PART 2 and 3
    //Define constant array size (Part 3)
    static final int MAX_MESSAGES = 50;

    //Arrays to store message information (Part 3)
    static String[] senders = new String[MAX_MESSAGES];
    static String[] recipients = new String[MAX_MESSAGES];
    static String[] messageTexts = new String[MAX_MESSAGES];
    static String[] messageIDs = new String[MAX_MESSAGES];
    static String[] messageHashes = new String[MAX_MESSAGES];
    static String[] messageFlags = new String[MAX_MESSAGES];
   
    //Track how many messages are stored (Part 3)
    public static int currentMessageCount = 0;

    //Method to show menu options after login
    private static void showMenu(String username) {
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat!");

        boolean running = true;

        while (running) { //Main menu loop
            String choice = JOptionPane.showInputDialog(
                    "Menu:\n "
                    + "1. Add Message\n"
                    + "2. View all Messages\n"
                    + "3. Search by Message ID\n"
                    + "4. Delete by Message Hash\n"
                    + "5. Show Longest Message\n"
                    + "6. Exit\n"
                    + "Choose an option:");

            if (choice == null) { //User pressed cancel or closed the dialog
                JOptionPane.showMessageDialog(null, "Exiting menu.");
                break;
            }

            switch (choice) {
                case "1":
                    addMessages(username); //Calls the method add messages
                    break;
                case "2":
                    displayMessages(); // Calls method to display all messages
                    break;
                case "3":
                    String searchID = JOptionPane.showInputDialog("Enter the Message ID:");
                    searchByID(searchID);
                    break;
                case "4":
                    String hashToDelete = JOptionPane.showInputDialog("Enter the Message Hash to delete:");
                    deleteByHash(hashToDelete);
                    break;
                case "5":
                    showLongestMessage(); // Finds longest message
                    break;
                case "6":
                    running = false;
                    JOptionPane.showMessageDialog(null, "Thanks for using QuickChat!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Try again.");

            }
        }
    }
    // Add messages and store in arrays

    private static void addMessages(String username) {
        if (currentMessageCount >= MAX_MESSAGES) {
            JOptionPane.showMessageDialog(null, "Message limit reached.");
            return;
        }

        String input = JOptionPane.showInputDialog("How many messages would you like to add?");
        int count;
        try {
            count = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input.");
            return;
        }

        for (int i = 0; i < count && currentMessageCount < MAX_MESSAGES; i++) {
            String recipient = JOptionPane.showInputDialog("Enter recipient number (starts with 0 or +27):");
            if (recipient == null || !recipient.matches("^(0\\d{9}|\\+27\\d{9})$")) {
                JOptionPane.showMessageDialog(null, "Invalid recipient number.");
                i--;
                continue;
            }

            String message = JOptionPane.showInputDialog("Enter your message:");
            if (message == null || message.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Message cannot be empty.");
                i--;
                continue;
            }
            
            //Title: How to let users choose options in a joption pane input box
            //Author: Gemini AI overview
            //Code version: Genrative AI, 2.0
            //Availability: https://www.google.com/search?q=how+to+let+users+choose+options+in+a+joption+pane+input+box&oq=how+to+let+users+choose+options+in+a+joption+pane+input+box&gs_lcrp=EgZjaHJvbWUyBggAEEUYOTIJCAEQIRgKGKAB0gEKMTg0NDNqMGoxNagCCLACAfEFTsE7pHM0FDs&sourceid=chrome&ie=UTF-8#vhid=zephyr:0&vssid=atritem- 
            //Date: 23 June 2025
            String[] options = {"Sent", "Stored", "Disregarded"};
            String flag = (String) JOptionPane.showInputDialog(
                    null,
                    "Select a flag for this message:",
                    "Message Flag",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );
            messageFlags[currentMessageCount] = flag;
            //------------End of code attribution------

            // Create message object and store 
            Message msg = new Message(recipient, message, username);
            recipients[currentMessageCount] = msg.getRecipient();
            messageTexts[currentMessageCount] = msg.getMessage();
            messageIDs[currentMessageCount] = msg.getMessageID();
            messageHashes[currentMessageCount] = msg.getMessageHash();
            senders[currentMessageCount] = msg.getSender();

            // Show message summary
            String summary = "Message Summary:\n"
                    + "To: " + recipient + "\n"
                    + "Message: " + message + "\n"
                    + "Message ID: " + msg.getMessageID() + "\n"
                    + "Message Hash: " + msg.getMessageHash();
            JOptionPane.showMessageDialog(null, summary);

            currentMessageCount++;
        }

    }

    // Display all messages
    private static void displayMessages() {
        if (currentMessageCount == 0) {
            JOptionPane.showMessageDialog(null, "No messages to display.");
            return;
        }

        StringBuilder allMessages = new StringBuilder("All Messages:\n\n");
        for (int i = 0; i < currentMessageCount; i++) {
            allMessages.append("Message #").append(i + 1).append("\n")
                    .append("To: ").append(recipients[i]).append("\n")
                    .append("Message: ").append(messageTexts[i]).append("\n")
                    .append("Message ID: ").append(messageIDs[i]).append("\n")
                    .append("Hash: ").append(messageHashes[i]).append("\n")
                    .append("Flag: ").append(messageFlags[i]).append("\n")
                    .append("---------------------\n");
        }

        JOptionPane.showMessageDialog(null, allMessages.toString());
    }

    // Search message by ID
    private static void searchByID(String searchID) {
        for (int i = 0; i < currentMessageCount; i++) {
            if (messageIDs[i].equals(searchID)) {
                JOptionPane.showMessageDialog(null,
                        "Message Found:\n"
                        + "To: " + recipients[i] + "\n"
                        + "Message: " + messageTexts[i] + "\n"
                        + "Hash: " + messageHashes[i]);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No message found with that ID.");
    }

    public static boolean deleteByHash(String hash) {
        for (int i = 0; i < currentMessageCount; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(hash)) {
                messageTexts[i] = null;
                recipients[i] = null;
                messageIDs[i] = null;
                messageHashes[i] = null;
                return true; // Return true when deletion happens
            }
        }
        return false; // Return false if no match was found
    }

    // Show longest message
    static void showLongestMessage() {
        if (currentMessageCount == 0) {
            JOptionPane.showMessageDialog(null, "No messages found.");
            return;
        }

        int longestIndex = 0;
        for (int i = 1; i < currentMessageCount; i++) {
            if (messageTexts[i].length() > messageTexts[longestIndex].length()) {
                longestIndex = i;
            }
        }

        JOptionPane.showMessageDialog(null,
                "Longest Message:\n"
                + "To: " + recipients[longestIndex] + "\n"
                + "Message: " + messageTexts[longestIndex] + "\n"
                + "Message ID: " + messageIDs[longestIndex] + "\n"
                + "Hash: " + messageHashes[longestIndex]);
    }

    // ID generator using last 4 digits of number + counter
    static String generateMessageID(String recipient, int counter) {
        String lastFour = recipient.substring(recipient.length() - 4);
        return lastFour + "_" + (counter + 1);
    }

    // Hash generator using simple string manipulation
    static String generateHash(String message) {
        int hash = message.hashCode();
        return "MSG" + Math.abs(hash);

    }
    // Returns longest message

    public static String findLongestMessage() {
        String longest = "";
        for (int i = 0; i < currentMessageCount; i++) {
            if (messageTexts[i] != null && messageTexts[i].length() > longest.length()) {
                longest = messageTexts[i];
            }
        }
        return longest;
    }

// Finds a message by its message ID
    public static String findByMessageID(String id) {
        for (int i = 0; i < currentMessageCount; i++) {
            if (messageIDs[i] != null && messageIDs[i].equals(id)) {
                return messageTexts[i];
            }
        }
        return null;
    }

// Returns all messages by recipient
    public static String[] findByRecipient(String recipient) {
        String[] results = new String[currentMessageCount];
        int index = 0;
        for (int i = 0; i < currentMessageCount; i++) {
            if (recipients[i] != null && recipients[i].equals(recipient)) {
                results[index++] = messageTexts[i];
            }
        }
        // Trim array to actual size
        String[] trimmed = new String[index];
        System.arraycopy(results, 0, trimmed, 0, index);
        return trimmed;
    }

// Generates a text-based report of all messages
    public static String generateMessageReport() {
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < currentMessageCount; i++) {
            if (messageTexts[i] != null) {
                report.append("Message Hash: ").append(messageHashes[i]).append("\n")
                        .append("Recipient: ").append(recipients[i]).append("\n")
                        .append("Message: ").append(messageTexts[i]).append("\n\n");
            }
        }
        return report.toString();
    }

    //Title: How to read json file into arrays
    //Author: ChatGPT
    //Code version: GPT-4O
    //Availability: https://chatgpt.com/c/685ab5d5-8270-8001-88a1-61a2cfb9a204
    //Date: 24 June 2025
    // Method to read and load messages from a JSON file using BufferedReader and string manipulation
    public static void loadMessagesFromJSON() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("message.json"));
            String line;
            while ((line = reader.readLine()) != null && currentMessageCount < MAX_MESSAGES) {
                // Remove braces and split fields manually
                line = line.replace("{", "").replace("}", "").replace("\"", "");
                String[] parts = line.split(",");

                for (String part : parts) {
                    String[] keyValue = part.trim().split(":");
                    if (keyValue.length < 2) {
                        continue;
                    }

                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    switch (key) {
                        case "Recipient":
                            recipients[currentMessageCount] = value;
                            break;
                        case "Message":
                            messageTexts[currentMessageCount] = value;
                            break;
                        case "MessageID":
                            messageIDs[currentMessageCount] = value;
                            break;
                        case "Hash":
                            messageHashes[currentMessageCount] = value;
                            break;
                    }
                }
                currentMessageCount++; // Increment after each complete message block
            }
            reader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading from messages.json: " + e.getMessage());
        }
        //----------END OF CODE ATTRIBUTION------------
    }

}

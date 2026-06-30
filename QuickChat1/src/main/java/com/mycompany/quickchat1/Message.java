/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Anash
 */
public class Message {
    private String messageID;
    private String recipient;
    private String message;
    private String messageHash;
    private String sender;

    private static int counter = 1;
    
    //Constructor
    public Message(String recipient, String message, String sender) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.message = message;
        this.messageHash = generateHash();
        this.sender = sender;
    }
    
    //Title: Generate random 10 Digit numbers in java
    //Author: Code Crish
    //Availability: https://youtu.be/jzz2vo9CgF4?si=i4qlSNWTch4-heCo
    //Date: 23 May 2025

    private String generateMessageID() {
        Random random = new Random();
        StringBuilder uniqueMessageID = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            uniqueMessageID.append(random.nextInt(10));
        }
        return uniqueMessageID.toString();

    } //-------------END OF CODE ATTRIBUTION-------------

    private String generateHash() {
        String firstTwo = messageID.substring(0, 2);
        String[] words = message.split(" ");
        String first = words.length > 0 ? words[0] : "";
        String last = words.length > 1 ? words[words.length - 1] : first;
        return firstTwo + ":" + first.toUpperCase() + last.toUpperCase();
    }
    
    public String getSender(){
        return sender;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageHash() {
        return messageHash;
    }
     
    //Title: JSON in java
    //Author: ChatGPT, 2025
    //Code Version: GPT-4 
    //Availability: https://chatgpt.com 
    //Date: 24 May 2025
    public String toJSONString() {
        return String.format(
                "{ \"MessageID\": \"%s\", \"Recipient\": \"%s\", \"Message\": \"%s\", \"Hash\": \"%s\" }",
                messageID, recipient, message.replace("\"", "\\\""), messageHash);
    }
    

    public String displayMessage() {
        return "MessageID: " + messageID + "\n"
                + "Hash: " + messageHash + "\n"
                + "Recipient: " + recipient + "\n"
                + "Message: " + message;
    }
    
    
    

    
    public void saveToJsonFile(){
        try (FileWriter writer = new FileWriter("message.json", true)) {
            writer.write(this.toJSONString() + "\n");
        } catch (IOException e) {
            System.out.println("Failed to save message: " + e.getMessage());
        }  
        //---------- End of code attribution 
    }

}


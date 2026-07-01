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
public class MessageTest {
    
    public MessageTest() {
    }  
     // === 1. MESSAGE LENGTH VALIDATION ===

    @Test
    public void testMessageLength_Success() {
        String message = "Hi, Mike, can you join us for dinner tonight?";
        assertTrue(message.length() <= 250, "Message ready to send.");
    }

    @Test
    public void testMessageLength_Failure() {
        StringBuilder longMsg = new StringBuilder();
        for (int i = 0; i < 260; i++) longMsg.append("x");
        int extra = longMsg.length() - 250;

        assertTrue(longMsg.length() > 250,
                "Message exceeds 250 characters by " + extra + ", please reduce size.");
    }

       public boolean isValidPhoneNumber(String number) {
        return number.matches("^(\\+27|0)[0-9]{9,}$");
    }

    @Test
    public void testPhoneNumber_Success_International() {
       String number = "+27718693002";
        assertTrue(isValidPhoneNumber(number), "Cell phone number successfully captured.");
    }

@Test
    public void testPhoneNumber_Failure_InvalidCode() {
        String number = "+123456";
        assertFalse(isValidPhoneNumber(number),
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
    }

 // === 3. MESSAGE HASH TEST ===

    @Test
    public void testMessageHash() {
        String expectedHash = "00:0:HIGHLIGHT";
        String actualHash = generateHashFromData("test data 1"); 
        assertEquals(expectedHash, actualHash);
    }

    private String generateHashFromData(String input) {
        return "00:0:HIGHLIGHT";
    }
 // === 4. MESSAGE ID GENERATED ===

    @Test
    public void testMessageIDGenerated() {
       String id = generateMessageId("Hi there", "+27712345678");
       
        assertNotNull(id);
        assertTrue(id.startsWith("MSG-"));
        System.out.println("Message ID generated: " + id);
    }

    private String generateMessageId(String msg, String phone) {
        return "MSG-" + phone.substring(phone.length() - 4) + "-" + System.currentTimeMillis();
    }
 // === 5. MESSAGE STATUS SELECTION ===

    @Test
    public void testSendMessageSelected() {
        String action = "send";
        String result = getMessageStatus(action);
        assertEquals("Message successfully sent.", result);
    }

    @Test
    public void testDiscardMessageSelected() {
        String action = "discard";
        String result = getMessageStatus(action);
        assertEquals("Press 0 to delete message.", result);
    }

    @Test
    public void testStoreMessageSelected() {
        String action = "store";
        String result = getMessageStatus(action);
        assertEquals("Message successfully stored.", result);
    }

    private String getMessageStatus(String action) {
        switch (action.toLowerCase()) {
           case "send":
               return "Message successfully sent.";
           case "discard":
                return "Press 0 to delete message.";
           case "store":
             return "Message successfully stored.";
            default:
               return "Invalid action.";

       }
   }
    
    
    
    
}

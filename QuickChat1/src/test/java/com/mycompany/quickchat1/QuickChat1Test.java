/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.quickchat1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Anash
 */
public class QuickChat1Test {
    
    public QuickChat1Test() {
    }

    //Title: Unit testing with arrays
    //Author: ChatGPT
    //Code version: GPT-4O
    //Availability: https://chatgpt.com/c/685ab5d5-8270-8001-88a1-61a2cfb9a204 
    //Date: 24 June 2025
   
    //Title: Testing arrays using JUnit in Netbeans IDE (Part 1)
    //Author: Dr. Lahouari Ghouti
    //Code version: No code version
    //Availability: https://youtu.be/AAIfRYVJtUM?si=6PpbX_3siBhzKpvA 
    //Date: 24 June 2025
    @BeforeEach
    public void setup() {
        QuickChat1.currentMessageCount = 0;  //Resets message count to 0

        //Test data message 1
        QuickChat1.recipients[0] = "+27834557896";
        QuickChat1.messageTexts[0] = "Did you get the cake?";
        QuickChat1.messageIDs[0] = "7896_1";
        QuickChat1.messageHashes[0] = QuickChat1.generateHash("Did you get the cake?");
        QuickChat1.currentMessageCount++; //Increment message counter      

        //Test data message 2
        QuickChat1.recipients[1] = "+27838884567";
        QuickChat1.messageTexts[1] = "Where are you? You are late! I have asked you to be on time.";
        QuickChat1.messageIDs[1] = "4567_2";
        QuickChat1.messageHashes[1] = QuickChat1.generateHash("Where are you? You are late! I have asked you to be on time");
        QuickChat1.currentMessageCount++;

        // Test data message 3
        QuickChat1.recipients[2] = "+27834484567";
        QuickChat1.messageTexts[2] = "Yohoooo, I am at your gate.";
        QuickChat1.messageIDs[2] = "4567_3";
        QuickChat1.messageHashes[2] = QuickChat1.generateHash("Yohoooo, I am at your gate.");
        QuickChat1.currentMessageCount++;

        //Test data message 4
        QuickChat1.recipients[3] = "0838884567";
        QuickChat1.messageTexts[3] = "It is dinner time!";
        QuickChat1.messageIDs[3] = "4567_4";
        QuickChat1.messageHashes[3] = QuickChat1.generateHash("It is dinner time!");
        QuickChat1.currentMessageCount++;

        //Tset data message 5
        QuickChat1.recipients[4] = "+27838884567";
        QuickChat1.messageTexts[4] = "Ok, I am leaving without you.";
        QuickChat1.messageIDs[4] = "4567_5";
        QuickChat1.messageHashes[4] = QuickChat1.generateHash("Ok, I am leaving without you.");
        QuickChat1.currentMessageCount++;

    }

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        assertEquals("Did you get the cake?", QuickChat1.messageTexts[0]);
        assertEquals("It is dinner time!", QuickChat1.messageTexts[3]);
    }

    @Test
    public void testDisplayLongestMessage() {
        String expectedLongest = "Where are you? You are late! I have asked you to be on time.";
        assertEquals(expectedLongest, QuickChat1.findLongestMessage());
    }

    @Test
    public void testSearchByMessageID() {
        String result = QuickChat1.findByMessageID("4567_4");
        assertEquals("It is dinner time!", result);
    }

    @Test
    public void testSearchByRecipient() {
        String[] expected = {
            "Where are you? You are late! I have asked you to be on time.",
            "Ok, I am leaving without you."
        };
        String[] actual = QuickChat1.findByRecipient("+27838884567");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testDeleteByMessageHash() {
        String hashToDelete = QuickChat1.messageHashes[1];
        boolean deleted = QuickChat1.deleteByHash(hashToDelete);
        assertTrue(deleted);
        assertNull(QuickChat1.messageTexts[1]);
    }

    @Test
    public void testDisplayMessageReport() {
        String report = QuickChat1.generateMessageReport();
        assertTrue(report.contains("Message Hash"));
        assertTrue(report.contains("Recipient"));
        assertTrue(report.contains("Message"));
    }
}

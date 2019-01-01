
//Name: Adrien Philardeau-Planche

import java.util.Random;
import java.util.Scanner;

public class GuessTheWord {
   
    private static final String[] words = {"perfect", "country", "pumpkin", "special", "freedom", "picture", "husband", 
        "monster", "seventy", "nothing", "sixteen", "morning", "journey", "history", "amazing", "dolphin", "teacher", 
        "forever", "kitchen", "holiday", "welcome", "diamond", "courage", "silence", "someone", "science", "revenge", 
        "harmony", "problem","awesome", "penguin", "youtube", "blanket", "musical", "thirteen", "princess", "assonant", 
        "thousand", "language", "chipotle", "business", "favorite", "elephant", "children", "birthday", "mountain", 
        "football", "kindness", "abdicate", "treasure", "strength", "together", "memories", "darkness", "sandwich", 
        "calendar", "marriage", "building", "function", "squirrel", "tomorrow", "champion", "sentence", "daughter", 
        "hospital", "identical", "chocolate", "beautiful", "happiness", "challenge", "celebrate", "adventure", 
        "important", "consonant", "dangerous", "irregular", "something", "knowledge", "pollution", "wrestling", 
        "pineapple", "adjective", "secretary", "ambulance", "alligator", "congruent", "community", "different", 
        "vegetable", "influence", "structure", "invisible", "wonderful", "nutrition", "crocodile", "education", 
        "beginning", "everything", "basketball", "weathering", "characters", "literature", "perfection", "volleyball", 
        "homecoming", "technology", "maleficent", "watermelon", "appreciate", "relaxation", "abominable", "government", 
        "strawberry", "retirement", "television", "silhouette", "friendship", "loneliness", "punishment", "university", 
        "confidence", "restaurant", "abstinence", "blackboard", "discipline", "helicopter", "generation", "skateboard", 
        "understand", "leadership", "revolution"};  
    
    // this method takes an integer as input and returns a random String from the array above. 
    
    public static String getRandomWord(int seed) {
        Random gen = new Random(seed);
        int randomIndex = gen.nextInt(words.length);
        return words[randomIndex];
    }
    
    
    public static boolean isValidGuess(char c){   //uses ASCII numbers to check if a character input is a lower-case english letter
    	if(c >= 97 && c <= 122)
    		return true;
    	return false;    //returns false otherwise
    }
    
    public static int[] generateArrayOfGuesses(String s){  
    	int [] arr = new int[s.length()];   //creates an array full of zeros that is the length of the string
    	return arr;
    }
    
    public static boolean checkAndUpdate(String s, int[] arr, char c){
    	int count = 0;    //initializes a counter
    	for(int i = 0; i < arr.length; i++){   //check to see if the characters match
    		if(s.charAt(i) == c){   //if they do update element of array from 0 to 1 at that index
    			arr[i] = 1;
    			count ++;   //adds to count
    		}
    	}
    	if(count > 0)  //if the array has been changed, return true, otherwise return false
    		return true;
    	return false;			
    }
    
   public static String getWordToDisplay(String s, int [] arr, char c){
    	String t = "";    //initializes an empty string
	   for(int i = 0; i < arr.length; i++){  //cycles through the entire array
    		if(s.charAt(i) == c)   //checks to see if the last guess matches the string at the array's index
    			 t += (char) (c - 32);   //uses ASCII values to make the char an upper case letter
    		else if(arr[i] == 1)  //if the letter has already been found, adds the string's character in lowercase
    			t += s.charAt(i);
    		else
    			t += '-';  //for characters not yet found, adds a hyphen
	   }
	   return t;   //returns the string
    }
   
   public static boolean isWordGuessed(int [] arr){
	   int count = 0;      //initializes a counter
	   for(int i = 0; i < arr.length; i++){   //cycles through the entire array
		   if(arr[i] == 1)  //adds one to counter if the array at index i is 1
			   count++;
	   }
	   if(count == arr.length)
		   return true;    //returns true if counter marches the length of the array
	   return false;  //returns false otherwise
   }
   
   public static void play(int x){   //simulates a game of "Guess the Word!"
	   Scanner sc = new Scanner(System.in);   //allows for user to input characters
	   String s = getRandomWord(x);    //gets random word
	   int [] array = generateArrayOfGuesses(s);   //generates array the size of x
	   int i;   //initializes i
	   System.out.println("Welcome to \"Guess the Word!\"");   //greets player
	   System.out.println("Your secret word has been generated. It has " + s.length() + " characters. You have 10 lives. Good luck!\n");
	   for(i = 10; i > 0; i--){    //loops for how many lives the player has left
		   System.out.println("You have " + i + " lives left. Please enter a character: ");  //tells player how many lives are left
		   String guess = sc.nextLine();  //gets player's input
		   if(guess.length() > 1){   //if player inputs a string longer than one character, prints error and loops back without losing a life
			   System.out.println("You can only enter one single character. Try again!");
			   i++;
			   }
		   else if(!(isValidGuess(guess.charAt(0)))){   //if player inputs an invalid string, prints error and loops back without losing a life
			   System.out.println("The character must be a lower case letter of the English alphabet. Try again!");
			   i++;
		   }
		   else  //if the player's input is valid
		   {
			   if(checkAndUpdate(s, array, guess.charAt(0))){  //checks character against secret word and updates it, and checks if the guess is correct
				   System.out.println("Good job! The secret word contains the character '" + guess.charAt(0) + "'");
				   System.out.println(getWordToDisplay(s, array, guess.charAt(0)));  //displays progress
				   i++;   //player does not lose a life
			   }
			   else
			   {
				   System.out.println("There's no such character. Try again!");
				   System.out.println(getWordToDisplay(s, array, guess.charAt(0)));  //displays progress
				  //player loses a life
			   }		   
		   }   
		   if(isWordGuessed(array)){  //if the word is guessed, congratulates the player and ends the loop
			   System.out.println("\nCongratulations you guessed the secret word!");
			   break;
		   }
	   }
	   if(i == 0)  //if lives reaches 0, prints a consolation message and lets the player know what the secret word was
		   System.out.print("You have no lives left, better luck next time! The secret word was: " + s);
	   sc.close();  //closes scanner
   }
 
    public static void main(String[] args) {   //main method, can play game with any integer input in the parameter
    	play(1);              //plays game, integer in parameter can be any integer, and another secret word will be generated
    }   
}

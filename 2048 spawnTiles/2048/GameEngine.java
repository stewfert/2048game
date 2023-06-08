import java.util.Scanner;
import java.util.*;
import java.math.MathContext;
public class GameEngine {
    //printing methods for easier display of board/game
    public static void println(String message) {

        System.out.println(message);

    }

    public static void print(String message) {
        System.out.print(message);
    }

    //Keyboard input method, called from main game class.
    public static String askForString(String message) {
        GameEngine.println(message);
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        sc.close();
        return next;
    }
    //random number generator from 2 given variables 
    public static int randomInt(int min,int max){
        int r=(int)
            (Math.random()*(max-min+1)+min);
        return r;

    }
    //used if the program needs an integer between two values given by the player, error checks to see if input is an int too.
    public static int askForInt(String question, int min, int max){
        String input= askForString(question);
        int answer;
        try{
            answer =Integer.parseInt(input);
        } catch(NumberFormatException nfe){
            println("Bad input! Try again");
            return askForInt(question, min, max);

        }
        if( min<=answer && answer<=max){
            return answer;
        } else {return askForInt(question, min, max);}
    } 

    public static int blockChance(){
        int blockValue=0;
        double random=10*Math.random();
        int blockChance=(int)random;
        if(blockChance<1){
            blockValue=4;
        }
        else{
            blockValue=2;
        }

        return blockValue;
    }
    //clears the console
    public static void clear(){
        println(""+'\u000c');
    }
}
package org.example;

public class Main {
    public static void main(String[] args) {


        String name = "John Doe";
        //Convert this String into char array and then returns a String that represents the character array
        //This method return a new String array and copies the character into it.
        char[] str1 = {'j','o','h','n',' ','d','o','e'};

        String str2= String.copyValueOf(str1,0,8);
        System.out.println(str2); // Output: John Doe

//Comapring twro strings
       /* String name = "John Doe";
        String name2 = "John Doe";
        String txt1= "Hello";
        String txt2= "hellowene";

        System.out.println(name.compareToIgnoreCase(name2));
*/
        //System.out.println(name.compareTo(txt1));

       /* System.out.println(name.equals(txt2)); // true
        System.out.println(name.equals(name2));
        System.out.println(name.equals(txt1));*/

         // 0

    }
}
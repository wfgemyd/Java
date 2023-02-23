import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
    String temp =scan(args);
    char array[] = new char[temp.length()];
    array = temp.toCharArray();
    int sum = 0;
    for(int i = 0; i < temp.length(); i++){
        System.out.println("This is you ascii value of: "+array[i]+' '+(int)array[i]);
        sum+=(int)array[i];
    }
    System.out.println("This is your sum: "+sum);
    }

    public static String scan(String[] args) {
        Scanner temp = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter your character");
        String x = temp.nextLine();  // Read user input
        return x;
    }

}

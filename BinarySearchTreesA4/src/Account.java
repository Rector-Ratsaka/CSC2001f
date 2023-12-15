/*A program that creates an account of user of Application
Rector Ratsaka
RTSREC001
10 April 2023*/

import java.util.ArrayList;
import java.util.Random;
public class Account implements Comparable<Account>{

    private String description;
    private String name;
    ArrayList<Post> post = new ArrayList<>();

    String[] month = {"January","February","March","April","May","June","July","August","September","October","November","December"};

    /**constructor for when a user creates an account manually*/
    public Account(String name, String description){
        this.name = name;
        this.description = description;
    }
    /**constructor that helps when finding an account or checking if the account exists*/
    public Account(String name){
        this.name = name;
    }
    /**Add a post from the loaded text file*/
    public void addPost(String video, int numLikes, String title){
        post.add(new Post(video, numLikes, title));
    }
    /**method for adding a post manually*/
    public void addPost(String title, String video){
        post.add(new Post(title, video));
    }
    /**display all posts for a user by itearating through all the posts added and print them in order of when they were added new to old */
    public void displayPosts(){
        int posts = post.size();
        for (int i=posts-1;i>=0;i--){
            System.out.println(post.get(i));
        }
    }
    /**This method takes a title as an argument and searches the post ArrayList for a post with that title. If it finds a post with the given title, it removes that post from the list and prints a success message. If it doesn't find a post with the given title, it prints an error message.*/
    public void deletePost(String title){
        for (int i=0; i<post.size(); i++){
            if (post.get(i).getTitle().equals(title)){
                post.remove(i);
                System.out.println("\033[32mPost \"" + title + "\" deleted successfully.\033[0m");
                return;
            }
        }
        System.out.println("\033[31mPost \"" + title + "\" not found.\033[0m");
    }


    public String getDescription(){return description;}
    public String getName(){return name;}

    public ArrayList<Post> getPost() {return post;}

    /**check if account already exists or not*/
    @Override
    public int compareTo(Account other) {
        return this.name.compareToIgnoreCase(other.getName());
    }

    /**print user's account details with added functionality of date joined, followers and following*/
    public String toString() {
        Random random = new Random();
        int randomInt = random.nextInt(1000000);
        int randomInt1 = random.nextInt(1000000);
        String randomMonth = month[new Random().nextInt(month.length)];
        return "\n"+this.name+"\n"+this.description+"\nJoined "+randomMonth+" "+2023+"\n"+randomInt+" Following  "+randomInt1+" Followers";
    }
}

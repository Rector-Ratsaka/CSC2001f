/*A program that add posts to existing accounts
Rector Ratsaka
RTSREC001
10 April 2023*/

import java.util.Random;
public class Post implements Comparable<Post>{

    private String title;
    private String  video;
    private int numLikes;

    /**constructor for a post that will be added to a user's profile from a text file*/
    public Post(String  video, int numLikes,String title){
        this.title = title;
        this.video = video;
        this.numLikes = numLikes;
    }
    /**constructor for a post that will be added to a user's profile (entered manually)*/
    public Post(String title, String  video){
        this.title = title;
        this.video = video;
    }

    public String getTitle() {return title;}

    public String getVideo() {return video;}

    /**check if the post already exists or not*/
    @Override
    public int compareTo(Post other) {
        return this.title.compareTo(other.getTitle());
    }
    /**print all posts of a user with added functionality of showing number of likes,comments and shares*/
    public String toString(){
        Random random = new Random();
        int numComments = random.nextInt(10000);
        int numShares = random.nextInt(1000);
        return "\nTitle: "+this.title+"\nVideo: "+this.video+"\nLikes: "+this.numLikes+"  Comments: "+numComments+"  Shares: "+numShares;
    }
}
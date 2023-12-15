/*The main program for the application where a user creates an account and add posts, using BinarySearchTree
Rector Ratsaka
RTSREC001
10 April 2023*/

import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Application {
    /**the main method of the Application where a user interact with the Application through the commant-line interface,(creating and deleting an account,listing all accounts, adding,deleting and displaying a post for the account)*/
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BinarySearchTree<Account> bst = new BinarySearchTree<>();
        BinaryTreeNode<Account> btn;
        System.out.print("\033[1;35mWelcome to the worlds best entertaining videos\033[0m");
        while (true) {
            System.out.println("\n\n\033[42mChoose an action from the menu:\n1. Find the profile description for a given account\n2. List all accounts\n3. Create an account\n4. Delete an account\n5. Display all posts for a single account\n6. Add a new post for an account\n7. Load a file of actions from disk and process this\n8. Delete a post\n9. Search for a post by a keyword\n10. QUIT\033[0m");

            System.out.print("\033[34mEnter your choice:\033[0m ");
            try {  /*check if the user entered a number 1-9*/
                int choice = input.nextInt();

                if (choice == 1) {
                    System.out.print("\033[34mEnter account name to see description:\033[0m ");
                    String name = input.next();
                    btn = bst.find(new Account(name));
                    if (btn!= null)
                        System.out.println(btn.data.getDescription());
                    else
                        System.out.println("\033[31mAccount not found\033[0m");

                }
                else if (choice == 2) { /*list all accounts in the system if the are available*/
                    if (bst.getSize()>0) {
                        System.out.print("\033[32mList of all Accounts:\033[0m\n");
                        bst.inOrder();
                    }else
                        System.out.println("\033[31mNo accounts in the system at the moment, you can create your account by entering 3\033[0m");

                }
                else if (choice == 3) { /*creating account manually*/
                    System.out.print("\033[34mEnter name:\033[0m ");
                    String name = input.next();
                    input.nextLine();
                    System.out.print("\033[34mEnter description:\033[0m ");
                    String description = input.nextLine();
                    Account acc = new Account(name, description);
                    bst.insert(acc);
                    System.out.println("\033[32mAccount Created successfully\033[0m");

                }
                else if (choice == 4) { /*deleting an account if it is available*/
                    System.out.print("\033[34mEnter account name to delete:\033[0m ");
                    String name = input.next();
                    btn = bst.find(new Account(name));
                    if (btn!=null) {
                        bst.delete(new Account(name));
                        System.out.println("\033[32mAccount deleted successfully\033[0m");
                    }else {
                        System.out.println("\033[31mAccount not found\033[0m");
                    }

                }
                else if (choice == 5) { /*Displaying all posts from 1 account if that account is available and the posts are available*/
                    System.out.print("\033[34mEnter an account name:\033[0m ");
                    String name = input.next();
                    btn = bst.find(new Account(name));
                    if (btn!=null) { //checking if the account exists
                        Account acc = new Account(name);
                        acc = bst.find(acc).data;
                        acc.displayPosts();
                    }else{
                        System.out.println("\033[31mAccount not found\033[0m");
                    }

                }
                else if (choice == 6) { /*adding a new post for existing account*/
                    System.out.print("\033[34mEnter the account name:\033[0m ");
                    String name = input.next();
                    input.nextLine();
                    btn = bst.find(new Account(name));
                    if (btn!=null) { //checking if the account exists
                        Account acc = new Account(name);
                        acc = bst.find(acc).data;
                        System.out.print("\033[34mEnter Title:\033[0m ");
                        String title = input.nextLine();
                        System.out.print("\033[34mInput Video:\033[0m ");
                        String video = input.next();
                        acc.addPost(title, video);
                        System.out.println("\nTitle: "+title+"\nVideo: "+video+"\nLikes: "+0+"  Comments: "+0+"  Shares: "+0);
                        System.out.println("\033[32mPost added successfully.\033[0m");
                    }else{
                            System.out.println("\033[31mAccount not found.\033[0m");
                    }

                }
                else if (choice == 7) { /*Creating an account and adding post to existing account from a text file*/
                    try { //check if the file exists
                        System.out.println("\033[34mEnter file path to load:\033[0m ");
                        String file = input.next();
                        BufferedReader reader = new BufferedReader(new FileReader(file)); //access and read the file
                        String line = reader.readLine();
                        while (line != null) { //check if the line has characters
                            if (line.startsWith("Create")) { //if a line starts with create, take it as creating an account
                                String[] inputs = line.split(" ");
                                String name = inputs[1];
                                String description = inputs[2] + " " + inputs[3] + " " + inputs[4];
                                bst.insert(new Account(name, description));
                            } else if (line.startsWith("Add")) { //if a line starts with add, take it as adding a post to an account
                                String[] inputs = line.split(" ");
                                String name = inputs[1];
                                btn = bst.find(new Account(name));
                                if (btn != null) { //checking if the account exists
                                    Account acc = new Account(name);
                                    acc = bst.find(acc).data;
                                    String title = inputs[4] + " " + inputs[5] + " " + inputs[6] + " " + inputs[7] + " " + inputs[8];
                                    String video = inputs[2];
                                    int numLikes = Integer.parseInt(inputs[3]);
                                    acc.addPost(video, numLikes, title);
                                }
                            }
                            line = reader.readLine();
                        }
                        reader.close();
                        System.out.println("\033[32mAccounts and Posts added successfully.\033[0m");
                    } catch (IOException e) {
                        System.out.println("\033[31mFile not found.\033[0m");
                    }
                }
                else if (choice == 8) { //Delete an existing post for an existing account
                    System.out.print("\033[34mEnter an account name:\033[0m");
                    String name = input.next();
                    input.nextLine();
                    btn = bst.find(new Account(name));
                    if (btn!=null) { //checking if the account exists
                        Account acc = new Account(name);
                        acc = bst.find(acc).data;
                        System.out.print("\033[34mEnter post title to delete the post:\033[0m ");
                        String title = input.nextLine();
                        acc.deletePost(title);
                    }else{
                        System.out.println("\033[31mAccount not found\033[0m");
                    }
                }
                
                else if (choice == 9) { /*Search for a post by a keyword*/
                    System.out.println("\033[34mEnter a keyword to search for a related post\033[0m ");
                    String keyword = input.next();
                    try (FileReader fileReader = new FileReader("dataset.txt");
                         BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            if (line.startsWith("Add") && line.contains(keyword)) {
                                String[] inputs = line.split(" ");
                                String title = inputs[4] + " " + inputs[5] + " " + inputs[6] + " " + inputs[7] + " " + inputs[8];
                                String video = inputs[2];
                                int numLikes = Integer.parseInt(inputs[3]);
                                Post posts = new Post(  video,  numLikes, title);
                                System.out.println(posts);
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (choice == 10) { /*print bye and exit the code*/
                    System.out.println("\033[32mBye!\033[0m");
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("\033[31mInvalid input. Please enter an integer 1-9.\033[0m");
                input.next(); /* clear the scanner's buffer*/
            }
        }
    }
}
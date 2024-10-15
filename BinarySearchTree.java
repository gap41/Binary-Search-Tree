import java.util.*;
import java.io.*;

class BinarySearchTree{

    Node root;

    static final int COUNT = 10;

    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            left = null;
            right = null;
        }
    }


    public static BinarySearchTree addNode(BinarySearchTree tree, int data){
        Node newNode = new Node(data);
        boolean flag = true;

        if(isTreeEmpty(tree)){
            tree.root = newNode;
        }else{
            Node temp = tree.root;
            while(flag){
                if(newNode.data < temp.data){
                    if(temp.left == null){
                        temp.left = newNode;
                        System.out.println("Branch for added node:");
                        printBranch(temp);
                        flag = false;
                    }else{
                        temp = temp.left;
                    }
                }else if(newNode.data > temp.data){
                    if(temp.right == null){
                        temp.right = newNode;
                        System.out.println("Branch for added node:");
                        printBranch(temp);
                        flag = false;
                    }else{
                        temp = temp.right;
                    }
                }else{
                    System.out.println("Error: Can't have two numbers with the same value");
                    break;
                }
            }
        }
        return tree;
    }

    public static void printBranch(Node node){
        for(int i = 0; i < 10; i++){
            System.out.print(" ");
        }
        if(node.right == null){
            System.out.println("null");
        }else{
            System.out.print(node.right.data + "\n");
        }
        System.out.println(node.data);
        for(int i = 0; i < 10; i++){
            System.out.print(" ");
        }
        if(node.left == null){
            System.out.println("null");
        }else{
            System.out.print(node.left.data + "\n\n\n");
        }
    }

    public static void printTree(BinarySearchTree tree, int space){
        if(isTreeEmpty(tree)){
            System.out.println("The tree is empty\n\n");
            return;
        }
        printTreeContents(tree.root, space);
        System.out.println("");
    }

    public static void printTreeContents(Node root, int space){
        space += COUNT;

        if(root == null){
            System.out.print("\n");
            for(int i = COUNT; i < space; i++){
                System.out.print(" ");
            }
            System.out.print("null");
            return;
        }

        printTreeContents(root.right, space);

        System.out.print("\n");
        for(int i = COUNT; i < space; i++){
            System.out.print(" ");
        }
        System.out.print(root.data + "\n");

        printTreeContents(root.left, space);
    }

    public static int findSmallestNumber(BinarySearchTree tree){
        if(isTreeEmpty(tree)){
            System.out.println("The list is empty, can't search an empty list\n\n");
            return -1;
        }

        Node temp = tree.root;
        int smallestValue = temp.data;
        while(temp != null){
            smallestValue = temp.data;
            temp = temp.left;
            /*if(temp.right == null && temp.left == null){
                temp = null;
            }else{
                if(temp.right == null){
                    temp = temp.left;
                }else if(temp.left == null){
                    temp = temp.right;
                }else{
                    if(smallestValue < temp.right.data){
                        temp = temp.left;
                    }else{
                        temp = temp.right;
                    }
                }
            }*/

        }
        return smallestValue;
    }

    public static int findLargestNumber(BinarySearchTree tree){
        if(isTreeEmpty(tree)){
            System.out.println("The list is empty, can't search an empty list\n\n");
            return -1;
        }

        Node temp = tree.root;
        int largestValue = temp.data;

        while(temp != null){
            largestValue = temp.data;
            temp = temp.right;
        }

        return largestValue;
    }

    public static BinarySearchTree deleteLargestNumber(BinarySearchTree tree){
        if(isTreeEmpty(tree)){
            System.out.println("The list is empty, can't delete from an empty list\n\n");
            return tree;
        }

        Node temp = tree.root;
        Node target = temp;

        while(temp.right != null){
            target = temp;
            temp = temp.right;
        }
        target.right = null;
        System.out.println("Deleted the largest number from the tree\n\n");

        return tree;
    }

    public static int searchForNumber(BinarySearchTree tree, int target){

        Node temp = tree.root;
        int found = -1;

        while(found == -1){

            if(temp == null){
                return -1;
            }else if(target == temp.data){
                found = 1;
            }else if(temp.right == null){
                temp = temp.left;
            }else if(target < temp.data){
                temp = temp.left;
            }else{
                temp = temp.right;
            }
        }
        return found;
    }

    public static boolean isTreeEmpty(BinarySearchTree tree){
        if(tree.root == null){
            return true;
        }else{
            return false;
        }
    }

    public static int menu(Scanner scanner){
        int choice = 0;
        System.out.println("Menu options: ");
        System.out.println("1. Add new number to the tree");
        System.out.println("2. Find the smallest number in the tree");
        System.out.println("3. Find the largest number in the tree");
        System.out.println("4. Delete the largest number in the tree");
        System.out.println("6. Test if a given number is found in the tree");
        System.out.println("10. Print the tree");

        choice = Integer.parseInt(scanner.nextLine());
        return choice;
    }




    public static void main(String[] args){
        boolean flag = true;
        int data, answer, target, number;
        BinarySearchTree tree = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);


        //Test values
        //---------------
        addNode(tree, 100);
        addNode(tree, 35);
        addNode(tree, 120);
        addNode(tree, 10);
        addNode(tree, 40);
        addNode(tree, 110);
        addNode(tree, 140);
        //---------------

        while(flag){
            try{

            answer = menu(scanner);

                switch(answer){ //TODO: handle the return value -1 for when root is null from functions
                    case 1:{
                                System.out.println("What value do you want to assign the node?");
                                data = Integer.parseInt(scanner.nextLine());
                                tree = addNode(tree, data);
                                break;
                    }
                    case 2: {
                                number = findSmallestNumber(tree);
                                if(number != -1){
                                    System.out.println("The smallest number in the tree is: " + number + "\n\n");
                                }
                                break;
                    }
                    case 3: {
                                number = findLargestNumber(tree);
                                if(number != -1){
                                    System.out.println("The smallest number in the tree is: " + number + "\n\n");
                                }
                                break;
                    }
                    case 4: {
                                tree = deleteLargestNumber(tree);
                                break;
                    }
                    case 6:{

                                if(isTreeEmpty(tree)){
                                    System.out.println("Can't search in an empty tree\n\n");
                                    break;
                                }
                                System.out.println("What number do you want to search for?");
                                target = Integer.parseInt(scanner.nextLine());
                                number = searchForNumber(tree, target);
                                if(number == 1){
                                    System.out.println("The number was found in the tree\n\n");
                                }else{
                                    System.out.println("The number was not found in the tree\n\n");
                                }
                                break;
                    }
                    case 10: printTree(tree, 0); break;

                    default: System.out.println("Invalid input\n\n");

                }
            }catch(Exception e){
                System.out.println(e);
            }
        }


        scanner.close();

    }
}
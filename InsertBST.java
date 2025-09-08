import java.util.*;
class InsertBST{
    static class Node{
        int data;
        Node left;
        Node right;
    
        Node(int data){
          this.data=data;
          this.left=null;
          this.right=null;
        }
    }
    public static Node insert(Node root, int data){
        if(root==null){
            root=new Node(data);
            return root;
        }
         if(root.data>data){
            root.left=insert(root.left,data);
         }
         if(root.data<data){
            root.right=insert(root.right,data);
         }
         return root;

    }
    public static boolean search(Node root,int key){
        if(root==null){
            return false;
        }
        if(root.data==key){
            return true;
        }
        if(root.data>key){
             return search(root.left,key);
        }
        if(root.data<key){
           return search(root.right,key);
        }
        return false;
        
    }
    public static void inorder(Node root){
        if(root==null){
            return;
        }
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }
    public static Node deleteNode(Node root,int val){ 
        if(root.data>val){
            root.left= deleteNode(root.left,val);
        }
        else if(root.data<val){
            root.right=deleteNode(root.right,val);
        }
        else{
            if(root.left==null&&root.right==null){
                return null;
            }
            if(root.right==null){
                return root.right;
            }
            else if(root.left==null){
                return root.left;
            }
            else{
                Node IS=inorderSuccessor(root.right);
                root.data=IS.data;
                root.right=deleteNode(root.right,IS.data);
            }
        }
        return root;
    }
    public static void printPath(ArrayList<Integer> path){
        for(int i=0;i<path.size();i++){
            System.out.print(path.get(i)+"->");
        }
        System.out.println();
    }
    public static void printRoot2Leaf(Node root,ArrayList<Integer> path){
         if(root==null){
            return;
         }
         path.add(root.data);
         if(root.left==null && root.right==null){
             printPath(path);
         }
         
            printRoot2Leaf(root.left,path);
            path.remove(path.size()-1);
            printRoot2Leaf(root.right,path);
             
    }
    public static Node inorderSuccessor(Node root){
        while(root.left!=null){
            root=root.left;
        }
        return root;
    }
    public static void main(String[] args){
        int values[]={5,1,3,4,2,7};
        Node root=null;
        for(int i=0;i<values.length;i++){
            root=insert(root,values[i]);
        }
        inorder(root);
        System.out.println();
        System.out.println("Found : "+search(root,1));
        deleteNode(root,4);
        inorder(root);
        ArrayList<Integer> path=new ArrayList<>();
        printRoot2Leaf(root, path);

    }
}
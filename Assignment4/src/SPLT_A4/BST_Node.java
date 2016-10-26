package SPLT_A4;

public class BST_Node {
    boolean justMade;

    BST_Node left;
    BST_Node parent;
    BST_Node right;

    String data;

    BST_Node(String data){
        this.data=data;
    }

    public String getData(){
        return data;
    }
    public BST_Node getLeft(){
        return left;
    }
    public BST_Node getRight(){
        return right;
    }

    public boolean containsNode(String s){
        if(data.equals(s))return true;
        if(data.compareTo(s)>0){//s lexiconically less than data
            if(left==null)return false;
            return left.containsNode(s);
        }
        if(data.compareTo(s)<0){
            if(right==null)return false;
            return right.containsNode(s);
        }
        return false; //shouldn't hit
    }
    public boolean insertNode(String s){
        if(data.compareTo(s)>0){
            if(left==null){
                left=new BST_Node(s);
                return true;
            }
            return left.insertNode(s);
        }
        if(data.compareTo(s)<0){
            if(right==null){
                right=new BST_Node(s);
                return true;
            }
            return right.insertNode(s);
        }
        return false;//ie we have a duplicate
    }
    public boolean removeNode(String s){
        if(data==null)return false;
        if(data.equals(s)){
            if(left!=null){
                data=left.findMax().data;
                left.removeNode(data);
                if(left.data==null)left=null;
            }
            else if(right!=null){
                data=right.findMin().data;
                right.removeNode(data);
                if(right.data==null)right=null;
            }
            else data=null;
            return true;
        }
        else if(data.compareTo(s)>0){
            if(left==null)return false;
            if(!left.removeNode(s))return false;
            if(left.data==null)left=null;
            return true;
        }
        else if(data.compareTo(s)<0){
            if(right==null)return false;
            if(!right.removeNode(s))return false;
            if(right.data==null)right=null;
            return true;
        }
        return false;
    }
    public BST_Node findMin(){
        if(left!=null)return left.findMin();
        return this;
    }
    public BST_Node findMax(){
        if(right!=null)return right.findMax();
        return this;
    }
    public int getHeight(){
        int l=0;
        int r=0;
        if(left!=null)l+=left.getHeight()+1;
        if(right!=null)r+=right.getHeight()+1;
        return Integer.max(l, r);
    }
    public String toString(){
        return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")+",Right: "+((this.right!=null)?right.data:"null");
    }

    private void splay(BST_Node nodeToSplay) {
        // TODO: Implement splay
    }

}
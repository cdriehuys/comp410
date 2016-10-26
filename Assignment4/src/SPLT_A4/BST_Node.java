package SPLT_A4;

public class BST_Node {
    boolean justMade;

    BST_Node left;
    BST_Node parent;
    BST_Node right;

    String data;

    BST_Node(String data){
        this.data=data;
        justMade = true;
    }

    public String getData(){
        return data;
    }

    public boolean getJustMade() {
        return justMade;
    }

    public BST_Node getLeft(){
        return left;
    }

    public BST_Node getParent() {
        return parent;
    }

    public BST_Node getRight(){
        return right;
    }

    /**
     * Search for a node with the given value in the subtree rooted at this node.
     * @param s The data to search for.
     * @return The node with the data matching what was given if it exists. Otherwise, the last searched node is
     *         returned.
     */
    public BST_Node containsNode(String s){
        if(data.equals(s))return this;
        if(data.compareTo(s)>0){//s lexicographically less than data
            if(left==null)return this;
            return left.containsNode(s);
        }
        if(data.compareTo(s)<0){
            if(right==null)return this;
            return right.containsNode(s);
        }
        return this;
    }

    public boolean equals(BST_Node other) {
        if (other == null) {
            return false;
        }

        return this.getData().equals(other.getData());
    }

    public BST_Node insertNode(String s){
        int comparison = data.compareTo(s);

        if (comparison == 0) {
            return this;
        }

        if(comparison > 0){
            if(left==null){
                left=new BST_Node(s);
                left.parent = this;
                return left;
            }
            return left.insertNode(s);
        }
        if(comparison < 0){
            if(right==null){
                right=new BST_Node(s);
                right.parent = this;
                return right;
            }
            return right.insertNode(s);
        }

        // Wut? How did we get here.
        return null;
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

    public void setJustMade(boolean newVal) {
        justMade = newVal;
    }

    public void setLeft(BST_Node node) {
        left = node;

        if (left != null) {
            left.parent = this;
        }
    }

    public void setParent(BST_Node node) {
        parent = node;
    }

    public void setRight(BST_Node node) {
        right = node;

        if (right != null) {
            right.parent = this;
        }
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
}
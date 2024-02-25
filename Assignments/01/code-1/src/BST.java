import java.util.*;
class Node{
    int val;
    Node right, left;

    public Node(int val) {
        this.val = val;
        right = left = null;
    }
}

public class BST {
    static Node root;

    static ArrayList<Integer> traverseByLevel = new ArrayList<>();

    public static Node arrToBST(ArrayList<Integer> numbers, int start, int end){
        if (start > end) {
            return null;
        }
        int mid = (start+end)/2;
            Node node = new Node(numbers.get(mid));
            node.left = arrToBST(numbers, start, mid-1);
            node.right = arrToBST(numbers, mid+1, end);

            return node;
    }

    public static int height(Node node){
        if (node == null)
            return 0;

        int lheight = height(node.left);
        int rheight = height(node.right);

        if (lheight > rheight)
            return lheight + 1;
        else
            return rheight + 1;

    }

    public static  void printByLevels(Node node){
        if (node == null)
            return;

        int h = height(node);
        for (int i = 1; i < h+1; i++) {
            exploreLevel(node, i);
        }
        for (int i = 0; i < traverseByLevel.size(); i++) {
            if (i == traverseByLevel.size()-1)
                System.out.print(traverseByLevel.get(i)+"");
            else
                System.out.print(traverseByLevel.get(i)+", ");
        }

    }
    public static void exploreLevel(Node node, int level){
        if (node == null)
            return;

        if (level == 1)
            traverseByLevel.add(node.val);
        else if (level > 1){
            exploreLevel(node.left, level-1);
            exploreLevel(node.right, level-1);
        }
    }

    public static String BFS(Node node){
        ArrayList<Node> list = new ArrayList<>();
        list.add(node);
        String ans = "";
        for(int i = 0; i < list.size(); i++) {
            Node n = list.get(i);
            ans = ans.concat(n.val + ", ");
            if(n.left != null) {
                list.add(n.left);
            }
            if(n.right != null) {
                list.add(n.right);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()){
            String num=scanner.nextLine();
            numbers.add(Integer.parseInt(num));
        }
        scanner.close();

        Collections.sort(numbers);
        System.out.print('[');
        root = BST.arrToBST(numbers, 0, numbers.size()-1);
//        BST.printByLevels(root);
        String s = BST.BFS(root);
        System.out.print(s.substring(0,s.length()-2));
        System.out.print(']');
    }
}

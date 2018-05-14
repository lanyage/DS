package Register;

/**
 * Created by lanyage on 2018/5/14.
 */

import java.util.LinkedList;

/**
 * 二叉树有非常良好的性质,虽然其是树种的一个特例,但是其可以用来表示一般的树
 */
class Node {
    int data;
    Node parent;
    Node left;
    Node right;
    Node root;

    public Node() {
        this(null, null, null, null);
    }

    public Node(int data) {
        this.data = data;
    }

    public Node(Node parent, Node left, Node right, Node root) {
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.root = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                ", root=" + root +
                '}';
    }
}

public class BinaryTree {
    private Node genesis = new Node();

    /**
     * 二叉树的初始化
     * @param data root节点的内容
     * @return
     */
    private Node init(int data) {
        genesis.root = new Node(data);
        genesis.root.parent = genesis;
        return genesis.root;
    }

    public void addLeftNodeAfter(Node parent, Node left) {
        parent.left = left;
        left.parent = parent;
    }

    public void addRightNodeAfter(Node parent, Node right) {
        parent.right = right;
        right.parent = parent;
    }

    /**
     * 递归前序遍历
     * @param root
     */
    public void preOrder(Node root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 迭代前序遍历
     * @param root
     */
    public void preOrderII(Node root) {
        System.out.print("前序迭代:");
        //需要一个辅助的
        LinkedList<Node> stack = new LinkedList<>();
        class Walker {
            private void walk(Node node) {
                while (node != null) {
                    System.out.print(node.data + " ");
                    stack.push(node.right);
                    node = node.left;
                }
            }
        }
        Walker walker = new Walker();
        walker.walk(root);
        while (!stack.isEmpty()) {
            Node curNode = stack.pop();
            if (curNode != null) {
                walker.walk(curNode);
            }
        }
    }

    /**
     * 递归中序遍历
     * @param root
     */
    public void inOrder(Node root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    /**
     * 迭代中序遍历
     * @param root
     */
    public void inOrderII(Node root) {
        System.out.print("中序迭代:");
        LinkedList<Node> stack = new LinkedList<>();
        class Walker {
            private void walk(Node node) {
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }
        Walker walker = new Walker();
        walker.walk(root);
        while (!stack.isEmpty()) {
            Node left = stack.pop();
            System.out.print(left.data + " ");
            if (left.right != null) {
                walker.walk(left.right);
            }
        }
    }

    /**
     * 递归后序遍历
     * @param root
     */
    public void suffixOrder(Node root) {
        if (root == null) return;
        suffixOrder(root.left);
        suffixOrder(root.right);
        System.out.print(root.data + " ");
    }

    /**
     * 迭代后续遍历
     * @param root
     */
    public void suffixOrderII(Node root) {
        System.out.print("后序迭代:");
        LinkedList<Node> stack = new LinkedList<>();
        class Walker {
            private void walk(Node node) {
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }
        Walker walker = new Walker();
        walker.walk(root);
        while (!stack.isEmpty()) {
            Node currNode = stack.pop();
            System.out.print(currNode.data + " ");
            Node parent = currNode.parent;
            if (parent.right != null && currNode == parent.left) {
                walker.walk(parent.right);
            }
        }
    }

    public void level(Node root) {
        System.out.print("层次遍历:");
        LinkedList<Node> queue = new LinkedList<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            Node currNode = queue.pollLast();
            System.out.print(currNode.data + " ");
            Node currLeft = currNode.left;
            Node currRight = currNode.right;

            if (currLeft != null) {
                queue.push(currLeft);
            }
            if (currRight != null) {
                queue.push(currRight);
            }
        }
    }

    public static void main(String[] args) {
        /** 新建树,会有一个创世节点*/
        BinaryTree binaryTree = new BinaryTree();
        /** 初始化一个根节点 */
        Node root = binaryTree.init(0);
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);
        binaryTree.addLeftNodeAfter(three, seven);
        binaryTree.addRightNodeAfter(three, eight);
        binaryTree.addLeftNodeAfter(one, three);
        binaryTree.addRightNodeAfter(one, four);
        binaryTree.addLeftNodeAfter(two, five);
        binaryTree.addRightNodeAfter(two, six);
        binaryTree.addLeftNodeAfter(root, one);
        binaryTree.addRightNodeAfter(root, two);

        System.out.print("前序递归:");
        binaryTree.preOrder(root);
        System.out.println();

        binaryTree.preOrderII(root);
        System.out.println();

        System.out.print("中序递归:");
        binaryTree.inOrder(root);
        System.out.println();

        binaryTree.inOrderII(root);
        System.out.println();

        System.out.print("后序递归:");
        binaryTree.suffixOrder(root);
        System.out.println();

        binaryTree.suffixOrderII(root);
        System.out.println();

        binaryTree.level(root);
        System.out.println();
    }
}

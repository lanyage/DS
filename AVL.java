package Register;

import java.util.LinkedList;

/**
 * 二叉搜索树节点
 *
 * @author lanyage 2018/05/17
 */
class BTSNode {
    private int data;//数据
    private BTSNode parent;//父节点
    private BTSNode left;//左孩子
    private BTSNode right;//右孩子
    private boolean isRoot;//是不是根节点

    public BTSNode() {
    }

    public BTSNode(int data) {
        this();
        this.data = data;
    }

    public BTSNode(int data, boolean isRoot) {
        this(data);
        this.isRoot = isRoot;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BTSNode getParent() {
        return parent;
    }

    public void setParent(BTSNode parent) {
        this.parent = parent;
    }

    public BTSNode getLeft() {
        return left;
    }

    public void setLeft(BTSNode left) {
        this.left = left;
    }

    public BTSNode getRight() {
        return right;
    }

    public void setRight(BTSNode right) {
        this.right = right;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }

    /**
     * 树的高度
     */
    public int getHeight() {
        BTSNode left = this.left;
        BTSNode right = this.right;
        if (left == null && right == null) {//如果树的左右孩子都为null
            return 0;
        } else if (left != null && right == null) {//如果左孩子不为空,那么就用左孩子的高度+1
            return left.getHeight() + 1;
        } else if (left == null && right != null) {//如果右孩子不为空,那么就用右孩子的高度+1
            return right.getHeight() + 1;
        } else {//左右孩子都不为空,那么就用两者高度的最大值+1
            return Math.max(left.getHeight() + 1, right.getHeight() + 1);
        }
    }

    /**
     * 将父亲节点转接给其他节点
     */
    public void giveParentTo(BTSNode node) {
        BTSNode parent = this.getParent();
        node.setParent(parent);
    }

    public boolean isLeft() {
        return this == this.getParent().getLeft();
    }

    public boolean isRight() {
        return this == this.getParent().getRight();
    }

    @Override
    public String toString() {
        return "{" +
                data +
                '}';
    }
}

class AVL {
    /**
     * 创世节点
     */
    private BTSNode genesis = new BTSNode();
    /**
     * 根节点
     */
    private BTSNode root;
    /**
     * 树中节点的个数
     */
    private int size;

    /**
     * 初始化树
     */
    public void init(int rootLoad) {
        root = new BTSNode(rootLoad, true);
        root.setParent(genesis);
        size++;
    }

    public BTSNode getRoot() {
        return root;
    }

    public void setRoot(BTSNode root) {
        this.root = root;
    }

    public int getSize() {
        return size;
    }

    /**
     * 获取树的高度,如果为空树就返回-1
     */
    private int getHeight(BTSNode node) {
        int height = node == null ? -1 : node.getHeight();
        return height;
    }

    /**
     * 平衡因子,等于两边子树的高度相减
     */
    public int balanceFactor(BTSNode node) {
        int bf;
        BTSNode left = node.getLeft();
        BTSNode right = node.getRight();
        bf = getHeight(left) - getHeight(right);
        return bf;
    }

    /**
     * 平衡二叉树的搜索, 如果命中就返回该节点,否则返回该节点的父节点
     */
    public BTSNode search(int key) {
        BTSNode hot = null;//hot表示查询不到节点后返回的哨兵节点的父节点
        BTSNode current = root;
        while (current != null) {
            int currentKey = current.getData();
            if (key == currentKey) {
                return current;
            }
            hot = current;
            current = key < currentKey ? current.getLeft() : current.getRight();
        }
        return hot;
    }

    /**
     * 插入新节点,插入完成后要根据树的结构进行重构,遍历hot的所有的父节点,看是否有存在不平衡的情况,如果有就用3+4重构
     */
    public BTSNode insert(int key) {
        BTSNode x = search(key);
        int xData = x.getData();
        BTSNode add = null;
        if (key != xData) {
            if (key < xData) {
                add = new BTSNode(key);
                add.setParent(x);
                x.setLeft(add);
                for (BTSNode p = x.getParent(); p != genesis; p = p.getParent()) {
                    if (!avlBalanced(p)) {
                        reBalance(p);
                        break;
                    }
                }
            } else {
                add = new BTSNode(key);
                add.setParent(x);
                x.setRight(add);
                for (BTSNode g = x.getParent(); g != genesis; g = g.getParent()) {
                    if (!avlBalanced(g)) {
                        reBalance(g);
                        break;
                    }
                }
            }
            size++;
        }
        return add == null ? null : add;
    }

    public void remove(int key) {
        BTSNode x = search(key);
        BTSNode hot = null;
        if (key == x.getData()) {
            hot = removeHandler(x);
        }
        if (hot == null)
            return;
        for (BTSNode p = hot; p != genesis; p = p.getParent()) {
            if (!avlBalanced(p)) {
                reBalance(p);
            }
        }
    }

    private BTSNode removeHandler(BTSNode node) {
        BTSNode l = node.getLeft();
        BTSNode r = node.getRight();
        BTSNode p = node.getParent();
        if (l == null && r == null) {
            if (node.isLeft()) {
                p.setLeft(null);
            } else {
                p.setRight(null);
            }
            if (node == getRoot())
                this.setRoot(null);
        } else if (l != null && r == null) {
            if (node == getRoot())
                this.setRoot(l);
            else {
                if (node.isLeft()) {
                    p.setLeft(l);
                } else {
                    p.setRight(l);
                }
            }
            l.setParent(p);
        } else if (l == null && r != null) {
            if (node == getRoot())
                this.setRoot(r);
            else {
                if (node.isRight()) {
                    p.setRight(r);
                } else {
                    p.setLeft(r);
                }
            }
            r.setParent(p);
        } else {
            BTSNode curr = r;
            BTSNode remove = null;
            while (curr != null) {
                remove = curr;
                curr = curr.getLeft();
            }
            node.setData(remove.getData());
            removeHandler(remove);
        }
        return p;
    }

    /**
     * 二叉树的中序遍历
     */
    public void inOrderII(BTSNode root) {
        System.out.print("中序迭代:");
        LinkedList<BTSNode> stack = new LinkedList<>();
        class Walker {
            private void walk(BTSNode node) {
                while (node != null) {
                    stack.push(node);
                    node = node.getLeft();
                }
            }
        }
        Walker walker = new Walker();
        walker.walk(root);
        while (!stack.isEmpty()) {
            BTSNode left = stack.pop();
            System.out.print(left.getData() + " ");
            if (left.getRight() != null) {
                walker.walk(left.getRight());
            }
        }
    }

    public void level(BTSNode root) {
        System.out.print("层次遍历:");
        LinkedList<BTSNode> queue = new LinkedList<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            BTSNode currNode = queue.pollLast();
            if(currNode == null)
                break;
            System.out.print(currNode.getData() + " ");
            BTSNode currLeft = currNode.getLeft();
            BTSNode currRight = currNode.getRight();

            if (currLeft != null) {
                queue.push(currLeft);
            }
            if (currRight != null) {
                queue.push(currRight);
            }
        }
    }

    /**
     * 重构
     */
    public void reBalance(BTSNode v3) {
        rotate(v3);
    }

    /**
     * 3 + 4重构
     */
    public void rotate(BTSNode v3) {
        if (balanceFactor(v3) > 0) {
            BTSNode v2 = v3.getLeft();
            if (balanceFactor(v2) > 0) {
                BTSNode t3 = v2.getRight();
                checkRoot(v2, v3);
                bindLeft(v3, t3);
                bindRight(v2, v3);
            } else {
                BTSNode v1 = v2.getRight();
                BTSNode t2 = v1.getLeft();
                BTSNode t3 = v1.getRight();
                checkRoot(v1, v3);
                bindRight(v2, t2);
                bindLeft(v3, t3);
                bindLeft(v1, v2);
                bindRight(v1, v3);
            }
        } else {
            BTSNode v2 = v3.getRight();
            if (balanceFactor(v2) < 0) {
                BTSNode t2 = v2.getLeft();
                checkRoot(v2, v3);
                bindRight(v3, t2);
                bindLeft(v2, v3);
            } else {
                BTSNode v1 = v2.getLeft();
                BTSNode t2 = v1.getLeft();
                BTSNode t3 = v1.getRight();
                checkRoot(v1, v3);
                bindLeft(v2, t3);
                bindRight(v3, t2);
                bindLeft(v1, v3);
                bindRight(v1, v2);
            }
        }
    }

    /**
     * 转交root根节点
     */
    private void checkRoot(BTSNode v2, BTSNode v3) {
        BTSNode parent = v3.getParent();
        v3.giveParentTo(v2);
        if (v3.isRoot()) {
            v2.setRoot(true);
            v3.setRoot(false);
            this.setRoot(v2);
        } else {
            if (v3.isLeft()) {
                parent.setLeft(v2);
            } else {
                parent.setRight(v2);
            }
        }
    }

    private void bindLeft(BTSNode parent, BTSNode sub) {
        parent.setLeft(sub);
        if (sub != null) {
            sub.setParent(parent);
        }
    }

    private void bindRight(BTSNode parent, BTSNode sub) {
        parent.setRight(sub);
        if (sub != null) {
            sub.setParent(parent);
        }
    }

    /**
     * 判断是不是平衡二叉搜索树
     */
    public boolean avlBalanced(BTSNode node) {
        int bf = balanceFactor(node);
        return bf > -2 && bf < 2;
    }

    public static void main(String[] args) {
        /////////////////////
        //平衡二叉树的初始化
        /////////////////////
        AVL avl = new AVL();
        avl.init(1);
        for (int i = 2; i < 20; i++) {
            avl.insert(i);
        }
        //////////////////////
        //测试搜索耗时
        /////////////////////
//        long start = System.currentTimeMillis();
//        avl.search(10);
//        long end = System.currentTimeMillis();
//        System.out.println("Time Consuming:" + (end - start) + " ms");
        /////////////////////
        //测试删除
        ////////////////////
        avl.remove(1);
        avl.remove(2);
        avl.remove(3);
        avl.remove(4);
        avl.remove(5);
        avl.remove(6);
        avl.remove(7);
        avl.remove(8);
        avl.remove(9);
        /////////////////
        //进行层次遍历
        ////////////////
        avl.inOrderII(avl.getRoot());
        System.out.println();
        avl.level(avl.getRoot());
        System.out.println();
    }
}

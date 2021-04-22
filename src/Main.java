import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int b[] = { 10, 12, 6, 8, 4, 7, 9};

        // добавление элементов массива в дерево
        /*for (int i = 1; i < b.length; i++)
            tree.insertNode(b[i]);
        */
        // вывод содержимого дерева
        int value = in.nextInt();
        BinaryTree tree = new BinaryTree(value);
        tree.printBinaryTree(0);
        value = in.nextInt();
        while (value != 0) {
            tree.insertNode(value);
            tree = tree.getRoot();
            tree.printBinaryTree(0);
            value = in.nextInt();
        }
    }
}

class BinaryTree {

    int value = 0;
    BinaryTree parent = null;
    BinaryTree lchild = null; // левый потомок
    BinaryTree rchild = null; // правый потомок
    int length = 0;
    int rightLength = 0;
    int leftLength = 0;

    public BinaryTree(int value) {
        this.value = value;
        this.lchild = null;
        this.rchild = null;
        this.parent = null;
        this.length = 1;
        this.leftLength = 0;
        this.rightLength = 0;
    }

    public BinaryTree insertNode(int newValue) {

        if (value > newValue) {
            if (lchild == null) {
                lchild = new BinaryTree(newValue);
                lchild.parent = this;
                getRoot().setLengths();
                getRoot().correctLength();
            }
            else
                lchild.insertNode(newValue);

            return getRoot();
        }
        else if (value < newValue) {
            if (rchild == null) {
                rchild = new BinaryTree(newValue);
                rchild.parent = this;
                getRoot().setLengths();
                getRoot().correctLength();
            }
            else
                rchild.insertNode(newValue);
        }
        return null;
    }

    public BinaryTree getRoot() {
        if (parent != null)
            return parent.getRoot();
        return this;
    }

    public void setLengths() {
        if (rchild != null) {
            rchild.setLengths();
            rightLength = rchild.length;
        }
        else
            rightLength = 0;
        if (lchild != null) {
            lchild.setLengths();
            leftLength = lchild.length;
        }
        else
            leftLength = 0;
        length = Math.max(rightLength, leftLength) + 1;
    }

    public void correctLength() {
        if (rightLength - leftLength >= 2) {
            int rightLeftLength = rchild.leftLength;
            int rightRightLength = rchild.rightLength;
            if (rightLeftLength <= rightRightLength)
                smallLeftRotation();
            else
                bigLeftRotation();
            getRoot().setLengths();
        }
        else if (leftLength - rightLength >= 2) {
            int leftLeftLength = lchild.leftLength;
            int leftRightLength = lchild.rightLength;
            if (leftRightLength <= leftLeftLength)
                smallRightRotation();
            else
                bigRightRotation();
            getRoot().setLengths();
        }
        if (rchild != null)
            rchild.correctLength();
        if (lchild != null)
            lchild.correctLength();
    }

    public void smallLeftRotation() {
        BinaryTree root = parent;
        BinaryTree right = rchild;
        BinaryTree rightLeft = rchild.lchild;
        BinaryTree left = lchild;
        right.parent = root;
        if (root != null)
            root.changeChild(this, right);
        right.lchild = this;
        this.parent = right;
        this.rchild = rightLeft;
        if (rightLeft != null)
            rightLeft.parent = this;
    }

    public void smallRightRotation() {
        BinaryTree root = parent;
        BinaryTree right = rchild;
        BinaryTree leftRight = lchild.rchild;
        BinaryTree left = lchild;
        left.parent = root;
        if (root != null)
            root.changeChild(this, left);
        left.rchild = this;
        this.parent = left;
        this.lchild = leftRight;
        if (leftRight != null)
            leftRight.parent = this;
    }

    public void bigLeftRotation() {
        BinaryTree root = parent;
        BinaryTree right = rchild;
        BinaryTree left = lchild;
        right.smallRightRotation();
        smallLeftRotation();
    }

    public void bigRightRotation() {
        BinaryTree root = parent;
        BinaryTree right = rchild;
        BinaryTree left = lchild;
        left.smallLeftRotation();
        smallRightRotation();
    }

    public void changeChild(BinaryTree oldChild, BinaryTree newChild) {
        if (rchild == oldChild) {
            rchild = newChild;
        }
        if (lchild == oldChild) {
            lchild = newChild;
        }
    }

    public void printBinaryTree(int level) {
        if (rchild != null)
            rchild.printBinaryTree(level + 1);
        for (int i = 0; i < level; i++)
            System.out.print("   ");
        System.out.println(value + "(" + length + ")");
        if (lchild != null)
            lchild.printBinaryTree(level + 1);
    }
}

public class BinaryTree {

    int value;
    BinaryTree lchild; // левый потомок
    BinaryTree rchild; // правый потомок

    public BinaryTree(int value) {
        this.value = value;
        this.lchild = null;
        this.rchild = null;
    }

    /* метод вставки элементов в дерево
          node - ссылка на текущий узел дерева
          для создания дерева будем передавать null в качестве node
          valueNode - значение, которое добавляем в дерево
       */
    public BinaryTree insertNode(BinaryTree node, int targetValue) {
        // если дерево пустое
        if (node == null) {
            node = new BinaryTree(targetValue);
            return node;
        }
             /*если значение в текущем узле больше valueNode, то переходим в
               левое поддерево*/
        if (node.value > targetValue) {
            //если левого потомка нет, то создаем его с значением valueNode
            if (node.lchild == null)
                return node.lchild = new BinaryTree(targetValue);
                // если левый потомок есть, то переходим ниже в левое поддерево
            else
                return insertNode(node.lchild, targetValue);
        }
             /*если значение в текущем узле меньше вставляемого,
               то переходим в правое поддерево*/
        else if (node.value < targetValue) {
            //если правого потомка нет, то создаем его с значением valueNode
            if (node.rchild == null)
                return node.rchild = new BinaryTree(targetValue);
                // если правый потомок есть, то переходим ниже в правое поддерево
            else
                return insertNode(node.rchild, targetValue);
        }
        // Если ключи одинаковые, вставки не происходит
        return null;
    }

    // метод вывода дерева на экран
    public void printBinaryTree(BinaryTree node, int level) {
        if (node != null) {
            printBinaryTree(node.lchild, level + 1);
            for (int i = 0; i < level; i++)
                System.out.print("   ");//чем ниже уровень, тем отступ больше
            System.out.println(node.value);
            printBinaryTree(node.rchild, level + 1);
        }
    }

    public static void main(String[] args) {
        // массив значений для добавления в дерево
        int b[] = { 10, 25, 20, 6, 4, 8, 50, 30, 6 };
        BinaryTree tree = new BinaryTree(b[0]);
        // добавление элементов массива в дерево
        for (int i = 1; i < b.length; i++)
            tree.insertNode(tree, b[i]);
        // вывод содержимого дерева
        tree.printBinaryTree(tree, 0);
    }
}

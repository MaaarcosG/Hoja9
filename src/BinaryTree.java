
//import javax.xml.soap.Node;
//import javax.xml.soap.Node;

/**
 * @author Marcos Gutierrez				17909
 * @author Raul Monzon					17014
 * Clase que implementa el BinaryTree
 * @link https://algorithms.tutorialhorizon.com/binary-search-tree-complete-implementation/
 * @link https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
 */
public class BinaryTree<E>
{
	
    protected E valor;
    protected BinaryTree<E> parent, left, right;
    
    //Constructor del arbol
    public BinaryTree()
    {
        valor = null;
        parent = null;
        left = null;
        right = null;
    }
    
    public BinaryTree(E valor)
    {
        this.valor = valor;
        setLeft(new BinaryTree<>());
        setRight(new BinaryTree<>());
    }
    
    public BinaryTree(E valor, BinaryTree<E> left, BinaryTree<E> right)
    {
        this(valor);
        if(left != null) {
        	setLeft(left); 
        }
        if(right != null) {
        	setRight(right);
        }
    }
    
    public boolean hijoIzq()
    {
        if(parent == null)
        {
        	return false;
        }
        else {
        	BinaryTree left = parent.left();
            return this == left;
        }
        
    }
    
    
    //Esta función nos ayuda a saber si el nodo tiene hijos
    public boolean conHijos()
    {
    	if ((left() != null) || (right != null)) {
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    //Comprueba si esta vacio el arbol
    protected boolean isEmpty()
    {
        return valor == null;
    }
    
    //girar el arbol hacia la derecha sobre el nodo que lo llamo
    protected BinaryTree<E> girarDer()
    {
        BinaryTree<E> raiz = left();
        
        if(raiz == null) {
        	return this;
        }
        
        BinaryTree<E> padreTemp = this.parent();
        boolean izquierdo = this.hijoIzq();
        setLeft(raiz.right());
        raiz.setRight(this);
        
        if(padreTemp != null)
        {
            if(izquierdo) { 
            	padreTemp.setLeft(raiz);
            }
            else {
            	padreTemp.setRight(raiz);
            }
        }
        else {
        	raiz.setParent(null);
        }
        return raiz;
    }
    
    //girar el arbol hacia la izquierda sobre el nodo que lo llamo
    protected BinaryTree<E> rotateLeft()
    {
        BinaryTree<E> raiz = this.right();
        
        if(raiz == null) {
        	return this;
        }
        
        BinaryTree<E> padreTemp = this.parent();
        boolean izquierdo = this.hijoIzq();
        setRight(raiz.left());
        raiz.setLeft(this);
        
        if(padreTemp != null)
        {
            if(izquierdo) padreTemp.setLeft(raiz);
            else padreTemp.setRight(raiz);
        }
        else {
        	raiz.setParent(null);
        }
        return raiz;
    }
    
    //gets
    public BinaryTree<E> left() { 
    	return left; 
    }
    
    public BinaryTree<E> right() { 
    	return right;
    }
    
    public BinaryTree<E> parent() {
    	return parent; 
    }
    
    public E valor() { 
    	return valor; 
    }
    
    //sets
    public void setLeft(BinaryTree<E> newLeft) 
    {
        left = newLeft;
        newLeft.setParent(this);
    }

    public void setvalor(E newvalor) { 
    	valor = newvalor; 
    }
    
    public void setRight(BinaryTree<E> newRight)
    {
        right = newRight;
        newRight.setParent(this);
    }
    
    protected void setParent(BinaryTree<E> newParent) 
    {
        parent = newParent;
    }
}
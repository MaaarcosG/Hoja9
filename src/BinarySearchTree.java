import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author Marcos Gutierrez				17909
 * @author Raul Monzon					17014
 * Clase principal
 */
public class BinarySearchTree<E extends Comparable<E>>
{
    protected BinaryTree<E> root;
    protected BinaryTree<E> nodo;
    protected int size;
    
    public BinarySearchTree()
    {
        root = new BinaryTree<>();
        size = 0;
    }
    
    public boolean isEmpty() 
    {
        return root.isEmpty();
    }

    public void clear() 
    {
        root = new BinaryTree<>();
        size = 0;
    }
    
    public int size() 
    {
       return size;
    }
    
    public E add(E value)
    {
        E nodo_viejo = null;
        
        if (!(root.isEmpty()))
        {
        	
        	// find node where provided key should go
            BinaryTree<E> nodoE = encontarNodo(value, root);
            // save found node as action-node for use in other algorithms
            nodo = nodoE;
            
            // key already in tree, update value
            if(!nodoE.isEmpty())
            {
                //old = nodoE.value().getValue();
                //nodoE.setValue(e);
          	  nodo_viejo = nodoE.valor();
                nodoE.setvalor(value);
            }
            else
            {
//                nodoE.setValue(e);
                nodoE.setvalor(value);
                // add empty children nodes if needed
                if (nodoE.left()== null)
                    nodoE.setLeft(new BinaryTree<>());
                if (nodoE.right()== null)
                    nodoE.setRight(new BinaryTree<>());
                size++;
            }
            
          
        }
        else
        {
        	root = new BinaryTree<>(value);
            nodo = root;
            size++;                  	
        }
        
        return nodo_viejo;
    }
    
    public boolean vacio(E contenido)
    {
    	if (get(contenido) == null) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public E get(E dato)
    {
        if(dato == null) {
        	if (root.isEmpty()) {
        		return null;
        	}
        }
        BinaryTree<E> node = encontarNodo(dato, root);
        if(node.isEmpty()) { 
        	return null;
        }
        nodo = node;
        return node.valor();
    }

    public E remove(E value) 
    {
        if(value == null) return null;
        BinaryTree<E> nodoE = encontarNodo(value, root);
        // key is not in tree
        if(nodoE.isEmpty()) return null;
        E temp = nodoE.valor();
        // handle removing the last node
        if(size == 1)
        {
            clear();
            return temp;
        }
        else
        {
            if(nodoE.left().isEmpty()) 
                removeExternal(nodoE.left());
            else if(nodoE.right().isEmpty()) 
                removeExternal(nodoE.right());
            else
            {
                // find replacement node
                BinaryTree<E> rep = nodoE.right();
                while(rep.left().conHijos()) rep = rep.left();
                // move rep value to nodoE
                nodoE.setvalor(rep.valor());
                // remove external node that is left child of replacement node
                removeExternal(rep.left());
                // update action-node to point to nodoE parent
                nodo = nodoE.parent();
            }
        }
        size--;
        return temp;
    }

    public List<E> valuesInOrder()
    {
        List<E> list = new ArrayList<>();
        if(isEmpty()) return list;
        inOrderRecursive(root, list);
        return list;
    }
    
    /**
     * Helper method used by add, get and remove methods.
     * @param node
     * @param value
     * @return 
     */
    protected BinaryTree<E> encontarNodo(E value, BinaryTree<E> node)
    {
        if(node.isEmpty()) return node;
        else
        {
           // compare current node key with provided key
           int c =  node.valor().compareTo(value);
           if (c == 0 )return node; // we found the node
           else if (c > 0) // node key > key
           {
               // search left
               return encontarNodo(value, node.left());
           }
           else 
           {
               // search right
              return encontarNodo(value, node.right()); 
           }
        }
    }
    
    /**
     * Remove external node v and its parent p, and replace p with v's sibling.
     * @param v 
     */
    protected void removeExternal(BinaryTree<E> v)
    {
        // node is not external
        if(!v.isEmpty()) return;
        // grab v's sibling
        BinaryTree<E> s = null;
        // grab v's parent
        BinaryTree<E> p = v.parent();
        if(v.hijoIzq()) s = p.right();
        else s = p.left();
        if(p.parent() != null)
        {
            if(p.hijoIzq()) p.parent().setLeft(s);
            else p.parent().setRight(s);
        }
        else
        {
            root = s;
            root.setParent(null);
        }
    }
    
    protected void inOrderRecursive(BinaryTree<E> node, List<E> list)
    {
        if(node.left() != null && !node.left().isEmpty()) 
            inOrderRecursive(node.left(), list);
        list.add(node.valor());
        if(node.right() != null && !node.right().isEmpty()) 
            inOrderRecursive(node.right(), list);
    }
}

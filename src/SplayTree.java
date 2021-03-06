/**
 * @author Marcos Gutierrez				17909
 * @author Raul Monzon					17014
 * Clase principal
 */
public class SplayTree<E extends Comparable<E>> extends BinarySearchTree<E>
{
    public E add(E value)
    {
        E old = super.add(value);
        splay(actionNode);
        return old;
    }
    
    public E remove(E element)
    {
        E old = super.remove(element);
        if(actionNode != null) splay(actionNode);
        return old;
    }
    
    public boolean vacio(E element)
    {
        boolean b = super.vacio(element);
        if(b) splay(actionNode);
        return b;
    }
    
    public E get(E element)
    {
        E e = super.get(element);
        if(e != null) splay(actionNode);
        return e;
    }
    
    /**
     * Performs a sequence of rotations until the provided node is the root of
     * the tree.
     * @param node Node to splay.
     * pre: node is not the root of the tree.
     * post: node is the root of the tree.
     */
    protected void splay(BinaryTree<E> node)
    {
        if(node.equals(root) || node == null) return;
        // get the parent and grandparent of this node
        BinaryTree<E> gp = null;
        BinaryTree<E> p = null;
        boolean hasGp = false;
        while(node.parent() != null)
        {
            p = node.parent();
            if(p != null) gp = p.parent();
            hasGp = gp != null;
            // <editor-fold defaultstate="collapsed" desc="Node is left child">
            if (node.hijoIzq()) {
                if (hasGp) // node has a grandparent
                {
                    if (p.hijoIzq()) // left-left
                    {
                        gp.girarDer();
                        p.girarDer();
                    } else // left-right
                    {
                        p.girarDer();
                        gp.rotateLeft();
                    }
                } else // left; node has only parent.
                {                    
                    p.girarDer();
                }
            } // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="Node is right child">
            else // node is right child
            {
                if (hasGp) // node has a grandparent
                {
                    if (p.hijoIzq()) // right-left
                    {
                        p.rotateLeft();
                        gp.girarDer();
                    } else // right-right
                    {
                        gp.rotateLeft();
                        p.rotateLeft();
                    }
                } else // right; node has only parent.
                {                    
                    p.rotateLeft();
                }
            }

// </editor-fold>
        }
        // the splayed node is now the root of the tree
        root = node;
    }
}

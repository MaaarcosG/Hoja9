
//import javax.xml.soap.Node;
//import javax.xml.soap.Node;

/**
 * @author Marcos Gutierrez				17909
 * @author Raul Monzon					17014
 * Clase que implementa el BinaryTree
 * @link https://algorithms.tutorialhorizon.com/binary-search-tree-complete-implementation/
 * @link https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
 */
public class BinaryTree<K extends Comparable<K>, V> {
	/*le damos valor al nodo*/
	public Node<K,V> root;
	
	/*Constructor de la clase BinaryTree*/
	public BinaryTree() {
		this.root = null;
	}
	
	/**
	 * @param key
	 * @return
	 * Busca si hay una llave dentro del arbol, si encuentre de vuelve valor de llave
	 */
	public V find(K key) {
		Node<K,V> data = root;
		/*Ciclo para recorrer lso datos de la raiz*/
		while(data != null) {
			/*Compara las palabras con la llave*/
			int comparation = data.datos.compareTo(key);
			if(comparation == 0) {
				return data.datos.getValue();
			} else if(comparation > 0) {
				/*datos de la */
				data = data.left;
			} else {
				data = data.right;
			}
		}
		return null;
	}
	
	/**
	 * Agreaga nuevos vertices al arbol
	 * @param key
	 * @param value
	 */
	public void insert(K key, V value) {
		/*Nuevos nodos para la evaluacion*/
		Node<K,V> nNode = new Node<K,V>(key, value);
		if(root == null) {
			root = nNode;
			return;
		}
		
		Node<K,V> data = root;
		Node<K,V> parent = null;
		/*Ciclo para recorrer el arbol*/
		while(true) {
			parent = data;
			int comparation = data.datos.compareTo(key);
			if(comparation > 0) {
				data = data.left;
				if(data == null) {
					parent.left = nNode;
					return;
				}
			} else {
				data = data.right;
				if(data == null) {
					parent.right = nNode;
					return;
				}
			}
		}
	}
	
	/**
	 * Metodo para recorrer el arbol binario
	 * @param root
	 */
	public void display(Node<K,V> root) {
		if(root != null) {
			display(root.left);
			System.out.print(root.datos.toString());
			display(root.right);
		}
	}
	
/*Clase Nodos modificada para la asociacion entre palabras
 * @param <K>
 * @param <V>
 */
class Node<K extends Comparable<K>,V>{
	Association<K,V> datos;
	Node<K,V> left;
	Node<K,V> right;
	
	/*Clase para la asociacion*/
	public Node(K key, V value) {
		datos = new Association<K,V>(key, value);
		left = null;
		right = null;
	}
}
}

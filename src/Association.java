import java.util.Map;
/**
 * @author Marcos Gutierrez				17909
 * @author Raul Monzon					17014
 * Clase Association que se parece a un Diccionario
 */
public class Association<K extends Comparable<K>, V> implements Map.Entry<K,V> {
	/*the inmutuable key*/
	protected K key;
	/*the mutuable value */
	protected V value;
	
	/**
	 * @param k
	 * @param v
	 * Constructor de la clase
	 */
	public Association(K k, V v) {
		k = key;
		v = value;
	}
	
	/**
	 * @return key
	 */
	@Override
	public K getKey() {
		/*retorna el valor de la llave*/
		return key;
	}

	/**
	 * @return value
	 */
	@Override
	public V getValue() {
		/*retorna el valor de value*/
		return value;
	}

	/**
	 * @param v
	 * Le da un valor nuevo al valor
	 */
	@Override
	public V setValue(V v) {
		V oldvalue = value;
		value = v;
		return oldvalue;
	}
	
	/**
	 * @param o
	 * @return o.getKey();
	 */
	public int compareTo(K key) {
		return key.compareTo(key);
	}
}

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
		assert (key != null);
		k = key;
		v = value;
	}
	
	 /**
     * @param k
     * 
     */
    public Association(K key)
    {
        this(key,null);
    }
    
    /**
     * @param otra asociasion.
     * @return devuelve true si las key son iguales.
     */
    public boolean equals(Object other)
    {
        Association otherAssoc = (Association)other;
        return getKey().equals(otherAssoc.getKey());
    }
    
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append(" - "+getKey()+": "+getValue()+"\n");
        return s.toString();
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
	 * @param key es la llave de la asociacion
	 * @return devuelve mayor a cero si es mas grande, 0 si son iguales, y menor a cero si es menor
	 */
	public int compareTo(K key) {
		return this.key.compareTo(key);
	}
}

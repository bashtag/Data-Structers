import java.util.Map;

/*
 * T is the type of properties
 */
public class Vertex<T> {
	/* defaults */
	private static final double	DEFAULT_W = 1.0;

	private int	id;
	private String	label;
	private double	weight;

	/* for Vertex properties */
	private Map<String, T>	properties;


	/**
	 * No Parameter Constructor
	 */
	public	Vertex() {}

	/**
	 * Copy Constructor
	 * @param v
	 */
	public	Vertex(Vertex<T> v) {
		this(v.id, v.label, v.weight);
	}

	/**
	 * Construct a weighted Vertex.
	 * @param id
	 * @param label
	 * @param weight
	 */
	public	Vertex(int id, String label, double weight) {
		this.id = id;
		this.label = label;
		this.weight = weight;
	}

	/**
	 * Construct a Vertex with an id and a label.
	 * Set the weight to 1.0;
	 * @param id
	 * @param labeel
	 */
	public	Vertex(int id, String label) {
		this(id, label, DEFAULT_W);
	}


	/**
	 * toString override
	 * write the id of the vertex
	 */
	@Override
	public String	toString() {
		return (Integer.toString(this.id));
	}

	/**
	 * Get the id
	 * @return id
	 */
	public int getId() {
		return (id);
	}

	/**
	 * Get label
	 * @return label
	 */
	public String getLabel() {
		return (label);
	}

	/**
	 * Set label
	 * @param label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Get weight
	 * @return
	 */
	public double getWeight() {
		return (weight);
	}

	/**
	 * Set weight
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * Get a property with given key
	 * @return a property
	 */
	public T getProperty(String key) {
		return (this.properties.get(key));
	}

	/**
	 * add a property to the map
	 * @param key unique key of the property
	 * @param value the value of this property
	 */
	public void setProperty(String key, T value) {
		this.properties.put(key, value);
	}
}

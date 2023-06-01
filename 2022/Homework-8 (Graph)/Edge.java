/**
 * T is the type of properties of vertexes
 */
public class Edge<T> implements Comparable<Edge<T>> {
	/* defaults */
	private static final double	DEFAULT_W = 1.0;

	/* the destination vertex for an edge */
	private Vertex<T>	dest;
	/* the source vertex of an edge */
	private Vertex<T>	source;
	/* the weight */
	private double	weight;

	/**
	 * Constructs an Edge from source to dest.
	 * Sets the weight to 1.0.
	 * 
	 * @param source
	 * @param dest
	 */
	public	Edge(Vertex<T> source, Vertex<T> dest) {
		this(source, dest, DEFAULT_W);
	}

	/**
	 * Constructs an Edge from source to dest.
	 * Sets the weight to w.
	 * @param source
	 * @param dest
	 * @param w
	 */
	public	Edge(Vertex<T> source, Vertex<T> dest, double w) {
		this.source = source;
		this.dest = dest;
		this.weight = w;
	}

	/**
	 * Get the destination vertex
	 * @return the destination vertex
	 */
	public Vertex<T>	getDest() {
		return (this.dest);
	}

	/**
	 * Get the source vertex
	 * @return the source vertex
	 */
	public Vertex<T>	getSources() {
		return (this.source);
	}

	/**
	 * Get the weight
	 * @return the weight
	 */
	public double	getWeight() {
		return (this.weight);
	}

	/**
	 * Compare weights
	 * @return > 0 if weight of this object is greater than given object's.
	 */
	@Override
	public int compareTo(Edge<T> o) {
		return (Double.compare(this.weight, o.weight));
	}

	/**
	 * Compares two edges for equality.
	 * @return if the edges source and destination vertices are the same.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		if (obj instanceof Edge) {
			Edge<T>	edge = (Edge<T>) obj;
			if (this.dest == edge.dest && this.source == edge.source)
				return (true);
		}
		return (false);
	}

	/**
	 * The hash code depends only on the source and destination
	 * @return the hash code for an edge
	 */
	@Override
	public int hashCode() {
		int	res = 17;
		res = 31 * res + this.dest.hashCode();
		res = 31 * res + this.source.hashCode();
		
		return (res);
	}

	/**
	 * Returns a string representation of the edge
	 * @return a string representation of the edge
	 */
	@Override
	public String toString() {
		return ("(" + this.source + " -> " + this.dest + ")");
	}
}

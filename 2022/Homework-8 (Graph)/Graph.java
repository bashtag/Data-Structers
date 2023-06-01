import	java.util.Iterator;

/**
 * Interface to specify a Graph ADT. A graph is a set of vertices and
	a set of edges. Vertices are represented by integers
	from 0 to n ‐ 1. Edges are ordered pairs of vertices.
	Each implementation of the Graph interface should
	provide a constructor that specifies the number of
	vertices and whether or not the graph is directed.
 * 
 * T is type of properties of vertexes
 */
public interface Graph<T> {
	// Accessor Methods

	/**
	 * Return the number of vertices
	 * @return the number of vertices
	 */
	public int	getNumV();

	/**
	 * Determine whether this is a directed graph
	 * @return true if this is a directed graph
	 */
	public boolean	isDirected();

	/**
	 * Insert a new edge into the graph
	 * @param edge The new edge.
	 */
	public void	insert(Edge<T> edge);

	/**
	 * Determine whether an edge exists.
	 * @param source The source vertex
	 * @param dest The destination vertex
	 * @return true if theere is an edge from source to dest
	 */
	public boolean	isEdge(Vertex<T> source, Vertex<T> dest);

	/**
	 * Get the edge between two vertices.
	 * @param source The source vertex
	 * @param dest The destination vertex
	 * @return The Edge between these two vertices
	 * or null if there is no edge.
	 */
	public Edge<T>	getEdge(Vertex<T> source, Vertex<T> dest);

	/**
	 * Return an iterator to the edges connected to a given vertex.
	 * @param source The source vertex
	 * @return An Iterator<Edge> to the vertices connected to source
	 */
	public Iterator<Edge<T>>	edgeIterator(int source);
}

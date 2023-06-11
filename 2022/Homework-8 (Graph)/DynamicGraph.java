/**
 * For manipulating Graph
 * T is type of properties of vertexes
 */
public interface DynamicGraph<T> extends Graph<T> {
	
	/**
	 * Generate a new vertex by given parameters.
	 * @param label
	 * @param weight
	 * @return the new vertex
	 */
	public Vertex<T>	newVertex(String label, double weight);

	/**
	 * Add the given vertex to the graph
	 * @param newVertex
	 * @return insertion is successful or not
	 */
	public boolean	addVertex(Vertex<T> newVertex);

	/**
	 * Add an edge between the given two vertices in the graph.
	 * @param vertexId1
	 * @param verrtexId2
	 * @param weight
	 * @return insertion is successful or not
	 */
	public boolean	addEdge(int vertexId1, int vertexId2, double weight);

	/**
	 * Remove the edge between the given two vertices.
	 * @param vertexId1
	 * @param vertexId2
	 * @return removing is successful or not
	 */
	public boolean	removeEdge(int vertexId1, int vertexId2);

	/**
	 * Remove the vertex from the graph with respect to the given vertex id.
	 * @param vertexId
	 * @return removing is successful or not
	 */
	public boolean	removeVertex(int vertexId);

	/**
	 * Remove the vertex that have the given label from the graph.
	 * @param label
	 * @return removing is successful or not
	 */
	public boolean	removeVertex(String label);

	/**
	 * Is given index on the graph or not
	 * @param vertex
	 * @return is vertex find
	 */
	public boolean	containsVertex(Vertex<T> vertex);

	/**
	 * Filter the vertices by the given property
	 * @param key
	 * @param filter
	 * @return a subgraph of the graph
	 */
	public DynamicGraph<T>	filterVertices(String key, T filter);

	/**
	 * Generate the adjacency matrix represantation of the graph
	 * @return the matrix
	 */
	public double[][]	exportMatrix();

	/**
	 * Print the graph in adjacency list format.
	 * Format is imported by the method in AbstarctGraph in the book.
	 */
	public void	printGraph();
}
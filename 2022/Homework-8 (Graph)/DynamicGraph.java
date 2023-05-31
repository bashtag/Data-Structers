/**
 * For manipulating Graph
 */
public interface DynamicGraph extends Graph {
	
	/**
	 * Generate a new vertex by given parameters.
	 * @param label
	 * @param weight
	 * @return the new vertex
	 */
	public Vertex	newVertex(String label, double weight);

	/**
	 * Add the given vertex to the graph
	 * @param newVertex
	 * @return insertion is successful or not
	 */
	public boolean	addVertex(Vertex newVertex);

	/**
	 * Add an edge between the given two vertices in the graph.
	 * @param vertexId1
	 * @param verrtexId2
	 * @return insertion is successful or not
	 */
	public boolean	addEdge(int vertexId1, int verrtexId2);

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
	 * Filter the vertices by the given property
	 * @param key
	 * @param filter
	 * @return a subgraph of the graph
	 */
	public DynamicGraph	filterVertices(String key, String filter);

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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * T is a type of vertex properties
 */
public class CustomGraph<T> implements DynamicGraph<T> {
	/* array of vertices */
	private List<Vertex<T>>	vertices;
	/* an array to determine adjacencies */
	private List<List<Edge<T>>>	adjacencyList;
	/* the graph is directed or undirected */
	private boolean	isDirect;

	/**
	 * Constructs the Graph
	 */
	public	CustomGraph(boolean isDirected) {
		this.vertices = new ArrayList<>();
		this.adjacencyList = new ArrayList<>();
		this.isDirect = isDirected;
	}

	/**
	 * Get the number of vertices
	 * @return number of vertices
	 */
	@Override
	public int getNumV() {
		return (this.vertices.size());
	}

	/**
	 * is the graph a directed graph or not
	 * @return true
	 */
	@Override
	public boolean	isDirected() {
		return (this.isDirect);
	}

	/**
	 * insert an edge to the graph
	 */
	@Override
	public void insert(Edge<T> edge) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'insert'");
	}

	@Override
	public boolean isEdge(Vertex<T> source, Vertex<T> dest) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'isEdge'");
	}

	@Override
	public Edge<T> getEdge(Vertex<T> source, Vertex<T> dest) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getEdge'");
	}

	@Override
	public Iterator<Edge<T>> edgeIterator(int source) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'edgeIterator'");
	}

	/**
	 * Creates a new Vertex
	 * @param label Vertex's label
	 * @param label Vertex's weight
	 * @return a Vertex
	 */
	@Override
	public Vertex<T> newVertex(String label, double weight) {
		return (new Vertex<>(vertices.size(), label, weight));
	}

	/**
	 * 
	 */
	@Override
	public boolean addVertex(Vertex<T> newVertex) {
		int id = newVertex.getId();
		
		/* id must be equal the index of adjacency list */
		if (id >= this.getNumV())
			for (int i = this.getNumV(); i <= id; ++i)
				this.adjacencyList.add(new ArrayList<>());;

		vertices.add(newVertex);
		return (true);
	}

	/**
	 * Insert an edge to the graph with source, destination vertices and a weight value.
	 * @param vertexId1 source
	 * @param vertexId2 destination
	 * @param weight
	 * @return this operation is happen or not
	 */
	@Override
	public boolean addEdge(int vertexId1, int vertexId2, double weight) {
		/* parameter check */
		if (vertexId1 >= this.getNumV() ||vertexId2 >= this.getNumV())
			return (false);

		/* getting vertices */
		Vertex<T>	source = this.vertices.get(vertexId1);
		Vertex<T>	dest = this.vertices.get(vertexId2);
		
		/* adding an edge */
		Edge<T>	newEdge = new Edge<>(source, dest, weight);
		this.adjacencyList.get(vertexId1).add(newEdge);

		/* for undirected graph 
		 * Reverse edge
		*/
		if (!this.isDirected()) {
			Edge<T>	reverseEdge = new Edge<>(dest, source, weight);
			this.adjacencyList.get(vertexId2).add(reverseEdge);
		}
		
		return (true);
	}

	/**
	 * Remove an edge from the Graph using source and destination vertices.
	 * @param vertexId1 source
	 * @param vertexId2 destination
	 * @return this operation is happen or not
	 */
	@Override
	public boolean removeEdge(int vertexId1, int vertexId2) {
		/* parameter check */
		if (vertexId1 < 0 || vertexId1 >= this.getNumV() ||
			vertexId2 < 0 || vertexId2 >= this.getNumV())
			return (false);
		
		/* connected edges with vertex that has vertexId */
		List<Edge<T>>	connectedEdges;
		
		/* removing */
		connectedEdges = this.adjacencyList.get(vertexId1);
		connectedEdges.removeIf(e -> e.getDest().getId() == vertexId2);

		/* to remove from an undirected graph using reverse edge */
		if (!this.isDirected()) {
			connectedEdges = this.adjacencyList.get(vertexId2);
			connectedEdges.removeIf(e -> e.getDest().getId() == vertexId1);
		}

		return (true);
	}

	/**
	 * Remove a vertex from the graph using verted id
	 * @param vertexID
	 * @return this operation is happened or not
	 */
	@Override
	public boolean removeVertex(int vertexId) {
		/* parameter check */
		if (vertexId < 0 || vertexId >= this.getNumV() || this.vertices.get(vertexId) == null)
			return (false);

		/* set the vertex to null cause of raising index errors  */
		this.vertices.set(vertexId, null);

		/* Remove outgoing edges from the vertex */
		this.adjacencyList.set(vertexId, null);
		// List<Edge<T>>	connectedEdges = this.adjacencyList.get(vertexId);
		// connectedEdges.removeIf(edge -> edge.getSource().getId() == vertexId);

		/* Remove incoming edges to the vertex */
		for (List<Edge<T>> edges : this.adjacencyList)
			edges.removeIf(edge -> edge.getDest().getId() == vertexId);

		return (true);
	}

	/**
	 * Remove the vertices that have the given label from the graph.
	 * @param label
	 * @param boolean this operation is happened or not
	 */
	@Override
	public boolean removeVertex(String label) {

		Optional<Vertex<T>>	optionalVertex = this.vertices
											.stream()
											.filter(vertex -> vertex.getLabel().equals(label))
											.findFirst();
										
		if (!optionalVertex.isPresent())
			return (false);

		/* Set the vertex to null cause of raising index errors */
		this.vertices.replaceAll(vertex ->  vertex.getLabel().equals(label) ? null : vertex);

		for (List<Edge<T>> edges : this.adjacencyList) {
			/* Remove outgoing edges from the vertex */
			edges.removeIf(edge -> edge.getSource().getLabel().equals(label));
			/* Remove incoming edges to the vertex */
			edges.removeIf(edge -> edge.getDest().getLabel().equals(label));
		}

		return (true);
	}

	@Override
	public DynamicGraph<T> filterVertices(String key, String filter) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'filterVertices'");
	}

	@Override
	public double[][] exportMatrix() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'exportMatrix'");
	}

	@Override
	public void printGraph() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'printGraph'");
	}

}

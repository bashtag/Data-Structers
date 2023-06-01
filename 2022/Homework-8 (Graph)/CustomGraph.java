import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * T is a type of vertex properties
 */
public class CustomGraph<T> implements DynamicGraph<T> {
	/* array of vertices */
	private List<Vertex<T>>	vertices;
	/* an array to determine adjacencies */
	private List<List<Edge<T>>>	adjacencyList;

	/**
	 * Constructs the Graph
	 */
	public	CustomGraph() {
		this.vertices = new ArrayList<>();
		this.adjacencyList = new ArrayList<>();
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
	public boolean isDirected() {
		return (true);
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
	 * 
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
		vertices.add(newVertex);
		adjacencyList.add(new ArrayList<>());
		return (true);
	}

	@Override
	public boolean addEdge(int vertexId1, int vertexId2, double weight) {
		Vertex<T>	source = null, destination = null;
		int	indexOfV1 = 0;

		for (int i = 0; i < vertices.size(); ++i) {
			if (vertices.get(i).getId() == vertexId1) {
				source = vertices.get(i);
				indexOfV1 = i;
			}
			else if (vertices.get(i).getId() == vertexId2)
				destination = vertices.get(i);
		}
		
		if (source == null || destination == null)
			return (false);

		Edge<T>	newEdge = new Edge<>(source, destination, weight);
		this.adjacencyList.get(indexOfV1).add(newEdge);
		
		return (true);
	}

	@Override
	public boolean removeEdge(int vertexId1, int vertexId2) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'removeEdge'");
	}

	@Override
	public boolean removeVertex(int vertexId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'removeVertex'");
	}

	@Override
	public boolean removeVertex(String label) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'removeVertex'");
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

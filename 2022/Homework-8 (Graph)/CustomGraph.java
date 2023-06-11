import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
	/* number of vertices */
	private int	numV = 0;
	/* number of edges */
	private int	numE = 0;
	/* counter to trace the elements */
	private int	counter = 0;

	/**
	 * Constructs the Graph
	 */
	public	CustomGraph(boolean isDirected) {
		this.vertices = new ArrayList<>();
		this.adjacencyList = new ArrayList<>();
		this.isDirect = isDirected;
	}

	public int	getSize() {
		return (this.vertices.size());
	}

	/**
	 * Get the number of edges
	 * @return the number of edges
	 */
	public int	getNumE() {
		return (this.numE);
	}

	/**
	 * Get the number of vertices
	 * @return number of vertices
	 */
	@Override
	public int getNumV() {
		return (this.numV);
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
	 * Is given index on the graph or not
	 * @param vertex
	 * @return is vertex find
	 */
	@Override
	public boolean	containsVertex(Vertex<T> vertex) {
		if (vertex == null)
			return (false);

		for (Vertex<T> vrtx : this.vertices)
			if (vrtx.getId() == vertex.getId())
				return (true);

		return (false);
	}

	/**
	 * insert an edge to the graph
	 */
	@Override
	public void insert(Edge<T> edge) {
		this.addEdge(edge.getSource().getId(), edge.getSource().getId(), edge.getWeight());
	}

	/**
	 * Determine whether an edge exists.
	 * @param source The source vertex
	 * @param dest The destination vertex
	 * @return true if theere is an edge from source to dest
	 */
	@Override
	public boolean isEdge(Vertex<T> source, Vertex<T> dest) {
		return (this.getEdge(source, dest) != null);
	}

	/**
	 * Get the edge between two vertices.
	 * @param source The source vertex
	 * @param dest The destination vertex
	 * @return The Edge between these two vertices
	 * or null if there is no edge.
	 */
	@Override
	public Edge<T> getEdge(Vertex<T> source, Vertex<T> dest) {
		/* all edges */
		for (int i = 0; i < this.vertices.size(); ++i) {
			List<Edge<T>>	edges = this.adjacencyList.get(i);

			if (edges == null)
				continue;

			for (Edge<T> edge : edges)
				if (edge.getSource().equals(source) && edge.getDest().equals(dest))
					return (edge);
		}

		return (null);
	}

	/**
	 * Return an iterator to the edges connected to a given vertex.
	 * @param source The source vertex
	 * @return An Iterator<Edge> to the vertices connected to source
	 */
	@Override
	public Iterator<Edge<T>> edgeIterator(int sourceId) {
		return (new EdgeIterator<>(this.adjacencyList, sourceId));
	}

	/**
	 * iterator for edges connected a vertex
	 */
	public static class	EdgeIterator<T> implements Iterator<Edge<T>> {
		private final List<Edge<T>>	edgeList;
		private int	index = 0;

		public EdgeIterator(List<List<Edge<T>>> adjacencyList, int vertexId) {
			edgeList = adjacencyList.get(vertexId);
		}

		@Override
		public boolean	hasNext() {
			return (index != edgeList.size());
		}

		@Override
		public Edge<T>	next() {
			return (edgeList.get(index++));
		}
	}

	/**
	 * Creates a new Vertex
	 * @param label Vertex's label
	 * @param label Vertex's weight
	 * @return a Vertex
	 */
	@Override
	public Vertex<T> newVertex(String label, double weight) {
		return (new Vertex<>(this.counter++, label, weight));
	}

	/**
	 * Add a Vertex to the Graph
	 * @param newVertex
	 * @return if this operation is happen
	 */
	@Override
	public boolean addVertex(Vertex<T> newVertex) {
		int id = newVertex.getId();
		/* id must be equal the index of adjacency list */
		if (id >= this.adjacencyList.size())
			for (int i = this.adjacencyList.size(); i <= id; ++i) {
				this.adjacencyList.add(new ArrayList<>());
				if (i != id)
					this.vertices.add(null);
			}

		vertices.add(newVertex);
		++this.numV;
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
		if (vertexId1 >= this.getNumV() || vertexId2 >= this.getNumV())
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
			++this.numE;
		}
		
		++this.numE;
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
			--this.numE;
		}

		--this.numE;
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
			edges.removeIf(edge -> {
					boolean	remove = edge.getDest().getId() == vertexId;
					if (remove)
						--this.numE;
					return (remove);
				});

		--this.numV;
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
											.filter(vertex -> vertex != null ? vertex.getLabel().equals(label) : false)
											.findFirst();
										
		if (!optionalVertex.isPresent())
			return (false);

		/* Set the vertex to null cause of raising index errors */
		this.vertices.replaceAll(vertex ->  vertex == null || vertex.getLabel().equals(label) ? null : vertex);

		for (List<Edge<T>> edges : this.adjacencyList) {
			/* Remove outgoing edges from the vertex */
			edges.removeIf(edge -> {
										boolean remove = edge.getSource().getLabel().equals(label);
										if (remove)
											--this.numE;
										return (remove);
									});
			/* Remove incoming edges to the vertex */
			edges.removeIf(edge -> {
										boolean remove = edge.getDest().getLabel().equals(label);
										if (remove)
											--this.numE;
										return (remove);
									});
		}

		--this.numV;

		return (true);
	}

	/**
	 * Filter the vertices by the given property
	 * @param key
	 * @param filter
	 * @return a subgraph of the graph
	 */
	@Override
	public DynamicGraph<T> filterVertices(String key, T filter) {
		DynamicGraph<T>	filteredGraph = new CustomGraph<>(this.isDirect);

		/* all vertices */
		for (int i = 0; i < this.vertices.size(); ++i) {
			Vertex<T>	vertex = this.vertices.get(i);

			if (vertex == null)
				continue;

			T	propertyValue = vertex.getProperty(key);

			if (propertyValue != null && propertyValue.equals(filter))
				filteredGraph.addVertex(new Vertex<>(vertex.getId(), vertex.getLabel(), vertex.getWeight()));
		}

		/* all edges */
		for (int i = 0; i < this.vertices.size(); ++i) {
			List<Edge<T>>	edges = this.adjacencyList.get(i);

			if (edges == null)
				continue;

			for (Edge<T> edge : edges) {
				Vertex<T>	source = edge.getSource();
				Vertex<T>	dest = edge.getDest();

				if (filteredGraph.containsVertex(source) && filteredGraph.containsVertex(dest))
					filteredGraph.addEdge(source.getId(), dest.getId(), edge.getWeight());
			}
		}

		return (filteredGraph);
	}

	/**
	 * Generate the adjacency matrix represantation of the graph
	 * @return the matrix
	 */
	@Override
	public double[][] exportMatrix() {
		int	size = this.vertices.size();
		double[][]	matrix = new double[size][size];

		/* initialize with zero */
		for (int i = 0; i < size; ++i)
			for (int j = 0; j < size; ++j)
				matrix[i][j] = 0;

		/* Iterate over all edges in the graph */
		for (int i = 0; i < size; ++i) {
			List<Edge<T>>	edges = this.adjacencyList.get(i);

			/* update matrix */
			for (Edge<T> edge : edges) {
				int	sourceIndex = edge.getSource().getId();
				int	destIndex = edge.getDest().getId();
				double	weight = edge.getWeight();

				/* set the weight */
				matrix[sourceIndex][destIndex] = weight;
				
				/* set if the graph is undirected */
				if (!this.isDirected())
					matrix[destIndex][sourceIndex] = weight;
			}
		}
		
		return (matrix);
	}

	/**
	 * Print the Graph
	 */
	@Override
	public void printGraph() {
		System.out.println("Number of vertices on the Graph: " + this.getNumV());
		System.out.println("Number of edges on the Graph: " + this.getNumE());

		/* write all of the vertices and edges */
		for (int i = 0; i < this.vertices.size(); ++i) {
			Vertex<T>	vertex = this.vertices.get(i);
			List<Edge<T>>	edges = this.adjacencyList.get(i);
			
			if (vertex == null || edges == null)
				continue;

			System.out.print(vertex.getId() + ": ");
			for (Edge<T> edge : edges)
				System.out.print(edge.getDest().getId() + " ");
			System.out.println();
		}
	}

	/**
	 * get Vertex at the index
	 */
	@Override
	public Vertex<T> getVertex(int index) {
		try {
			return (this.vertices.get(index));
		} catch (ArrayIndexOutOfBoundsException e) {
			return (null);
		}
	}

	/**
	 * get edges at the id
	 */
	@Override
	public List<Edge<T>> getEdges(int vertexId) {
		try {
			return (this.adjacencyList.get(vertexId));
		} catch (ArrayIndexOutOfBoundsException e) {
			return (null);
		}
	}

	/**
	 * get all vertices
	 */
	@Override
	public List<Vertex<T>> getVertices() {
		return (this.vertices);
	}
}

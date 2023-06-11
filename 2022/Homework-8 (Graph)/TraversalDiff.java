import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class TraversalDiff {

	/**
	 * Calculate the total distance of two search
	 * @param graph
	 * @return |bfs - dfs| = 0
	 */
	public static double	calculateTraversalDiff(Graph<?> graph, int vertexId) {
		double	bfsDistance = breadthFirstSearch(graph, vertexId);
		double	dfsDistance = depthFirstSearch(graph, vertexId);

		return (Math.abs(bfsDistance - dfsDistance));
	}

	/**
	 * Breadth First Search algorithm using a Graph
	 * @param graph
	 * @return
	 */
	public static double	breadthFirstSearch(Graph<?> graph, int vertexId) {
		double	totalDistance = 0;
		Queue<Vertex<?>>	queue = new LinkedList<>();
		Set<Integer>	visited = new HashSet<>();
		Map<Integer, Double>	distances = new HashMap<>();

		/* initialize structures */
		Vertex<?>	curVertex = graph.getVertex(0);
		queue.add(curVertex);
		visited.add(curVertex.getId());
		/* set the distance of first vertex 0.0 */
		distances.put(curVertex.getId(), 0.0);

		/* bfs */
		while (!queue.isEmpty()) {
			curVertex = queue.poll();
			Double	curDistance = distances.get(curVertex.getId());

			for (Edge<?> edge : graph.getEdges(curVertex.getId())) {
				Vertex<?>	neighVertex = edge.getDest();

				if (!visited.contains(neighVertex.getId())) {
					visited.add(neighVertex.getId());
					queue.add(neighVertex);
					distances.put(neighVertex.getId(), curDistance + edge.getWeight());
					totalDistance += curDistance + edge.getWeight();
				}
			}
		}

		return (totalDistance);
	}

	/**
	 * Depth First Search using Stack
	 * @param graph
	 * @return
	 */
	public static double	depthFirstSearch(Graph<?> graph, int vertexId) {
		double	totalDistance = 0;
		Stack<Vertex<?>>	stack = new Stack<>();
		Set<Integer>	visited = new HashSet<>();
		Map<Integer, Double>	distances = new HashMap<>();

		/* initialize structures */
		Vertex<?>	curVertex = graph.getVertex(0);
		stack.add(curVertex);
		visited.add(curVertex.getId());
		/* set the distance of first vertex 0.0 */
		distances.put(curVertex.getId(), 0.0);

		/* dfs */
		while (!stack.empty()) {
			curVertex = stack.pop();
			Double	curDistance = distances.get(curVertex.getId());

			for (Edge<?> edge : graph.getEdges(curVertex.getId())) {
				Vertex<?>	neighVertex = edge.getDest();

				if (!visited.contains(neighVertex.getId())) {
					stack.add(neighVertex);
					visited.add(neighVertex.getId());
					distances.put(neighVertex.getId(), curDistance + edge.getWeight());
					totalDistance += curDistance + edge.getWeight();
				}
			}
		}

		return (totalDistance);
	}

	/**
	 * find shortest paths with boosting value using dijkstra's algorithm
	 * @param graph
	 * @param vertexId start vertex id
	 * @return
	 */
	public static Map<Integer, Double>	shortestPaths(Graph<Double> graph, int vertexId) {
		PriorityQueue<Vertex<Double>>	pqueue = new PriorityQueue<>((x, y) -> Double.compare(x.getProperty("distance"), y.getProperty("distance")));
		Map<Integer, Double>	distances = new HashMap<>();
		Set<Integer>	visited = new HashSet<>();

		/* set the distance values infinity for comparisions */
		for (Vertex<Double> vertex : graph.getVertices()) {
			distances.put(vertex.getId(), Double.POSITIVE_INFINITY);
			vertex.setProperty("distance", Double.POSITIVE_INFINITY);
		}

		/* initialize structures */
		Vertex<Double>	curVertex = graph.getVertex(vertexId);
		/* set the distance of first vertex 0.0 */
		curVertex.setProperty("distance", 0.0);
		pqueue.offer(curVertex);
		visited.add(curVertex.getId());

		while (!pqueue.isEmpty()) {
			curVertex = pqueue.poll();
			Double	curDistance = curVertex.getProperty("distance");
			Double	boosting = curVertex.getProperty("boosting");

			for (Edge<Double> edge : graph.getEdges(curVertex.getId())) {
				Vertex<Double>	neighborVertex = edge.getDest();
				
				double	newDistance = curDistance + edge.getWeight() - boosting;
				if (!visited.contains(neighborVertex.getId()) && newDistance < neighborVertex.getProperty("distance")) {
					neighborVertex.setProperty("distance", newDistance);
					distances.put(neighborVertex.getId(), newDistance);
					pqueue.offer(neighborVertex);
					visited.add(neighborVertex.getId());
				}
			}
		}

		return (distances);
	}
}

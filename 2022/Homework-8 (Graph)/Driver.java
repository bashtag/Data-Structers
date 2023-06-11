/**
 * Driver class
 */
public class Driver {
	/**
	 * main method to test graph and it's algorithms
	 * @param args
	 */
	public static void main(String[] args) {
		CustomGraph<Double>	graph = new CustomGraph<>(true);
		Vertex<Double>	v0 = graph.newVertex("31", 1);
		Vertex<Double>	v1 = graph.newVertex("52", 1);
		Vertex<Double>	v2 = graph.newVertex("69", 1);
		Vertex<Double>	v3 = graph.newVertex("71", 1);

		v0.setProperty("boosting", 0.0);
		v1.setProperty("boosting", 1.0);
		v2.setProperty("boosting", 6.0);
		v3.setProperty("boosting", 8.0);

		graph.addVertex(v0);
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addEdge(v0.getId(), v1.getId(), 3);
		graph.addEdge(v0.getId(), v3.getId(), 0.4);
		graph.addEdge(v1.getId(), v3.getId(), 2.8);
		graph.addEdge(v3.getId(), v1.getId(), 3.1);
		graph.addEdge(v3.getId(), v2.getId(), 7.7);

		// graph.removeVertex("31");
		// graph.removeVertex("52");
		// graph.removeVertex("69");
		// graph.removeVertex("71");
		// System.out.println(v0.getProperty("ge"));
		System.out.println(TraversalDiff.shortestPaths(graph, 0));

		graph.printGraph();
	}
}

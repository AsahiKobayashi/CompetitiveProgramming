public record Edge(int to, long weight) {}

public long[] dijkstra(int start, List<Edge>[] graph) {
    int n = graph.length;
    long[] dist = new long[n];
    Arrays.fill(dist, Long.MAX_VALUE);
    dist[start] = 0;
    PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
    pq.add(new long[]{start, 0});
    while (!pq.isEmpty()) {
        long[] curr = pq.poll();
        int node = (int) curr[0];
        long d = curr[1];
        if (dist[node] < d) continue;
        for (Edge e : graph[node]) {
            long newDist = d + e.weight;
            if (newDist < dist[e.to]) {
                dist[e.to] = newDist;
                pq.add(new long[]{e.to, newDist});
            }
        }
    }
    return dist;
}

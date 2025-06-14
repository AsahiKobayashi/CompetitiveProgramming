class Floyd_Warshall {

    private static final long inf = (long)1e15;
    private long[][] dist;
    private int N;
    private boolean cycle;

    Floyd_Warshall(int N) {
        this.N = N;
        this.cycle = false;
        this.dist = new long[N][N];
        for (long[] v : dist) Arrays.fill(v, inf);
        for (int i = 0; i < N; i++) dist[i][i] = 0;
    }

    public void addEdge(int u, int v, long w) {
        this.dist[u][v] = w;
    }

    public void run() {
        for (int mid = 0; mid < N; mid++) {
            for (int begin = 0; begin < N; begin++) {
                for (int end = 0; end < N; end++) {
                    long u = dist[begin][mid], v = dist[mid][end], g = dist[begin][end];
                    if (u == inf || v == inf) continue;
                    if (u + v < g) {
                        dist[begin][end] = u + v;
                    }
                    // ここに最短経路が同じ場合の処理を追加可能
                }
                if (dist[begin][begin] < 0) cycle = true;
            }
        }
    }

    public boolean isCycle() {
        return cycle;
    }

    public long dist(int u, int v) {
        return dist[u][v] == inf ? -1 : dist[u][v];
    }
  
}

## 使用例
```java
Floyd_Warshall fw = new Floyd_Warshall(N);
fw.addEdge(u, v, w);  // 辺の追加
fw.run();             // 計算実行
long shortest = fw.dist(u, v); // u→v の最短距離（存在しない場合は -1）
boolean hasNegativeCycle = fw.isCycle();

# 平方分割（Sqrt Root Decomposition）

## 概要

データ列に対する区間演算・点更新を高速に処理するデータ構造。  
モノイドの演算を用いて、**静的区間クエリ + 動的点更新**を `O(√N)` で処理できる。

- 各操作の計算量は **`O(√N)`**
- **再構築なしで動的更新が可能**
- セグメント木よりも実装・定数が軽い
- 演算は任意のモノイドに対応

---
## 使用例
```java
Monoid<Integer> sumMonoid = new Monoid<>() {
    public Integer e() { return 0; }
    public Integer op(Integer a, Integer b) { return a + b; }
};

int n = 100000;
AbstractSqrtRootDecomposition<Integer> sqrt = new AbstractSqrtRootDecomposition<>(n, sumMonoid);

// 値を更新
sqrt.update(10, 5);

// 区間和 [10, 20)
int result = sqrt.query(10, 20);

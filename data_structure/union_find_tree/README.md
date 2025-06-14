# UnionFindTree

## 概要

UnionFindTree は Union-Find（素集合管理）構造 を表すクラスです。  
集合の統合や判定、グループ数や各集合サイズの取得ができます。

---
## 使用例

```java
UnionFindTree uf = new UnionFindTree(5);

uf.unite(0, 1);
uf.unite(3, 4);

System.out.println(uf.same(0, 1)); // true
System.out.println(uf.same(1, 2)); // false
System.out.println(uf.count(0));  // 2
System.out.println(uf.size());    // 3

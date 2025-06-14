## 使用例
```java
BinaryOperator<Integer> sumOp = Integer::sum;

// ノードの初期値 (キー, 値)
Pair<Integer, Integer> base = new Pair<>(0, 1);  // すべて値1で初期化

// 長さ5のSplay木を作成
SplayNode<Integer, Integer> root = Splay.newSpalyNode(5, base, sumOp);

// 区間[1,4)の合計を取得
var res = Splay.query(1, 4, root);
root = res.f();  // 根は操作で更新される
System.out.println("sum[1,4): " + res.s()); // → 3

// インデックス2のノードを (2,10) に更新
root = Splay.update(2, new Pair<>(2, 10), root, sumOp);

// 更新後の合計再計算
var res2 = Splay.query(1, 4, root);
root = res2.f();
System.out.println("after update sum[1,4): " + res2.s()); // → 12

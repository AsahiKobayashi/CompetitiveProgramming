# Splay木 (スプレー木)

## 概要

このクラスは自己調整型の**Splay木**を汎用的に実装したものです。  
ノードの型を `Pair<T, S>` とし、`T` はキー、`S` は区間集約用の値です。

- 区間集約演算を `BinaryOperator<S>` で柔軟に定義可能
- 区間クエリ・更新・挿入・削除に対応
- サイズ・集約値を持つノードを使い、区間操作が可能
- **各操作はならし計算量 O(log N)** で高速

---
## 使用例
```java
// 区間和を定義
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

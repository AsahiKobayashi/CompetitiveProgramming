# SegmentTree

## 概要

`SegmentTree<T>` は、任意の**モノイド（結合法則 + 単位元）**に基づいた汎用の**セグメントツリー**です。  
クエリや更新処理が O(log N) で行えます。

---

## 使用条件

### Monoid インタフェースの定義が必要です：

```java
interface Monoid<T> {
    T op(T a, T b);  // 演算（結合法則が成り立つもの）
    T e();           // 単位元（identity element）
}

---
## 使用例

```java
Integer[] arr = {1, 3, 5, 7, 9, 11};
Monoid<Integer> sumMonoid = new Monoid<>() {
    public Integer op(Integer a, Integer b) { return a + b; }
    public Integer e() { return 0; }
};

SegmentTree<Integer> seg = new SegmentTree<>(arr, sumMonoid);

System.out.println(seg.query(1, 4)); // 出力: 15（3+5+7）
seg.update(2, 10);                   // arr[2] = 10 に変更
System.out.println(seg.query(1, 4)); // 出力: 20（3+10+7）
System.out.println(seg.prod());      // 全区間和

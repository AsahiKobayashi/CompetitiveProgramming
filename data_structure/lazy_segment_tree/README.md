#  LazySegtree

## 概要

`LazySegtree<S, F>` は **遅延評価付きセグメントツリー**（Lazy Segment Tree）です。  
- 任意の **モノイドデータ構造 (`S`)** に対して  
- 任意の **作用モノイド (`F`)** による**一括更新（範囲更新）**が可能です。

---

## 使用条件

使用前に、以下のような `MonoidAction<S, F>` インターフェースを定義してください：

```java
interface MonoidAction<S, F> {
    S op(S a, S b);              // モノイドの二項演算
    S mapping(F f, S x);         // 作用 f を要素 x に適用
    F composition(F f, F g);     // f ∘ g（後に g, 先に f を適用）
    S e();                       // S の単位元
    F id();                      // F の単位元（恒等写像）
}
```
---
## 使用例
```java
class RangeSumMonoid implements MonoidAction<Long, Long> {
    private final int size;
    public RangeSumMonoid(int size) { this.size = size; }

    public Long op(Long a, Long b) { return a + b; }

    public Long mapping(Long f, Long x) {
        return f + x;  // 加算
    }

    public Long composition(Long f, Long g) {
        return f + g; // 作用の合成も加算
    }

    public Long e() { return 0L; }     // 加算の単位元
    public Long id() { return 0L; }    // 恒等写像（加算0）
}
```
```java
List<Long> list = Arrays.asList(1L, 2L, 3L, 4L, 5L);
RangeSumMonoid act = new RangeSumMonoid(list.size());

LazySegtree<Long, Long> seg = new LazySegtree<>(list, act);

System.out.println(seg.prod(0, 5)); // 出力: 15

seg.apply(1, 4, 10L); // 区間[1,4)に+10

System.out.println(seg.prod(0, 5)); // 出力: 15 + 30 = 45
System.out.println(seg.get(2));     // 出力: 13（3 + 10）

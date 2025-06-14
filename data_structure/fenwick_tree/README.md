# 🌲 FenwickTree（Binary Indexed Tree）

## 概要

`FenwickTree`（別名：**Binary Indexed Tree**）は、以下の操作を効率よく処理できるデータ構造です。

- ✅ `add(p, x)`：要素 `p` に `x` を加算（O(log N))
- ✅ `sum(l, r)`：区間 `[l, r)` の総和を取得（O(log N))
- ✅ `set(p, x)`：要素 `p` を `x` に変更（O(log N))
- ✅ `get(p)`：要素 `p` の値を取得（O(log N))
---
## 使用例
```java
long[] arr = {1, 3, 5, 7, 9};
FenwickTree ft = new FenwickTree(arr);

System.out.println(ft.sum(0, 3)); // 1 + 3 + 5 = 9
ft.add(1, 2); // index 1 の値を +2（3 → 5）
System.out.println(ft.get(1));    // 5
ft.set(2, 0);                     // index 2 を 0 に
System.out.println(ft.sum(0, 5)); // 1 + 5 + 0 + 7 + 9 = 22

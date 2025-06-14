# BinaryTrie

## 概要
`BinaryTrie` は、整数をビット単位で管理するためのトライ木（Trie）ベースのデータ構造です。  
要素の挿入・削除・検索・順序統計・MEX（最小未使用値）などを高速に行えます。

---
## 使用例

```java
BinaryTrie trie = new BinaryTrie(30); // 最大30bitまで対応（2^30 未満）

trie.add(5);
trie.add(8);
trie.add(8);

System.out.println(trie.contains(8));      // true
System.out.println(trie.count(8));         // 2
System.out.println(trie.kthSmallest(2));   // 8
System.out.println(trie.rangeCount(4, 9)); // 3
System.out.println(trie.mex());            // 0（0が未使用）

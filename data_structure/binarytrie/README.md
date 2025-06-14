# 使用例

BinaryTrie trie = new BinaryTrie(30); // 最大30bitまで対応（2^30 未満）

trie.add(5);
trie.add(8);
trie.add(8);

System.out.println(trie.contains(8));      // true
System.out.println(trie.count(8));         // 2
System.out.println(trie.kthSmallest(2));   // 8
System.out.println(trie.rangeCount(4, 9)); // 3
System.out.println(trie.mex());            // 0 (0が未使用)

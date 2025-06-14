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

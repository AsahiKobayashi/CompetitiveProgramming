class Splay<T,S>{

    public static <T,S> SplayNode<T,S> newSpalyNode(int n , Pair<T,S> base , BinaryOperator<S> op) {
        SplayNode<T,S> nodes = new SplayNode<>(base,op);
        for(int i = 0 ; i < n - 1 ; i ++)
            nodes = Splay.merge(nodes , new SplayNode<>(base, op));
        return nodes ;
    }

    public static <T,S> SplayNode<T,S> update(int index , Pair<T,S> value , SplayNode<T,S> root , BinaryOperator<S> op) {
        SplayNode<T,S> insert = new SplayNode<>(value , op);
        root = insert(index, insert, root);
        var spl = delete(index + 1, root);
        return spl.f();
    }

    public static <T,S> Pair<SplayNode<T,S>,S> query(int l , int r , SplayNode<T,S> root) {
        SplayNode<T,S> lroot , croot , rroot ;
        var nodes = split(r , root);
        rroot = nodes.s();
        nodes = split(l, nodes.f());
        lroot = nodes.f();
        croot = nodes.s();
        S ans = croot.agg;
        return new Pair<>(merge(merge(lroot, croot),rroot) , ans);
    }

    public static <T,S> SplayNode<T,S> insert(int index , SplayNode<T,S> insertRoot , SplayNode<T,S> baseRoot) {
        var nodes = split(index, baseRoot);
        SplayNode<T,S> lroot = nodes.f() , rroot = nodes.s();
        return merge(merge(lroot,insertRoot), rroot);
    }

    public static <T,S> Pair<SplayNode<T,S>,SplayNode<T,S>> delete(int index , SplayNode<T,S> root) {
        root = get(index,root);
        SplayNode<T,S> lroot = root.left , rroot = root.right;
        if(lroot != null) lroot.parent = null ;
        if(rroot != null) rroot.parent = null ;
        root.left = null ; root.right = null ;
        return new Pair<>(merge(lroot, rroot) , root);
    }

    public static <T,S> SplayNode<T,S> merge(SplayNode<T,S> lroot , SplayNode<T,S> rroot) {
        if(lroot == null) return rroot ;
        if(rroot == null) return lroot ;
        lroot = get(lroot.size - 1 , lroot); // 左の部分木の右端は根の部分木のサイズの-1（0-indexed）
        lroot.right = rroot; // 根の右を移し替える
        rroot.parent = lroot; // 右の根の親を張り替える
        lroot.update(); // 子が更新されたから
        return lroot;
    }

    public static <T,S> Pair<SplayNode<T,S>,SplayNode<T,S>> split(int lsize , SplayNode<T,S> root) {
        if(lsize == 0) return new Pair<>(null, root);
        if(lsize == root.size) return new Pair<>(root,null);
        root = get(lsize , root); // 左と右で分割する
        SplayNode<T,S> lroot , rroot ;
        lroot = root.left;
        rroot = root;
        rroot.left = null ;
        lroot.parent = null ;
        rroot.update();// 子が更新されたから
        return new Pair<>(lroot , rroot);
    }

    public static <T,S> SplayNode<T,S> get(int index , SplayNode<T,S> root) {
        SplayNode<T,S> now = root ;
        for(;;){
            int lsize = (now.left == null) ? 0 : now.left.size;
            if(index < lsize) // 左の部分木のサイズの方が目的の値より大きい -> 左の部分木に存在する
                now = now.left ;
            if(index == lsize) { // 目的の所に到達したらsplay()を呼び上に挙げる必要がある
                now.splay();
                return now ;
            }
            if(index > lsize) {  //　右の部分木に存在する 
                now = now.right ;
                index = index - lsize - 1 ;
            }
        }
    }

    public static <T,S> SplayNode<T,S> dump(SplayNode<T,S> node) {
        StringBuffer result = new StringBuffer();
        for(int i = 0 ; i < node.size ; i ++){
            node = get(i, node);
            result.append(node.value+" ");
        }
        System.out.println(result);
        return node;
    }

}

class SplayNode<T,S>{

    int size ;
    Pair<T,S> value ;
    S agg ;
    SplayNode<T,S> left , right , parent ;
    BinaryOperator<S> op ;

    SplayNode(Pair<T,S> value , BinaryOperator<S> op) { 
        left = right = parent = null ; size = 1;
        this.value = value; 
        this.agg = value.s();
        this.op = op;
    }

    public void rotate() { 
        SplayNode<T,S> par = this.parent ; // 自分の親
        SplayNode<T,S> parpar = par.parent ; // 自分の親の親
        SplayNode<T,S> ch = null ; // 自分の子 (右回転、左回転かによって変わる)
        /**
         *  左回転
         */
        if(par.left == this) {
            ch = this.right ;
            this.right = par ; 
            par.left = ch ;
        }
        /**
         *  右回転
         */
        if(par.right == this) {
            ch = this.left ;
            this.left = par ;
            par.right = ch ;
        }
        /**
         * 　親の親が存在する場合、回転後自分の親を張り替える
         */
        if(parpar != null) { 
            if(parpar.left == par)  
                parpar.left = this;
            if(parpar.right == par)
                parpar.right = this;
        }
        this.parent = parpar; // 自分の親は元の親の親になる
        par.parent = this; // 元の親は自分の子になる
        if(ch != null)
            ch.parent = par ;
        /**
         * 一度の回転で部分木のサイズに影響があるのは自分とその親
         * ※　ボトムアップで計算する必要がある -> 元の親から
         */
        par.update();
        this.update();
    }
    /**
     * 自分は根になるまで（親が存在する間）
     * 
     * 自分の親、自分の親の親連続で同じ方向に生えている場合 : 親 -> 自分 の順番で上げていく
     * 自分の親、自分の親の親連続で同じ方向に生えていない場合 : 自分 -> 自分 の順番で上げていく
     */
    public void splay() { 
        while(!(this.state() == 0)) { // 自分の親がいる間
            if(this.parent.state() == 0) { // 自分の親の親が存在しない場合 -> 一度自分をあげるだけ
                this.rotate();
            }
            else if(this.parent.state() == this.state()) {
                this.parent.rotate();
                this.rotate();
            }
            else {
                this.rotate();
                this.rotate();
            }
        }
    }

    /**
     *  右の子、左の子の部分木の大きさの合算
     */
    public void update() { 
        this.size = 1 ;
        this.agg = this.value.s();
        if(this.left != null) {
            this.size += this.left.size;
            this.agg = op.apply(this.agg , this.left.agg);
        }
        if(this.right != null) {
            this.size += this.right.size;
            this.agg = op.apply(this.agg , this.right.agg);
        }
    }
    /**
     * 自分の親が存在するか、自分は親のどちらの子か
     * 
     * 自分の親、自分の親の親連続で同じ方向に生えている場合 : 親 -> 自分 の順番で上げていく
     * 自分の親、自分の親の親連続で同じ方向に生えていない場合 : 自分 -> 自分 の順番で上げていく
     * @return
     */
    private int state() {
        if(this.parent == null) return 0 ;
        if(this.parent.left == this) return 1 ;
        if(this.parent.right == this) return -1 ;
        return 0 ;
    }

}

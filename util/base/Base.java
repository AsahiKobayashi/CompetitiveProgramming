@SuppressWarnings("unchecked")
class Base {

    protected static final int INF_INT = (1 << 30);
    protected static final long INF_LONG = (1L << 60);
    protected static final String YES = "Yes" , NO = "No";
    protected static final int [][] DIRECTION_4 = {{-1,0},{0,1},{1,0},{0,-1}};  // {y,x}
    protected static final int [][] DIRECTION_8 = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}}; // {y,x}
    protected static final long MOD9 = 998_244_353;
    protected static final long MOD7 = 1_000_000_007;

    public static <T> T[] wrap(Object array) {
        Class<?> clazz = array.getClass().getComponentType();
        if (clazz == int.class) return (T[]) convert(array, Integer.class);
        if (clazz == long.class) return (T[]) convert(array, Long.class);
        if (clazz == double.class) return (T[]) convert(array, Double.class);
        if (clazz == boolean.class) return (T[]) convert(array, Boolean.class);
        if (clazz == char.class) return (T[]) convert(array, Character.class);
        if (clazz == byte.class) return (T[]) convert(array, Byte.class);
        if (clazz == short.class) return (T[]) convert(array, Short.class);
        if (clazz == float.class) return (T[]) convert(array, Float.class);
        return (T[]) convert(array, (Class<?>) clazz);
    }

    private static <T> T[] convert(Object array, Class<T> clazz) {
        int length = Array.getLength(array);
        T[] newArray = (T[]) Array.newInstance(clazz, length);
        Arrays.setAll(newArray, i -> clazz.cast(Array.get(array, i)));
        return newArray;
    }

    public static final long ceil(long u,long d){ 
        return (u+d-1)/d; 
    }
    
    public static final long sqrt(long x) { 
        long low=0,high=Math.min(x,1L<<32);
        while(low<=high){
            long mid=(low+high)>>>1;
            long sq=mid*mid;
            if(sq==x)
                return mid;
            if(sq<0||sq>x)
                high=mid-1;
            else low=mid+1;
        }
        return high; 
    }
    
}

package lamada;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class LamadaTest {

    public static void main(String[] args) {
        ILamada lam = ()-> System.out.println("Hello World");
        lam.say();
        String[] fieldList = {"firmName", "airSwitchNo", "name", "type", "position"};
        long c = Arrays.stream(fieldList).filter(x -> "name".equals(x)).count();
        System.out.println(c);
        System.out.println(Stream.iterate(0, x -> x+1).limit(5).collect(Collectors.toList()));
        System.out.println(apply(x-> x*2,10));
        System.out.println(product(()->5));
        consumer(x->System.out.println("my age is "+x), 21);
        System.out.println(predicate(x->x%2==0, 10));
        
    }
    
    public static Integer apply(Function<Integer, Integer> fun, int t){
        return fun.apply(t);
    }
    public static Integer product(Supplier<Integer> su){
        return su.get();
    }
    public static void consumer(Consumer<Integer> con, int t){
        con.accept(t);
    }
    public static boolean predicate(Predicate<Integer> pre, int t){
        return pre.test(t);
    }
}

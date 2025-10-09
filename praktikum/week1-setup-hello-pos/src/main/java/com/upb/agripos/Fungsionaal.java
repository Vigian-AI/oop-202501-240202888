package main.java.com.upb.agripos;

import java.util.function.BiConsumer;

public class Fungsionaal {
    static void main() {
        BiConsumer<String, Integer> intro = (name, nim) ->
                IO.println("Hello, I am " + name + "-" + nim);

        intro.accept("Vigian AGus Isnaeni", 240202888);

    }
}

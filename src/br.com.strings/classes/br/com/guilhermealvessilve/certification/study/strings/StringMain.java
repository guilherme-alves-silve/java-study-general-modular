package br.com.guilhermealvessilve.certification.study.strings;

/**
 *
 * @author Alves
 */
public class StringMain {
    
    public static void main(String[] args) {
        String a = "Tea";
        String b = "Tea";
        System.out.println(a == b);
        System.out.println(b == a);
        String c = new String("Tea");
        System.out.println(a == c);
        System.out.println(b ==c);
        System.out.println(b == c.intern());
        System.out.println(b = "Te" + "a");
        System.out.println(a == "Te" + "a");
        System.out.println(a == new String("Te") + "a");
        System.out.println(a == (new String("Te") + "a"));
        System.out.println(a == (new String("Te") + "a").intern());
        System.out.println(a == b);
        var d = c.intern();
        System.out.println(a == c);
        System.out.println(a == d);
        System.out.println(b == d);
        System.out.println(c == d);
        System.out.println(c.intern() == d);
        var e = "Te" + 'a';
        System.out.println(a == e);
        var f = "Te" + ((char) '\141');
        System.out.println(b == f);
        System.out.println(c.indexOf('T'));
        System.out.println(c.indexOf('a'));
        System.out.println(c.indexOf('T', 1));
        System.out.println(c.lastIndexOf('T'));
        var g = "TeaT";
        System.out.println(g.lastIndexOf('T'));
        System.out.println(g.indexOf('T'));
        System.out.println(g.substring(0, 1));
        System.out.println(g.substring(0, 2));
        System.out.println(g.substring(0, 3));
        System.out.println(g.substring(0, g.length()));
        System.out.println(g.substring(1, g.length()));
        StringBuilder str = new StringBuilder(c);
        System.out.println(str.toString());
        System.out.println(str.capacity());
        System.out.println(str.length());
        System.out.println(str.append(a).append(b).append(c));
        System.out.println(str.toString());
        System.out.println(str.insert(0, "NANI?!??!?!!"));
        try {
            str.insert(50, 'T');
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            str.insert(50, "T");
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println(str.insert(20, "T"));
    }
}

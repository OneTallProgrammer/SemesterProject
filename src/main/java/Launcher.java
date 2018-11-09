import org.jpl7.*;

public class Launcher {
    
    public static void main(String[] args) {

        Query q1 =  new Query(
                "consult",
                new Term[] {new Atom("test.pl")}

        );

        System.out.println( "test.pl loaded: " + (q1.hasSolution() ? "true" : "false"));

        Query q2 = new Query(
                "goodtogo",
                new Term[] {new Atom("group")}
        );

        System.out.println( "are we good to go? " + (q2.hasSolution() ? "Yes!" : "No..."));

}

}

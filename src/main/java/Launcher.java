import synth.ToneSynth;

public class Launcher {
    
    public static void main(String[] args) {

        ToneSynth syn = new ToneSynth();

        String[] c1 = new String[]{"4C#", "4E", "4G#", "5C#"};
        String[] c2 = new String[]{"", "", "", ""};
        String[] c3 = new String[]{"", "", "", "5D#"};
        String[] c4 = new String[]{"3G#", "", "", ""};
        String[] c5 = new String[]{"3B#", "", "4G#", "6E"};
        String[] c6 = new String[]{"", "", "", "6F#"};
        String[] c7 = new String[]{"3G#", "", "", "6B#"};
        String[] c8 = new String[]{"", "", "", "6F#"};
        String[] c9 = new String[]{"2D#", "", "", "6G#"};
        String[] c10 = new String[]{"", "", "", "6E"};
        String[] c11 = new String[]{"", "", "", "6F#"};
        String[] c12 = new String[]{"2D#", "4F#", "", "6G#"};
        String[] c13 = new String[]{"2C#", "4G#", "4G#", "5C#"};
        String[] c14 = new String[]{"", "", "", ""};
        String[] c15 = new String[]{"", "", "", "5D#"};
        String[] c16 = new String[]{"3G#", "", "", ""};
        String[] c17 = new String[]{"4C#", "4G#", "5C#", "5E"};

        int t = 300;

        syn.playBlock(c1, t);
        syn.playBlock(c2, t);
        syn.playBlock(c3, t);
        syn.playBlock(c4, t);
        syn.playBlock(c5, t);
        syn.playBlock(c6, t);
        syn.playBlock(c7, t);
        syn.playBlock(c8, t);
        syn.playBlock(c9, t);
        syn.playBlock(c10, t);
        syn.playBlock(c11, t);
        syn.playBlock(c12, t);
        syn.playBlock(c13, t);
        syn.playBlock(c14, t);
        syn.playBlock(c15, t);
        syn.playBlock(c16, t);
        syn.playBlock(c17, t * 16);


//        Query q1 =  new Query(
//                "consult",
//                new Term[] {new Atom("test.pl")}
//
//        );
//
//        System.out.println( "test.pl loaded: " + (q1.hasSolution() ? "true" : "false"));
//
//        Query q2 = new Query(
//                "goodtogo",
//                new Term[] {new Atom("group")}
//        );
//
//        System.out.println( "are we good to go? " + (q2.hasSolution() ? "Yes!" : "No..."));

}

}

import synth.ToneSynth;

import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Launcher {
	Query q1, q2;
	boolean jplWorks = false;

	ToneSynth syn;
	Scanner scan;

	ArrayList<ArrayList<String>> notes;
	String temp, song = "";
	String[] ta, ta2, ta3;
	char[] tc;
	int bpm, major;
	float beat;
	
	public Launcher() {
		loadMusic();
		jplWorks = openJPL();
		
		if(jplWorks) {
			runSynth();
		}
	}

	private void loadMusic() {
		try {
			scan = new Scanner(new File("src/main/java/song.abc"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		notes = new ArrayList<ArrayList<String>>();

		while(scan.hasNext()) {
			temp = scan.nextLine();
			temp = temp.trim();

			if(temp.matches("\\w: .*")) {
				ta = temp.split(":");

				switch(ta[0]) {
					case "L": beat = (Float.parseFloat(ta[1].split("/")[0])/Float.parseFloat(ta[1].split("/")[1])); break;
					case "K": major = 12; break; // This is for C, change later
					case "Q": bpm = Integer.parseInt(ta[1].trim()); break;
				}
				continue;
			}

			if(temp.matches("%  .*")) {
				continue;
			}
			
			song += " " + temp;
		}

		song = song.replace('|', ' ');
		System.out.println(song);
			
		ta = song.trim().split("\\s");

		for(int i = 0; i < ta.length; i++) {
			notes.add(new ArrayList<String>());
			tc = ta[i].toCharArray();

			if(tc[0] == '[') {
				boolean trigger = false;
				int lastIndex = 1;
				for(int j = 0; j < tc.length; j++) {
					if(trigger) {
						if(Character.isAlphabetic(tc[j]) || tc[j] == ']') {
							notes.get(i).add(ta[i].substring(lastIndex, j));
							lastIndex = j;
							trigger = false;
						}
					} else {
						if(Character.isDigit(tc[j])) {
							trigger = true;
						}
					}
				}
			} else {
				notes.get(i).add(ta[i]);
			}
		}

		for(ArrayList<String> a : notes) {
			System.out.println(a);
		}

		scan.close();
	} 

	private boolean openJPL() {
		q1 = new Query("consult", new Term[] {new Atom("test.pl")});

		if(q1.hasSolution()) {
			System.out.println("JPL Loadead test.pl");
			q2 = new Query("goodtogo", new Term[] {new Atom("group")});
		} else {
			return false;
		}

		return q2.hasSolution();
	}

	private void runSynth() {
		syn = new synth.ToneSynth();

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
	}

    public static void main(String[] args) {
		new Launcher();
	}
}

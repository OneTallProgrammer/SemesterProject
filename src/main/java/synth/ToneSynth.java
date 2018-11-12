package synth;



import com.sun.javafx.image.IntPixelGetter;

import javax.sound.midi.*;
import java.util.ArrayList;

public class ToneSynth {

    private Synthesizer syn;
    private MidiChannel[] midichannels;

    public ToneSynth() {
        try {
            syn = MidiSystem.getSynthesizer();
            syn.open();
            midichannels = syn.getChannels();
            syn.loadAllInstruments(syn.getDefaultSoundbank());

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * takes in an arraylist of formatted strings representing musical notes
     * and returns their values coded for midi
     *
     * format: <octave><pitch><# or b>
     *
     * example: 4C# is C# in the 4th octave
     *
     * @param noteString: list of formatted strings representing musical notes
     * @return an arraylilst of the note's associated midi values
     */
    private int getMidiValue(String noteString)
    {
        if(noteString.length() == 0)
            return 0;

        int midiVal = 12;  // value of 1C
        int octave = Integer.parseInt(noteString.substring(0, 1));
        char pitch = noteString.charAt(1);

        midiVal += midiVal * octave;

        switch(pitch)
        {
            case 'D':
                midiVal += 2;
                break;
            case 'E':
                midiVal += 4;
                break;
            case 'F':
                midiVal += 5;
                break;
            case 'G':
                midiVal += 7;
                break;
            case 'A':
                midiVal += 9;
                break;
            case 'B':
                midiVal += 11;
                break;
        }

        // note is sharp or flat
        if(noteString.length() > 2)
        {
            char accidental = noteString.charAt(2);

            switch(accidental)
            {
                case '#':
                    midiVal += 1;
                    break;
                case 'b':
                    midiVal -= 1;
                    break;
            }
        }


        return midiVal;

    }

    /**
     * plays a block of notes for the specified duration
     * @param notes: the array of formatted note names
     *             format: <octave><pitch><# or b>
     * @param duration: the amount of time in milliseconds that the block will be played
     */
    public void playBlock(String[] notes, int duration)
    {

        int[] midiValues = new int[notes.length];

        for(int i = 0; i < notes.length; i++)
        {
            midiValues[i] = getMidiValue(notes[i]);
        }

        // turn off any notes previously playing
        for(int i = 0; i < midiValues.length; i++)
        {
            if(midiValues[i] != 0)
                midichannels[i].allNotesOff();
        }

        // turn on any new notes
        for(int i = 0; i < midiValues.length; i++)
        {
            if(midiValues[i] != 0)
                midichannels[i].noteOn(midiValues[i], 100);
        }

        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

//    public void play(String[] notes, int duration)
//    {
//
//        int[] midiVales = new int[notes.length];
//
//        for(int i = 0; i < notes.length; i++)
//        {
//            midiVales[i] = getMidiValue(notes[i]);
//        }
//
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                playBlock(midiVales);
//
//                try {
//                    Thread.sleep(duration);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        t.run();
//
//    }




    /*
    Plays sequence all notes passed as arguments
    @param notes: Piano keys in the range 0 - 127 with 60 as middle C
    @param length: The duration in milliseconds that the notes will play
     */
//    public void playNotes(int len, int... notes) {
//
//
//        int[] scale;
//        if(notes[notes.length - 1] % 2 == 0) {
//            scale = PlayControl.generateMinorScale(notes[0]);
//        }
//        else {
//            scale = PlayControl.generateMajorScale(notes[0]);
//        }
//
//
////        for(int i = 0; i< minor.length; i++) {
////            System.out.println(minor[i]);
////        }
//        AtomicInteger beatCounter = new AtomicInteger(0);
//        AtomicInteger i = new AtomicInteger(0);
//        if( scheduler != null )
//            stopNotes();
//        scheduler = SchedulerManager.builder().build();
//        scheduler.repeat(task -> {
//            int beatCount = beatCounter.getAndIncrement();
//            int j = i.getAndIncrement();
//            if (j >= notes.length) {
//                task.stop();
//            } else {
//                // Harmonize notes every 4 beats
//                if(beatCount % 4 == 0){
//                    this.midichannel[2].noteOn(scale[notes[j] % 13], 100);
//                }
//
//                this.midichannel[2].noteOn(scale[notes[j] % 15], 100);
//                scheduler.run(() -> this.midichannel[2].noteOff(scale[Math.abs(notes[j]) % 15]), len, TimeUnit.MILLISECONDS);
//            }
//        }, len, TimeUnit.MILLISECONDS);
//    }

//    public void stopNotes() {
//        scheduler.endAll();
//    }
}

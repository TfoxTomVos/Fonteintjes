package com.tomvosdev.fountains.show;

import com.tomvosdev.fountains.Fountains;
import com.tomvosdev.fountains.fountain.JetFountain;
import org.bukkit.Location;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Show {

    Location center;
    HashMap<Integer, JetFountain> jetFountains;

    boolean debug;
    MidiDevice device;
    Sequencer sequencer;
    int[] controllers;
    String name;
    public Show(String name, Location center, HashMap<Integer,JetFountain> jetFountains){

        this.name = name;

        this.center = center;
        this.jetFountains = jetFountains;
        debug = false;

        try {
            sequencer = MidiSystem.getSequencer();
        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        }

        controllers = new int[127];
        for(int i : controllers){

            controllers[i] = i;

        }

    }

    public void playShow(String fileName, int bpm){

        Sequence sequence;

        try {
            sequence = MidiSystem.getSequence(new File(Fountains.getPlugin(Fountains.class).getDataFolder(), fileName+".mid"));

            sequencer.setSequence(sequence);

        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            sequencer.open();
            sequencer.getTransmitter().setReceiver(new ShowMidiReceiver(name));
        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        }
        sequencer.setTempoInBPM(bpm);
        try {
            for(Instrument inst : MidiSystem.getSynthesizer().getLoadedInstruments()){

                MidiSystem.getSynthesizer().unloadInstrument(inst);

            }
        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        }
        sequencer.start();

    }

    public void initDebugging(){

        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();

        for(int i = 0; i < infos.length; i++){

            try {
                device = MidiSystem.getMidiDevice(infos[i]);
                if(!(device.getDeviceInfo().toString().equalsIgnoreCase("fountaindebug"))) continue;
                System.out.println(device.getDeviceInfo());
                if(device.getMaxTransmitters() == 0) continue;

                Transmitter transmitter = device.getTransmitter();
                device.open();
                transmitter.setReceiver(new ShowMidiReceiver(name));

            } catch (MidiUnavailableException e) {
                throw new RuntimeException(e);
            }

        }

    }

    public void stopDebugging(){

        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();

        for(int i = 0; i < infos.length; i++){

            try {
                device = MidiSystem.getMidiDevice(infos[i]);

                device.close();

            } catch (MidiUnavailableException e) {
                throw new RuntimeException(e);
            }

        }

    }

    public Location getCenter() {
        return center;
    }

    public HashMap<Integer, JetFountain> getJetFountains() {
        return jetFountains;
    }

    public boolean isDebugging() {
        return debug;
    }

    public void setDebugging(boolean debug) {
        this.debug = debug;
    }
}

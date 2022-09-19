package com.tomvosdev.fountains.show;

import com.tomvosdev.fountains.Fountains;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class ShowMidiReceiver implements Receiver {

    String showName;

    public ShowMidiReceiver(String showName){

        this.showName = showName;

    }

    @Override
    public void send(MidiMessage message, long timeStamp){

        if(message instanceof ShortMessage){

            ShortMessage sm = (ShortMessage) message;

            Show show = ShowManager.shows.get(showName);

            if(sm.getCommand() == ShortMessage.CONTROL_CHANGE) controlChange(sm, show);


            else if(sm.getCommand() == ShortMessage.NOTE_ON){
                // eduihgafuifgd8uyga8w PROBLEEM
                if(sm.getChannel() == 0){

                    int key = sm.getData1()-23;
                    if(show.jetFountains.containsKey(key)){

                        System.out.println(show.jetFountains.get(key).getData1());
                        show.jetFountains.get(key).start();

                    }
                }
                if(sm.getChannel() == 1){
                    int key = sm.getData1()-23;
                    /*if(show.sprayFountains.containsKey(key)){

                        show.sprayFountains.get(key).start();

                    }*/
                }

            }
            else if(sm.getCommand() == ShortMessage.NOTE_OFF){

                if(sm.getChannel() == 0){
                    int key = sm.getData1()-23;
                    if(show.jetFountains.containsKey(key)){

                        show.jetFountains.get(key).stop();

                    }
                }
                if(sm.getChannel() == 1){
                    int key = sm.getData1()-23;
                    /*if(show.sprayFountains.containsKey(key)){

                        show.sprayFountains.get(key).stop();

                    }*/
                }

            }

        }

    }

    @Override
    public void close() {

    }

    public void controlChange(ShortMessage event, Show show) {
        if (event.getChannel() == 0) {

            for(int i = 0; i < 32; i++){


               if(event.getData1() / 4  >= i){

                   if(!show.jetFountains.containsKey(i+1)) return;
                   int dataNum = (((event.getData1()+1) - (i*4)) % 4);



                   if(dataNum == 1) {
                       show.jetFountains.get(i + 1).updateData1(event.getData2() - 64);
                   }else if(dataNum == 2){
                       show.jetFountains.get(i+1).updateData2(event.getData2()-64);
                   }else if(dataNum == 3){
                       show.jetFountains.get(i+1).updateData3(event.getData2());
                   }

                   return;

               }

            }

        }

    }
}

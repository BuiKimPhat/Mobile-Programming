package com.example.piano;

public class SoundManager {
    private SoundManager instance;
    public SoundManager getInstance(){
        if (instance == null){
            instance = new SoundManager();
        }
        return instance;
    }
}

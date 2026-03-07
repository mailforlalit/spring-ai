package com.concept.springai.service;

import org.springframework.ai.audio.tts.TextToSpeechPrompt;
import org.springframework.ai.audio.tts.TextToSpeechResponse;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class AudioService {

    OpenAiAudioSpeechModel openAiAudioSpeechModel;

    public AudioService(OpenAiAudioSpeechModel openAiAudioSpeechModel) {
        this.openAiAudioSpeechModel = openAiAudioSpeechModel;
    }

    public String convertText(String text){
        OpenAiAudioSpeechOptions speechOptions =  OpenAiAudioSpeechOptions.builder()
                .model("gpt-4o-mini-tts")
                .voice(OpenAiAudioApi.SpeechRequest.Voice.SHIMMER)
                .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                .speed(1.0)
                .build();

        TextToSpeechPrompt speechPrompt = new TextToSpeechPrompt(text, speechOptions);
        TextToSpeechResponse response = openAiAudioSpeechModel.call(speechPrompt);

        byte[] audio = response.getResult().getOutput();

        try (FileOutputStream f = new FileOutputStream("voice.mp3")) {
            f.write(audio);
            System.out.println("Audio file saved as output.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Audio generation successful. Check mp3 file.";

    }


}

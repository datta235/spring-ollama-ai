package com.codeworm.ai.spring_ollama_ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api")
public class AiController {
    private final ChatClient chatClient;

    public AiController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    record Message(String message) {}
    record ActorFilms(String actor, List<String> movies) {}

    @GetMapping("/ai")
    Message generation(String userInput){
        return  this.chatClient.prompt().user(userInput)
                .call().entity(Message.class);
    }

    @GetMapping("/actors")
    ActorFilms actors(){
         return chatClient.prompt().user("Generate the filmography for a random actor").call().entity(ActorFilms.class);
    }

}

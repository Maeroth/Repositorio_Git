package es.ztory;

import java.util.ArrayList;
import java.util.List;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.model.Model;
import com.theokanning.openai.service.OpenAiService;

public class TestAPIChatGpt {

	public static void main(String[] args) {
		// Coloca tu API Key
        String apiKey = "tu_api_key_aqui";
        OpenAiService service = new OpenAiService("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");
        String model = "gpt-3.5-turbo";
//        String prompt = "Crea una historia de 60 palabras sobre los zombis";
//        CompletionRequest completionRequest = CompletionRequest.builder()
//        		.prompt(prompt)
//        		.model(model)
//        		.echo(true)
//        		.n(3)
//        		.user("testing")
//        		.build();
        List<ChatMessage> chatMessage = new ArrayList<ChatMessage>();
        chatMessage.add(new ChatMessage("user", "Crea una historia de 60 palabras sobre los zombis"));
         ChatCompletionRequest completionChatRequest = ChatCompletionRequest.builder()
        		.messages(chatMessage)
         		.model(model)
         		.user("testing")
         		.build();
        List<ChatCompletionChoice> listChoices = service.createChatCompletion(completionChatRequest).getChoices();
        for(ChatCompletionChoice c: listChoices) {
        	System.out.println(c.getMessage());
        }
        System.out.println("\nCreating Image...");
        CreateImageRequest request = CreateImageRequest.builder()
                .prompt("A zombie with a turtle dancing")
                .build();

        System.out.println("\nImage is located at:");
        System.out.println(service.createImage(request).getData().get(0).getUrl());
    }
	
}

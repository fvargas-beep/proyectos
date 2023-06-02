package logicadenegocios;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.edit.EditChoice;
import com.theokanning.openai.edit.EditRequest;
import java.util.List;

public class ChatGPT{
  private final String pregunta;
  private String respuesta;
    
  public ChatGPT(String pPregunta){
      this.pregunta = pPregunta;
  }
    
  public String conexion(){
    OpenAiService service = new OpenAiService("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");
    EditRequest request = EditRequest.builder()
      .model("text-davinci-edit-001")
      .input(pregunta)
      .instruction("Responder pregunta y explicar respuesta")
      .build();
    service.createEdit(request).getChoices();
    List<EditChoice> choices = service.createEdit(request).getChoices();
    for (EditChoice choice : choices) {
      respuesta += choice.toString() + "\n.";
    }
    return respuesta;
  }
    
  public String ejemplo(){
    OpenAiService service = new OpenAiService("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");        
    EditRequest request = EditRequest.builder()
      .model("text-davinci-edit-001")
      .input(this.pregunta)
      .instruction("Dar y explicar ejemplo")
      .build();
    service.createEdit(request).getChoices();
    List<EditChoice> choices = service.createEdit(request).getChoices();
    for(EditChoice choice : choices){
      respuesta += choice.toString() + "\n.";
    }
    return respuesta;
  }
}
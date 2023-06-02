package logicadenegocios;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Analisis_Sentimientos {
  Traductor traductor = new Traductor();
  
  public String analizarSentimiento(String text) throws IOException {
    text = traductor.traducirAIngles(text);
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    Annotation annotation = new Annotation(text);
    pipeline.annotate(annotation);
    CoreDocument document = new CoreDocument(annotation);
    String sentiment = document.sentences().get(0).sentiment();
    return traductor.traducirAEspanol(sentiment);
  }
    
  public String analizarSentimientosLista(ArrayList<String> textos) throws IOException {
    textos = traductor.traducirAInglesArray(textos);
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    StringBuilder resultado = new StringBuilder();
    for (String texto : textos) {
      Annotation annotation = new Annotation(texto);
      pipeline.annotate(annotation);
      CoreDocument document = new CoreDocument(annotation);
      String sentiment = document.sentences().get(0).sentiment();
      String res = "->" + texto + ": " + sentiment + "\n";
      resultado.append(res).append("\n");
    }
    return traductor.traducirAEspanol(resultado.toString());
  }
}
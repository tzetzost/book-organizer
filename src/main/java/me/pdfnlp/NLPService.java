package me.tstefanov.pdfnlp.service;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreEntityMention;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class NLPService {

    private final StanfordCoreNLP pipeline;

    public NLPService() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
        pipeline = new StanfordCoreNLP(props);
    }

    public List<String> extractTags(String content) {
        CoreDocument document = new CoreDocument(content);
        pipeline.annotate(document);

        List<String> tags = new ArrayList<>();
        for (CoreEntityMention entityMention : document.entityMentions()) {
            tags.add(entityMention.text());
        }
        return tags;
    }
}

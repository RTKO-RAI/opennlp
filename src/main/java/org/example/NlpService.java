package org.example;

import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;

import java.io.InputStream;
import java.util.Objects;

public class NlpService {
    private final Tokenizer tokenizer;
    private final DocumentCategorizerME categorize;

    public NlpService() throws Exception {
        this.tokenizer = SimpleTokenizer.INSTANCE;

        try (InputStream modelIn = getClass().getResourceAsStream("/models/en-leave-intent.bin")) {
            DoccatModel model = new DoccatModel(Objects.requireNonNull(modelIn));
            this.categorize = new DocumentCategorizerME(model);
        }
    }
    public String getIntent(String text) {
        String[] tokens = tokenizer.tokenize(text);

        double[] outcomes = categorize.categorize(tokens);
        return categorize.getBestCategory(outcomes);
    }

}

package services;

import org.apache.lucene.morphology.LuceneMorphology;
import org.apache.lucene.morphology.russian.RussianLuceneMorphology;

import java.io.IOException;
import java.util.*;

public class Morphology {

    private static Map<List<String>, Integer> lemmMap = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {


        //getSetLemmas("Вася или Петя пошел в лес, а также в поле; лишь Саша не пошел - но он почти герой");
        Map<List<String>, Integer> lemTextMap = getSetLemmas("Повторное появление леопарда в Осетии позволяет предположить, что леопард постоянно обитает в некоторых районах Северного Кавказа.");
        System.out.println(lemTextMap);
    }

    private static Map<List<String>, Integer> getSetLemmas(String text) throws IOException {
        //RussianAnalyzer analyzer = new RussianAnalyzer();
        LuceneMorphology luceneMorph = new RussianLuceneMorphology();
        String[] disassembledText = text.trim().split("\\s+");

        for (String str : disassembledText) {
            String s = str.toLowerCase().replaceAll("[\\p{Punct}\\s&&[^\\h]]", "");
            if (!(s == null || s.isEmpty() || s.trim().isEmpty())){
                List<String> wordBaseForms = luceneMorph.getMorphInfo(s);
                for (String word : wordBaseForms){
                if (!(word.contains("СОЮЗ") || word.contains("МЕЖД") || word.contains("ПРЕДЛ") || word.contains("ЧАСТ"))) {
                    List<String> lemmaForms = luceneMorph.getNormalForms(s);
                    Integer countlemm = lemmMap.get(lemmaForms);
                    if (countlemm == null){
                        lemmMap.put(lemmaForms, 1);
                        continue;
                    }
                    else {countlemm++;
                    lemmMap.replace(lemmaForms, countlemm);}
                }
              }
            }
        }
        return lemmMap;
    }

}

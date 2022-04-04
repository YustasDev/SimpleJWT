package services;

import org.apache.lucene.morphology.LuceneMorphology;
import org.apache.lucene.morphology.english.EnglishAnalyzer;
import org.apache.lucene.morphology.russian.RussianAnalyzer;
import org.apache.lucene.morphology.russian.RussianLuceneMorphology;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Morphology {

    public static void main(String[] args) throws IOException {

        LuceneMorphology luceneMorph = new RussianLuceneMorphology();
//        List<String> wordBaseForms = luceneMorph.getNormalForms("леса");
//        wordBaseForms.forEach(System.out::println);

        List<String> wordBaseForms = luceneMorph.getMorphInfo("или");
        wordBaseForms.forEach(System.out::println);



    }

    private static Set<String> getSetLemmas(String text) throws IOException {
        RussianAnalyzer analyzer = new RussianAnalyzer();









        return null;
    }

}

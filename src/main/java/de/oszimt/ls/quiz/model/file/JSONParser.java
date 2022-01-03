package de.oszimt.ls.quiz.model.file;


import java.io.File;
import java.io.FileWriter;

import javax.json.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import de.oszimt.ls.quiz.StartQuiz;
import de.oszimt.ls.quiz.model.Model;
import de.oszimt.ls.quiz.model.Schueler;
import de.oszimt.ls.quiz.model.Spielstand;


public class JSONParser {

private String File;

    public JSONParser(String file) {
        this.File = file;
    }



    public void speichern(Model model){

            JsonObjectBuilder builder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            for (Schueler schueler : model.getAlleSchueler()) {
                arrayBuilder.add(Json.createObjectBuilder()
                        .add("name", schueler.getName())
                        .add("joker", schueler.getJoker())
                        .add("blamiert", schueler.getBlamiert())
                        .add("fragen", schueler.getFragen()));
            }

            builder.add("schueler", arrayBuilder);

            JsonObject jo = builder.build();

        try {

            FileWriter fw = new FileWriter("Klasse.json");
            JsonWriter jw = Json.createWriter(fw);
            jw.write(jo);
            jw.close();
            fw.close();



        } catch (Exception e) {
            StartQuiz.showException(e, "Speichern der Datei fehlgeschlagen!");

        }


    }

}

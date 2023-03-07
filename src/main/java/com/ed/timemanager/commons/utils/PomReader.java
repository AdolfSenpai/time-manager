package com.ed.timemanager.commons.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PomReader {
    
    public String getProjectVersion() {

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        xmlInputFactory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);

        try {

            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream("pom.xml"));
            String version = "version not found";
            Deque<String> parents = new ArrayDeque<>();

            while (reader.hasNext()) {

                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {

                    StartElement element = event.asStartElement();
                    String elementName = element.getName().getLocalPart();

                    if ("version".equalsIgnoreCase(elementName) && "project".equalsIgnoreCase(parents.peek())) {

                        version = reader.nextEvent().asCharacters().getData();
                        break;
                    }
                    parents.push(elementName);
                }
                else if (event.isEndElement()) {

                    parents.pop();
                }
            }
            reader.close();

            return version;
        } 
        catch (FileNotFoundException | XMLStreamException e) {
            
            throw new IllegalStateException("Error occurred while reading project version", e);
        }
    }
}

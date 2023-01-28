package com.ed.timemanager.commons.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
        
        try {

            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream("pom.xml"));
            String version = "version not found";

            while (reader.hasNext()) {

                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {

                    StartElement element = event.asStartElement();
                    if (element.getName().getLocalPart().equalsIgnoreCase("version")) {

                        version = event.asCharacters().getData();
                        break;
                    }
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

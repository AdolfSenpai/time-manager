package com.ed.timemanager.commons.components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtPrivateKey {
    //region Fields

    @Value("${application.jwt-key-filename}")
    private String jwtKeyFilename;

    private String cachedJwtKey = null;

    //endregion
    //region Public

    public String get() {

        if (this.cachedJwtKey == null) {

            this.cachedJwtKey = this.readFile();
        }

        return this.cachedJwtKey;
    }

    //endregion
    //region Private

    private String readFile() {

        StringBuilder resultStringBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(this.jwtKeyFilename))) {
            
            String line;
            
            while ((line = br.readLine()) != null) {
                
                resultStringBuilder.append(line).append("\n");
            }
        }
        catch (IOException e) {

            throw new IllegalStateException("Error occurred while reading private key for jwt from file.", e);
        }
        return resultStringBuilder.toString();
    }

    //endregion
}

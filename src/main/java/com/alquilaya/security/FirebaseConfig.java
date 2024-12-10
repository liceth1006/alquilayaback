package com.alquilaya.security;



import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
	
	
	  @Value("${service-account-file}")
	    private String serviceAccountFile;
	  
	@PostConstruct
    public void initFirebase() throws IOException {
        // Ruta del archivo de credenciales del servicio
        FileInputStream serviceAccount = new FileInputStream(serviceAccountFile);

        // Configuración usando el método actualizado
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        // Inicializa Firebase App
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }
}

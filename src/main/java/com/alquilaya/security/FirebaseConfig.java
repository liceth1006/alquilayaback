package com.alquilaya.security;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
	
	@PostConstruct
    public void initFirebase() throws IOException {
        // Ruta del archivo de credenciales del servicio
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase-service-account.json");

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

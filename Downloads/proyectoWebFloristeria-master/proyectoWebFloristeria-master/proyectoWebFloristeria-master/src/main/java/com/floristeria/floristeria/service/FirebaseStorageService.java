package com.floristeria.floristeria.service;

import org.springframework.web.multipart.MultipartFile;

public interface FirebaseStorageService {

    public String cargaImagen(MultipartFile archivoLocalCliente, 
            String carpeta, Long id);

    //El BuketName es el <id_del_proyecto> + ".appspot.com"
    final String BucketName = "floristeria-f5cbd.appspot.com";

    //Esta es la ruta básica de este proyecto Floristeria
    final String rutaSuperiorStorage = "floristeria";

    //Ubicación donde se encuentra el archivo de configuración Json
    final String rutaJsonFile = "firebase";
    
    //El nombre del archivo Json
    final String archivoJsonFile = "floristeria-f5cbd-firebase-adminsdk-sgvlc-67a233ea2d.json";
}

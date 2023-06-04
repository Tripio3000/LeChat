package ru.java.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import ru.java.api.domain.PictureEntity;
import ru.java.api.repository.PictureRepository;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final String DIRPATH = "/Users/alex/Diploma/kerascode/";

    public Resource getFilePathByMessageId(UUID id) {
        String file = DIRPATH + pictureRepository.findFilePathByMessageId(id).getFilePath();
        
        try {
            Path filePath = Paths.get(file);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read file: " + file);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + file, e);
        }
    }
}

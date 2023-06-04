package ru.java.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.java.api.service.PictureService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class PictureController {

    private final PictureService pictureService;
    @GetMapping("/picture/{id}")
    public ResponseEntity<Resource> getPictureByMessageId(@PathVariable("id") UUID messageId) throws IOException {
        Resource file = pictureService.getFilePathByMessageId(messageId);
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.IMAGE_JPEG);
        respHeaders.setContentLength(file.contentLength());
        respHeaders.setContentDispositionFormData(
                "attachment", URLEncoder.encode(Objects.requireNonNull(file.getFilename()), StandardCharsets.UTF_8));

        return ResponseEntity.ok().headers(respHeaders).body(file);
    }
}

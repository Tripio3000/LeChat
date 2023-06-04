package ru.java.api.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface PictureService {

    Resource getFilePathByMessageId(UUID id);
}

package com.gesti.bank.service;

import java.io.File;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
  public void init();

  public void save(MultipartFile file);

  public Resource load(String folder, String id, String filename);

  public void deleteAll();

  public Stream<Path> loadAll();
  
  public boolean deleteDirectory(File directoryToBeDeleted);
}

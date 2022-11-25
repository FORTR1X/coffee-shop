package ru.shop.coffee.service;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

  private static final String UPLOADS_ABSOLUTE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\uploads\\bg";

  private void writePhotoStock(Integer id, MultipartFile file) {
    try {
      Path productDirPath = Paths.get(UPLOADS_ABSOLUTE_PATH + "\\" + id + ".jpg");
      Files.write(productDirPath, file.getBytes());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public List<String> addPhotoStock(Integer id, MultipartFile file) {
    writePhotoStock(id, file);
    return getStocks();
  }

  public List<String> deletePhotoStock(Integer id) {
    try {
      File file = new File(UPLOADS_ABSOLUTE_PATH + "\\" + id + ".jpg");
      if (file.exists())
        file.delete();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return getStocks();
  }

  public List<String> getStocks() {
    File stockFolder = new File(UPLOADS_ABSOLUTE_PATH);
    File[] stockImages = stockFolder.listFiles();

    if (stockImages == null) return new ArrayList<>();

    List<String> stocksFilesName = new ArrayList<>();
    for (File image : stockImages) {
      stocksFilesName.add(image.getName());
    }

    return stocksFilesName;
  }

  public List<String> editIdStock(Integer prevId, Integer newId) {
    try {
      File prevFile = new File(UPLOADS_ABSOLUTE_PATH + "\\" + prevId + ".jpg");
      if (!prevFile.exists()) throw new FileNotFoundException();

      File renameFileTo = new File(UPLOADS_ABSOLUTE_PATH + "\\" + newId + ".jpg");
      boolean isRenamed = prevFile.renameTo(renameFileTo);
      if (!isRenamed) throw new InvalidFileNameException("Переименование файла", "Не удалось переименовать файл");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return getStocks();
  }

  public List<String> editPhotoStock(Integer id, MultipartFile photo) {
    try {
      File currentPhoto = new File(UPLOADS_ABSOLUTE_PATH + "\\" + id + ".jpg");
      if (!currentPhoto.exists()) throw new FileNotFoundException();

      writePhotoStock(id, photo);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return getStocks();
  }
}

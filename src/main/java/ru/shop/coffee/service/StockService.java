package ru.shop.coffee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

  private static final String UPLOADS_ABSOLUTE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\uploads\\bg";


  public void addPhotoStock(Integer id, MultipartFile file) {
    try {
      Path productDirPath = Paths.get(UPLOADS_ABSOLUTE_PATH + "\\" + id + ".jpg");
      Files.write(productDirPath, file.getBytes());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void deletePhotoStock(Integer id) {
    try {
      File file = new File(UPLOADS_ABSOLUTE_PATH + "\\" + id + ".jpg");
      if (file.exists())
        file.delete();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public List<String> getStocks() {
    File stockFolder = new File(UPLOADS_ABSOLUTE_PATH);
    File[] stockImages =  stockFolder.listFiles();

    if (stockImages == null) return new ArrayList<>();

    List<String> stocksFilesName = new ArrayList<>();
    for (File image : stockImages) {
      stocksFilesName.add(image.getName());
    }

    return stocksFilesName;
  }
}

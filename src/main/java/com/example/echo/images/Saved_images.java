package com.example.echo.images;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Color;

public class Saved_images {
    //様々な拡張子の付いた画像をjpgに統一した上で保存
    public void Saved_image(MultipartFile file){
        if (!file.isEmpty()) {
            try {
                File savefile = new File("src/main/resources/static/img/icon/" + file.getOriginalFilename());
                byte[] bytes = file.getBytes();
                FileOutputStream fos = new FileOutputStream(savefile);
                fos.write(bytes);
                fos.close();

                //File file = new File("C:/Users/202152/Desktop/api_test/demo/src/main/java/com/example/demo/api/news_gohou.tiff");
                File file2 = new File("src/main/resources/static/img/icon/" + file.getOriginalFilename());
                BufferedImage image = ImageIO.read(file2);
        
                // 画像を JPG フォーマットに変換する
                BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
                newImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
        
                // 出力画像を保存する
                ImageIO.write(newImage, "jpg", new File("src/main/resources/static/img/icon/" + file.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
                
            }
        } else {
            
        }
    }
    //様々な拡張子の付いた画像をjpgに統一した上で、アイコン用に縦横比やサイズに調整を加えて保存
    public void saved_icon(MultipartFile file){
        if (!file.isEmpty()) {
            try {
                File savefile = new File("src/main/resources/static/img/icon/" + file.getOriginalFilename());
                byte[] bytes = file.getBytes();
                FileOutputStream fos = new FileOutputStream(savefile);
                fos.write(bytes);
                fos.close();

                //File file = new File("C:/Users/202152/Desktop/api_test/demo/src/main/java/com/example/demo/api/news_gohou.tiff");
                File file2 = new File("src/main/resources/static/img/icon/" + file.getOriginalFilename());
                BufferedImage image = ImageIO.read(file2);
        
                // 画像を JPG フォーマットに変換する
                BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
                newImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
        
                // リサイズ後の幅
                int width = 1080;
                // リサイズ後の高さ
                int height = 1080;

                // リサイズする
                BufferedImage outputImage = new BufferedImage(width, height, newImage.getType());
                outputImage.getGraphics().drawImage(newImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
                // 出力画像を保存する
                ImageIO.write(outputImage, "jpg", new File("src/main/resources/static/img/icon/" + "001" + ".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
                
            }
        } else {
            
        }
    }
}

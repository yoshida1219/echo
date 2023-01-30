package com.example.echo.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.services.youtube.YouTube;
import com.example.echo.config.Youtube_key;
// 必要なライブラリのインポート
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;


import java.io.IOException;
import java.io.File;

import java.awt.Image;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.ExecutorService;

@Service
@Transactional
public class Saved_thumbnail{

    public List<String> getYoutubeInf(String str, String url) throws IOException{
        
        // YouTube APIのクライアントを作成
        YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {}
        }).setApplicationName("youtube-api-example").build();

        List<String> sample_list = new ArrayList<String>();

        // 検索した際に、APIから受け取る値をlistに入れる
        YouTube.Search.List searchRequest = youtube.search().list("id,snippet");
        

        //youtube apiのアクセスキー
        searchRequest.setKey(str);
        
        /* 
        //検索する動画のurlを代入し、動画IDだけを抜き取る
        String videoId = "https://www.youtube.com/watch?v=" + url;
        videoId = videoId.replace("https://www.youtube.com/watch?v=", "");
        */

        //検索するIDをここに入れる
        searchRequest.setQ(url);

        //検索結果の上位何件までを取得するか指定する
        searchRequest.setMaxResults(1L);

        //なんかしてます
        SearchListResponse searchResponse = searchRequest.execute();

        //検索結果をsearchResultsに代入する
        List<SearchResult> searchResults = searchResponse.getItems();

        //検索結果が1件以上だった場合は検索結果を出力する
        if (searchResults.size() > 0) {

            /*
            * 「id」：動画のIDを表す項目です。検索結果から、各動画のIDを取り出すことができます。
            * 「snippet」：動画の情報を表す項目です。検索結果から、各動画のタイトルや説明文、登録日時、サムネイル画像のURLなど、動画の情報を取り出すことができます。
            *  idの項目を取得する場合は「getId()」の後ろに、snippetの項目を取得する場合は「getSnippet()」の後ろに「get〇〇()」を置く
            */

            //検索結果の1件目のみを変数resultに代入する
            SearchResult ifSearchResult = searchResults.get(0);

            sample_list.add(ifSearchResult.getId().getVideoId());
            sample_list.add(ifSearchResult.getId().getKind());

            sample_list.add(ifSearchResult.getSnippet().getPublishedAt().toString());
            sample_list.add(ifSearchResult.getSnippet().getChannelId());
            sample_list.add(ifSearchResult.getSnippet().getTitle());
            sample_list.add(ifSearchResult.getSnippet().getDescription());
            sample_list.add(ifSearchResult.getSnippet().getChannelTitle());
            
            boolean checkFlag = false;
            String[] imageQuality = {"maxresdefault", "sddefault", "hqdefault", "mqdefault", "default"};

            String imageUrl = "";

            boolean imageExistCheck = false;
            int saveArea = 0;
            for(int count = 0; count < imageQuality.length; count++){
                File file = new File("src/main/resources/static/img/thumbnail/" + url + ".jpg");
                if (file.exists()) {
                    imageExistCheck = true;
                    saveArea = count;
                    break;
                }
            }
            
            String saveQuality = "";
            //画像がすでに保存されてあるかテェック
            if(imageExistCheck){
                sample_list.add("../img/thumbnail/" + url + ".jpg");
                
            }else{
                for(int imageQualityCheck = 0; imageQualityCheck < imageQuality.length; imageQualityCheck++){

                    try {
                        // 画像を表示しているURL
                        URL imageCheck = new URL("https://img.youtube.com/vi/" + ifSearchResult.getId().getVideoId() + "/"  + imageQuality[imageQualityCheck] + ".jpg");
                        
                        // URLから画像を読み込む
                        InputStream inputStream = imageCheck.openStream();
                        BufferedImage image = ImageIO.read(inputStream);
            
                        // 画像が読み込めた場合
                        if (image != null) {
                            sample_list.add("https://img.youtube.com/vi/" + ifSearchResult.getId().getVideoId() + "/" + imageQuality[imageQualityCheck] + ".jpg");
                            imageUrl = "https://img.youtube.com/vi/" + ifSearchResult.getId().getVideoId() + "/"  + imageQuality[imageQualityCheck] + ".jpg";
                            saveQuality = imageQuality[imageQualityCheck];
                            checkFlag = true;
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                  }
    
                  
                  if(!(checkFlag)){
                    sample_list.add("https://img.youtube.com/vi/" + ifSearchResult.getId().getVideoId() + "/maxresdefault.jpg");
                    imageUrl = "https://img.youtube.com/vi/" + ifSearchResult.getId().getVideoId() + "/maxresdefault.jpg";
                  }
    
                  //画像保存
                  try {
                    // URLから画像データを読み込む
                    URL imgUrl = new URL(imageUrl);
                    BufferedImage image = ImageIO.read(imgUrl);
            
                    // 画像データを保存する
                    ImageIO.write(image, "jpg", new File("src/main/resources/static/img/thumbnail/" + url + ".jpg"));
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
            }

            return sample_list;
        
        }else{
            //検索結果が0件だった場合はエラーを出力する。
            //System.out.println("error");
        }
            return null;
    }

    public String savedThumbnail(String url) throws IOException{
            // YouTube APIのクライアントを作成
            YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {}
            }).setApplicationName("youtube-api-example").build();
    
            // 検索した際に、APIから受け取る値をlistに入れる
            YouTube.Search.List searchRequest = youtube.search().list("id,snippet");
            
            Youtube_key access = new Youtube_key();

            String key = access.get_access();
            //youtube apiのアクセスキー
            searchRequest.setKey(key);
            
            String postUrl;
            if(url.substring(0, 1).equals("-")){
                postUrl = "- " + url.substring(1);
            }else{
                postUrl = url;
            }
            //検索するIDをここに入れる
            searchRequest.setQ(postUrl);
    
            //検索結果の上位何件までを取得するか指定する
            searchRequest.setMaxResults(1L);
    
            //なんかしてます
            SearchListResponse searchResponse = searchRequest.execute();
    
            //検索結果をsearchResultsに代入する
            List<SearchResult> searchResults = searchResponse.getItems();

            String[] imageQuality = {"maxresdefault", "sddefault", "hqdefault", "mqdefault", "default"};

            boolean checkFlag = false;
            String imageUrl = "";
    
            boolean imageExistCheck = false;


            String title = "";
            long start = 0L;
            long stop = 0L;
            int imageQualityJudge = 0;
    
            //検索結果が1件以上だった場合は検索結果を出力する
            if (searchResults.size() > 0) {
    
                /*
                * 「id」：動画のIDを表す項目です。検索結果から、各動画のIDを取り出すことができます。
                * 「snippet」：動画の情報を表す項目です。検索結果から、各動画のタイトルや説明文、登録日時、サムネイル画像のURLなど、動画の情報を取り出すことができます。
                *  idの項目を取得する場合は「getId()」の後ろに、snippetの項目を取得する場合は「getSnippet()」の後ろに「get〇〇()」を置く
                */
    
                //検索結果の1件目のみを変数resultに代入する
                SearchResult ifSearchResult = searchResults.get(0);
                

                title = ifSearchResult.getSnippet().getTitle();
                start =  System.currentTimeMillis();
                //thumbnailフォルダにサムネイル画像が保存されているか判定する
                File file = new File("src/main/resources/static/img/thumbnail/" + url + ".jpg");
                if (file.exists()) {
                    imageExistCheck = true;
                }
            
                
                
                //画像がすでに保存されてあるかテェック
                if(!(imageExistCheck)){
                    
                    

                        // try {
                        //     System.out.println("保存try");
                        //     start = System.currentTimeMillis();
                        //     // 画像を表示しているURL
                        //     URL imageCheck = new URL("https://img.youtube.com/vi/" + url + "/"  + imageQuality[imageQualityCheck] + ".jpg");

                        //     /* 時間計測 */
                        //     stop = System.currentTimeMillis();
                        //     System.out.println("" + (stop - start) + " ms");
                        //     start = System.currentTimeMillis();
                            
                        //     // URLから画像を読み込む
                        //     InputStream inputStream = imageCheck.openStream();
                        //     BufferedImage image = ImageIO.read(inputStream);

                        //     /* 時間計測 */
                        //     stop = System.currentTimeMillis();
                        //     System.out.println("" + (stop - start) + " ms");
                        //     start = System.currentTimeMillis();
                
                        //     // 画像が読み込めた場合
                        //     if (image != null) {
                                
                        //         System.out.println(imageQuality[imageQualityCheck] + " OK");
                        //         imageUrl = "https://img.youtube.com/vi/" + url + "/"  + imageQuality[imageQualityCheck] + ".jpg";
                        //         checkFlag = true;

                        //         /* 時間計測 */
                        //         stop = System.currentTimeMillis();
                        //         System.out.println("成功" + (stop - start) + " ms");
                        //         start = System.currentTimeMillis();

                        //         break;
                        //     }
                        //     System.out.println(imageQuality[imageQualityCheck] + " NG");
                        //     /* 時間計測 */
                        //     stop = System.currentTimeMillis();
                        //     System.out.println("失敗" + (stop - start) + " ms");
                        //     start = System.currentTimeMillis();
                        // } catch (Exception e) {
                        //     e.printStackTrace();
                        // }
                        ExecutorService executor = Executors.newFixedThreadPool(10);
                        String[] imageUrls = {"https://img.youtube.com/vi/" + url + "/" + imageQuality[0] + ".jpg",
                                            "https://img.youtube.com/vi/" + url + "/" + imageQuality[1] + ".jpg",
                                            "https://img.youtube.com/vi/" + url + "/" + imageQuality[2] + ".jpg",
                                            "https://img.youtube.com/vi/" + url + "/" + imageQuality[3] + ".jpg",
                                            "https://img.youtube.com/vi/" + url + "/" + imageQuality[4] + ".jpg"};

                        start = System.currentTimeMillis();
                        List<Future<Image>> futureImages = new ArrayList<>();

                        for(String imageUrlBaby : imageUrls){
                            
                            Future<Image> futureImage = executor.submit(new Callable<Image>() {
                                public Image call() throws IOException {
                                    return ImageIO.read(new URL(imageUrlBaby));
                                }
                            });
                            futureImages.add(futureImage);
                        }

                        for(Future<Image> futureImage : futureImages){
                            System.out.print(imageUrls[imageQualityJudge]);
                            try {
                                Image image = futureImage.get();
                                if (image != null) {
                                    System.out.println("OK");
                                    imageUrl = imageUrls[imageQualityJudge];
                                    checkFlag = true;
                                    break;
                                    
                                } else {
                                    System.out.println("NG");
                                }
                            } catch (InterruptedException | ExecutionException e) {
                                e.printStackTrace();
                            }
                            imageQualityJudge++;

                        }
                        stop = System.currentTimeMillis();
                        System.out.println("処理時間:" + (stop - start) + " ms");
                        

                    }
                    
                    
                    if(!(checkFlag)){
                    imageUrl = "https://img.youtube.com/vi/" + url + "/maxresdefault.jpg";
                    }

                    /* 時間計測 */
                    stop = System.currentTimeMillis();
                    System.out.println("try開始" + (stop - start) + " ms");
                    start = System.currentTimeMillis();
                    //画像保存
                    try {
                        
                        // URLから画像データを読み込む
                        URL imgUrl = new URL(imageUrl);
                        BufferedImage image = ImageIO.read(imgUrl);
                
                        // 画像データを保存する
                        ImageIO.write(image, "jpg", new File("src/main/resources/static/img/thumbnail/" + url + ".jpg"));

                          // リサイズ後の幅
                        int width = 1200;
                        // リサイズ後の高さ
                        int height = 700;

                        // 入力画像を読み込む
                        BufferedImage inputImage = ImageIO.read(new File("src/main/resources/static/img/thumbnail/" + url + ".jpg"));
                        // リサイズする
                        BufferedImage outputImage = new BufferedImage(width, height, inputImage.getType());
                        outputImage.getGraphics().drawImage(inputImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
                        // 出力画像を保存する
                        ImageIO.write(outputImage, "jpg", new File("src/main/resources/static/img/thumbnail/" + url + ".jpg"));


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                
                
            }
            /* 時間計測 */
            stop = System.currentTimeMillis();
            System.out.println("try終了" + (stop - start) + " ms");
            return title;

        }

    /*
    public void changeImage() throws IOException{
        // 画像を読み込む
        File file = new File("img/news_gohou.png");
        BufferedImage image = ImageIO.read(file);

        // 画像を JPG フォーマットに変換する
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        newImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);

        // 変換した画像を新しいファイルに保存する
        File newFile = new File("img/news_gohou.jpg");
        ImageIO.write(newImage, "jpg", newFile);

        
    }

    
    public void updatethumbnail(Movie movie) {
        repository.save(movie);
        
    }
    */

}

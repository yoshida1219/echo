package com.example.echo;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.echo.api.Saved_thumbnail;
import com.example.echo.entity.Movie;
import com.example.echo.entity.Response;
import com.example.echo.entity.User;
import com.example.echo.entity.select.ThreadDetail;
import com.example.echo.form.CreateUserForm;
import com.example.echo.form.ResponseCreateForm;
import com.example.echo.service.Movie.MovieService;
import com.example.echo.service.Response.ResponseService;
import com.example.echo.service.User.UserService;
import com.example.echo.service.ApiAccount.ApiAccountService;

@Component
public class Collection {  
    private final UserService userService;
    private final MovieService movieService;
    private final ResponseService responseService;
    private final ApiAccountService apiAccountService;

    private String movie_id = "";

    @Autowired
    public Collection(
        UserService userService,
        MovieService movieService,
        ResponseService responseService,
        ApiAccountService apiAccountService
    ) {
        this.userService = userService;
        this.movieService = movieService;
        this.responseService = responseService;
        this.apiAccountService = apiAccountService;
    }

    /*
     * 最大のIDを渡す -> +1されたIDが返る 
     */
    public String createId(String id) {
        String id_head = id.substring(0, 1);
        int id_length = id.length();
        id = id.substring(1, id.length());

        String format = "%0" + Integer.toString(id_length-1) + "d";
        int id_int = Integer.parseInt(id) + 1;

        id = String.format(format, id_int);
        id = id_head + id;

        return id;
    }

    /*
     * CreateUserForm から User への詰め替え
     */
    public User makeUser(CreateUserForm form) {
        User user = new User();

        user.setSearch_name("@" + form.getSearch_name());
        user.setUser_name(form.getUser_name());
        user.setPassword(form.getPass());

        String user_id = userService.selectUserIdMax();
        user.setUser_id(createId(user_id));

        return user;
    }

    /*
     * mail の重複チェック
     * 
     * していない -> true
     * 重複している -> false
     */
    /*public boolean checkMail(String mail) {
        Optional<String> mailOpt = userService.selectMail(mail);
        if(mailOpt.isPresent()) {
            return false; 
        }
        return true;
    }
    */

    /*
     * search_name の重複チェック
     * 
     * していない -> true
     * 重複している -> false
     */
    public boolean checkSearchName(String search_name) {
        Optional<String> searchNameOpt = userService.selectSearchName(search_name);
        if(searchNameOpt.isPresent()) {
            return false; 
        }
        return true;
    }

    /*
     * 検索のエスケープ
     * _,% -> \_, \% に変換
     */
    public String escape(String word) {
        String ret = "";

        for(int i=0; i<word.length(); i++) {
            String ws = word.substring(i,i+1);
            if (ws.equals("_")) {
                
                ws = "\\_";
            }else if (ws.equals("%")) {
                ws = "\\%";
            }
            ret += ws;
        }

        return ret;
    }

    /*
     * ResponseCreateForm　-> Movie　への詰め替え
     */
    public Movie makeMovie(ResponseCreateForm form) throws IOException{
        Movie movie = new Movie();
        Saved_thumbnail api = new Saved_thumbnail();
        String url = form.getUrl().substring(form.getUrl().length()-11);

        String movie_id = movieService.SelectMaxMovieId();
        movie_id = createId(movie_id);

        
        Optional<Movie> user = movieService.existsMovie(url);

        String[] movie_return = api.savedThumbnail(url, user, apiAccountService);
        

        String thumbnail = "https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/" + url + ".jpg";



        this.movie_id = movie_id;

        movie.setMovie_id(movie_id);
        movie.setMovie_name(movie_return[0]);
        movie.setThumbnail(thumbnail);
        movie.setUrl(url);
        movie.setMovie_time(movie_return[1]);


        return movie;
    }

    /*
     * ResponseCreateForm　-> Response　への詰め替え
     */
    public Response makeResponse(ResponseCreateForm form) {
        Response response = new Response();

        String response_id = responseService.selectMaxResponseId(form.getUser_id());

        if(response_id == null) {
            response_id = "R000001";
        }

        response.setResponse_creater(form.getUser_id());
        response.setResponse_id(createId(response_id));
        response.setResponse_name(form.getResponse_name());
        response.setThread_id(form.getThread_id());
        response.setMovie_id(this.movie_id);

        return response;
    }


    /*
     * レスポンス作成機能
     */
    public ThreadDetail response_create(ResponseCreateForm responseCreateForm) throws Exception {
        Response response = makeResponse(responseCreateForm);
        Integer response_count = responseService.ThreadResponseCount(response.getThread_id());
        

        //short動画を共有する際に付属する場合がある文章を削除
        if(responseCreateForm.getUrl().substring(responseCreateForm.getUrl().length() - 14).equals("?feature=share")){
            responseCreateForm.setUrl(responseCreateForm.getUrl().substring(0, responseCreateForm.getUrl().length() - 14));

            //普通の動画を共有する際に付属する場合がある文章を削除
        }else if(responseCreateForm.getUrl().substring(0, 15).equals("https://youtu.be")){
            responseCreateForm.setUrl("https://www.youtube.com/watch?v=" + responseCreateForm.getUrl().substring(responseCreateForm.getUrl().length()-11));
        }

        Optional<Movie> movie = movieService.selectMovieId(
            responseCreateForm.getUrl().substring(responseCreateForm.getUrl().length()-11));



        Movie new_movie = new Movie();
        //同じURLの動画がある -> その動画のIDを使う
        if(movie.isPresent()) {
            response.setMovie_id(movie.get().getMovie_id());
            new_movie = movie.get();
        }else {
            //同じURLの動画がない -> 登録する
            new_movie = makeMovie(responseCreateForm);
            movieService.insert(new_movie);

            response.setMovie_id(new_movie.getMovie_id());
        }

        responseService.insert(response);

        //画像ダウンロードを待つために、1秒止める
        try {
            java.lang.Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        Optional<User> user = userService.selectUser(response.getResponse_creater());

        //同じスレッドを見ているユーザに反映
        ThreadDetail detail = new ThreadDetail(
            response.getThread_id(),
            null,
            response.getResponse_id(),
            response.getResponse_name(),
            response.getResponse_creater(),
            response.getResponse_submit(),
            "0",
            "0",
            new_movie.getMovie_name(),
            new_movie.getUrl(),
            new_movie.getThumbnail(),
            responseCreateForm.getUser_id(),
            user.get().getUser_name(),
            user.get().getIcon(),

            //2023-02-08追加(阿部)
            response_count
        );

        return detail;
    }
    
    
}

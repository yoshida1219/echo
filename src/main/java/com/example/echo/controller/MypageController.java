package com.example.echo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.echo.entity.User;
import com.example.echo.api.Saved_thumbnail;
import com.example.echo.entity.Jenre;
import com.example.echo.entity.select.FavoriteMovie;
import com.example.echo.entity.select.Follower;
import com.example.echo.entity.select.MypageResponse;
import com.example.echo.entity.select.SubmitResponse;
import com.example.echo.service.FavoriteMovie.FavoriteMovieService;
import com.example.echo.service.Follower.FollowerService;
import com.example.echo.service.MypageResponse.MypageResponseService;
import com.example.echo.service.Recommend.RecommendService;
import com.example.echo.service.SubmitResponse.SubmitResponseService;
import com.example.echo.service.User.UserService;
import com.example.echo.session.SessionData;

import jakarta.websocket.Session;

import com.example.echo.service.Jenre.JenreService;


/*
 * マイページ機能
 */
@Controller
@RequestMapping("")
public class MypageController {
    private final UserService userService;
    private final FollowerService followerService;
    private final SubmitResponseService submitResponseService;
    private final FavoriteMovieService favoriteMovieService;
    private final MypageResponseService mypageResponseService;
    private final RecommendService recommendService;
    private final JenreService jenreService;

    private final SessionData sessionData;

    @Autowired
    public MypageController(
        UserService userService,
        FollowerService followerService,
        SubmitResponseService submitResponseService,
        FavoriteMovieService favoriteMovieService,
        MypageResponseService mypageResponseService,
        RecommendService recommendService,
        JenreService jenreService,

        SessionData sessionData
    ) {
        this.userService = userService;
        this.followerService = followerService;
        this.submitResponseService = submitResponseService;
        this.favoriteMovieService = favoriteMovieService;
        this.mypageResponseService = mypageResponseService;
        this.recommendService = recommendService;
        this.jenreService = jenreService;

        this.sessionData = sessionData;
    }


    /*
     * マイページを表示
     */
    @GetMapping("/mypage")
    public String showMypage(Model model, @RequestParam("user_id") String user_id) {

        String login_user = sessionData.getUser_id();

        Optional<User> list = userService.selectMypageUser(user_id);
        Optional<Integer> checkFollow = followerService.FindCheckFollow(login_user, user_id);
        Optional<Follower> follow = followerService.OrderFollower(user_id);
        Optional<Follower> follower = followerService.OrderFollowerPeople(user_id);
        Optional<SubmitResponse> responseCount = submitResponseService.findSubmitResponse(user_id);
        Iterable<FavoriteMovie> favoriteMovies = favoriteMovieService.OrderFavoriteMovie(user_id);
        Iterable<MypageResponse> mypageResponse = mypageResponseService.orderMypageResponse(user_id);

        model.addAttribute("login", login_user);
        model.addAttribute("checkFollow", checkFollow.get());
        model.addAttribute("list", list.get());
        model.addAttribute("follow", follow.get());
        model.addAttribute("follower", follower.get());
        model.addAttribute("responseCount", responseCount.get());
        model.addAttribute("favoriteMovieList", favoriteMovies);
        model.addAttribute("myResponseList",mypageResponse);
        
        Iterable<User> recommend = recommendService.FindRecommendUser(sessionData.getUser_id());
        model.addAttribute("recommend", recommend);
        

        return "mypage";
    }


    @GetMapping("/mediate")
    public String showMediate(Model model, RedirectAttributes redirectAttributes) {

        return "redirect:/mypage?user_id=" + sessionData.getUser_id();
    }

    @GetMapping("/edit")
    public String showEdit(Model model, @RequestParam("user_id") String user_id) {

        String login_user = sessionData.getUser_id();

        Iterable<Jenre> jenre = jenreService.findJenre(user_id);
        Optional<User> list = userService.selectMypageUser(user_id);

        //System.out.println("mypage：" + user_id + "　login：" + login_user);
        

        model.addAttribute("jenre", jenre);
        model.addAttribute("list", list.get());
        

        return "mypage_edit";
    }

    //プロフィールを編集する
    @PostMapping("/edit_change")
    public String changeEdit(Model model, @RequestParam("user_id") String user_id, @RequestParam("file") MultipartFile file) {

        String login_user = sessionData.getUser_id();
        Saved_thumbnail saved_thumbnail = new Saved_thumbnail();

        String icon = "https://skpacket.s3.ap-northeast-1.amazonaws.com/icon/" + user_id + ".jpg";
        saved_thumbnail.saved_icon(file, user_id);

        userService.updateIcon(user_id, icon);


        
        

        return "redirect:/mypage?user_id=" + user_id;
    }

    //人をフォローする（フォローを外す）
    @GetMapping("/follow")
    public String showFollow(Model model,  @RequestParam("user_id") String user_id, @RequestParam("check_follow") Integer check_follow, RedirectAttributes redirectAttributes) {
    
        if(check_follow == 0) {
            followerService.FollowInsert(sessionData.getUser_id(), user_id);
        }else {
            followerService.FollowDelete(sessionData.getUser_id(), user_id);

        }
        return "redirect:/mypage?user_id=" + user_id;

    }

    //動画をお気に入りにする
    @GetMapping("/favoriteMovie")
    public String showFavoriteMovie(Model model, @RequestParam("movie_id") String movie_id, @RequestParam("user_id") String user_id, @RequestParam("check") Integer check, RedirectAttributes redirectAttributes) {

        boolean deplicate_result;
        Boolean count_result;
        if(check==1) {
            favoriteMovieService.DeleteFavoriteMovie(user_id, movie_id);
        }else {
            deplicate_result = favoriteMovieService.CheckDeplicate(user_id, movie_id);
            count_result = favoriteMovieService.FavoriteMovieCount(user_id);
            if(deplicate_result && count_result) {
                favoriteMovieService.InsertFavoriteMovie(user_id, movie_id);
            }
        }
        return "redirect:/mypage?user_id=" + user_id;
    }

}

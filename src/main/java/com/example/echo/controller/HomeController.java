package com.example.echo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.echo.entity.Jenre;
import com.example.echo.entity.User;
import com.example.echo.Collection;
import com.example.echo.entity.select.SelectFollowerMovie;
import com.example.echo.form.ResponseCreateForm;
import com.example.echo.form.ThreadCreateForm;
import com.example.echo.service.Home.HomeService;
import com.example.echo.service.Jenre.JenreService;
import com.example.echo.service.Notice.NoticeService;
import com.example.echo.service.Recommend.RecommendService;
import com.example.echo.service.User.UserService;
import com.example.echo.session.SessionData;

/*
 * ホーム画面　の機能
 */
@Controller
@RequestMapping("")
public class HomeController {

    private final HomeService homeService;
    private final NoticeService noticeService;
    private final RecommendService recommendService;
    private final UserService userService;
    private final JenreService jenreService;

    private final SessionData sessionData;
    private final Collection collection;

    @Autowired
    public HomeController(
            HomeService homeService,
            NoticeService noticeService,
            RecommendService recommendService,
            UserService userService,
            JenreService jenreService,

            SessionData sessionData,
            Collection collection

    ) {
        this.homeService = homeService;
        this.noticeService = noticeService;
        this.recommendService = recommendService;
        this.userService = userService;
        this.jenreService = jenreService;

        this.sessionData = sessionData;
        this.collection = collection;
    }

    @ModelAttribute
    public SessionData setUpSessionData() {
        return new SessionData();
    }

    @ModelAttribute
    public ResponseCreateForm setUpResponseCreateForm() {
        return new ResponseCreateForm();
    }
    
    @ModelAttribute
    public ThreadCreateForm setUpForm() {
        return new ThreadCreateForm();
    }

    /*
     * URL : /home
     * 
     * ホーム画面
     */
    @GetMapping("/home")
    public String showHome(Model model) {
        String return_word = "redirect:/";

        if (sessionData.getUser_id() != null) {

            Iterable<SelectFollowerMovie> list = homeService.selectFollowerMovie(sessionData.getUser_id());

            model.addAttribute("timeline", list);

            Iterable<User> recommend = recommendService.FindRecommendUser(sessionData.getUser_id());
            model.addAttribute("recommend", recommend);

            Iterable<User> followNotice = noticeService.FindNoticeFollow(sessionData.getUser_id());

            model.addAttribute("follow_notice", followNotice);

            Optional<User> side_user = userService.selectMypageUser(sessionData.getUser_id());
            model.addAttribute("side_user", side_user.get());

            Iterable<Jenre> jenre = jenreService.selectAll();
            model.addAttribute("jenre", jenre);

            return_word = "home";
        }



        return return_word;

    }

    /*
     * ホーム画面から投稿する
     */
    @GetMapping("/response_creater")
    public String responseCreate(Model model, @Validated ResponseCreateForm responseCreateForm) throws Exception {
        responseCreateForm.setThread_id(null);
        collection.response_create(responseCreateForm);

        return "redirect:/home";
    }

    @GetMapping("/notice")
    public String showNoticeCheck(Model model, @RequestParam("user_id") String user_id) {
        noticeService.FindUpdateFollow(user_id, sessionData.getUser_id());
        return "redirect:/mypage?user_id=" + user_id;
    }
}

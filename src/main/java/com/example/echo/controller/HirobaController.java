package com.example.echo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.echo.entity.Response;
import com.example.echo.entity.Thread;
import com.example.echo.entity.User;
import com.example.echo.entity.View_response;
import com.example.echo.entity.select.FollowThread;
import com.example.echo.entity.select.JenreThread;
import com.example.echo.entity.select.MyThread;
import com.example.echo.entity.select.PopularThread;
import com.example.echo.entity.select.SelectResponse;
import com.example.echo.entity.select.SharedThread;
import com.example.echo.entity.select.SpikeUpMovie;
import com.example.echo.entity.select.SubmitResponse;
import com.example.echo.entity.select.ThreadDetail;
import com.example.echo.entity.select.ThreadList;
import com.example.echo.form.AlertFrom;
import com.example.echo.form.CommentCreateForm;
import com.example.echo.form.CommentPosting;
import com.example.echo.form.ResponseCreateForm;
import com.example.echo.form.ThreadCreateForm;
import com.example.echo.Collection;
import com.example.echo.entity.Comment;
import com.example.echo.entity.Jenre;
import com.example.echo.entity.Movie;
import com.example.echo.service.Comment.CommentService;
import com.example.echo.service.FollowThread.FollowThreadService;
import com.example.echo.service.Follower.FollowerService;
import com.example.echo.service.Jenre.JenreService;
import com.example.echo.service.Movie.MovieService;
import com.example.echo.service.MyThread.MyThreadService;
import com.example.echo.service.Notice.NoticeService;
import com.example.echo.service.PopularThread.PopularThreadService;
import com.example.echo.service.Recommend.RecommendService;
import com.example.echo.service.Response.ResponseService;
import com.example.echo.service.SelectResponse.SelectResponseService;
import com.example.echo.service.SharedThread.SharedThreadService;
import com.example.echo.service.SpikeUpMovie.SpikeUpMovieService;
import com.example.echo.service.SubmitResponse.SubmitResponseService;
import com.example.echo.service.Thread.ThreadService;
import com.example.echo.service.ThreadDetail.ThreadDetailService;
import com.example.echo.service.ThreadList.ThreadListService;
import com.example.echo.service.User.UserService;
import com.example.echo.service.View_response.View_responseService;
import com.example.echo.session.SessionData;

/*
 * 広場画面　の機能
 */
@Controller
@RequestMapping("/Hiroba")
public class HirobaController {
    @Autowired
    CommentService commentservice;

    @Autowired
    MovieService movieservice;

    @Autowired
    SharedThreadService sharedThreadService;

    @Autowired
    ResponseService responseService;

    @Autowired
    SelectResponseService selectResponseService;

    @Autowired
    PopularThreadService popularThreadservice;

    @Autowired
    FollowThreadService followthreadservice;

    @Autowired
    MyThreadService mythreadservice;

    @Autowired
    ThreadListService threadListService;

    @Autowired
    ThreadDetailService threadDetailService;

    @Autowired
    JenreService jenreService;

    @Autowired
    ThreadService threadService;

    @Autowired
    SpikeUpMovieService spikeUpMovieService;

    @Autowired
    FollowerService followerService;

    @Autowired
    SubmitResponseService submitResponseService;

    @Autowired
    NoticeService noticeService;

    @Autowired
    Collection collection;

    @Autowired
    RecommendService recommendService;

    @Autowired
    UserService userService;

    @Autowired
    SessionData sessionData;

    @Autowired
    View_responseService view_responseService;

    @ModelAttribute
    public ThreadCreateForm setUpForm() {
        return new ThreadCreateForm();
    }

    @ModelAttribute
    public ResponseCreateForm setUpResponseCreateForm() {
        return new ResponseCreateForm();
    }

    @ModelAttribute
    public SessionData setUpSessionData() {
        return new SessionData();
    }

    /*
     * 広場画面の表示
     */
    @GetMapping
    public String showHiroba(Model model) {

        String return_word = "redirect:/";

        if (sessionData.getUser_id() != null) {

            Iterable<SelectResponse> popularmovie = selectResponseService.OrderPopular();
            Iterable<SelectResponse> newthread = selectResponseService.OrderNewThread(sessionData.getJenre_id());
            Iterable<PopularThread> popularthread = popularThreadservice.OrderPopularThread(sessionData.getJenre_id());
            Iterable<FollowThread> followthread = followthreadservice.OrderFollowThread(sessionData.getUser_id());
            Iterable<MyThread> mythread = mythreadservice.OrderMyThread(sessionData.getUser_id());
            Iterable<Jenre> jenre = jenreService.selectAll();
            Iterable<SpikeUpMovie> spikeUpMovie = spikeUpMovieService.FindSpikeUpMovie();

            model.addAttribute("popularmovie", popularmovie);
            model.addAttribute("followthread", followthread);
            model.addAttribute("popularthread", popularthread);
            model.addAttribute("newthread", newthread);
            model.addAttribute("mythread", mythread);
            model.addAttribute("jenre", jenre);
            model.addAttribute("spikeupmovie", spikeUpMovie);

            Iterable<User> recommend = recommendService.FindRecommendUser(sessionData.getUser_id());
            model.addAttribute("recommend", recommend);

            Iterable<User> followNotice = noticeService.FindNoticeFollow(sessionData.getUser_id());
            model.addAttribute("follow_notice", followNotice);

            Optional<User> side_user = userService.selectMypageUser(sessionData.getUser_id());
            model.addAttribute("side_user", side_user.get());

            return_word = "Hiroba";
        }

        return return_word;
    }

    /*
     * ユーザ検索
     * 結果を返す
     */
    @GetMapping("/search_user")
    public String searchUser(Model model, @RequestParam("search_word") String search_word) {
        // search_wordの加工
        if (search_word.substring(0, 1).equals("@")) {
            search_word = search_word.substring(1);
        }

        Iterable<User> user = userService.selectUserByWord(search_word);

        model.addAttribute("user", user);

        return "commons/layout :: result_user";
    }

    /*
     * レスポンス詳細画面
     */
    @GetMapping("RessDetail/{url}")
    public String showRessDatail(Model model,
            AlertFrom alertFrom,
            @PathVariable("url") String url,
            @RequestParam("user_id") String user_id,
            @RequestParam("response_id") String response_id,
            RedirectAttributes redirectAttributes) {

        String return_word = "redirect:/";

        if (sessionData.getUser_id() != null) {

            Response threadList = new Response();
            String login_user = sessionData.getUser_id();
            threadList.setThread_id(selectResponseService.SelectThread_id(response_id, user_id));
            threadList.setMovie_id(selectResponseService.SelectMovie_id(response_id, user_id));
            Iterable<SharedThread> thread = sharedThreadService.selectThread(threadList);

            // 2023-02-01追加(阿部)
            Optional<Integer> checkFollow = followerService.FindCheckFollow(login_user, user_id);

            // view_response ここの処理をressdetailに遷移したときに行う
            Response view_response = new Response();
            view_response.setResponse_id(response_id);
            view_response.setResponse_creater(user_id);
            view_response.setView_user(sessionData.getUser_id());

            Boolean FirstTimeView = responseService.FirstTimeView(view_response);

            if (FirstTimeView == false) {
                responseService.insertView_Response(view_response);
            }

            Iterable<Comment> Comment = commentservice.SelectComment(user_id, response_id);
            Optional<SelectResponse> response = selectResponseService.SelectResponse(response_id, user_id);
            Optional<Movie> movie = movieservice.SelectMovie(threadList.getMovie_id());

            model.addAttribute("list", Comment);
            model.addAttribute("movie", movie.get());
            model.addAttribute("obj", response.get());
            model.addAttribute("thread", thread);
            model.addAttribute("send_url", url);
            model.addAttribute("login_user", sessionData.getUser_id());
            model.addAttribute("response_id", response_id);
            model.addAttribute("response_creater", user_id);
            model.addAttribute("check_follow", checkFollow.get());
            model.addAttribute("login_user", login_user);

            Iterable<User> recommend = recommendService.FindRecommendUser(sessionData.getUser_id());
            model.addAttribute("recommend", recommend);

            Iterable<User> followNotice = noticeService.FindNoticeFollow(sessionData.getUser_id());
            model.addAttribute("follow_notice", followNotice);

            Optional<User> side_user = userService.selectMypageUser(sessionData.getUser_id());
            model.addAttribute("side_user", side_user.get());
            return_word = "RessDetail";

            // 2023-02-07追加(阿部)
            Integer count = responseService.OrderShare_check(login_user, user_id, response_id);
            model.addAttribute("share_count", count);

            Iterable<Jenre> jenre = jenreService.selectAll();
            model.addAttribute("jenre", jenre);
        }

        return return_word;
    }

    /*
     * レスポンス詳細 いいね数の登録
     * 
     */
    @GetMapping("RessDetail/update_like")
    public String updateLike(@RequestParam("response_id") String response_id,
            @RequestParam("response_creater") String response_creater, @RequestParam("like") String like) {
        String view_user = sessionData.getUser_id();
        Date date = new Date();

        View_response view_response = new View_response(
                response_id,
                response_creater,
                view_user,
                like,
                date);

        view_responseService.updateLike(view_response);

        return "commons/layout :: empty";
    }

    /*
     * スレッド詳細画面
     */
    @GetMapping("ThreadDetail")
    public String showThreadFil(Model model, @RequestParam String thread_id) {

        String return_word = "redirect:/";

        if (sessionData.getUser_id() != null) {

            Iterable<ThreadDetail> list_popular3 = threadDetailService.selectThreadDetail_Popular3(thread_id);
            Iterable<ThreadDetail> list = threadDetailService.selectThreadDetailAll(thread_id);
            Optional<JenreThread> thread_data = threadService.findJenreThread(thread_id);
            Optional<Thread> thread = threadService.selectThread(thread_id);
            Integer threadFollow = threadService.findFollowCheck(sessionData.getUser_id(), thread_id);

            //2023-02-09追加（阿部）
            String login_user_id = sessionData.getUser_id();
            model.addAttribute("login_user_id", login_user_id);
            model.addAttribute("thread_creater", thread.get().getThread_creater());
            //ここまで

            model.addAttribute("list_popular3", list_popular3);
            model.addAttribute("list", list);
            model.addAttribute("thread_data", thread_data.get());
            model.addAttribute("checkFollow", threadFollow);

            // thread_name は投稿がないと表示されない
            // 外部結合に直してね
            model.addAttribute("thread_name", thread.get().getThread_name());
            model.addAttribute("genres", jenreService.selectAll());
            model.addAttribute("thread_id", thread_id);
            model.addAttribute("user_id", sessionData.getUser_id());

            Iterable<User> recommend = recommendService.FindRecommendUser(sessionData.getUser_id());
            model.addAttribute("recommend", recommend);

            Iterable<User> followNotice = noticeService.FindNoticeFollow(sessionData.getUser_id());
            model.addAttribute("follow_notice", followNotice);

            Optional<User> side_user = userService.selectMypageUser(sessionData.getUser_id());
            model.addAttribute("side_user", side_user.get());
            return_word = "ThreadDetail";

            Iterable<Jenre> jenre = jenreService.selectAll();
            model.addAttribute("jenre", jenre);
        }

        return return_word;
    }

    /*
     * スレッド詳細のリアルタイム処理
     */
    @MessageMapping("/response")
    @SendTo("/response/posting")
    public ThreadDetail posting(@Validated ResponseCreateForm responseCreateForm) throws Exception {
        ThreadDetail detail = collection.response_create(responseCreateForm);

        return detail;
    }

    /*
     * スレッド作成機能
     */
    @PostMapping("thread_create")
    public String threadCreate(@Validated ThreadCreateForm threadCreateForm, BindingResult result, Model model) {

        String thread_creater = sessionData.getUser_id();
        String MaxThread_id = collection.createId(threadService.selectMaxThread_id());
        Thread thread = new Thread();
        thread.setThread_id(MaxThread_id);
        thread.setThread_name(threadCreateForm.getThread_name());
        thread.setThread_creater(thread_creater);
        thread.setJenre_id(threadCreateForm.getGenre_id());

        threadService.insertThread(thread);

        Iterable<ThreadDetail> list_popular3 = threadDetailService.selectThreadDetail_Popular3(MaxThread_id);
        Iterable<ThreadDetail> list = threadDetailService.selectThreadDetailAll(MaxThread_id);

        model.addAttribute("list_popular3", list_popular3);
        model.addAttribute("list", list);
        model.addAttribute("thread_name", threadCreateForm.getThread_name());
        model.addAttribute("jenre_name", threadService.findThreadJenre_name(MaxThread_id));
        model.addAttribute("thread_id", MaxThread_id);

        return "redirect:/Hiroba/ThreadDetail?thread_id=" + MaxThread_id;

    }

    /*
     * コメント作成機能
     */
    @MessageMapping("/comment")
    @SendTo("/comment/posting")
    public CommentPosting comment_create(CommentCreateForm commentCreateForm, Model model) {
        Comment comment = new Comment();
        String view_user = commentCreateForm.getUser_id();
        String comment_id = commentservice.maxCommentId(view_user);
        if (comment_id != null) {
            comment_id = collection.createId(commentservice.maxCommentId(view_user));
        } else {
            comment_id = "C00001";
        }

        comment.setResponse_id(commentCreateForm.getResponse_id());
        comment.setView_user(view_user);
        comment.setComment_id(comment_id);
        comment.setComment(commentCreateForm.getComment());
        comment.setResponse_creater(commentCreateForm.getResponse_creater());
        commentservice.insertComment(comment);

        Optional<User> user = userService.selectUser(view_user);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        CommentPosting posting = new CommentPosting();
        posting.setComment(comment.getComment());
        posting.setUser_name(user.get().getUser_name());
        posting.setSubmit_time(sdf.format(date));
        posting.setIcon(user.get().getIcon());
        posting.setUser_id(user.get().getUser_id());

        return posting;
    }

    /*
     * スレッド一覧画面
     */
    @GetMapping("ThreadList/{modeThread}")
    public String showThreadList(Model model, @PathVariable("modeThread") String modeThread) {

        String return_word = "redirect:/";

        if (sessionData.getUser_id() != null) {

            String title = "";
            Iterable<ThreadList> list = null;

            String genre_id = sessionData.getJenre_id();

            switch (modeThread) {
                case "myThread":
                    title = "自作スレッド一覧";

                    // 登録順の一覧
                    list = threadListService.selectMyThread_OrderByRegist(sessionData.getUser_id());

                    model.addAttribute("myThread_OrderByRegist", list);

                    // 更新順の一覧
                    list = threadListService.selectMyThread_OrderByUpdate(sessionData.getUser_id());
                    model.addAttribute("myThread_OrderByUpdate", list);

                    break;

                case "followThread":
                    title = "フォロースレッド一覧";

                    // 登録順の一覧
                    list = threadListService.selectFollowThread_OrderByRegist(sessionData.getUser_id());
                    model.addAttribute("followThread_OrderByRegist", list);

                    // 更新順の一覧
                    list = threadListService.selectFollowThread_OrderByUpdate(sessionData.getUser_id());
                    model.addAttribute("followThread_OrderByUpdate", list);

                    break;

                case "genreThread":
                    title = "ジャンルスレッド一覧";
                    Iterable<Jenre> jenre = jenreService.selectAll();

                    // 人気順の一覧
                    list = threadListService.selectGenreThread_OrderByPopular(genre_id);
                    model.addAttribute("genreThread_OrderByPopular", list);

                    // 新着順の一覧
                    list = threadListService.selectGenreThread_OrderByRegist(genre_id);
                    model.addAttribute("genreThread_OrderByRegist", list);

                    model.addAttribute("jenre", jenre);

                    break;

                case "popularThread":
                    title = "急上昇スレッド一覧";
                    break;

                case "searchThread":
                    title = "スレッド検索";
                    break;
            }

            model.addAttribute("modeThread", modeThread);
            model.addAttribute("title", title);

            Iterable<User> recommend = recommendService.FindRecommendUser(sessionData.getUser_id());
            model.addAttribute("recommend", recommend);

            Iterable<User> followNotice = noticeService.FindNoticeFollow(sessionData.getUser_id());
            model.addAttribute("follow_notice", followNotice);

            Optional<User> side_user = userService.selectMypageUser(sessionData.getUser_id());
            model.addAttribute("side_user", side_user.get());
            return_word = "ThreadList";

            Iterable<Jenre> jenre = jenreService.selectAll();
            model.addAttribute("jenre", jenre);
        }

        return return_word;
    }

    @GetMapping("follow_thread")
    public String followThread(Model model, @RequestParam("user_id") String user_id,
            @RequestParam("thread_id") String thread_id, @RequestParam("check") Integer check) {

        if (check == 0) {

            threadService.insertFollowThread(user_id, thread_id);

        } else {

            threadService.deleteFollowThread(user_id, thread_id);

        }

        return "redirect:/Hiroba/ThreadDetail?thread_id=" + thread_id;
    }

    /*
     * response 削除
     */
    @GetMapping("delete_response/{scene}")
    public String deleteResponse(Model model,
            @PathVariable("scene") String scene,
            @RequestParam("response_creater") String response_creater,
            @RequestParam("response_id") String response_id,
            @RequestParam("thread_id") String thread_id) {

        responseService.deleteResponse(response_creater, response_id);

        if (scene.equals("ThreadDetail")) {
            // .equals(msg2)
            return "redirect:/Hiroba/" + scene + "?thread_id=" + thread_id;
        } else if (scene.equals("mypage")) {
            return "redirect:/mypage?user_id=" + response_creater;
        } else {
            return "redirect:/" + scene;
        }
    }

    /*
     * thread 削除
     */
    @GetMapping("delete_thread")
    public String deleteThread(Model model, @RequestParam("thread_id") String thread_id) {
        threadService.deleteThread(thread_id);
        return "redirect:/Hiroba";
    }

    /* 共有するとき */
    @GetMapping("share_response")
    public String ShareResponse(Model model, @RequestParam("response_creater") String response_creater,
            @RequestParam("response_id") String response_id, @RequestParam("url") String url,
            @RequestParam("check_share") String check_share) {
        String login_user_id = sessionData.getUser_id();

        if (check_share.equals("0")) {
            String new_response_id = "R000000";
            Optional<SubmitResponse> count = submitResponseService.findSubmitResponse(login_user_id);
            Integer response_count = count.get().getSubmitcount();
            if (response_count != 0) {
                new_response_id = responseService.selectMaxResponseId(login_user_id);
            }
            String login_user_response = collection.createId(new_response_id);

            responseService.ShareResponse(login_user_id, login_user_response, response_creater, response_id);

        } else {

            responseService.DeleteShareResponse(login_user_id, response_creater, response_id);

        }
        return "redirect:/Hiroba/RessDetail/" + url + "?user_id=" + response_creater + "&response_id=" + response_id;
    }

    /**/
    @GetMapping("delete_share_response")
    public String delete_ShareResponse(Model model, @RequestParam("response_creater") String response_creater,
            @RequestParam("response_id") String response_id, @RequestParam("url") String url) {
        String login_user_id = sessionData.getUser_id();

        System.out.println(login_user_id);
        System.out.println(response_creater);
        System.out.println(response_id);

        responseService.DeleteShareResponse(login_user_id, response_creater, response_id);

        return "redirect:/Hiroba/RessDetail/" + url + "?user_id=" + response_creater + "&response_id=" + response_id;
    }

    /*
     * スレッド検索(昔)
     */
    @GetMapping("search_result")
    public String showSearch_result(Model model, @RequestParam("search_word") String search_word) {
        Iterable<ThreadList> searchThread = threadListService.selectSearchThread(search_word);
        model.addAttribute("searchThread", searchThread);

        // スレッド一覧へ
        return showThreadList(model, "searchThread");
    }

    /* スレッド検索(今) */
    @GetMapping("SearchThread")
    public String showSearch_thread(Model model, @RequestParam("search_word") String search_word) {
        String return_word = "redirect:/";

        if (sessionData.getUser_id() != null) {

            Iterable<ThreadList> searchThread = threadListService.selectSearchThread(search_word);
            model.addAttribute("searchThread", searchThread);

            Optional<User> side_user = userService.selectMypageUser(sessionData.getUser_id());
            model.addAttribute("side_user", side_user.get());

            Iterable<Jenre> jenre = jenreService.selectAll();
            model.addAttribute("jenre", jenre);

            return_word = "SearchThread";
        }

        // スレッド検索結果へ
        return return_word;
    }

    @GetMapping("jenre_change")
    public String showJenre_change(Model model, @RequestParam("jenre_id") String jenre_id) {
        sessionData.setJenre_id(jenre_id);
        return "redirect:/Hiroba";
    }

    @GetMapping("genre_change_detail")
    public String showJenre_change_detail(Model model, @RequestParam("genre_id") String genre_id) {

        String return_word = "redirect:/";

        if (sessionData.getUser_id() != null) {
            sessionData.setJenre_id(genre_id);
            Iterable<ThreadList> list = threadListService.selectGenreThread_OrderByPopular(genre_id);
            model.addAttribute("genreThread_OrderByPopular", list);

            // 新着順の一覧
            list = threadListService.selectGenreThread_OrderByRegist(genre_id);
            model.addAttribute("genreThread_OrderByRegist", list);

            return_word = "redirect:/Hiroba/ThreadList/genreThread";
        }

        return return_word;
    }

    // 2023-02-01追加(阿部)
    @GetMapping("ress_follow")
    public String showFollow(Model model,
            @RequestParam("url") String url,
            @RequestParam("user_id") String user_id,
            @RequestParam("response_id") String response_id,
            @RequestParam("check_follow") Integer check_follow,
            RedirectAttributes redirectAttributes) {

        if (check_follow == 0) {
            followerService.FollowInsert(sessionData.getUser_id(), user_id);
        } else {
            followerService.FollowDelete(sessionData.getUser_id(), user_id);

        }
        return "redirect:/Hiroba/RessDetail/" + url + "?user_id=" + user_id + "&response_id=" + response_id;

    }

}
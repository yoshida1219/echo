package com.example.echo.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.echo.Collection;
import com.example.echo.entity.User;
import com.example.echo.form.CreateUserForm;
import com.example.echo.form.LoginForm;
import com.example.echo.service.User.UserService;
import com.example.echo.session.SessionData;

import jakarta.servlet.http.HttpSession;

/*
 * ログイン機能
 */
@Controller
@RequestMapping("")
public class LoginController {

    private final UserService userService;

    private final SessionData sessionData;
    private final HttpSession session;
    private final Collection collection;

    
    @Autowired
    public LoginController(
        UserService userService,
        Collection collection,
        SessionData sessionData,
        HttpSession session
    ) {
        this.userService = userService;
        this.collection = collection;
        this.sessionData = sessionData;
        this.session = session;
    }
    

    @ModelAttribute
    public LoginForm setUpLoginForm() {
         return new LoginForm();
    }

    @ModelAttribute
    public CreateUserForm setUpCreateUserForm() {
        return new CreateUserForm();
    }

    @ModelAttribute
    public SessionData setUpSessionData() {
        return new SessionData();
    }
    
    /*
     * URL : /
     * 
     * ログイン画面
     */
    @GetMapping("/")
    public String start() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    /*
     * URL : /createUser/complete
     * 
     * アカウント作成
     * 
     * 正常に登録できた　-> / 初期画面にリダイレクト
     * 登録できなかった -> アカウント作成画面を表示
     */
    @PostMapping("/createUser/complete")
    public String createUser(CreateUserForm form, Model model, RedirectAttributes redirectAttributes) throws Exception{
        if(!collection.checkMail(form.getMail())) {
            model.addAttribute("mailError", "メールアドレスはすでに使われています");
            return "login";
        }

        if(!collection.checkSearchName(form.getSearch_name())) {;
            model.addAttribute("searchNameError", "このIDはすでに使われています");
            return "login";
        }

        //パスワードのハッシュ化
        form.setPass(this.makeHashPass(form.getPass()));

        User user = collection.makeUser(form);
        userService.insertUser(user);

        redirectAttributes.addFlashAttribute("complete", "アカウントを作成しました");
        return "redirect:/";
    }


    /*
     * ログイン処理の制御
     */
    @PostMapping("/login/complete")
    public String login(LoginForm form, Model model) throws Exception{
        String search_name = "@" + form.getSearch_name();
        String pass = this.makeHashPass(form.getPass());

        if(!this.login(search_name, pass)) {
            model.addAttribute("error", "ログインに失敗しました");
            return "login";
        }

        //sessionに格納
        Optional<String> user_id = userService.selectUserId(search_name);
        
        if (user_id.isPresent()) {
            sessionData.setUser_id(user_id.get());
            sessionData.setJenre_id("J0001");
            session.setAttribute("sessionData", sessionData);
        }
        
        return "redirect:/home";
    }


    @GetMapping("logout")
    public String logout(Model model) {
        sessionData.setJenre_id(null);
        sessionData.setUser_id(null);
        session.removeAttribute(null);

        return "login";
        
    }

    /*
     * ログイン処理
     * 
     * 正常にログインできるとき -> true
     * ログインできないとき -> false
     */
    public boolean login(String search_name, String pass) {
		Optional<User> user = userService.selectUserOne(search_name);

        //ユーザが見つからない
		if (!user.isPresent()) {
			return false;
		}

        //パスワードが違う
		if (!user.get().getPassword().equals(pass)) {
			return false;
		}

        return true;
    }

    /*
     * パスワードをハッシュ化する (SHA-256) -> 64文字
     */
    public String makeHashPass(String password) throws Exception{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] result = digest.digest(password.getBytes());
        
        String ret = String.format("%064x", new BigInteger(1, result));

        return ret;
    }
    
}

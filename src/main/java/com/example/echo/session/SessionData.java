package com.example.echo.session;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@SessionScope
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionData implements Serializable{
    private String user_id;

    private String jenre_id;
}

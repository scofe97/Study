package com.example.chat;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "chat")
public class Chat {

    @Id
    private String id;
    private String msg;
    private String sender; // 보낸사람
    private String receiver; // 받는사람(만약 귓속말 사용하면 쓸거임)
    private Integer roomNum; // 방 번호

    private LocalDateTime createdAt;
}

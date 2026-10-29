package com.lilemy.airobot.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Chat Client 客户端
 *
 * @author lilemy
 * @date 2026-10-28
 */
@RestController
@RequestMapping("/v2/ai")
public class ChatClientController {

    @Resource
    private ChatClient chatClient;

    /**
     * 普通对话
     *
     * @param message 消息
     * @return 回复
     */
    @GetMapping("/generate")
    public String generate(@RequestParam(value = "message", defaultValue = "你是谁？") String message,
                           @RequestParam(value = "chatId") String chatId) {
        // 一次性返回结果
        return chatClient.prompt()
                .user(message)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, chatId)) // 对话 ID
                .call()
                .content();
    }

    /**
     * 流式对话
     *
     * @param message 消息
     * @return 流式回复
     */
    @GetMapping(value = "/generateStream", produces = "text/html;charset=utf-8")
    public Flux<String> generateStream(@RequestParam(value = "message", defaultValue = "你是谁？") String message,
                                       @RequestParam(value = "chatId") String chatId) {
        return chatClient.prompt()
                .user(message) // 提示词
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, chatId)) // 对话 ID
                .stream() // 流式输出
                .content();
    }

}

package com.lilemy.airobot.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * DeepSeek 聊天
 *
 * @author lilemy
 * @date 2026-10-27
 */
@RestController
@RequestMapping("/ai")
public class DeepSeekChatController {

    @Resource
    private DeepSeekChatModel chatModel;

    /**
     * 普通对话
     *
     * @param message 消息
     * @return 回复
     */
    @GetMapping("/generate")
    public String generate(@RequestParam(value = "message", defaultValue = "你是谁？") String message) {
        // 一次性返回结果
        return chatModel.call(message);
    }

    /**
     * 流式对话
     *
     * @param message 消息
     * @return 回复
     */
    @GetMapping(value = "/generateStream", produces = "text/html;charset=utf-8")
    public Flux<String> generateStream(@RequestParam(value = "message", defaultValue = "你是谁？") String message) {
        // 构建提示词
        Prompt prompt = new Prompt(new UserMessage(message));

        // 流式输出
        return chatModel.stream(prompt).mapNotNull(chatResponse -> {
            Generation result = chatResponse.getResult();
            if (result == null) {
                return null;
            }
            return result.getOutput().getText();
        });
    }

}

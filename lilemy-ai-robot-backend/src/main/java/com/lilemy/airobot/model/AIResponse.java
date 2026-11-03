package com.lilemy.airobot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AI 对话响应类
 *
 * @author lilemy
 * @date 2026-11-03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AIResponse {

    // 流式响应内容
    private String v;
}

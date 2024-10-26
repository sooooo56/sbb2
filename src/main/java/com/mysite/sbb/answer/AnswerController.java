package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String crateAnswer(Model model, @PathVariable("id") Integer id, @RequestParam("content") String content){
        Question question = this.questionService.getQuestion(id);

        this.answerService.create(question, content);

        return String.format("redirect:/question/detail/%s", id);
    }

}

package com.mysite.sbb.question;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    // 목록
    @GetMapping("/list")
    public String list(Model model){
        List<Question> questionList = this.questionService.getList();

        model.addAttribute("questionList",questionList);
        return "question_list";
    }

    // 상세보기
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id){

        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question",question);
        return "/question_detail";
    }

    // 질문 등록
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    @PostMapping("/create")
    public  String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "question_form";
        }

        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list"; // 질문 저장 후 질문목록으로 이동
    }
}

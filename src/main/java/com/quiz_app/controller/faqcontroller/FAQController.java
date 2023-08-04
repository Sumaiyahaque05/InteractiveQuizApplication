package com.quiz_app.controller.faqcontroller;

import com.quiz_app.entity.FAQ.FAQ;
import com.quiz_app.entity.FAQ.FAQDto;
import com.quiz_app.service.faq.FAQService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/faq")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class FAQController {

    private final FAQService faqService;
    private final ModelMapper modelMapper;

    @GetMapping("/all")
    public List<FAQDto> getAllFAQs() {
        return faqService.getAllFaqs();
    }

    @PatchMapping("/update")
    public void updateFAQ(@RequestBody @Valid FAQDto faq) {
        faqService.updateFAQ(modelMapper.map(faq, FAQ.class));
    }

    @PostMapping("/add")
    public void addNewFAQ(@RequestBody FAQDto faq) {
        faqService.addNewFAQ(modelMapper.map(faq, FAQ.class));
    }

    @DeleteMapping("/remove")
    public void addRemoveFAQ(@RequestBody FAQDto faq) {
        faqService.deleteFAQ(modelMapper.map(faq, FAQ.class));
    }
}

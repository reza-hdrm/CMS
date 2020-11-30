package com.rezahdrm.cms.web.controller;

import com.rezahdrm.cms.model.Post;
import com.rezahdrm.cms.model.dto.Message;
import com.rezahdrm.cms.repositroy.PostRepository;
import com.rezahdrm.cms.serivce.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("admin/post")
public class PostController {
    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("posts", postService.findAll());
        return "/post/index";
    }

    @GetMapping("create")
    public String create(ModelMap modelMap) {
        //TODO sent category model
        return "/post/create";
    }

    @PostMapping("/store")
    public String store(@ModelAttribute @Valid Post post, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors())
            return "/post/create";
        postService.save(post);
        redirectAttributes.addFlashAttribute("message", new Message("مطلب جدید با موفقیت اضافه شد", "add_post"));
        return "redirect:/admin/post";
    }

    @GetMapping("edit/{id:\\d+}")
    public String edit(@PathVariable Long id, ModelMap modelMap) {
        modelMap.addAttribute(postService.findById(id));
        return "/post/edit";
    }

    @PostMapping("update")
    public String update(@Valid @ModelAttribute Post post, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors())
            return "/post/edit";
        postService.save(post);
        redirectAttributes.addFlashAttribute("message", new Message("مطلب جدید با موفقیت ویرایش شد", "update_post"));
        return "redirect:/admin/post";
    }

    @GetMapping("delete/{id:\\d+}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        postService.delete(id);
        redirectAttributes.addFlashAttribute("message", new Message("مطلب با موفقیت حذف شد", "delete_post"));
        return "redirect:/admin/post";
    }
}

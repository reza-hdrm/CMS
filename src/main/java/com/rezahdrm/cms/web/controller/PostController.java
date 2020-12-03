package com.rezahdrm.cms.web.controller;

import com.ibm.icu.text.DateFormat;
import com.rezahdrm.cms.model.Post;
import com.rezahdrm.cms.model.dto.Message;
import com.rezahdrm.cms.serivce.CategoryService;
import com.rezahdrm.cms.serivce.PhotoService;
import com.rezahdrm.cms.serivce.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("admin/post")
/*@SessionAttributes("message")*/
public class PostController {
    PostService postService;
    CategoryService categoryService;
    PhotoService photoService;
    @Autowired
    DateFormat persianCalendar;

    public PostController(PostService postService, CategoryService categoryService, PhotoService photoService) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.photoService = photoService;
    }

    @GetMapping
    public String index(ModelMap modelMap,@ModelAttribute Message message) {
        modelMap.
                addAttribute("posts", postService.findAll()).
                addAttribute("persianCalendar", persianCalendar).
                addAttribute("message",message);
        return "admin/post/index";
    }

    @GetMapping(value = "create", name = "post.create")
    public String create(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAll()).
                addAttribute("post", new Post()).
                addAttribute("categories", categoryService.findAll());
        return "admin/post/create";
    }

    @PostMapping("/store")
    public String store(@ModelAttribute @Valid Post post, BindingResult bindingResult, @ModelAttribute("photoFile") MultipartFile photoFile, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors())
            return "redirect:/admin/post/create";
        postService.save(post, photoFile);
        redirectAttributes.addFlashAttribute("message", new Message("مطلب جدید با موفقیت اضافه شد", "alert alert-success"));
        return "redirect:/admin/post";
    }

    @GetMapping("edit/{id:\\d+}")
    public String edit(@PathVariable Long id, ModelMap modelMap) {
        modelMap.addAttribute("post", postService.findById(id));
        return "admin/post/edit";
    }

    @PostMapping(value = "update")
    public String update(
            @Valid @ModelAttribute Post post, BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            MultipartFile photoFile) {
        if (bindingResult.hasErrors())
            return "admin/post/edit";

        postService.save(post, photoFile);

        redirectAttributes.addFlashAttribute(
                "message",
                new Message("مطلب جدید با موفقیت ویرایش شد", "alert alert-success"));
        return "redirect:/admin/post";
    }

    @GetMapping("delete/{id:\\d+}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        postService.delete(id);
        redirectAttributes.addFlashAttribute("message", new Message("مطلب با موفقیت حذف شد", "alert alert-danger"));
        return "redirect:/admin/post";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public StackTraceElement[] showException(Exception e) {
        return e.getStackTrace();
    }
}

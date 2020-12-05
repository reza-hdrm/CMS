package com.rezahdrm.cms.web.controller.admin;

import com.ibm.icu.text.DateFormat;
import com.rezahdrm.cms.model.Post;
import com.rezahdrm.cms.model.dto.Message;
import com.rezahdrm.cms.service.CategoryService;
import com.rezahdrm.cms.service.PostService;
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
    private final PostService postService;
    private final CategoryService categoryService;
    private final DateFormat persianCalendar;

    public PostController(
            PostService postService,
            CategoryService categoryService,
            DateFormat persianCalendar) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.persianCalendar = persianCalendar;
    }

    @GetMapping
    public String index(ModelMap modelMap, Message message) {
        modelMap.
                addAttribute("posts", postService.findAll()).
                addAttribute("persianCalendar", persianCalendar).
                addAttribute("message", message);
        return "admin/post/index";
    }

    @GetMapping(value = "create", name = "post.create")
    public String create(ModelMap modelMap) {
        modelMap.addAttribute("post", new Post()).
                addAttribute("categories", categoryService.findAll());
        return "admin/post/create";
    }

    @PostMapping("/store")
    public String store(
            @ModelAttribute @Valid Post post, BindingResult bindingResult,
            @ModelAttribute("photoFile") MultipartFile photoFile,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors())
            return "redirect:/admin/post/create";

        postService.save(post, photoFile);
        redirectAttributes.addFlashAttribute("message", new Message("مطلب جدید با موفقیت اضافه شد", "alert alert-success"));
        return "redirect:/admin/post";
    }

    @GetMapping("edit/{id:\\d+}")
    public String edit(@PathVariable Long id, ModelMap modelMap) {

        modelMap.addAttribute("post", postService.findById(id)).
                addAttribute("categories", categoryService.findAll());

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
                new Message("مطلب با موفقیت ویرایش شد", "alert alert-success"));
        return "redirect:/admin/post";
    }

    @PostMapping("delete/{id:\\d+}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        postService.softDelete(id);

        redirectAttributes.addFlashAttribute(
                "message",
                new Message("مطلب با موفقیت حذف شد", "alert alert-danger"));
        return "redirect:/admin/post";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public StackTraceElement[] showException(Exception e) {
        return e.getStackTrace();
    }
}

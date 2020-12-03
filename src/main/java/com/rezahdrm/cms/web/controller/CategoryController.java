package com.rezahdrm.cms.web.controller;

import com.ibm.icu.text.DateFormat;
import com.rezahdrm.cms.model.Category;
import com.rezahdrm.cms.model.dto.Message;
import com.rezahdrm.cms.serivce.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("admin/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final DateFormat persianCalendar;

    public CategoryController(CategoryService categoryService, DateFormat persianCalendar) {
        this.categoryService = categoryService;
        this.persianCalendar = persianCalendar;
    }

    @GetMapping
    public String index(ModelMap modelMap, Message message) {

        modelMap.addAttribute("categories", categoryService.findAll()).
                addAttribute("message", message).
                addAttribute("persianCalender", persianCalendar).
                addAttribute("title", "لیست دسته بندی ها");

        return "admin/category/index";
    }

    @GetMapping("create")
    public String create(ModelMap modelMap) {
        modelMap.addAttribute("title", "ایجاد دسته بندی جدید");
        return "admin/category/create";
    }

    @PostMapping("store")
    public String store(
            Category category, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        categoryService.save(category);
        redirectAttributes.addFlashAttribute(
                "message",
                new Message("دسته بندی جدید با موفقیت اضافه شد", "alert alert-success"));
        return "redirect:/admin/category";
    }

    @GetMapping("edit/{id:\\d+}")
    public String edit(@PathVariable Long id, ModelMap modelMap) {
        modelMap.addAttribute("category", categoryService.findById(id));
        return "admin/category/edit";
    }

    @PostMapping("update")
    public String update(Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        categoryService.save(category);

        redirectAttributes.addFlashAttribute(
                "message",
                new Message("دسته بندی با موفقیت ویرایش شد", "alert alert-success"));

        return "redirect:/admin/category";
    }

    @PostMapping("delete/{id:\\d+}")
    public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes) {

        categoryService.delete(id);

        redirectAttributes.addFlashAttribute(
                "message",
                new Message("دسته بندی با موفقیت حذف شد", "alert alert-danger"));
        return "redirect:/admin/category";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public StackTraceElement[] showException(Exception e) {
        return e.getStackTrace();
    }

}

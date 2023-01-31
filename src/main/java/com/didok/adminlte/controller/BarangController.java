package com.didok.adminlte.controller;

import com.didok.adminlte.model.Barangs;
import com.didok.adminlte.service.BarangsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("barangs")
public class BarangController {

    private BarangsService barangService;

    @Autowired
    public void setBarangService(BarangsService barangService) {
        this.barangService = barangService;
    }

    @GetMapping
    public String index() {
        return "redirect:/barangs/1";
    }

    @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<Barangs> page = barangService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "barangs/list";

    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("barang", new Barangs());
        return "barangs/form";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        model.addAttribute("barang", barangService.get(id));
        return "barangs/form";

    }

    @PostMapping(value = "/save")
    public String save(Barangs barang, final RedirectAttributes ra) {

        Barangs save = barangService.save(barang);
        ra.addFlashAttribute("successFlash", "Cliente foi salvo com sucesso.");
        return "redirect:/barangs";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        barangService.delete(id);
        return "redirect:/barangs";

    }

}
